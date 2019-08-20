package org.jk.smartRoom.view.mobile.tabs;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import org.jk.smartRoom.component.ReturnButton;
import org.jk.smartRoom.component.SettingsButton;

@SuppressWarnings("unused")
@Route("mobile/settings")
public class SettingsView extends VerticalLayout implements RouterLayout {
    private boolean darkState = false;

    public SettingsView() {
        ReturnButton returnButton = new ReturnButton("mobile/room");

        SettingsButton darkMode = new SettingsButton("Zmień Wygląd", new Icon(VaadinIcon.MOON_O));
        darkMode.addClickListener(event -> checkState());

        SettingsButton connStatus = new SettingsButton("Status Połączenia", new Icon(VaadinIcon.CONNECT_O));

        SettingsButton aboutSystem = new SettingsButton("O Systemie", new Icon(VaadinIcon.MOBILE_BROWSER));

        VerticalLayout layout = new VerticalLayout(darkMode, connStatus, aboutSystem);
        layout.setSizeFull();

        setSizeFull();
        add(returnButton, layout);
    }

    private void checkState() {
        if (darkState) {
            UI.getCurrent().getPage().executeJs("document.documentElement.setAttribute(\"theme\",\"dark\")");
        } else {
            UI.getCurrent().getPage().executeJs("document.documentElement.setAttribute(\"theme\",\"white\")");
        }
        darkState = !darkState;
    }
}
