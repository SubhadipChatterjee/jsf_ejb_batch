/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poc.springbatch.event.messages;

import java.util.Date;
import poc.springbatch.types.OrderStatus;

/**
 *
 * @author subhadip.chatterjee@tcs.com
 */
public class TimeSnap {

    private Date timestamp;
    private OrderStatus orderStatus;

    public TimeSnap() {
        timestamp = new Date();
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
