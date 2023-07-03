package com.harrisonwells.funding.views;


import com.harrisonwells.funding.components.appnav.AppNav;
import com.harrisonwells.funding.components.appnav.AppNavItem;
import com.harrisonwells.funding.security.SecurityUtils;
import com.harrisonwells.funding.views.announcements.AnnouncementView;
import com.harrisonwells.funding.views.applications.EntrepreneurApplicationView;
import com.harrisonwells.funding.views.applications.FundApplicationView;
import com.harrisonwells.funding.views.applications.MyApplicationView;
import com.harrisonwells.funding.views.dasboard.DashboardView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Collection;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);


        Button logout = new Button("Logout", VaadinIcon.SIGN_OUT.create());
        logout.getStyle().set("margin-left", "auto");
        logout.getStyle().set("padding", "15px");
        logout.getStyle().set("color", "red");

        logout.addClickListener(event -> {
            SecurityUtils.logout();
        });

        addToNavbar(true, toggle, viewTitle, logout);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Entrepreneurship Granting Application system");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private AppNav createNavigation() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();

        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();

        System.out.println(roles);

        for (GrantedAuthority role : roles) {
            if (role.getAuthority().equals("ROLE_INVESTOR")) {
                nav.addItem(new AppNavItem("Dashboard", DashboardView.class, LineAwesomeIcon.HOME_SOLID.create()));
                nav.addItem(new AppNavItem("Manage Announcements", AnnouncementView.class, LineAwesomeIcon.NEWSPAPER.create()));
                nav.addItem(new AppNavItem("Fund Applications", FundApplicationView.class, LineAwesomeIcon.MONEY_CHECK_SOLID.create()));
            }
            if (role.getAuthority().equals("ROLE_ENTREPRENEUR")) {
                nav.addItem(new AppNavItem("Dashboard", DashboardView.class, LineAwesomeIcon.HOME_SOLID.create()));
                nav.addItem(new AppNavItem("Announcements", EntrepreneurApplicationView.class, LineAwesomeIcon.NEWSPAPER.create()));
                nav.addItem(new AppNavItem("Project Applications", MyApplicationView.class, LineAwesomeIcon.PROJECT_DIAGRAM_SOLID.create()));
            }
        }
        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();
        layout.add(new Label("EGAS@2023"));
        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
