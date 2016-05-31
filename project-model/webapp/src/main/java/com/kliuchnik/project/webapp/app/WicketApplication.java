package com.kliuchnik.project.webapp.app;

import javax.inject.Inject;

import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AnnotationsRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.kliuchnik.project.webapp.page.customer.CustomerEditPanel;
import com.kliuchnik.project.webapp.page.home.HomePage;
import com.kliuchnik.project.webapp.page.login.LoginPage;
import com.kliuchnik.project.webapp.page.order.OrderEditPanel;
import com.kliuchnik.project.webapp.page.product.ProductEditPanel;
import com.kliuchnik.project.webapp.page.sklad.SkladEditPanel;

@Component("wicketWebApplicationBean")
public class WicketApplication extends AuthenticatedWebApplication {
	@Inject
	private ApplicationContext applicationContext;

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();
		getMarkupSettings().setStripWicketTags(true);
		// add your configuration here
		getComponentInstantiationListeners().add(new SpringComponentInjector(this, getApplicationContext()));
		getSecuritySettings().setAuthorizationStrategy(new AnnotationsRoleAuthorizationStrategy(this));
		// mount
	//	mountPage("/productDetails", ProductEditPanel.class);
		
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return AuthorizedSession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends Page> getHomePage() {

		return HomePage.class;
	}

}