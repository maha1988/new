package com.kliuchnik.project.webapp.component.menu;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import com.kliuchnik.project.webapp.app.AuthorizedSession;
import com.kliuchnik.project.webapp.page.customer.CustomersPage;
import com.kliuchnik.project.webapp.page.home.HomePage;
import com.kliuchnik.project.webapp.page.login.LoginPage;
import com.kliuchnik.project.webapp.page.order.OrdersPage;
import com.kliuchnik.project.webapp.page.product.ProductsPage;
import com.kliuchnik.project.webapp.page.sklad.SkladPage;
import com.kliuchnik.project.webapp.page.user.UsersPage;


public class MenuPanel extends Panel {

    public MenuPanel(String id) {
        super(id);
       // setRenderBodyOnly(true);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Link("link-home") {
            @Override
            public void onClick() {
                setResponsePage(new HomePage());
            }
        });

        add(new Link("link-products") {
            @Override
            public void onClick() {
                setResponsePage(new ProductsPage());
            }
        });
        
        add(new Link("link-sklad") {
            @Override
            public void onClick() {
                setResponsePage(new SkladPage());
            }
        });
      
        
        add(new Link("link-customers") {
            @Override
            public void onClick() {
                setResponsePage(new CustomersPage());
            }
        });
        add(new Link("link-users") {
            @Override
            public void onClick() {
                setResponsePage(new  UsersPage());
            }
        });
        
        add(new Link("link-orders") {
            @Override
            public void onClick() {
                setResponsePage(new OrdersPage());
            }
        });
        Link link = new Link("link-logout") {
            @Override
            public void onClick() {
                getSession().invalidate();
                setResponsePage(LoginPage.class);
            }
        };
        link.setVisible(AuthorizedSession.get().isSignedIn());
        add(link);
    }
}
