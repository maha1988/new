package com.kliuchnik.project.webapp.page.product;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.kliuchnik.project.datamodel.Product;
import com.kliuchnik.project.webapp.page.AbstractPage;

public class ProductDetailsPage  extends AbstractPage {

	    public ProductDetailsPage(PageParameters parameters) {
	        super(parameters);
	    }

	    public ProductDetailsPage(Product product) {
	        super();

	    }

	}

