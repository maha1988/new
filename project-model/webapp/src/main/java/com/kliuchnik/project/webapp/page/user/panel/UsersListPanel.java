package com.kliuchnik.project.webapp.page.user.panel;

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

import com.kliuchnik.project.dataaccess.filters.UserFilter;
import com.kliuchnik.project.datamodel.User;
import com.kliuchnik.project.datamodel.User_;
import com.kliuchnik.project.service.UserService;
import com.kliuchnik.project.webapp.page.user.UsersPage;

public class UsersListPanel extends Panel {

	@Inject
	private UserService userService;
	private User user;
	private ModalWindow modalWindow;

	

	public UsersListPanel(ModalWindow modalWindow, User user) {
		super(modalWindow.getContentId());
		this.user = user;
		this.modalWindow = modalWindow;	
	}

		
	
	public UsersListPanel(String id) {
		super(id);

		UserDataProvider userDataProvider = new UserDataProvider();
		DataView<User> dataView = new DataView<User>("userlist", userDataProvider, 10) {
			@Override
			protected void populateItem(Item<User> item) {
				User user = item.getModelObject();

				item.add(new Label("id", user.getId()));
				item.add(new Label("name", user.getName()));
				item.add(new Label("password", user.getPassword()));
				item.add(new Label("role", user.getRole()));



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
							userService.delete(user);
						} catch (PersistenceException e) {
							System.out.println("caughth persistance exception");
						}

						setResponsePage(new UsersPage());
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

	private class UserDataProvider extends SortableDataProvider<User, Serializable> {

		private UserFilter userFilter;

		public UserDataProvider() {
			super();
			userFilter = new UserFilter();
			
			userFilter.setFetchCustomer(true);
			
			setSort((Serializable) User_.name, SortOrder.ASCENDING);
		}

		@Override
		public Iterator<User> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			userFilter.setSortProperty((SingularAttribute) property);
			userFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

			userFilter.setLimit((int) count);
			userFilter.setOffset((int) first);
			return userService.find(userFilter).iterator();
		}

		@Override
		public long size() {
			return userService.count(userFilter);
		}

		@Override
		public IModel<User> model(User object) {
			return new Model(object);
		}

	}
}
