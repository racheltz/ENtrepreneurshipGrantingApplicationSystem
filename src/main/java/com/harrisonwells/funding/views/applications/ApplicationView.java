package com.harrisonwells.funding.views.applications;


import com.harrisonwells.funding.backend.models.Announcement;
import com.harrisonwells.funding.backend.services.AnnouncementService;
import com.harrisonwells.funding.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "applications", layout = MainLayout.class)
@RolesAllowed("ENTREPRENEUR")
public class ApplicationView extends VerticalLayout {


    public ApplicationView(AnnouncementService announcementService) {


// Set the header for the actions column
        Grid<Announcement> grid = new Grid<>(Announcement.class);

        grid.setColumns("title", "description", "investor", "published");
        Grid.Column<Announcement> actionsColumn = grid.addComponentColumn(announcement -> {
            // Create a button component for the cell
            Button button = new Button("APPLY");
            button.addClickListener(event -> {
                // Handle button click event
                // ...
            });
            return button;
        });
        actionsColumn.setHeader("Actions");

        grid.setItems(announcementService.findAll());


        add(new H1("Announcements"), grid);

    }
}
