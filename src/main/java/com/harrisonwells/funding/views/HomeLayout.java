package com.harrisonwells.funding.views;

import com.harrisonwells.funding.components.appnav.AppNav;
import com.harrisonwells.funding.components.appnav.AppNavItem;
import com.harrisonwells.funding.security.SecurityUtils;
import com.harrisonwells.funding.views.announcements.HomeAnnouncementView;
import com.harrisonwells.funding.views.home.HomeView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class HomeLayout extends AppLayout {

    private H2 viewTitle;
    private Button login = new Button("Login", VaadinIcon.SIGN_OUT.create());

    public HomeLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);


        login.getStyle().set("margin-left", "auto");
        login.getStyle().set("padding", "15px");

        login.addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate("login")); // Redirect to main view
        });

        addToNavbar(true, toggle, viewTitle, login);


    }

    private void addDrawerContent() {
        H1 appName = new H1("Entrepreneurship Granting Application System");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private AppNav createNavigation() {
        AppNav nav = new AppNav();
        nav.addItem(new AppNavItem("Home", HomeView.class, LineAwesomeIcon.HOME_SOLID.create()));
        nav.addItem(new AppNavItem("Announcements", HomeAnnouncementView.class, LineAwesomeIcon.NEWSPAPER.create()));
        nav.addItem(new AppNavItem("Register", RegisterView.class, LineAwesomeIcon.REGISTERED_SOLID.create()));
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
        login.setVisible(!getCurrentPageTitle().equals("Login"));
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
