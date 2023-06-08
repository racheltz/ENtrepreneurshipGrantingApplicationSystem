package com.harrisonwells.funding.views.applications;


import com.harrisonwells.funding.backend.models.ProjectApplication;
import com.harrisonwells.funding.backend.services.ProjectApplicationService;
import com.harrisonwells.funding.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "my-applications", layout = MainLayout.class)
@RolesAllowed("ENTREPRENEUR")
public class MyApplicationView extends VerticalLayout {

    private final Grid<ProjectApplication> grid;

    public MyApplicationView(ProjectApplicationService projectApplicationService) {

// Set the header for the actions column
        grid = new Grid<>(ProjectApplication.class);

        grid.removeAllColumns();

        grid.addComponentColumn(projectApplication -> new Span(projectApplication.getAnnouncement().getTitle())).setHeader("Title");

        grid.addComponentColumn(projectApplication -> new Span(projectApplication.getDescription())).setHeader("Content");

        grid.addComponentColumn(projectApplication -> new Span(projectApplication.getPublished().toString())).setHeader("Published");

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);

        grid.setItems(projectApplicationService.findAll());

        add(new H1("My Applications"), grid);

    }
}
