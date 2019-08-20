package org.jk.smartRoom.view.mobile;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.jk.smartRoom.component.MenuButtonMoblie;
import org.jk.smartRoom.component.ViewType;

@Route("mobile/house")
public class MainViewHouse extends VerticalLayout {
    public MainViewHouse() {

        MenuButtonMoblie gate = new MenuButtonMoblie(ViewType.GATE);

        MenuButtonMoblie monitoring = new MenuButtonMoblie(ViewType.MONITORING);

        MenuButtonMoblie exit = new MenuButtonMoblie(ViewType.EXIT);

        setSizeFull();
        add(gate, monitoring, exit);
    }
}
