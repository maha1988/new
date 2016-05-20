package com.kliuchnik.project.webapp.page.product.panel;

import java.io.Serializable;
import java.util.Iterator;

import javax.inject.Inject;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
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


public class ProductListPanel extends Panel {

    @Inject
    private ProductService productService;

    public ProductListPanel(String id) {
        super(id);
        ProductsDataProvider productsDataProvider = new ProductsDataProvider();
        DataView<Product> dataView = new DataView<Product>("rows", productsDataProvider, 5) {
            @Override
            protected void populateItem(Item<Product> item) {
                Product product = item.getModelObject();

                item.add(new Label("id", product.getId()));
                item.add(new Label("name", product.getProductName()));
                item.add(new Label("price", product.getPrice()));
                item.add(new Label("currentQuantity", product.getCurrentQuantity()));
                item.add(new Label("unit", product.getUnit()));
              
                if (product.getSklad()!= null  ){
                item.add(new Label("sklad", product.getSklad().getId()));
                	}
                else {
                	item.add(new Label("sklad", 0L));
                }
                
            //    CheckBox checkbox = new CheckBox("active", Model.of(product.getActive()));
             //   checkbox.setEnabled(false);
              //  item.add(checkbox);
            }
        };
        add(dataView);
        add(new PagingNavigator("paging", dataView));

        add(new OrderByBorder("sort-id", Product_.id, productsDataProvider));
        add(new OrderByBorder("sort-name", Product_.productName, productsDataProvider));
        add(new OrderByBorder("sort-price", Product_.price, productsDataProvider));
        add(new OrderByBorder("sort-currentQuantity", Product_.currentQuantity, productsDataProvider));
        add(new OrderByBorder("sort-unit", Product_.unit, productsDataProvider));
        add(new OrderByBorder("sort-sklad", Product_.sklad, productsDataProvider));
    }

    private class ProductsDataProvider extends SortableDataProvider<Product, Serializable> {

        private ProductFilter productFilter;

        public ProductsDataProvider() {
            super();
            productFilter = new ProductFilter();
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
        
   
    
