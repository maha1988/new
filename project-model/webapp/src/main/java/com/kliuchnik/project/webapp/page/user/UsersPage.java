package com.kliuchnik.project.webapp.page.user ;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.WindowClosedCallback;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import com.kliuchnik.project.datamodel.User;
import com.kliuchnik.project.webapp.page.AbstractPage;
import com.kliuchnik.project.webapp.page.user.panel.UsersListPanel;

@AuthorizeInstantiation(value = { "ADMIN" })
public class UsersPage extends AbstractPage {
	 @Override
	    protected void onInitialize() {
	        super.onInitialize();

	        UsersListPanel userListPanel = new UsersListPanel("panel");
	        userListPanel.setOutputMarkupId(true);
	        add(userListPanel);

	        addModalWindow(userListPanel);

	        // addSearchComponents(attributesListPanel);
	    }
	 
	 private void addSearchComponents(UsersListPanel userListPanel) {
	        Form form = new Form("searchForm");
	        add(form);

	        form.add(new AjaxSubmitLink("search-link") {
	            @Override
	            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
	                super.onSubmit(target, form);
	                target.add(userListPanel);
	            }
	        });

	    }
	 
	 private void addModalWindow(UsersListPanel userListPanel) {
	        ModalWindow modalWindow = new ModalWindow("modal");
	        modalWindow.setTitle("Карточка пользователя");
	        add(modalWindow);

	        add(new AjaxLink("create") {
	            @Override
	            public void onClick(AjaxRequestTarget target) {
	                modalWindow.setContent(new UsersEditPanel(modalWindow, new User()));
	                modalWindow.show(target);
	            }
	        });

	        modalWindow.setWindowClosedCallback(new WindowClosedCallback() {

	            @Override
	            public void onClose(AjaxRequestTarget target) {
	                target.add(userListPanel);

	            }
	        });
	        add(new FeedbackPanel("feedback"));
	    }
	}
       
    

 
