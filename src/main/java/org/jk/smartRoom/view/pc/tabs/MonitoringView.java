package org.jk.smartRoom.view.pc.tabs;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.jk.smartRoom.component.MainMenuPC;
import org.jk.smartRoom.component.ViewType;

@Route("pc/monitoring")
public class MonitoringView extends VerticalLayout {

    public MonitoringView() {
        MainMenuPC mainMenuPC = new MainMenuPC(ViewType.MONITORING);
        mainMenuPC.setWidth("100%");

        setSizeFull();
        add(mainMenuPC);
    }
}
