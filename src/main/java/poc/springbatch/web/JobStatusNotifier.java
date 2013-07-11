/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poc.springbatch.web;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.event.Observes;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poc.springbatch.event.messages.TimeSnap;
import poc.springbatch.events.TimerEvent;

/**
 *
 * @author subhadip.chatterjee@tcs.com
 */
@ServerEndpoint("/job-status")
public class JobStatusNotifier {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final Set<Session> peers =
            Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(final Session peer) {
        try {
            peer.getBasicRemote().sendText("[Socket] Session opened");
            peers.add(peer);
            if (logger.isInfoEnabled()) {
                logger.info("New Client Session opened. ID: " + peer.getId());
            }
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    @OnMessage
    public String onMessage(String message) {
        return null;
    }

    public void onTimeEvent(@Observes @TimerEvent TimeSnap time) {
        if (logger.isInfoEnabled()) {
            logger.info("Timer event received...");
        }
        try {
            for (Session each : peers) {
                if (each.isOpen()) {
                    each.getBasicRemote().sendText("[Job Status]" + time.getOrderStatus().toString());
                }
                else{
                    peers.remove(each);
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
