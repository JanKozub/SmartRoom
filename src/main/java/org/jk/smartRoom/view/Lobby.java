package org.jk.smartRoom.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinSession;

@SuppressWarnings("unused")
@Route("")
@PWA(name = "SmartRoom app", shortName = "Smart Room", iconPath = "img/icons/logo.png")
public class Lobby extends VerticalLayout {

    public Lobby() {
        if (VaadinSession.getCurrent().getBrowser().isIOS() || VaadinSession.getCurrent().getBrowser().isAndroid()) {
            UI.getCurrent().navigate("mobile");
            UI.getCurrent().getPage().reload();
        } else {
            UI.getCurrent().navigate("pc");
            UI.getCurrent().getPage().reload();
        }
    }
}
