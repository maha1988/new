package com.kliuchnik.project.webapp.page.user;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
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
import com.kliuchnik.project.webapp.page.product.ProductsPage;

public class UsersEditPanel extends Panel {
	private static final long serialVersionUID = 1L;
	@Inject
	private UserService userService;
	private User user;
	private ModalWindow modalWindow;
    private UserFilter userFilter;
	
    public UsersEditPanel( User user, String id) {
        super(id);
        this.user = user;
        
        
    }
	
	public UsersEditPanel(ModalWindow modalWindow, User user) {
			super(modalWindow.getContentId());
			this.user = user;
			this.modalWindow = modalWindow;
		}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		userFilter= new UserFilter();
		Form  form = new Form ("form", new CompoundPropertyModel<>(user));
		add(form);
		
		TextField<String> nameField = new TextField<String>("name",
			new PropertyModel<>(user, "name"));
		nameField.setRequired(true);
		nameField.add(StringValidator.maximumLength(100));
		nameField.add(StringValidator.minimumLength(2));
		form.add(nameField);
		
		TextField<String> passwordField = new TextField<>("password",
			new PropertyModel<>(user, "password"));
		passwordField.setRequired(true);
		passwordField.add(StringValidator.maximumLength(100));
		form.add(passwordField);

		
		DropDownChoice<Role> role = new DropDownChoice<>("role",new PropertyModel<>(user, "role"),  Arrays.asList(Role.values()));
		role.setRequired(true);
		form.add(role);
		
		
	     form.add(new AjaxSubmitLink("save") {
	            @Override
	            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
	                super.onSubmit(target, form);
	                userService.saveOrUpdate(user);
	                
	                UsersPage page = new UsersPage();
	                String localizedMessage = getString("user.saved");
	                page.info(localizedMessage);

	                setResponsePage(page);
	                
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
