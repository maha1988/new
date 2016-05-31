package com.kliuchnik.project.webapp.page.order;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.RangeValidator;

import com.kliuchnik.project.dataaccess.filters.CustomerFilter;
import com.kliuchnik.project.dataaccess.filters.SkladFilter;
import com.kliuchnik.project.datamodel.Customer;
import com.kliuchnik.project.datamodel.Order;
import com.kliuchnik.project.datamodel.Sklad;
import com.kliuchnik.project.service.OrderService;
import com.kliuchnik.project.service.UserService;
import com.kliuchnik.project.webapp.page.common.CustomerChoiceRenderer;
import com.kliuchnik.project.webapp.page.common.SkladChoiceRenderer;

public class OrderEditPanel extends Panel {

	@Inject
	private OrderService orderService;
	@Inject
	private UserService userService;

	private Order order;
	private ModalWindow modalWindow;
	
	public OrderEditPanel(ModalWindow modalWindow, Order order) {
        super(modalWindow.getContentId());
        this.order = order;
        this.modalWindow = modalWindow;
    }

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form form = new Form("form", new CompoundPropertyModel<Order>(order));
		add(form);

		List<Customer> allCustomer = userService.find(new CustomerFilter());
	     form.add(new DropDownChoice<>("customer", allCustomer, CustomerChoiceRenderer.INSTANCE));
		
		
		

		TextField<BigDecimal> sumField = new TextField<>("sum");
		sumField.add(RangeValidator.<BigDecimal> range(new BigDecimal(0),new BigDecimal(1_000_000_000_000_000.00)));
		sumField.setRequired(true);
		form.add(sumField);
		
		

		DateTextField dateField = new DateTextField("date");
		dateField.add(new DatePicker());
		dateField.setRequired(true);
		form.add(dateField);
		



		  form.add(new AjaxSubmitLink("save") {
	            @Override
	            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
	                super.onSubmit(target, form);
	                orderService.saveOrUpdate(order);
	                modalWindow.close(target);
	            }
	        });
	     form.add(new AjaxLink("cancel") {
	            @Override
	            public void onClick(AjaxRequestTarget target) {
	                modalWindow.close(target);
	            }
	        });
}}
