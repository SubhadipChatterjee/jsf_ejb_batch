/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poc.springbatch.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.event.Observes;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poc.springbatch.enterprise.PostOrderSessionBean;
import poc.springbatch.entities.Person;
import poc.springbatch.event.messages.TimeSnap;
import poc.springbatch.events.TimerEvent;
import poc.springbatch.types.BatchProcessStatus;
import poc.springbatch.types.UserRegistrationStatus;

/**
 *
 * @author subhadip.chatterjee@tcs.com
 */
@ManagedBean
@RequestScoped
public class PostOrderHandler {

    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @EJB
    private PostOrderSessionBean ejbInstance;
    private String uid;
    private String firstName;
    private String lastName;
    private Date dateOfJoin; // expected format: YYYY-MM-DD
    private String department;
    private UserRegistrationStatus regnStatus = UserRegistrationStatus.UNKNOWN;
    private BatchProcessStatus batchStatus = BatchProcessStatus.UNKNOWN;

    static {
        dateFormatter.setLenient(false);
    }

    /**
     * Creates a new instance of PostOrderHandler
     */
    public PostOrderHandler() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        if (uid == null || uid.isEmpty()) {
            logger.error("User id is empty!");
            throw new IllegalArgumentException("User id is empty!");
        }
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            logger.error("First Name is empty!");
            throw new IllegalArgumentException("First Name is empty!");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            logger.error("First Name is empty!");
            throw new IllegalArgumentException("Last Name is empty!");
        }
        this.lastName = lastName;
    }

    public String getDateOfJoin() {
        return (dateOfJoin != null ? dateFormatter.format(dateOfJoin) : "");
    }

    public void setDateOfJoin(String dateOfJoin) {
        if (dateOfJoin == null || dateOfJoin.isEmpty()) {
            logger.error("Date of Join is empty!");
            throw new IllegalArgumentException("Date of Join is empty!");
        }
        try {
            this.dateOfJoin = dateFormatter.parse(dateOfJoin);
        } catch (ParseException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRegnStatus() {
        return regnStatus.toString();
    }
    
    public String getBatchStatus() {
        return batchStatus.toString();
    }
        
    public void handlePersistance() throws IOException {
        if (logger.isInfoEnabled()) {
            logger.info("Form data is submitted");
        }

        Person person = new Person();
        person.setId(uid);
        person.setFname(firstName);
        person.setLname(lastName);
        person.setDoj(dateOfJoin);
        person.setDept(department);

        regnStatus = ejbInstance.save(person);
        if (logger.isInfoEnabled()) {
            logger.info("Form data is saved");
        }                
    }            
}