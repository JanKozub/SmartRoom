package org.jk.smartRoom.view.pc;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.jk.smartRoom.component.MainMenuPC;
import org.jk.smartRoom.component.ViewType;

@SuppressWarnings("unused")
@Route("pc")
public class MainView extends VerticalLayout {

    public MainView() {

        MainMenuPC mainMenuPC = new MainMenuPC(ViewType.MAIN);
        mainMenuPC.setWidth("100%");
        setSizeFull();
        add(mainMenuPC);
    }

}