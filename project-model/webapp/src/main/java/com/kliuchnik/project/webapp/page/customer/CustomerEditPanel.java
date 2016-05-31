package com.kliuchnik.project.webapp.page.customer;

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

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<Customer> form = new Form<>("form", new CompoundPropertyModel<>(customer));
		add(form);

		TextField<String> addressField = new TextField<>("address");
		addressField.setRequired(true);
		addressField.add(StringValidator.maximumLength(100));
		form.add(addressField);

		
		TextField<String> nameField = new TextField<String>("name",
				new PropertyModel<>(user, "name"));
		nameField.setRequired(true);
		nameField.add(StringValidator.maximumLength(100));
		nameField.add(StringValidator.minimumLength(2));
		form.add(nameField);
		
		
		TextField<String> bankRField = new TextField<>("bankR");
		bankRField.setRequired(true);
		bankRField.add(StringValidator.maximumLength(100));
		form.add(bankRField);

		TextField<String> passwordField = new TextField<>("password",
			new PropertyModel<>(user, "password"));
		passwordField.setRequired(true);
		passwordField.add(StringValidator.maximumLength(100));
		form.add(passwordField);

		DropDownChoice<Role> role = new DropDownChoice<Role>("role",
				new PropertyModel<Role>(user, "role"),
		Arrays.asList(Role.values()));
		role.setNullValid(true);
		form.add(role);

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
