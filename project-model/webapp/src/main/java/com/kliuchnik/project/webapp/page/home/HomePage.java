package com.kliuchnik.project.webapp.page.home;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.Link;

import com.kliuchnik.project.webapp.page.AbstractPage;
import com.kliuchnik.project.webapp.page.customer.CustomersPage;
import com.kliuchnik.project.webapp.page.order.OrdersPage;
import com.kliuchnik.project.webapp.page.product.ProductsPage;
import com.kliuchnik.project.webapp.page.sklad.SkladPage;
@AuthorizeInstantiation(value = {"ADMIN","CUSTOMER"})
public class HomePage extends AbstractPage {

    public HomePage() {
        super();
    }

}
