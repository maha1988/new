package com.kliuchnik.project.webapp.page.product;

import javax.inject.Inject;

import org.apache.wicket.markup.html.link.Link;

import com.kliuchnik.project.service.ProductService;
import com.kliuchnik.project.webapp.page.AbstractPage;
import com.kliuchnik.project.webapp.page.home.HomePage;
import com.kliuchnik.project.webapp.page.product.panel.ProductListPanel;

public class ProductsPage extends AbstractPage {
	@Inject
    private ProductService productService; 
	
	    public ProductsPage() {
	        super();
	        
			
			add(new ProductListPanel("panel"));

	       
	    }
}
