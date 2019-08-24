package org.jk.smartRoom.view.pc;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("pc")
public class UserTypeView extends VerticalLayout {
    public UserTypeView() {

        Button roomButton = new Button("Pokój", new Icon(VaadinIcon.USER), event -> UI.getCurrent().navigate("pc/room"));
        roomButton.getStyle().set("font-size", "150%");

        Button houseButton = new Button("Dom", new Icon(VaadinIcon.USERS), event -> UI.getCurrent().navigate("pc/house"));
        houseButton.getStyle().set("font-size", "150%");

        roomButton.setWidth("25%");
        roomButton.setHeight("50%");
        houseButton.setWidth("25%");
        houseButton.setHeight("50%");

        setSizeFull();

        HorizontalLayout layout = new HorizontalLayout(roomButton, houseButton);
        layout.setSizeFull();
        layout.getStyle().set("margin-left", "25%");
        layout.getStyle().set("margin-right", "25%");
        layout.setAlignItems(Alignment.CENTER);

        add(layout);
    }
}
