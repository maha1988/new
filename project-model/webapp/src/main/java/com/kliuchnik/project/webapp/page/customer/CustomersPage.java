package com.kliuchnik.project.webapp.page.customer;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.WindowClosedCallback;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import com.kliuchnik.project.datamodel.Customer;
import com.kliuchnik.project.datamodel.Sklad;
import com.kliuchnik.project.datamodel.User;
import com.kliuchnik.project.webapp.page.AbstractPage;
import com.kliuchnik.project.webapp.page.customer.panel.CustomerListPanel;
import com.kliuchnik.project.webapp.page.sklad.SkladEditPanel;
import com.kliuchnik.project.webapp.page.sklad.panel.SkladListPanel;

@AuthorizeInstantiation(value = { "ADMIN" })
public class CustomersPage extends AbstractPage {

	@Override
    protected void onInitialize() {
        super.onInitialize();

        CustomerListPanel customerListPanel = new CustomerListPanel("panel");
        customerListPanel.setOutputMarkupId(true);
        add(customerListPanel);

        addModalWindow(customerListPanel);

        // addSearchComponents(attributesListPanel);
    }
 
 private void addSearchComponents(CustomerListPanel customerListPanel) {
        Form form = new Form("searchForm");
        add(form);

        form.add(new AjaxSubmitLink("search-link") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);
                target.add(customerListPanel);
            }
        });

    }
 
 private void addModalWindow(CustomerListPanel customerListPanel) {
        ModalWindow modalWindow = new ModalWindow("modal");
        add(modalWindow);

        add(new AjaxLink("create") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                modalWindow.setContent(new CustomerEditPanel(modalWindow, new Customer()));
                modalWindow.show(target);
            }
        });

        modalWindow.setWindowClosedCallback(new WindowClosedCallback() {

            @Override
            public void onClose(AjaxRequestTarget target) {
                target.add(customerListPanel);

            }
        });
        add(new FeedbackPanel("feedback"));
    }
}
 
