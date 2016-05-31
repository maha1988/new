package com.kliuchnik.project.webapp.page.user.panel;

import java.io.Serializable;
import java.util.Iterator;

import javax.inject.Inject;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
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
import com.kliuchnik.project.webapp.page.user.UsersEditPage;



@AuthorizeAction(roles = { "admin" }, action = Action.RENDER)
public class UsersListPanel extends Panel {

    @Inject
    private UserService userService;

    public UsersListPanel(String id) {
        super(id);

        UsersDataProvider userDataProvider = new UsersDataProvider();
        DataView<Customer> dataView = new DataView<Customer>("userlist", userDataProvider, 10) {
            @Override
            protected void populateItem(Item<Customer> item) {
            	Customer user = item.getModelObject();
            	
            	item.add(new Label("id", user.getId()));
				item.add(new Label("name", user.getUser().getName()));
				item.add(new Label("password", user.getUser().getPassword()));
				item.add(new Label("role",user.getUser().getRole()));
				
                item.add(new Link<Void>("edit-link") {
                    @Override
                    public void onClick() {
                        setResponsePage(new UsersEditPage(user));
                    }
                });

            }
        };
        add(dataView);
        add(new PagingNavigator("paging", dataView));
        add(new OrderByBorder("sort-id", User_.id, userDataProvider));
        add(new OrderByBorder("sort-name", User_.name, userDataProvider));
        add(new OrderByBorder("sort-password", User_.password, userDataProvider));
        add(new OrderByBorder("sort-role", User_.role, userDataProvider));
      
    }

    private class UsersDataProvider extends SortableDataProvider<Customer, Serializable> {

    	private CustomerFilter customerFilter;

		public UsersDataProvider() {
			super();
			customerFilter = new CustomerFilter();
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

