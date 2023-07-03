package com.harrisonwells.funding.views.home;

import com.harrisonwells.funding.security.MyUserDetailsService;
import com.harrisonwells.funding.views.HomeLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;


@PageTitle("Home")
@Route(value = "home", layout = HomeLayout.class)
@AnonymousAllowed
public class HomeView extends VerticalLayout {

    public HomeView(MyUserDetailsService myUserDetailsService) {
        setSpacing(false);

        H2 header = new H2("Welcome to the Entrepreneurship Granting Application System (EGAS)!");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new H3("About EGAS:"));
        add(new Paragraph("EGAS is a cutting-edge platform designed to empower entrepreneurs by providing them with the tools and resources to submit applications for capital grants. We understand the challenges faced by aspiring business owners in securing the necessary funding to bring their innovative ideas to life. Our mission is to bridge the gap between entrepreneurs and investors, facilitating connections and fostering a thriving entrepreneurial ecosystem."));

        add(new H3("Our Vision:"));
        add(new Paragraph("At EGAS, we envision a world where every entrepreneurial dream has the opportunity to become a reality. We believe that entrepreneurship is a powerful driver of economic growth and innovation. Our goal is to create an inclusive and supportive platform that empowers entrepreneurs from all backgrounds, helping them transform their business ideas into successful ventures."));

        setSizeFull();
    }

}
