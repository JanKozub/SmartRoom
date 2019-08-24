package org.jk.smartRoom.view.pc;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.jk.smartRoom.component.MainMenuPC;
import org.jk.smartRoom.component.ViewType;

@SuppressWarnings("unused")
@Route("pc/room")
public class MainViewRoom extends VerticalLayout {

    public MainViewRoom() {

        MainMenuPC mainMenuPC = new MainMenuPC(ViewType.MAIN);

        setSizeFull();
        add(mainMenuPC);
    }

}
