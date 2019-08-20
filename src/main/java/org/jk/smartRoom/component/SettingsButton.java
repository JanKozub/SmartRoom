package org.jk.smartRoom.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;

public class SettingsButton extends Button {

    public SettingsButton(String text, Component icon) {
        super(text, icon);
        setWidth("100%");
        setHeight("15%");
        getStyle().set("font-size", "150%");
    }
}
