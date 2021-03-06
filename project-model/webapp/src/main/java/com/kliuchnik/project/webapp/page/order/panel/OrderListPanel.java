package com.kliuchnik.project.webapp.page.order.panel;

import java.io.Serializable;
import java.util.Iterator;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.datetime.markup.html.basic.DateLabel;
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

import com.kliuchnik.project.dataaccess.filters.OrderFilter;
import com.kliuchnik.project.datamodel.Order;
import com.kliuchnik.project.datamodel.Order_;
import com.kliuchnik.project.service.OrderService;
import com.kliuchnik.project.webapp.page.order.OrdersPage;

public class OrderListPanel extends Panel {

	@Inject
	private OrderService orderService;

	public OrderListPanel(String id) {
		super(id);

		OrderDataProvider orderDataProvider = new OrderDataProvider();
		DataView<Order> dataView = new DataView<Order>("orderlist", orderDataProvider, 10) {
			@Override
			protected void populateItem(Item<Order> item) {
				Order order = item.getModelObject();

				item.add(new Label("id", order.getId()));
				item.add(DateLabel.forDatePattern("date", Model.of(order.getDate()), "dd-MM-yyyy"));
				item.add(new Label("sum", order.getSum()));

				if (order.getCustomer() == null ) {
					item.add(new Label("customer", "  "));
				} else {
					
				item.add(new Label("customer", order.getCustomer().getId()));

				}

				item.add(new Link<Void>("edit-link") {
					@Override
					public void onClick() {
						// setResponsePage(new CustomerEditPanel(customer));
					}
				});
				item.add(new Link<Void>("delete-link") {
					@Override
					public void onClick() {
						try {
							orderService.delete(order);
						} catch (PersistenceException e) {
							System.out.println("caughth persistance exception");
						}

						setResponsePage(new OrdersPage());
					}
				});
			}
		};
		add(dataView);
		add(new PagingNavigator("paging", dataView));

		add(new OrderByBorder("sort-id", Order_.id, orderDataProvider));
		add(new OrderByBorder("sort-customer", Order_.customer, orderDataProvider));
		add(new OrderByBorder("sort-sum", Order_.sum, orderDataProvider));

	}

	private class OrderDataProvider extends SortableDataProvider<Order, Serializable> {

		private OrderFilter orderFilter;

		public OrderDataProvider() {
			super();
			orderFilter = new OrderFilter();
			orderFilter.setFetchUser(true);
			orderFilter.setFetchCustomer(true);
			orderFilter.setFetchProduct(true);
			setSort((Serializable) Order_.date, SortOrder.ASCENDING);
		}

		@Override
		public Iterator<Order> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			orderFilter.setSortProperty((SingularAttribute) property);
			orderFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

			orderFilter.setLimit((int) count);
			orderFilter.setOffset((int) first);
			return orderService.find(orderFilter).iterator();
		}

		@Override
		public long size() {
			return orderService.count(orderFilter);
		}

		@Override
		public IModel<Order> model(Order object) {
			return new Model(object);
		}

	}
}
