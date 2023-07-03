package com.harrisonwells.funding.views.applications;


import com.harrisonwells.funding.backend.models.Announcement;
import com.harrisonwells.funding.backend.models.ProjectApplication;
import com.harrisonwells.funding.backend.services.AnnouncementService;
import com.harrisonwells.funding.backend.services.ProjectApplicationService;
import com.harrisonwells.funding.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "applications", layout = MainLayout.class)
@RolesAllowed("ENTREPRENEUR")
public class EntrepreneurApplicationView extends VerticalLayout {

    private final Grid<Announcement> grid;

    public EntrepreneurApplicationView(AnnouncementService announcementService, ProjectApplicationService projectApplicationService) {

// Set the header for the actions column
        grid = new Grid<>(Announcement.class);

        grid.setColumns("title", "description", "investor", "published");

        Grid.Column<Announcement> actionsColumn = grid.addComponentColumn(announcement -> {
            // Create a button component for the cell
            boolean isApplied = projectApplicationService.isProjectAlreadyApplied(announcement.getId());
            boolean isFunded = projectApplicationService.isProjectAlreadyFunded(announcement.getId());

            if (isApplied) {
                Span span = new Span("APPLIED");
                span.getStyle().set("font-weight", "bold");
                span.getStyle().set("color", "green");
                return span;
            } else {
                if (!isFunded) {
                    Button button = new Button("APPLY");
                    button.addClickListener(event -> {
                        openModalDialog(announcement, projectApplicationService);
                    });
                    return button;
                }else {
                    Span span = new Span("NOT ELIGIBLE");
                    span.getStyle().set("font-weight", "bold");
                    span.getStyle().set("color", "blue");
                    return span;
                }
            }
        });
        grid.addComponentColumn(announcement -> {
            // Create a button component for the cell
            boolean isFunded = projectApplicationService.isProjectAlreadyFunded(announcement.getId());
            Span span;
            if (isFunded) {
                span = new Span("FUNDED");
                span.getStyle().set("font-weight", "bold");
                span.getStyle().set("color", "green");
            } else {
                span = new Span("NOT FUNDED");
                span.getStyle().set("font-weight", "bold");
            }
            return span;
        }).setHeader("Status");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        actionsColumn.setHeader("Actions");

        grid.setItems(announcementService.findAll());


        add(new H1("Announcements"), grid);

    }

    private void openModalDialog(Announcement announcement, ProjectApplicationService projectApplicationService) {
        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(false); // Prevent closing dialog with ESC key
        dialog.setCloseOnOutsideClick(true); // Prevent closing dialog by clicking outside
        dialog.setWidth("50%");

        TextArea description = new TextArea("Description");
        description.setWidthFull();
        description.setHeight("30%");

        Label title = new Label(announcement.getTitle());
        title.getStyle().set("font-weight", "bold");

        VerticalLayout content = new VerticalLayout();
        content.add(title);
        content.add(description);

        Button apply = new Button("Apply", event -> {
            if (!description.isEmpty()) {
                ProjectApplication projectApplication = ProjectApplication.builder()
                        .announcement(announcement)
                        .description(description.getValue())
                        .build();
                projectApplicationService.add(projectApplication);
                dialog.close();
                getUI().ifPresent(ui -> ui.navigate("my-applications")); // Redirect to main view
            }
        });

        content.add(apply);

        dialog.add(content);
        dialog.open();
    }
}
