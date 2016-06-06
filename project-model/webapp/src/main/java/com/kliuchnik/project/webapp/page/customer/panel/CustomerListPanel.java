package com.kliuchnik.project.webapp.page.customer.panel;

import java.io.Serializable;
import java.util.Iterator;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.SingularAttribute;

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

import com.kliuchnik.project.dataaccess.filters.CustomerFilter;
import com.kliuchnik.project.datamodel.Customer;
import com.kliuchnik.project.datamodel.Customer_;
import com.kliuchnik.project.datamodel.User;
import com.kliuchnik.project.datamodel.User_;
import com.kliuchnik.project.service.UserService;
import com.kliuchnik.project.webapp.page.customer.CustomerEditPanel;
import com.kliuchnik.project.webapp.page.customer.CustomersPage;
import com.kliuchnik.project.webapp.page.product.ProductsPage;

public class CustomerListPanel extends Panel {

	@Inject
	private UserService userService;

	public CustomerListPanel(String id) {
		super(id);

		CustomerDataProvider customerDataProvider = new CustomerDataProvider();
		DataView<Customer> dataView = new DataView<Customer>("customerlist", customerDataProvider, 10) {
			@Override
			protected void populateItem(Item<Customer> item) {
				Customer customer = item.getModelObject();

				item.add(new Label("id", customer.getId()));
				item.add(new Label("firstN", customer.getFirstN()));
				item.add(new Label("lastN", customer.getLastN()));
				item.add(new Label("address", customer.getAddress()));
				item.add(new Label("bank", customer.getBankR()));
//				item.add(new Label("name", customer.getUser().getName()));
//				item.add(new Label("password", customer.getUser().getPassword()));
//				item.add(new Label("role", customer.getUser().getRole()));
//            
				item.add(new Link<Void>("edit-link") {
					@Override
					public void onClick() {
					//	setResponsePage(new CustomerEditPanel(customer));
					}
				});

				item.add(new Link<Void>("delete-link") {
					@Override
					public void onClick() {
												
						
						try { User user = customer.getUser();
							
							userService.delete(customer,user);
							;
						} catch (PersistenceException e) {
							System.out.println("caughth persistance exception");
						}

						setResponsePage(new CustomersPage());
					}
				});

			}
		};
		add(dataView);
		add(new PagingNavigator("paging", dataView));

		add(new OrderByBorder("sort-id", Customer_.id, customerDataProvider));
		add(new OrderByBorder("sort-firstN", Customer_.firstN, customerDataProvider));
		add(new OrderByBorder("sort-lastN", Customer_.lastN, customerDataProvider));
		add(new OrderByBorder("sort-address", Customer_.address, customerDataProvider));
		add(new OrderByBorder("sort-bank", Customer_.bankR, customerDataProvider));
//		add(new OrderByBorder("sort-name", User_.name, customerDataProvider));
//		add(new OrderByBorder("sort-password", User_.password, customerDataProvider));
//		add(new OrderByBorder("sort-role", User_.role, customerDataProvider));
//		
		
	}

	private class CustomerDataProvider extends SortableDataProvider<Customer, Serializable> {

		private CustomerFilter customerFilter;

		public CustomerDataProvider() {
			super();
			customerFilter = new CustomerFilter();
			customerFilter.setFetchOrder(true);
			customerFilter.setFetchUser(true);
			setSort((Serializable) Customer_.user, SortOrder.ASCENDING);
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
			return new Model(object);
		}

	}
}
