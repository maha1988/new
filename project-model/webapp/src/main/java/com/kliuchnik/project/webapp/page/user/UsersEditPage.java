package com.kliuchnik.project.webapp.page.user;

import java.util.Arrays;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import com.kliuchnik.project.datamodel.Customer;
import com.kliuchnik.project.datamodel.Role;
import com.kliuchnik.project.datamodel.Sklad;
import com.kliuchnik.project.datamodel.User;
import com.kliuchnik.project.service.UserService;
import com.kliuchnik.project.webapp.page.AbstractPage;
import com.kliuchnik.project.webapp.page.common.UserRoleChoiceRenderer;

public class UsersEditPage extends AbstractPage {

	@Inject
	private UserService userService;

	private Customer customer;
	private User user;

	 public UsersEditPage(Customer customer) {
	        super();
	        this.user = userService.getUser(user.getId());
	    }
	 public UsersEditPage(User user) {
			super();
			this.user = user;
		}


	@Override
	protected void onInitialize() {
		super.onInitialize();

		 Form<User> form = new Form<User>("form", new CompoundPropertyModel<User>(user));
	        add(form);

		TextField<String> nameField = new TextField<>("name");
		nameField.setRequired(true);
		form.add(nameField);

		TextField<String> passwordField = new TextField<>("password");
		passwordField.setRequired(true);
		form.add(passwordField);

		DropDownChoice<Role> roleField = new DropDownChoice<>("role", Arrays.asList(Role.values()),
				UserRoleChoiceRenderer.INSTANCE);
		roleField.setRequired(true);
		form.add(roleField);

		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				userService.update(user);
				setResponsePage(new UsersPage());
			}
		});

		add(new FeedbackPanel("feedback"));
	}

}
