package org.jk.smartRoom.view.mobile;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.jk.smartRoom.component.MenuButtonMoblie;
import org.jk.smartRoom.component.ViewType;

@SuppressWarnings("unused")
@Route("mobile/room")
public class MainViewRoom extends VerticalLayout {

    public MainViewRoom() {

        MenuButtonMoblie light = new MenuButtonMoblie(ViewType.LIGHTS);

        MenuButtonMoblie blinds = new MenuButtonMoblie(ViewType.BLINDS);

        MenuButtonMoblie door = new MenuButtonMoblie(ViewType.DOOR);

        MenuButtonMoblie music = new MenuButtonMoblie(ViewType.MUSIC);

        MenuButtonMoblie settings = new MenuButtonMoblie(ViewType.SETTINGS);

        MenuButtonMoblie exit = new MenuButtonMoblie(ViewType.EXIT);

        setSizeFull();
        add(light, blinds, door, music, settings, exit);
    }

}
