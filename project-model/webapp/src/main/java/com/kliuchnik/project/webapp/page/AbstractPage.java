package com.kliuchnik.project.webapp.page;

import java.util.Calendar;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.kliuchnik.project.webapp.component.menu.MenuPanel;
import com.kliuchnik.project.webapp.component.menu.MenuPanelLoggedUser;
import com.kliuchnik.project.webapp.page.product.ProductsPage;

public abstract class AbstractPage extends WebPage {

	 public AbstractPage() {
	        super();
	    }

	    public AbstractPage(PageParameters parameters) {
	        super(parameters);
	    }

	    @Override
	    protected void onInitialize() {
	        super.onInitialize();

	        if (getPage().getClass().equals(ProductsPage.class)) {
	            add(new MenuPanelLoggedUser("menu-panel"));
	        } else {
	            add(new MenuPanel("menu-panel"));
	        }
	        AbstractReadOnlyModel<Integer> yearModel = new AbstractReadOnlyModel<Integer>() {
	            @Override
	            public Integer getObject() {
	                return Calendar.getInstance().get(Calendar.YEAR);
	            }
	        };

//	        WebMarkupContainer footer = new WebMarkupContainer("footer");
//	        add(footer);
//	        footer.add(new Label("current-year", yearModel));
//	        footer.add(AttributeModifier.append("onclick", "alert('Im clicked')"));
	    }
	    

	}