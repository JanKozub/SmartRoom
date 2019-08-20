package org.jk.smartRoom.view.pc.tabs;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import org.jk.smartRoom.component.MainMenuPC;
import org.jk.smartRoom.component.MyNotification;
import org.jk.smartRoom.component.ViewType;
import org.jk.smartRoom.services.device.DeviceManager;
import org.jk.smartRoom.services.device.DeviceStateListener;
import org.jk.smartRoom.services.device.DeviceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route("pc/light")
@Push
@PreserveOnRefresh
@SuppressWarnings("unused")
public class LightView extends VerticalLayout {
    private static final Logger log = LoggerFactory.getLogger(LightView.class);
    private final DeviceManager deviceManager;
    private final Icon bulbIcon = new Icon(VaadinIcon.LIGHTBULB);
    private final Icon clockIcon = new Icon(VaadinIcon.CLOCK);
    private volatile DeviceStateListener relayListener;
    private volatile DeviceStateListener clockListener;

    public LightView(DeviceManager deviceManager) {
        this.deviceManager = deviceManager;

        MainMenuPC mainMenuPC = new MainMenuPC(ViewType.LIGHTS);
        mainMenuPC.setWidth("100%");

        bulbIcon.setSize("40%");
        clockIcon.setSize("40%");

        Button mainLight = new Button(bulbIcon, event -> {
            log.info("sent change mainLight request");
            deviceManager.sendMessage(DeviceType.RELAY, "TOGGLE");
        });

        Button clockLight = new Button(clockIcon, event -> {
            log.info("sent change mainLight request");
            deviceManager.sendMessage(DeviceType.CLOCK, "TOGGLE");
        });

        mainLight.setWidth("100%");
        mainLight.setHeight("200%");
        clockLight.setWidth("100%");
        clockLight.setHeight("200%");

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        Div spacer = new Div();
        spacer.setHeight("25%");

        HorizontalLayout layout = new HorizontalLayout(mainLight, clockLight);

        add(mainMenuPC, spacer, layout);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        log.info("Light view opened");

        relayListener = (t, s) -> relayValue(attachEvent.getUI(), s.isEnabled());
        deviceManager.register(DeviceType.RELAY, relayListener);
        relayStatus(attachEvent.getUI(), deviceManager.getState(DeviceType.RELAY).isConnected());
        checkState(deviceManager.getState(DeviceType.RELAY).isEnabled(), bulbIcon);

        clockListener = (t, s) -> clockValue(attachEvent.getUI(), s.isEnabled());
        deviceManager.register(DeviceType.CLOCK, clockListener);
        clockStatus(attachEvent.getUI(), deviceManager.getState(DeviceType.CLOCK).isConnected());
        checkState(deviceManager.getState(DeviceType.CLOCK).isEnabled(), clockIcon);
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        log.info("Light view closed");
        deviceManager.unregister(relayListener);
        deviceManager.unregister(clockListener);
    }

    private void relayStatus(UI ui, boolean conn) {
        ui.access(() -> {
            if (!conn) {

                MyNotification notification = new MyNotification("Smart RELAY nie jest podłączony");
                notification.open();
            }
        });
    }

    private void relayValue(UI ui, boolean state) {
        ui.access(() -> checkState(state, bulbIcon));
    }

    private void clockStatus(UI ui, boolean conn) {
        ui.access(() -> {
            if (!conn) {

                MyNotification notification = new MyNotification("Smart CLOCK nie jest podłączony");
                notification.open();
            }
        });
    }

    private void clockValue(UI ui, boolean state) {
        ui.access(() -> checkState(state, clockIcon));
    }

    private void checkState(boolean state, Icon icon) {
        String color;
        if (state) {
            color = "orange";
        } else {
            color = "grey";
        }
        icon.getStyle().set("color", color);
    }
}
