package com.kliuchnik.project.webapp.page.login;


import java.util.Calendar;

import org.apache.wicket.Application;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.string.Strings;
import com.googlecode.wicket.kendo.ui.widget.notification.Notification;
import com.kliuchnik.project.webapp.component.localization.LanguageSelectionComponent;
import com.kliuchnik.project.webapp.component.menu.MenuPanel;
import com.kliuchnik.project.webapp.page.user.UsersEditPanel;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.WindowClosedCallback;
/**
 * 
 * 
 * @since Mar 11, 2014
 */
public class LoginPage extends WebPage {

    public static final String ID_FORM = "form";

    private String username;
    private String password;

    @Override
    protected void onInitialize() {
        super.onInitialize();
       
        final Notification notification = new Notification("notification");
        add(new LanguageSelectionComponent("language-select"));
		// if already logged then should not see login page at all
		if (AuthenticatedWebSession.get().isSignedIn()) {
			setResponsePage(Application.get().getHomePage());
		}

		final Form<Void> form = new Form<Void>(ID_FORM);
		FeedbackPanel feedBackPanel = new FeedbackPanel("feedbackpanel");
		feedBackPanel.setOutputMarkupId(true);
		feedBackPanel.setVisible(false);
		form.add(feedBackPanel);
		form.setDefaultModel(new CompoundPropertyModel<LoginPage>(this));
		form.add(new RequiredTextField<String>("username").setRequired(false));
		form.add(new PasswordTextField("password").setRequired(false));
		form.add(notification);
		form.add(new AjaxSubmitLink("submit-btn") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				if (Strings.isEmpty(username) || Strings.isEmpty(password)) {
					notification.error(target, getString("login.error1"));
					return;
				}
				final boolean authResult = AuthenticatedWebSession.get().signIn(username, password);
				if (authResult) {
					// continueToOriginalDestination();
					setResponsePage(Application.get().getHomePage());
				} else {
					error(getString("loging.err.auth"));
					notification.error(target, getString("login.error2"));
				}
			}
		});

		add(form);
        
		final ModalWindow modal1;
		add(modal1 = new ModalWindow("modal1"));
		modal1.setCssClassName("modal_window");
		modal1.setInitialHeight(500);
		modal1.setResizable(false);
		modal1.setTitle("Карточка пользователя");
		this.setOutputMarkupId(true);
		add(new AjaxLink<Void>("newUser") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				modal1.setContent(new UsersEditPanel(modal1));
				modal1.show(target);
			}
		});

		modal1.setWindowClosedCallback(new WindowClosedCallback() {

			@Override
			public void onClose(AjaxRequestTarget target) {
				target.add(LoginPage.this);

			}
		});
       
	      
        
    }    
       
    }


