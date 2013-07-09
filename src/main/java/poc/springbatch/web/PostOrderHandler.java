/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poc.springbatch.web;

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
    
    private String firstName;
    
    private String lastName;
    
    private Date dateOfJoin;
    
    private String department;

    /**
     * Creates a new instance of PostOrderHandler
     */
    public PostOrderHandler() {
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

    public Date getDateOfJoin() {
        return dateOfJoin;
    }

    public void setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    
    public void handlePersistance(){
        if(logger.isInfoEnabled()){
            logger.info("Form data is submitted");
        }
        Person person  = new Person();
        person.setFname(firstName);
        person.setLname(lastName);
        person.setDoj(dateOfJoin);
        person.setDept(department);
        
        ejbInstance.save(person);
        if(logger.isInfoEnabled()){
            logger.info("Form data is saved");
        }
    }
            
}
