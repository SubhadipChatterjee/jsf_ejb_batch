package poc.springbatch.enterprise;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.PostConstruct;

import javax.annotation.Resource;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.TimerService;
import javax.sql.DataSource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import poc.springbatch.entities.Person;
import poc.springbatch.types.PersonRowMapper;

/**
 *
 * @author subhadip.chatterjee@tcs.com
 */
@Singleton
public class TimerSessionBean {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final ApplicationContext springContext;
    private static final DataSource dataSource;
    private static final String selectSQL;
    @Resource
    TimerService timerService;
    private Date lastAutomaticTimeout;

    static {
        springContext = new ClassPathXmlApplicationContext(new String[]{"classpath:/config/baseSetup.xml", "classpath:/config/dbToFileJob.xml"});
        dataSource = springContext.getBean("embeddedDataSource", DataSource.class);
        selectSQL = "select * from PERSON where JUST_IN = 1";
    }

    @PostConstruct
    public void onStartup() {
        if (logger.isInfoEnabled()) {
            logger.info("TimerSessionBean is initialized");
        }
    }

    public String getLastAutomaticTimeout() {
        if (lastAutomaticTimeout != null) {
            return lastAutomaticTimeout.toString();
        } else {
            return "never";
        }
    }

    @Schedule(second = "*/30", minute = "*", hour = "19-20", persistent = false)
    public void automaticTimeout() {
        this.lastAutomaticTimeout = new Date();
        if (logger.isInfoEnabled()) {
            logger.info("Autmatic timeout occured ->{0}", getLastAutomaticTimeout());
        }
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        try {
            List<Person> match = (List<Person>) jdbcTemplate.query(selectSQL, new PersonRowMapper());
            ListIterator<Person> finder = match.listIterator();

            if (logger.isInfoEnabled()) {
                logger.info("Current batch size = " + match.size());
            }
            
            // The Spring JobParameter code is still not optimized to accept multiple order id 
            JobLauncher jobLauncher = springContext.getBean(JobLauncher.class);
            Job job = springContext.getBean(Job.class);            
            while (finder.hasNext()) {
                JobParameters jobParameters = new JobParametersBuilder()
                        .addString("firstName", finder.next().getFname())
                        .addString("outputFile", "file:C:\\Batcave\\ProjectLogs\\personFound.txt")
                        .toJobParameters();
                try {
                    jobLauncher.run(job, jobParameters);
                } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException ex) {
                    logger.error(ex.getMessage());
                }
            }
        } catch (EmptyResultDataAccessException ex) {
            logger.warn("No matching records found :" + ex.getMessage());
        }
    }
}
