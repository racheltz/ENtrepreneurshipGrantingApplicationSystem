package com.harrisonwells.funding.views.announcements;


import com.harrisonwells.funding.backend.models.Announcement;
import com.harrisonwells.funding.backend.services.AnnouncementService;
import com.harrisonwells.funding.views.HomeLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route(value = "announcements", layout = HomeLayout.class)
@AnonymousAllowed
public class HomeAnnouncementView extends VerticalLayout {

    public HomeAnnouncementView(AnnouncementService announcementService) {
        var crud = new GridCrud<>(Announcement.class, announcementService);
        crud.getGrid().setColumns("title", "description", "investor", "published");
        crud.getCrudFormFactory().setVisibleProperties("title", "description", "investor");
        crud.setAddOperationVisible(false);
        crud.setDeleteOperationVisible(false);
        crud.setUpdateOperationVisible(false);
        add(new H1("Announcements"), crud);
    }
}
