package org.jk.smartRoom.component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

public class ReturnButton extends Button {
    public ReturnButton(String location) {
        addClickListener(event -> UI.getCurrent().navigate(location));
        setWidth("100%");
        setHeight("8%");
        setText("Powrót");
        setIcon(new Icon(VaadinIcon.ARROW_LEFT));
        getStyle().set("font-size", "150%");
    }
}
