package com.kliuchnik.project.webapp.page.product;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.WindowClosedCallback;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import com.kliuchnik.project.datamodel.Product;
import com.kliuchnik.project.webapp.page.AbstractPage;
import com.kliuchnik.project.webapp.page.product.panel.ProductListPanel;
@AuthorizeInstantiation(value = {"ADMIN",	"CUSTOMER"})

public class ProductsPage extends AbstractPage {
	
	 @Override
	    protected void onInitialize() {
	        super.onInitialize();

	        ProductListPanel productListPanel = new ProductListPanel("panel");
	        
	        productListPanel.setOutputMarkupId(true);
	        add(productListPanel);

	        addModalWindow(productListPanel);

	        // addSearchComponents(attributesListPanel);
	    }
	 
	 private void addSearchComponents(ProductListPanel productListPanel) {
	        Form form = new Form("searchForm");
	        add(form);

	        form.add(new AjaxSubmitLink("search-link") {
	            @Override
	            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
	                super.onSubmit(target, form);
	                target.add(productListPanel);
	            }
	        });

	    }
	 
	 	            
	 		 
	 
	 
	 private void addModalWindow(ProductListPanel productListPanel) {
	      
		 
		 final ModalWindow modal1 = new ModalWindow("modal1");
			modal1.setCssClassName("modal_window");
			modal1.setInitialHeight(500);
			modal1.setResizable(false);
			modal1.setWindowClosedCallback(new WindowClosedCallback() {

				@Override
				public void onClose(AjaxRequestTarget target) {
					target.add(ProductsPage.this);

				}
			});
			this.setOutputMarkupId(true);
			add(modal1);
			modal1.setTitle("Карточка продукта");
	        

	        add(new AjaxLink("create") {
	            @Override
	            public void onClick(AjaxRequestTarget target) {
	            	modal1.setContent(new ProductEditPanel(modal1, new Product()));
	            	modal1.show(target);
	            }
	        });
	        
	               

	        modal1.setWindowClosedCallback(new WindowClosedCallback() {

	            @Override
	            public void onClose(AjaxRequestTarget target) {
	                target.add(productListPanel);

	            }
	        });
	        add(new FeedbackPanel("feedback"));
	    }
	}
	 
