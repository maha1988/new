package com.kliuchnik.project.webapp.page.product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;

import com.googlecode.wicket.kendo.ui.widget.notification.Notification;
import com.kliuchnik.project.dataaccess.filters.SkladFilter;
import com.kliuchnik.project.datamodel.Product;
import com.kliuchnik.project.datamodel.Sklad;
import com.kliuchnik.project.datamodel.Unit;
import com.kliuchnik.project.service.ProductService;
import com.kliuchnik.project.service.SkladService;
import com.kliuchnik.project.webapp.page.common.SkladChoiceRenderer;
import com.kliuchnik.project.webapp.page.common.UnitChoiceRender;
import com.kliuchnik.project.webapp.page.product.panel.ProductListPanel;

public class ProductEditPanel extends Panel {

	@Inject
	private ProductService productService;
	@Inject
	private SkladService skladService;
	private static final long serialVersionUID = 1L;
	private Product product;
	
	private Sklad sklad;
	
	private ModalWindow modalWindow;

	public ProductEditPanel(String id, Product product,ModalWindow modalWindow) {
		super(id);
		this.product = product;
		this.modalWindow = modalWindow;
	}

	public ProductEditPanel(ModalWindow modalWindow, Product product) {
        super(modalWindow.getContentId());
        this.product = product;
        this.modalWindow = modalWindow;
    }

	@Override
	protected void onInitialize() {
		super.onInitialize();
//		if (product != null) {
//			add(new ProductEditPanel("panel", product, ProductsPage.this));
//		} else {
//			add(new ProductEditPanel("panel", ProductsPage.this));
//	}
		final Notification notification = new Notification("notification");
		this.add(notification);
		Form <Product> form = new Form <>("form", new CompoundPropertyModel<>(product));
		add(form);
		FeedbackPanel feedBackPanel = new FeedbackPanel("feedback");
		feedBackPanel.setVisible(false);
		form.add(feedBackPanel);
		
		
		TextField<String> nameField = new TextField<>("productName");
		nameField.setRequired(true);
		nameField.add(StringValidator.maximumLength(100));
		
		form.add(nameField);
		
		TextField<BigDecimal> priceFild = new TextField<BigDecimal>("price");
		priceFild.setRequired(true);
		priceFild.add(RangeValidator.<BigDecimal> range(new BigDecimal(0), new BigDecimal(1_000_000_000_000_000.00)));
		form.add(priceFild);
		
		TextField<Long> currentQuantityField = new TextField<>("currentQuantity");
		currentQuantityField.add(RangeValidator.<Long> range(0l, 1_000_000l));
		currentQuantityField.setRequired(true);
		form.add(currentQuantityField);
				
		
		
		DropDownChoice<Unit> unit = new DropDownChoice<Unit>("unit",
		new PropertyModel<Unit>(product, "unit" ), 
		Arrays.asList(Unit.values()));
		unit.setNullValid(true);
		form.add(unit);
		
		
		 List<Sklad> allSklad = skladService.find(new SkladFilter());
	     form.add(new DropDownChoice<>("sklad", allSklad, SkladChoiceRenderer.INSTANCE));
		
		
		
		
	     form.add(new AjaxSubmitLink("save") {
	            @Override
	            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
	                super.onSubmit(target, form);
	                productService.saveOrUpdate(product);
	                
	                ProductsPage page = new ProductsPage();
	                String localizedMessage = getString("product.saved");
	                page.info(localizedMessage);

	                setResponsePage(page);
	                
	                modalWindow.close(target);
	            }
	        });
	     form.add(new AjaxLink("cancel") {
	            @Override
	            public void onClick(AjaxRequestTarget target) {
	                modalWindow.close(target);
	            }
	        });

		
	}
}
