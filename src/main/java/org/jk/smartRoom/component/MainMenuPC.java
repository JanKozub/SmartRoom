package org.jk.smartRoom.component;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class MainMenuPC extends HorizontalLayout {

    public MainMenuPC(ViewType viewType) {

        menuButton lights = new menuButton("Światło", new Icon(VaadinIcon.LIGHTBULB), a -> UI.getCurrent().navigate("pc/light"));

        menuButton blinds = new menuButton("Rolety", new Icon(VaadinIcon.MODAL), b -> UI.getCurrent().navigate("pc/blinds"));

        menuButton door = new menuButton("Drzwi", new Icon(VaadinIcon.LOCK), c -> UI.getCurrent().navigate("pc/door"));

        menuButton music = new menuButton("Muzyka", new Icon(VaadinIcon.MUSIC), c -> UI.getCurrent().navigate("pc/dupa"));

        menuButton monitoring = new menuButton("Monitoring", new Icon(VaadinIcon.CAMERA), d -> UI.getCurrent().navigate("pc/monitoring"));

        menuButton settings = new menuButton("Ustawienia", new Icon(VaadinIcon.WRENCH), e -> UI.getCurrent().navigate("pc/settings"));

        switch (viewType) {
            case LIGHTS:
                lights.getStyle().set("color", "grey");
                break;
            case BLINDS:
                blinds.getStyle().set("color", "grey");
                break;
            case DOOR:
                door.getStyle().set("color", "grey");
                break;
            case MUSIC:
                music.getStyle().set("color", "grey");
                break;
            case MONITORING:
                monitoring.getStyle().set("color", "grey");
                break;
            case SETTINGS:
                settings.getStyle().set("color", "grey");
                break;
        }

        setHeight("8%");
        add(lights, blinds, door, music, monitoring, settings);
    }

    private static class menuButton extends Button {

        private menuButton(String text, Component icon, ComponentEventListener<ClickEvent<Button>> clickListener) {
            super(text, icon, clickListener);
            setSizeFull();
            setWidth("100%");
            getStyle().set("font-size", "150%");
        }
    }
}
