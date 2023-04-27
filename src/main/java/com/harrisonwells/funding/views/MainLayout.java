package com.harrisonwells.funding.views;


import com.harrisonwells.funding.components.appnav.AppNav;
import com.harrisonwells.funding.components.appnav.AppNavItem;
import com.harrisonwells.funding.views.announcements.AnnouncementView;
import com.harrisonwells.funding.views.applications.ApplicationView;
import com.harrisonwells.funding.views.home.HomeView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
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

        addToNavbar(true, toggle, viewTitle);
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

        for (GrantedAuthority role : roles) {
            if (role.getAuthority().equals("ROLE_INVESTOR")) {
                nav.addItem(new AppNavItem("Home", HomeView.class, LineAwesomeIcon.HOME_SOLID.create()));
                nav.addItem(new AppNavItem("Announcements", AnnouncementView.class, LineAwesomeIcon.NEWSPAPER.create()));
            }
            if (role.getAuthority().equals("ROLE_ENTREPRENEUR")){
                nav.addItem(new AppNavItem("Home", ApplicationView.class, LineAwesomeIcon.HOME_SOLID.create()));
            }
        }
        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

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
