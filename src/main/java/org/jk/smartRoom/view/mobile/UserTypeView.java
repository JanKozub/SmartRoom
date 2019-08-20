package org.jk.smartRoom.view.mobile;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("mobile")
public class UserTypeView extends VerticalLayout {
    public UserTypeView() {
        Button userButton = new Button("Pokój", new Icon(VaadinIcon.USER), buttonClickEvent -> UI.getCurrent().navigate("mobile/room"));
        userButton.setWidth("100%");
        userButton.setHeight("50%");
        userButton.getStyle().set("border-radius", "20px");
        userButton.getStyle().set("font-size", "150%");

        Button houseButton = new Button("Dom", new Icon(VaadinIcon.USERS), buttonClickEvent -> UI.getCurrent().navigate("mobile/house"));
        houseButton.setWidth("100%");
        houseButton.setHeight("50%");
        houseButton.getStyle().set("border-radius", "20px");
        houseButton.getStyle().set("font-size", "150%");

        setSizeFull();
        add(userButton, houseButton);
    }
}
