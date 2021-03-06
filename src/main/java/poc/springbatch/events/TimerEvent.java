/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poc.springbatch.events;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

/**
 *
 * @author subhadip.chatterjee@tcs.com
 */
@Qualifier
@Retention (RetentionPolicy.RUNTIME)
@Target( {ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD} )
public @interface TimerEvent {
    
}
