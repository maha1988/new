package com.kliuchnik.project.webapp.page.customer.panel;

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

import com.kliuchnik.project.dataaccess.filters.CustomerFilter;
import com.kliuchnik.project.dataaccess.filters.ProductFilter;
import com.kliuchnik.project.dataaccess.filters.SkladFilter;
import com.kliuchnik.project.datamodel.Customer;
import com.kliuchnik.project.datamodel.Customer_;
import com.kliuchnik.project.datamodel.Product;
import com.kliuchnik.project.datamodel.Product_;
import com.kliuchnik.project.datamodel.Sklad;
import com.kliuchnik.project.datamodel.Sklad_;
import com.kliuchnik.project.service.ProductService;
import com.kliuchnik.project.service.SkladService;
import com.kliuchnik.project.service.UserService;


public class CustomerListPanel extends Panel {

    @Inject
    private UserService userService;

    public CustomerListPanel(String id) {
        super(id);

      CustomerDataProvider customerDataProvider = new CustomerDataProvider();
        DataView<Customer> dataView = new DataView<Customer>("customerlist", customerDataProvider, 5) {
            @Override
            protected void populateItem(Item<Customer> item) {
                Customer customer = item.getModelObject();

                item.add(new Label("id", customer.getId()));
                item.add(new Label("address", customer.getAddress()));
                item.add(new Label("bank",customer.getBankR() ));
               
                                      
                               
              //  item.add(DateLabel.forDatePattern("created", Model.of(product.getCreated()), "dd-MM-yyyy"));

   //             CheckBox checkbox = new CheckBox("active", Model.of(product.getActive()));
     //           checkbox.setEnabled(false);
         //       item.add(checkbox);
            }
        };
        add(dataView);
        add(new PagingNavigator("paging", dataView));

        add(new OrderByBorder("sort-id", Customer_.id, customerDataProvider));
        add(new OrderByBorder("sort-address", Customer_.address, customerDataProvider));
        add(new OrderByBorder("sort-bank", Customer_.bankR, customerDataProvider));
       
        
       // add(new OrderByBorder("sort-products", Sklad_.products, skladDataProvider));
       
       
    }

    private class CustomerDataProvider extends SortableDataProvider<Customer, Serializable> {

        private CustomerFilter customerFilter;

        public CustomerDataProvider() {
            super();
            customerFilter = new CustomerFilter();
            setSort((Serializable) Customer_.address, SortOrder.ASCENDING);
        }

        @Override
        public Iterator<Customer> iterator(long first, long count) {
            Serializable property = getSort().getProperty();
            SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

            customerFilter.setSortProperty((SingularAttribute) property);
            customerFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

            customerFilter.setLimit((int) count);
            customerFilter.setOffset((int) first);
            return userService.find(customerFilter).iterator();
        }

        @Override
        public long size() {
            return userService.count(customerFilter);
        }

        @Override
        public IModel<Customer> model(Customer object) {
            return new Model( object);
        }

    }
    }
    
