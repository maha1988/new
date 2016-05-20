package com.kliuchnik.project.webapp.page.customer;

import javax.inject.Inject;

import com.kliuchnik.project.service.UserService;
import com.kliuchnik.project.webapp.page.AbstractPage;
import com.kliuchnik.project.webapp.page.customer.panel.CustomerListPanel;
import com.kliuchnik.project.webapp.page.sklad.panel.SkladListPanel;

public class CustomersPage extends AbstractPage {
	 
	    public CustomersPage() {
	        super();

	        add(new CustomerListPanel("panel"));
	    }
}
