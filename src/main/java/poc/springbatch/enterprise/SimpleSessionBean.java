/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poc.springbatch.enterprise;

import javax.ejb.Stateless;

/**
 *
 * @author subhadip.chatterjee@tcs.com
 */
@Stateless
public class SimpleSessionBean {

    public String sayHello(String name) {
        return "Hello " + name + "!!!";
    }

}
