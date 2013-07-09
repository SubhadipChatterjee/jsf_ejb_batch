/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poc.springbatch.types;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.springframework.jdbc.core.RowMapper;
import poc.springbatch.entities.Person;

/**
 *
 * @author subhadip.chatterjee@tcs.com
 */
public class PersonRowMapper implements RowMapper<Person>{

    @Override
    public Person mapRow(ResultSet rs, int i) throws SQLException {
        Person person = new Person();
        person.setId(rs.getObject("ID", String.class));
        person.setFname(rs.getObject("FIRST_NAME", String.class));
        person.setLname(rs.getObject("LAST_NAME", String.class));
        person.setDoj(rs.getObject("JOINED_AT", Date.class));
        person.setDept(rs.getObject("DEPARTMENT", String.class));
        return person;
    }
    
}
