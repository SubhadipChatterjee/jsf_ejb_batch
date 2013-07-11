/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poc.springbatch.event.messages;

import java.util.Date;
import poc.springbatch.types.BatchProcessStatus;

/**
 *
 * @author subhadip.chatterjee@tcs.com
 */
public class TimeSnap {

    private Date timestamp;
    private BatchProcessStatus orderStatus;

    public TimeSnap() {
        timestamp = new Date();
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public BatchProcessStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(BatchProcessStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
