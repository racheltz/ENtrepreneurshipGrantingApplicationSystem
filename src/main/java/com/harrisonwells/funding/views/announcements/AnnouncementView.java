package com.harrisonwells.funding.views.announcements;


import com.harrisonwells.funding.backend.models.Announcement;
import com.harrisonwells.funding.backend.services.AnnouncementService;
import com.harrisonwells.funding.views.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route(value = "announcements", layout = MainLayout.class)
@RolesAllowed("INVESTOR")
public class AnnouncementView extends VerticalLayout {

    public AnnouncementView(AnnouncementService announcementService) {
        var crud = new GridCrud<>(Announcement.class, announcementService);
        crud.getGrid().setColumns("title", "description", "investor", "published");
        crud.getCrudFormFactory().setVisibleProperties("title", "description", "investor");
        add(new H1("Announcements"), crud);
    }
}
