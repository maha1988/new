package com.kliuchnik.project.webapp.page.sklad;


import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.WindowClosedCallback;
import org.apache.wicket.markup.html.form.Form;


import com.kliuchnik.project.datamodel.Sklad;
import com.kliuchnik.project.webapp.page.AbstractPage;
import com.kliuchnik.project.webapp.page.sklad.panel.SkladListPanel;

@AuthorizeInstantiation(value = {"ADMIN",	"CUSTOMER"})
public class SkladPage extends AbstractPage {
	 @Override
	    protected void onInitialize() {
	        super.onInitialize();

	        SkladListPanel skladListPanel = new SkladListPanel("list");
	        skladListPanel.setOutputMarkupId(true);
	        add(skladListPanel);

	        addModalWindow(skladListPanel);

	        // addSearchComponents(attributesListPanel);
	    }
	 
	 private void addSearchComponents(SkladListPanel skladListPanel) {
	        Form form = new Form("searchForm");
	        add(form);

	        form.add(new AjaxSubmitLink("search-link") {
	            @Override
	            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
	                super.onSubmit(target, form);
	                target.add(skladListPanel);
	            }
	        });

	    }
	 
	 private void addModalWindow(SkladListPanel skladListPanel) {
	        ModalWindow modalWindow = new ModalWindow("modal");
	        add(modalWindow);

	        add(new AjaxLink("create") {
	            @Override
	            public void onClick(AjaxRequestTarget target) {
	                modalWindow.setContent(new SkladEditPanel(modalWindow, new Sklad()));
	                modalWindow.show(target);
	            }
	        });

	        modalWindow.setWindowClosedCallback(new WindowClosedCallback() {

	            @Override
	            public void onClose(AjaxRequestTarget target) {
	                target.add(skladListPanel);

	            }
	        });
	    }
	}
	 
	 
	 
//	public SkladPage() {
//		super();
//		add(new SkladListPanel("panel"));
//
//		add(new Link("create") {
//			@Override
//			public void onClick() {
//				setResponsePage(new SkladEditPanel(new Sklad()));
//			}
//		});
//	}
//}
