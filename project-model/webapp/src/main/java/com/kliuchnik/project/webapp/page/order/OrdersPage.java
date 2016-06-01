package com.kliuchnik.project.webapp.page.order;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.WindowClosedCallback;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import com.kliuchnik.project.datamodel.Order;
import com.kliuchnik.project.webapp.page.AbstractPage;
import com.kliuchnik.project.webapp.page.order.panel.OrderListPanel;
@AuthorizeInstantiation(value = {"ADMIN"	})
public class OrdersPage extends AbstractPage {

	 @Override
	    protected void onInitialize() {
	        super.onInitialize();

	        OrderListPanel orderListPanel = new OrderListPanel("panel");
	        orderListPanel.setOutputMarkupId(true);
	        add(orderListPanel);

	        addModalWindow(orderListPanel);

	        // addSearchComponents(attributesListPanel);
	    }
	 
	 private void addSearchComponents(OrderListPanel orderListPanel) {
	        Form form = new Form("searchForm");
	        add(form);

	        form.add(new AjaxSubmitLink("search-link") {
	            @Override
	            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
	                super.onSubmit(target, form);
	                target.add(orderListPanel);
	            }
	        });

	    }
	 
	 private void addModalWindow(OrderListPanel orderListPanel) {
	        ModalWindow modalWindow = new ModalWindow("modal");
	        add(modalWindow);

	        add(new AjaxLink("create") {
	            @Override
	            public void onClick(AjaxRequestTarget target) {
	                modalWindow.setContent(new OrderEditPanel(modalWindow, new Order()));
	                modalWindow.show(target);
	            }
	        });

	        modalWindow.setWindowClosedCallback(new WindowClosedCallback() {

	            @Override
	            public void onClose(AjaxRequestTarget target) {
	                target.add(orderListPanel);

	            }
	        });
	        add(new FeedbackPanel("feedback"));
	    }
	}