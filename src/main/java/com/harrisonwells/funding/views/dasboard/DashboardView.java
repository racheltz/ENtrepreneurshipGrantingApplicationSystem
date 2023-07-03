package com.harrisonwells.funding.views.dasboard;

import com.harrisonwells.funding.views.MainLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;


@PageTitle("Home")
@Route(value = "dashboard", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class DashboardView extends VerticalLayout {

    public DashboardView() {
        setSpacing(false);

        Image img = new Image();
        img.setWidthFull();

        try {
            byte[] data = Files.readAllBytes(Path.of("images/dashboard.jpg"));
            StreamResource streamResource = new StreamResource("dashboard.jpg", () -> new ByteArrayInputStream(data));
            img.setSrc(streamResource);
            add(img);
        } catch (IOException ignored) {
        }

        H2 header = new H2("Overview");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();


        for (GrantedAuthority role : roles) {
            if (role.getAuthority().equals("ROLE_INVESTOR")) {
                add(new Text("Welcome to the Investor's Dashboard of the Entrepreneurship Granting Application System (EGAS)! The Investor's Dashboard is designed to provide you with a powerful interface to manage your investment preferences and connect with promising business ideas."));
            }
            if (role.getAuthority().equals("ROLE_ENTREPRENEUR")) {
                add(new Text("Welcome to the Entrepreneur's Dashboard of the Entrepreneurship Granting Application System (EGAS)! This powerful tool has been designed to empower entrepreneurs like you in realizing your dreams by providing a comprehensive platform to submit your business ideas and connect with potential investors."));
            }
        }

        setSizeFull();
//        setJustifyContentMode(JustifyContentMode.CENTER);
//        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
//        getStyle().set("text-align", "center");
    }

}
