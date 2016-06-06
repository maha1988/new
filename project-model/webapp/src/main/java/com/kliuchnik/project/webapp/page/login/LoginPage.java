package com.kliuchnik.project.webapp.page.login;

import java.util.Calendar;

import org.apache.wicket.Application;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
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

import com.kliuchnik.project.webapp.component.localization.LanguageSelectionComponent;
import com.kliuchnik.project.webapp.component.menu.MenuPanel;

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
        
        
        // if already logged then should not see login page at all
        if (AuthenticatedWebSession.get().isSignedIn()) {
            setResponsePage(Application.get().getHomePage());
        }
        add(new LanguageSelectionComponent("language-select"));
        
        final Form<Void> form = new Form<Void>(ID_FORM);
        form.setDefaultModel(new CompoundPropertyModel<LoginPage>(this));
        form.add(new RequiredTextField<String>("username"));
        form.add(new PasswordTextField("password"));

        form.add(new SubmitLink("submit-btn") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                if (Strings.isEmpty(username) || Strings.isEmpty(password)) {
                    return;
                }
                final boolean authResult = AuthenticatedWebSession.get().signIn(username, password);
                if (authResult) {
                    // continueToOriginalDestination();
                    setResponsePage(Application.get().getHomePage());
                } else {
                	error(getString("loging.err.auth"));
                }
            }
        });

       
        add(form);

        add(new FeedbackPanel("feedbackpanel"));
       
	      
        
    }    
       
    }


