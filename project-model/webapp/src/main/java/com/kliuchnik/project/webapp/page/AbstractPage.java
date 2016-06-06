package com.kliuchnik.project.webapp.page;

import java.util.Calendar;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.kliuchnik.project.webapp.app.AuthorizedSession;
import com.kliuchnik.project.webapp.component.localization.LanguageSelectionComponent;
import com.kliuchnik.project.webapp.component.menu.MenuCustomer;
import com.kliuchnik.project.webapp.component.menu.MenuPanel;

public abstract class AbstractPage extends WebPage {
	boolean admin = AuthorizedSession.get().getRoles().contains("ADMIN");
	boolean customer = AuthorizedSession.get().getRoles().contains("CUSTOMER");

	 public AbstractPage() {
	        super();
	    }

	    public AbstractPage(PageParameters parameters) {
	        super(parameters);
	    }

	    

		public AbstractPage(String contentId) {
			
		}

		@Override
	    protected void onInitialize() {
	        super.onInitialize();
	        add(new LanguageSelectionComponent("language-select"));

	        if (admin  ) {
				add(new MenuPanel("menu-panel"));
			} else  {
				
				add(new MenuCustomer("menu-panel"));
			}
	        
	       
	        
	                
	        AbstractReadOnlyModel<Integer> yearModel = new AbstractReadOnlyModel<Integer>() {
	            @Override
	            public Integer getObject() {
	                return Calendar.getInstance().get(Calendar.YEAR);
	            }
	        };

	        WebMarkupContainer footer = new WebMarkupContainer("footer");
	        add(footer);
	        footer.add(new Label("current-year", yearModel));
	       // footer.add(AttributeModifier.append("onclick", "alert('Im clicked')"));
	    }
	      

	}