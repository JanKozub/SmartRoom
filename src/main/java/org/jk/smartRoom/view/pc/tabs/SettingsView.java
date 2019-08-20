package org.jk.smartRoom.view.pc.tabs;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import org.jk.smartRoom.component.MainMenuPC;
import org.jk.smartRoom.component.SettingsButton;
import org.jk.smartRoom.component.ViewType;

@SuppressWarnings("unused")
@Route("pc/settings")
public class SettingsView extends VerticalLayout implements RouterLayout {
    private boolean darkState = false;

    public SettingsView() {
        MainMenuPC mainMenuPC = new MainMenuPC(ViewType.SETTINGS);
        mainMenuPC.setWidth("100%");

        SettingsButton darkMode = new SettingsButton("Zmień Wygląd", new Icon(VaadinIcon.MOON_O));
        darkMode.addClickListener(event -> checkState());

        SettingsButton connStatus = new SettingsButton("Status Połączenia", new Icon(VaadinIcon.CONNECT_O));

        SettingsButton aboutSystem = new SettingsButton("O Systemie", new Icon(VaadinIcon.MOBILE_BROWSER));

        VerticalLayout layout = new VerticalLayout(darkMode, connStatus, aboutSystem);
        layout.setHeight("100%");
        layout.setWidth("50%");

        setAlignItems(Alignment.CENTER);
        setSizeFull();

        add(mainMenuPC, layout);
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
