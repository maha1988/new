package com.kliuchnik.project.webapp.page.product.panel;

import java.io.Serializable;
import java.util.Iterator;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.kliuchnik.project.dataaccess.filters.ProductFilter;
import com.kliuchnik.project.datamodel.Product;
import com.kliuchnik.project.datamodel.Product_;
import com.kliuchnik.project.service.ProductService;
import com.kliuchnik.project.webapp.app.AuthorizedSession;
import com.kliuchnik.project.webapp.page.product.ProductsPage;


public class ProductListPanel extends Panel {
	boolean customer = AuthorizedSession.get().getRoles().contains("CUSTOMER");
    @Inject
    private ProductService productService;
    
    public ProductListPanel(String id, Product product,ModalWindow modalWindow) {
		super(id);
		
    }
    public ProductListPanel(String id) {
        super(id);

        
        ProductsDataProvider productsDataProvider = new ProductsDataProvider();
        DataView<Product> dataView = new DataView<Product>("rows", productsDataProvider, 10) {
            @Override
            protected void populateItem(Item<Product> item) {
                Product product = item.getModelObject();

                item.add(new Label("id", product.getId()));
                item.add(new Label("productName", product.getProductName()));
                item.add(new Label("currentQuantity", product.getCurrentQuantity()));
                item.add(new Label("unit", product.getUnit()));
                item.add(new Label("price", product.getPrice()));
                          
                if(product.getSklad() == null){
                	 item.add(new Label("sklad", "  "));
                }
                else {
                	 item.add(new Label("sklad", product.getSklad().getName()));
                }
               
               
                 
                Link edit = new Link("edit-link", item.getModel()) {

             	// item.add(new Link<Void>("edit-link") {
                    @Override
                    public void onClick() {
                  
				//	setResponsePage( new ProductEditPanel(id, product, modalWindow));
                    }
                };
                item.add(edit);
                
                Link delete = new Link("delete-link", item.getModel()) {

              //  item.add(new Link<Void>("delete-link") {
                    @Override
                    public void onClick() {
                        try {
                            productService.delete(product);
                        } catch (PersistenceException e) {
                            System.out.println("caughth persistance exception");
                        }

                        setResponsePage(new ProductsPage());
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

        add(new OrderByBorder("sort-id", Product_.id, productsDataProvider));
        add(new OrderByBorder("sort-productName", Product_.productName, productsDataProvider));
        add(new OrderByBorder("sort-currentQuantity", Product_.currentQuantity, productsDataProvider));
        add(new OrderByBorder("sort-unit", Product_.unit, productsDataProvider));
        add(new OrderByBorder("sort-price", Product_.price, productsDataProvider));
        add(new OrderByBorder("sort-sklad", Product_.sklad, productsDataProvider));
    }
   
    private class ProductsDataProvider extends SortableDataProvider<Product, Serializable> {

        private ProductFilter productFilter;

        public ProductsDataProvider() {
            super();
            productFilter = new ProductFilter();
            productFilter.setFetchSklad(true);
            productFilter.setFetchOrder (true);            
            setSort((Serializable) Product_.productName, SortOrder.ASCENDING);
        }

        @Override
        public Iterator<Product> iterator(long first, long count) {
            Serializable property = getSort().getProperty();
            SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

            productFilter.setSortProperty((SingularAttribute) property);
            productFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

            productFilter.setLimit((int) count);
            productFilter.setOffset((int) first);
            return productService.find(productFilter).iterator();
        }

        @Override
        public long size() {
            return productService.count(productFilter);
        }

        @Override
        public IModel<Product> model(Product object) {
            return new Model(object);
        }

    }

}
