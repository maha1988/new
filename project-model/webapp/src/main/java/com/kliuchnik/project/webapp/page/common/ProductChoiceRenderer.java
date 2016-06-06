package com.kliuchnik.project.webapp.page.common;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import com.kliuchnik.project.datamodel.Product;

public class ProductChoiceRenderer extends ChoiceRenderer<Product> {

    public static final ProductChoiceRenderer INSTANCE = new ProductChoiceRenderer();

    private ProductChoiceRenderer() {
        super();
    }

    @Override
    public Object getDisplayValue(Product object) {
        return String.format("%s:%s", object.getProductName(), object.getPrice());
    }

    @Override
    public String getIdValue(Product object, int index) {
        return String.valueOf(object.getId());
    }
}
