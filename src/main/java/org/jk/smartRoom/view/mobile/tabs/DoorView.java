package org.jk.smartRoom.view.mobile.tabs;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import org.jk.smartRoom.component.MyNotification;
import org.jk.smartRoom.component.ReturnButton;
import org.jk.smartRoom.services.device.DeviceManager;
import org.jk.smartRoom.services.device.DeviceStateListener;
import org.jk.smartRoom.services.device.DeviceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
@Route("mobile/door")
@Push
@PreserveOnRefresh
public class DoorView extends VerticalLayout {
    private static final Logger log = LoggerFactory.getLogger(LightView.class);
    private final DeviceManager deviceManager;
    private volatile DeviceStateListener doorListener;
    private Icon icon = new Icon(VaadinIcon.LOCK);
    private Button button;
    private ReturnButton returnButton;

    public DoorView(DeviceManager deviceManager) {
        this.deviceManager = deviceManager;

        returnButton = new ReturnButton("mobile/room");
        button = new Button();
        button.setWidth("50%");
        button.setHeight("16%");
        button.addClickListener(event -> deviceManager.sendMessage(DeviceType.DOOR, "TOGGLE"));

        setAlignItems(Alignment.CENTER);
        setSizeFull();
        add(returnButton, icon, button);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        log.info("Door view opened");

        doorListener = (t, s) -> doorValue(attachEvent.getUI(), s.isEnabled());
        deviceManager.register(DeviceType.DOOR, doorListener);
        doorStatus(attachEvent.getUI(), deviceManager.getState(DeviceType.DOOR).isConnected());
        doorValue(attachEvent.getUI(), deviceManager.getState(DeviceType.DOOR).isEnabled());
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        log.info("Door view closed");
        deviceManager.unregister(doorListener);
    }

    private void doorStatus(UI ui, boolean conn) {
        ui.access(() -> {
            if (!conn) {
                MyNotification notification = new MyNotification("Smart DOOR nie jest podłączony");
                notification.open();
            }
        });
    }

    private void doorValue(UI ui, boolean state) {
        ui.access(() -> checkState(state));
    }

    private void checkState(boolean state) {
        if (state) {
            icon = new Icon(VaadinIcon.LOCK);
            icon.setSize("50%");
            button.setText("odblokuj");
            button.getStyle().set("font-size", "200%");
            removeAll();
            add(returnButton, icon, button);
        } else {
            icon = new Icon(VaadinIcon.UNLOCK);
            icon.setSize("50%");
            button.setText("zablokuj");
            button.getStyle().set("font-size", "200%");
            removeAll();
            add(returnButton, icon, button);
        }
    }
}