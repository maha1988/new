package com.kliuchnik.project.webapp.page.customer;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.StringValidator;

import com.kliuchnik.project.datamodel.Customer;
import com.kliuchnik.project.datamodel.Role;
import com.kliuchnik.project.datamodel.Unit;
import com.kliuchnik.project.datamodel.User;
import com.kliuchnik.project.service.UserService;
import com.kliuchnik.project.webapp.page.common.UserRoleChoiceRenderer;

public class CustomerEditPanel extends Panel {

	@Inject
	private UserService userService;
	private User user;
	private Customer customer;
	private ModalWindow modalWindow;

	public CustomerEditPanel(ModalWindow modalWindow, Customer customer) {
		super(modalWindow.getContentId());
		this.customer = customer;
		this.modalWindow = modalWindow;
	}

	public CustomerEditPanel(ModalWindow modalWindow, User user) {
		super(modalWindow.getContentId());
		this.user = user;
		this.modalWindow = modalWindow;
	}
	public CustomerEditPanel(String id) {
		super(id);
		user = new User();
		customer = new Customer();

	}
	public CustomerEditPanel(String id, Customer customer) {
		super(id);
		this.customer = customer;
		this.user = customer.getUser();

	}


	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form  form = new Form ("form", new CompoundPropertyModel<>(customer));
		add(form);
		TextField<String> firstNField = new TextField<>("firstN");
		firstNField.setRequired(true);
		firstNField.add(StringValidator.maximumLength(100));
		form.add(firstNField);
		
		TextField<String> lastNField = new TextField<>("lastN");
		lastNField.setRequired(true);
		lastNField.add(StringValidator.maximumLength(100));
		form.add(lastNField);
		
		TextField<String> addressField = new TextField<>("address");
		addressField.setRequired(true);
		addressField.add(StringValidator.maximumLength(100));
		form.add(addressField);

		TextField<String> bankRField = new TextField<>("bankR");
		bankRField.setRequired(true);
		bankRField.add(StringValidator.maximumLength(100));
		form.add(bankRField);
		
			
//		
//		TextField<String> nameField = new TextField<String>("name",
//				new PropertyModel<>(user, "name"));
//		nameField.setRequired(true);
//		nameField.add(StringValidator.maximumLength(100));
//		nameField.add(StringValidator.minimumLength(2));
//		form.add(nameField);
//		
//		
//		
//
//		TextField<String> passwordField = new TextField<>("password",
//			new PropertyModel<>(user, "password"));
//		passwordField.setRequired(true);
//		passwordField.add(StringValidator.maximumLength(100));
//		form.add(passwordField);
//
//		
//		DropDownChoice<Role> role = new DropDownChoice<>("role",new PropertyModel<>(user, "role"),  Arrays.asList(Role.values()));
//		role.setRequired(true);
//		form.add(role);

					
		
		form.add(new AjaxSubmitLink("save") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				 super.onSubmit(target, form);
	               
				 userService.saveOrUpdate(customer);
	                userService.saveOrUpdate(user);
	                modalWindow.close(target);
			}
		});

		form.add(new AjaxLink("cancel") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				modalWindow.close(target);
			}
		});

	}
}
