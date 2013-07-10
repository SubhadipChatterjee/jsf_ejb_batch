package poc.springbatch.enterprise;

import javax.ejb.Stateless;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import poc.springbatch.beans.PersonAddQuerySetter;
import poc.springbatch.entities.Person;

/**
 *
 * @author subhadip.chatterjee@tcs.com
 */
@Stateless
public class PostOrderSessionBean {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final ApplicationContext springContext;
    private static final DataSource dataSource;
    private static final String insertSQL;

    static {
        springContext = new ClassPathXmlApplicationContext(new String[]{"classpath:/config/baseSetup.xml", "classpath:/config/dbToFileJob.xml"});
        dataSource = springContext.getBean("embeddedDataSource", DataSource.class);
        insertSQL = "insert into PERSON (ID, FIRST_NAME, LAST_NAME, JOINED_AT, DEPARTMENT, JUST_IN) values (?, ?, ?, ?, ?, 1)";
    }

    /**
     *
     * @param person
     */
    public void save(Person person){
        if (logger.isInfoEnabled()) {
            logger.info("save() is beginning...");
        }
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        PersonAddQuerySetter querySetter = new PersonAddQuerySetter();
        querySetter.setPerson(person);
        int savedRows = jdbcTemplate.update(insertSQL, querySetter);

        if (savedRows > 0) {
            if (logger.isInfoEnabled()) {
                logger.info("RECORD is saved");
            }
        }
    }
}