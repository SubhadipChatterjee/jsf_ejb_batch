/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poc.springbatch.beans;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import poc.springbatch.enterprise.TimerSessionBean;
import static org.junit.Assert.*;

/**
 *
 * @author subhadip.chatterjee@tcs.com
 */
//@ContextConfiguration(locations = {"classpath:/config/baseSetup.xml", "classpath:/config/dbToFileJob.xml"})
//@RunWith(SpringJUnit4ClassRunner.class)
public class PersonFinderTest {

    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job job;

    //@Test
    public void testLaunchJobWithPersonName() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("firstName", "ALOK")
                .addString("outputFile", "file:C:\\Batcave\\ProjectLogs\\personFound.txt")
                .toJobParameters();
        jobLauncher.run(job, jobParameters);
    }

    //@Test
    public void testLaunchJobFromTimer() throws NamingException {        
        EJBContainer embeddedContainer = EJBContainer.createEJBContainer();
        Context context = embeddedContainer.getContext();
        TimerSessionBean timerBean = (TimerSessionBean) context.lookup("java:global/classes/poc/springbatch/enterprise/TimerSessionBean");
        assertNotNull(timerBean);
        
        embeddedContainer.close();
    }
}
