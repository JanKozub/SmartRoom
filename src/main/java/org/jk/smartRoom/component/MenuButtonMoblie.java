package org.jk.smartRoom.component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

public class MenuButtonMoblie extends Button {

    public MenuButtonMoblie(ViewType viewType) {
        setWidth("100%");
        setHeight("13%");
        getStyle().set("border-radius", "20px");
        getStyle().set("font-size", "150%");

        switch (viewType) {
            case LIGHTS:
                setText("Światło");
                setIcon(new Icon(VaadinIcon.LIGHTBULB));
                addClickListener(divClickEvent -> UI.getCurrent().navigate("mobile/light"));
                break;
            case BLINDS:
                setText("Rolety");
                setIcon(new Icon(VaadinIcon.MODAL));
                addClickListener(divClickEvent -> UI.getCurrent().navigate("mobile/blinds"));
                break;
            case DOOR:
                setText("Drzwi");
                setIcon(new Icon(VaadinIcon.LOCK));
                addClickListener(divClickEvent -> UI.getCurrent().navigate("mobile/door"));
                break;
            case SETTINGS:
                setText("Ustawienia");
                setIcon(new Icon(VaadinIcon.WRENCH));
                addClickListener(divClickEvent -> UI.getCurrent().navigate("mobile/settings"));
                break;
            case MUSIC:
                setText("Muzyka");
                setIcon(new Icon(VaadinIcon.MUSIC));
                break;
            case MONITORING:
                setText("Monitoring");
                setIcon(new Icon(VaadinIcon.FILE_MOVIE));
                break;
            case GATE:
                setText("Brama");
                setIcon(new Icon(VaadinIcon.RESIZE_V));
                break;
            case EXIT:
                setText("Wyjście");
                getStyle().set("color", "red");
                setIcon(new Icon(VaadinIcon.ENTER_ARROW));
                addClickListener(divClickEvent -> UI.getCurrent().navigate("mobile"));
                break;
        }
    }
}
