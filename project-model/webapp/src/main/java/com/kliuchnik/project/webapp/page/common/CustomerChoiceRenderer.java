package com.kliuchnik.project.webapp.page.common;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import com.kliuchnik.project.datamodel.Customer;


public class CustomerChoiceRenderer extends ChoiceRenderer<Customer> {

    public static final CustomerChoiceRenderer INSTANCE = new CustomerChoiceRenderer();

    private CustomerChoiceRenderer() {
        super();
    }

    @Override
    public Object getDisplayValue(Customer object) {
        return object.getLastN();
    }

    @Override
    public String getIdValue(Customer object, int index) {
        return String.valueOf(object.getId());
    }
}
