package com.harrisonwells.funding.views.applications;


import com.harrisonwells.funding.backend.models.ProjectApplication;
import com.harrisonwells.funding.backend.services.ProjectApplicationService;
import com.harrisonwells.funding.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route(value = "fund-applications", layout = MainLayout.class)
@RolesAllowed("INVESTOR")
public class FundApplicationView extends VerticalLayout {


    public FundApplicationView(ProjectApplicationService projectApplicationService) {

        var crud = new GridCrud<>(ProjectApplication.class, projectApplicationService);

        crud.setAddOperationVisible(false);
        crud.setDeleteOperationVisible(false);
        crud.setUpdateOperationVisible(false);

        crud.getGrid().setColumns("description", "entrepreneur");
        crud.getGrid().addComponentColumn(projectApplication -> {
            if (projectApplication.getIsFunded() == null || !projectApplication.getIsFunded()) {
                Button button = new Button("FUND");
                button.addClickListener(event -> {
                    projectApplication.setIsFunded(true);
                    projectApplicationService.update(projectApplication);
                    crud.refreshGrid();
                });
                return button;
            }else {
                Span span = new Span("FUNDED");
                span.getStyle().set("font-weight", "bold");
                span.getStyle().set("color", "green");
                return span;
            }
        }).setHeader("Actions");

        add(new H1("Applications"), crud);

    }
}
