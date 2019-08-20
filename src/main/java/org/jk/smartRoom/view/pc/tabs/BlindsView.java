package org.jk.smartRoom.view.pc.tabs;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.jk.smartRoom.component.MainMenuPC;
import org.jk.smartRoom.component.ViewType;

@SuppressWarnings("unused")
@Route("pc/blinds")
public class BlindsView extends VerticalLayout {
    private int leftVal;
    private int rightVal = 0;

    public BlindsView() {
        MainMenuPC mainMenuPC = new MainMenuPC(ViewType.BLINDS);
        mainMenuPC.setWidth("100%");

        Div spacer = new Div();
        spacer.setHeight("15%");

        HorizontalLayout layout = new HorizontalLayout(setValue(true), setValue(false));
        layout.getStyle().set("margin-left", "auto");
        layout.getStyle().set("margin-right", "auto");
        layout.setWidth("50%");
        layout.setHeight("50%");

        setSizeFull();
        add(mainMenuPC, spacer, layout);
    }

    private VerticalLayout setValue(boolean side) {

        Button up = new Button(new Icon(VaadinIcon.ARROW_UP));
        up.setWidth("100%");
        up.setHeight("20%");
        Button middle = new Button();
        middle.getStyle().set("font-size", "300%");
        middle.setWidth("100%");
        middle.setHeight("45%");
        Button down = new Button(new Icon(VaadinIcon.ARROW_DOWN));
        down.setWidth("100%");
        down.setHeight("20%");

        if (side) {
            middle.setText(Integer.toString(leftVal));
            up.addClickListener(event -> {
                leftVal = sumInt(leftVal, 1);
                middle.setText(Integer.toString(leftVal));
            });
            //middle.addClickListener(event -> );
            down.addClickListener(event -> {
                leftVal = sumInt(leftVal, -1);
                middle.setText(Integer.toString(leftVal));
            });
        } else {
            middle.setText(Integer.toString(rightVal));
            up.addClickListener(event -> {
                rightVal = sumInt(rightVal, 1);
                middle.setText(Integer.toString(rightVal));
            });
            //middle.addClickListener(event ->);
            down.addClickListener(event -> {
                rightVal = sumInt(rightVal, -1);
                middle.setText(Integer.toString(rightVal));
            });
        }
        VerticalLayout layout = new VerticalLayout(up, middle, down);
        layout.setSizeFull();
        return layout;
    }

    private int sumInt(int value, int addedValue) {
        int sum = value + addedValue;

        if (sum < 0) return 0;
        if (sum > 10) return 10;
        return sum;
    }
}
