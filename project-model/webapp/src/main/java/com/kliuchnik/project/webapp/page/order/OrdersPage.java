package com.kliuchnik.project.webapp.page.order;

import javax.inject.Inject;

import com.kliuchnik.project.service.OrderService;
import com.kliuchnik.project.webapp.page.AbstractPage;
import com.kliuchnik.project.webapp.page.order.panel.OrderListPanel;
import com.kliuchnik.project.webapp.page.product.panel.ProductListPanel;

public class OrdersPage extends AbstractPage {
	

	    public OrdersPage() {
	        super();

	        add(new OrderListPanel("panel"));
	    }
}
