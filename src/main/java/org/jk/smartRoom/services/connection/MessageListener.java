package org.jk.smartRoom.services.connection;

import org.jk.smartRoom.services.Message;

public interface MessageListener {

    void messageArrived(Message message);

}
