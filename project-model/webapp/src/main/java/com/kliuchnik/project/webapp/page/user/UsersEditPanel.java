package com.kliuchnik.project.webapp.page.user;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;

import com.kliuchnik.project.dataaccess.filters.SkladFilter;
import com.kliuchnik.project.dataaccess.filters.UserFilter;
import com.kliuchnik.project.datamodel.Customer;
import com.kliuchnik.project.datamodel.Product;
import com.kliuchnik.project.datamodel.Role;
import com.kliuchnik.project.datamodel.Sklad;
import com.kliuchnik.project.datamodel.Unit;
import com.kliuchnik.project.datamodel.User;
import com.kliuchnik.project.service.ProductService;
import com.kliuchnik.project.service.SkladService;
import com.kliuchnik.project.service.UserService;
import com.kliuchnik.project.webapp.page.AbstractPage;
import com.kliuchnik.project.webapp.page.common.SkladChoiceRenderer;
import com.kliuchnik.project.webapp.page.common.UserRoleChoiceRenderer;
import com.kliuchnik.project.webapp.page.login.LoginPage;
import com.kliuchnik.project.webapp.page.product.ProductsPage;

public class UsersEditPanel extends Panel {
	private static final long serialVersionUID = 1L;
	@Inject
	private UserService userService;
	private Customer customer;
	private User user;
	private ModalWindow modal1;
	private UserFilter userFilter;

	public UsersEditPanel(ModalWindow modal1) {
		super(modal1.getContentId());
		user = new User();
		customer = new Customer();

		this.modal1 = modal1;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		userFilter = new UserFilter();
		Form<User> form = new Form<User>("form", new CompoundPropertyModel<User>(user));
		add(form);
		FeedbackPanel feedBackPanel = new FeedbackPanel("feedback");
		feedBackPanel.setVisible(false);
		form.add(feedBackPanel);

		TextField<String> nameField = new TextField<String>("name", new PropertyModel<>(user, "name"));
		nameField.setRequired(true);
		nameField.add(StringValidator.maximumLength(100));
		nameField.add(StringValidator.minimumLength(2));
		form.add(nameField);

		TextField<String> passwordField = new TextField<>("password", new PropertyModel<>(user, "password"));
		passwordField.setRequired(true);
		passwordField.add(StringValidator.maximumLength(100));
		passwordField.add(StringValidator.minimumLength(3));
		form.add(passwordField);

		DropDownChoice<Role> role = new DropDownChoice<>("role", new PropertyModel<>(user, "role"),
				Arrays.asList(Role.values()));
		role.setRequired(true);
		form.add(role);

		TextField<String> firstNField = new TextField<String>("firstN", new PropertyModel<>(customer, "firstN"));
		firstNField.add(new AjaxFormComponentUpdatingBehavior("change") {
			@Override
			protected void onUpdate(AjaxRequestTarget target) {

			}
		});
		firstNField.setRequired(true);
		firstNField.add(StringValidator.maximumLength(100));
		firstNField.add(StringValidator.minimumLength(2));
		firstNField.add(new PatternValidator("[А-Яа-я]+"));
		form.add(firstNField);

		TextField<String> lastNField = new TextField<String>("lastN", new PropertyModel<>(customer, "lastN"));
		lastNField.add(new AjaxFormComponentUpdatingBehavior("change") {
			@Override
			protected void onUpdate(AjaxRequestTarget target) {

			}
		});
		lastNField.setRequired(true);
		lastNField.add(StringValidator.maximumLength(100));
		lastNField.add(StringValidator.minimumLength(2));
		lastNField.add(new PatternValidator("[А-Яа-я]+"));
		form.add(lastNField);

		TextField<String> addressField = new TextField<String>("address", new PropertyModel<>(customer, "address"));
		addressField.add(new AjaxFormComponentUpdatingBehavior("change") {
			@Override
			protected void onUpdate(AjaxRequestTarget target) {

			}
		});
		addressField.setRequired(true);
		addressField.add(StringValidator.maximumLength(100));
		addressField.add(StringValidator.minimumLength(2));
		addressField.add(new PatternValidator("[А-Яа-я]+"));
		form.add(addressField);

		TextField<String> bankRField = new TextField<String>("bankR", new PropertyModel<>(customer, "bankR"));
		bankRField.add(new AjaxFormComponentUpdatingBehavior("change") {
			@Override
			protected void onUpdate(AjaxRequestTarget target) {

			}
		});
		bankRField.setRequired(true);
		bankRField.add(StringValidator.maximumLength(100));
		bankRField.add(StringValidator.minimumLength(2));
		bankRField.add(new PatternValidator("[А-Яа-я]+"));
		form.add(bankRField);

		form.add(new AjaxSubmitLink("save") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				if (user.getId() == null) {

					userService.saveOrUpdate(user);
					userService.saveOrUpdate(customer);
				} else {
					userService.update(user);
					userService.update(customer);
				}
				modal1.close(target);
				setResponsePage(new UsersPage());
			}
		});

		form.add(new AjaxLink("cancel") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				modal1.close(target);
			}
		});

	}
}
