package com.kliuchnik.project.service;

import java.lang.reflect.Field;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kliuchnik.project.dataaccess.UserDao;
import com.kliuchnik.project.dataaccess.filters.CustomerFilter;
import com.kliuchnik.project.dataaccess.impl.AbstractDaoImpl;
import com.kliuchnik.project.datamodel.Customer;
import com.kliuchnik.project.datamodel.Customer_;
import com.kliuchnik.project.datamodel.Role;
import com.kliuchnik.project.datamodel.User;
import com.kliuchnik.project.service.UserService;

import com.kliuchnik.project.datamodel.User_;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class UserServiceTest {

	@Inject
	private UserService userService;

	@Inject
	private UserDao userDao;
	
	

	@Test
	public void test() {
		Assert.assertNotNull(userService);
	}

	@Test
	public void testEntityManagerInitialization()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field f = AbstractDaoImpl.class.getDeclaredField("entityManager");
		f.setAccessible(true);
		EntityManager em = (EntityManager) f.get(userDao);

		Assert.assertNotNull(em);
	}

	@Test
	public void testRegistration() {
		User user = new User();
		Customer customer = new Customer();

		user.setName("name");
		user.setPassword("pas");
		user.setRole(Role.ADMIN);

		customer.setAddress("Grodno");
		customer.setBankR("434341212121");

		userService.register(user, customer);

		User registredProfile = userService.getUser(user.getId());
		Customer registredUser = userService.getCustomer(customer.getId());

		Assert.assertNotNull(registredProfile);
		Assert.assertNotNull(registredUser);

		String updatedFName = "updatedName";
		user.setName(updatedFName);
		userService.update(user);

		//Assert.assertEquals(updatedFName, userService.getUser(user.getId()).getName());

		userService.delete(user.getId());

		// Assert.assertNull(userService.getCustomer(customer.getId()));
		 //Assert.assertNull(userService.getUser(user.getId()));
	}

	@Test
	public void testSearch() {
		// clean all data from users
		  List<User> all =userService.getAll();
	        for (User user : all) {
	            userService.delete(user.getId());
		}

		// start create new data
		int testObjectsCount = 5;
		for (int i = 0; i < testObjectsCount; i++) {
			Customer customer = new Customer();
			User user = new User();

			customer.setAddress("testAddress" + i);
			customer.setBankR("454612121" + i);

			user.setName(i+"Petrov S");
			user.setPassword(i + "pswd");
			user.setRole(Role.ADMIN);
			
			userService.register(user, customer);

		}

		CustomerFilter filter = new CustomerFilter();
		List<Customer> result = userService.find(filter);
		Assert.assertEquals(testObjectsCount, result.size());
		// test paging
		filter.setFetchUser(true);
		filter.setFetchOrder(true);
		
		int limit = 3;
		filter.setLimit(limit);
		filter.setOffset(0);
		result = userService.find(filter);
		Assert.assertEquals(limit, result.size());

		// test sort

		filter.setLimit(null);
		filter.setOffset(null);
		filter.setSortOrder(true);
		filter.setSortProperty(Customer_.address);
		result = userService.find(filter);
		Assert.assertEquals(testObjectsCount, result.size());

	}

}
