package org.jk.smartRoom.component;


import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

public class MyNotification extends Notification {

    public MyNotification(String text) {
        super(text);
        setDuration(3000);
        setPosition(Position.BOTTOM_START);
        addThemeVariants(NotificationVariant.LUMO_ERROR);
    }
}
