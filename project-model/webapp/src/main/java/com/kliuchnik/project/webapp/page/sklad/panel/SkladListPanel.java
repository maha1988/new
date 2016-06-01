package com.kliuchnik.project.webapp.page.sklad.panel;

import java.io.Serializable;
import java.util.Iterator;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.kliuchnik.project.dataaccess.filters.SkladFilter;
import com.kliuchnik.project.datamodel.Sklad;
import com.kliuchnik.project.datamodel.Sklad_;
import com.kliuchnik.project.service.SkladService;
import com.kliuchnik.project.webapp.app.AuthorizedSession;
import com.kliuchnik.project.webapp.page.customer.CustomerEditPanel;
import com.kliuchnik.project.webapp.page.product.ProductsPage;
import com.kliuchnik.project.webapp.page.sklad.SkladEditPanel;
import com.kliuchnik.project.webapp.page.sklad.SkladPage;


public class SkladListPanel extends Panel {
	boolean customer = AuthorizedSession.get().getRoles().contains("CUSTOMER");
    @Inject
    private SkladService skladService;

    public SkladListPanel(String id) {
        super(id);

        SkladDataProvider skladDataProvider = new SkladDataProvider();
        DataView<Sklad> dataView = new DataView<Sklad>("skladlist", skladDataProvider, 10) {
            @Override
            protected void populateItem(Item<Sklad> item) {
                Sklad sklad = item.getModelObject();

                item.add(new Label("id", sklad.getId()));
                item.add(new Label("name", sklad.getName()));
              //  item.add(new Label("products", new Model(sklad.getProducts());
            
                
                Link edit = new Link("edit-link", item.getModel()) {

                    // item.add(new Link<Void>("edit-link") {
                         @Override
                         public void onClick() {
                       //  setResponsePage(new PSkladEditPanel(id, sklad));
                         }
                     };
                     item.add(edit);
                
                
                Link delete = new Link("delete-link", item.getModel()) {

                    //  item.add(new Link<Void>("delete-link") {
                          @Override
                          public void onClick() {
                              try {
                            	  skladService.delete(sklad);
                              } catch (PersistenceException e) {
                                  System.out.println("caughth persistance exception");
                              }

                              setResponsePage(new SkladPage());
                          }
                      };
                      item.add(delete);
                      if (customer) {
                      	delete.setVisible(false);
      					
      				}

                  }
                      };
               
 
              
        add(dataView);
        add(new PagingNavigator("paging", dataView));

        add(new OrderByBorder("sort-id", Sklad_.id, skladDataProvider));
        add(new OrderByBorder("sort-name", Sklad_.name, skladDataProvider));
    //   add(new OrderByBorder("sort-products", Sklad_.products, skladDataProvider));
       
       
    }

    private class SkladDataProvider extends SortableDataProvider<Sklad, Serializable> {

        private SkladFilter skladFilter;

        public SkladDataProvider() {
            super();
            skladFilter = new SkladFilter();
            setSort((Serializable) Sklad_.name, SortOrder.ASCENDING);
        }

        @Override
        public Iterator<Sklad> iterator(long first, long count) {
            Serializable property = getSort().getProperty();
            SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

            skladFilter.setSortProperty((SingularAttribute) property);
            skladFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

            skladFilter.setLimit((int) count);
            skladFilter.setOffset((int) first);
            return skladService.find(skladFilter).iterator();
        }

        @Override
        public long size() {
            return skladService.count(skladFilter);
        }

        @Override
        public IModel<Sklad> model(Sklad object) {
            return new Model( object);
        }

    }
    }
    
