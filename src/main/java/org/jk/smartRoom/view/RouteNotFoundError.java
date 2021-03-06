package org.jk.smartRoom.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;
import com.vaadin.flow.router.NotFoundException;

import javax.servlet.http.HttpServletResponse;

@Tag(Tag.DIV)
class RouteNotFoundError extends Component implements HasErrorParameter<NotFoundException> {
    @Override
    public int setErrorParameter(BeforeEnterEvent event,
                                 ErrorParameter<NotFoundException> parameter) {
        UI.getCurrent().navigate("");
        UI.getCurrent().getPage().reload();
        return HttpServletResponse.SC_NOT_FOUND;
    }
}