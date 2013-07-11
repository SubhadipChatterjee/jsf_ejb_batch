/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poc.springbatch.web.sample;

import javax.faces.bean.ManagedBean;
import javax.inject.Named;

/**
 *
 * @author subhadip.chatterjee@tcs.com
 */
@ManagedBean(name="simpleBean")
public class SimpleBean {

    private String name;
    private int age;

    /**
     * Creates a new instance of SimpleBean
     */
    public SimpleBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
