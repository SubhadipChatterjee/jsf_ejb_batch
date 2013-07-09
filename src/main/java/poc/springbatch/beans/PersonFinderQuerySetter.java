/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poc.springbatch.beans;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.jdbc.core.PreparedStatementSetter;

/**
 *
 * @author subhadip.chatterjee@tcs.com
 */
public class PersonFinderQuerySetter implements PreparedStatementSetter {

    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setValues(PreparedStatement ps) throws SQLException {
        ps.setString(1, '%' + firstName + '%');
    }
}
