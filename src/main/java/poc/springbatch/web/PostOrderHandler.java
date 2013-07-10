/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poc.springbatch.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poc.springbatch.enterprise.PostOrderSessionBean;
import poc.springbatch.entities.Person;

/**
 *
 * @author subhadip.chatterjee@tcs.com
 */
@ManagedBean
@RequestScoped
public class PostOrderHandler {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @EJB
    private PostOrderSessionBean ejbInstance;
    private String uid;
    private String firstName;
    private String lastName;
    private String dateOfJoin; // expected format: YYYY-MM-DD (tied to the structure of HSQL/ Wrong Decision!)
    private String department;

    /**
     * Creates a new instance of PostOrderHandler
     */
    public PostOrderHandler() {
    }
    
    public String getUid() {
        return uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getDateOfJoin() {
        return dateOfJoin;
    }
    
    public void setDateOfJoin(String dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public void handlePersistance() {
        if (logger.isInfoEnabled()) {
            logger.info("Form data is submitted");
        }
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("YYYY-MM-DD");
            Person person = new Person();
            person.setId(uid);
            person.setFname(firstName);
            person.setLname(lastName);
            person.setDoj(dateFormatter.parse(dateOfJoin));
            person.setDept(department);
            
            ejbInstance.save(person);
            if (logger.isInfoEnabled()) {
                logger.info("Form data is saved");
            }
        } catch (ParseException ex) {
            logger.error("Date format is not correct: " + ex.getMessage());
        }        
    }
}
