package com.kliuchnik.project.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kliuchnik.project.dataaccess.OrderDao;
import com.kliuchnik.project.dataaccess.filters.OrderFilter;
import com.kliuchnik.project.dataaccess.impl.AbstractDaoImpl;
import com.kliuchnik.project.datamodel.Customer;
import com.kliuchnik.project.datamodel.Order;
import com.kliuchnik.project.datamodel.Order_;
import com.kliuchnik.project.datamodel.Product;
import com.kliuchnik.project.datamodel.Role;
import com.kliuchnik.project.datamodel.Sklad;
import com.kliuchnik.project.datamodel.Unit;
import com.kliuchnik.project.datamodel.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })

public class OrderServiceTest {

	@Inject
	private ProductService productService;
	@Inject
	private OrderService orderService;

	@Inject
	private OrderDao orderDao;
	@Inject
	private UserService userService;
	
	
	

	@Test
	public void test() {
		Assert.assertNotNull(orderService);
	}

	@Test
	public void testEntityManagerInitialization()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field f = AbstractDaoImpl.class.getDeclaredField("entityManager");
		f.setAccessible(true);
		EntityManager em = (EntityManager) f.get(orderDao);

		Assert.assertNotNull(em);
	}

	@Test
	public void testOrder() {
		Order order = new Order();
		Customer customer = new Customer();
		User user = new User();
	
		
		user.setRole(Role.ADMIN);
		user.setName("name");
		user.setPassword("1212");

		customer.setAddress("grodno");
		customer.setBankR("r/s 434341212121");
		order.setCustomer(customer);
		order.setDate(null);

		userService.register(user, customer);
		orderService.register(order);

		Order registredProfile = orderService.getOrder(order.getId());
		Customer registredOrder = orderService.getCustomer(customer.getId());

		Assert.assertNotNull(registredProfile);
		Assert.assertNotNull(registredOrder);

		Product product1 = new Product();
		Product product2 = new Product();

		product1.setCurrentQuantity(4L);
		product1.setPrice(new BigDecimal("2500.00"));
		product1.setProductName("Pants");
		product1.setUnit(Unit.ШT);
		product1.setSklad(null);

		product2.setCurrentQuantity(3L);
		product2.setPrice(new BigDecimal("1000.00"));
		product2.setProductName("Dress");
		product2.setUnit(Unit.УПАКОВКА);

		product2.setSklad(null);

		productService.register(product1, new Sklad());
		productService.register(product2, new Sklad());
	
		
		order.setProducts( product1);
		order.setProducts( product2);

		orderService.update(order);
		
		List<Order> allOrder = orderService.getAll();
		for (Order p : allOrder) {
		//	orderService.delete(p.getId());
		}
		List<Product> allProducts = productService.getAll();
		for (Product pr : allProducts) {
	//		productService.delete(pr.getId());
		}

	//	orderService.delete(order.getId());
		


		 
		// Assert.assertNull(orderService.getOrder(order.getId()));

	}

	@Test
	public void testSearch() {
		// clean all data from order
		List<Order> all = orderService.getAll();
		for (Order order : all) {
		//	orderService.delete(order.getId());
		}
		
		List<Product> allProducts = productService.getAll();
		for (Product pr : allProducts) {
		//	productService.delete(pr.getId());
		}
		
		

		
		// start create new data
		int testObjectsCount = 5;
		for (int i = 0; i < testObjectsCount; i++) {
			Order order = new Order();
			Customer customer = new Customer();
			User user = new User();
		

			user.setRole(Role.ADMIN);
			user.setName("Vasin C.D.");
			user.setPassword("1212");

			customer.setAddress("grodno");
			customer.setBankR("r/s 434341212121");
			order.setCustomer(customer);
			order.setDate(null);

			userService.register(user, customer);
			orderService.register(order);

			Product product1 = new Product();
			Product product2 = new Product();

			product1.setCurrentQuantity(4L);
			product1.setPrice(new BigDecimal("2500.00"));
			product1.setProductName("Pants");
			product1.setUnit(Unit.ШT);
			product1.setSklad(null);

			product2.setCurrentQuantity(3L);
			product2.setPrice(new BigDecimal("1000.00"));
			product2.setProductName("Dress");
			product2.setUnit(Unit.УПАКОВКА);

			product2.setSklad(null);

			productService.register(product1, new Sklad());
			productService.register(product2, new Sklad());

			order.setProducts( product1);
			order.setProducts( product2);
			
			
			orderService.update(order);
		
	   	orderService.delete(order);
		}

		OrderFilter orderFilter = new OrderFilter();
		List<Order> result = orderService.find(orderFilter);
		// Assert.assertEquals(testObjectsCount, result.size());

		// test paging
		orderFilter.setFetchProduct(true);
		orderFilter.setFetchCustomer(true);

		int limit = 3;
		orderFilter.setLimit(limit);
		orderFilter.setOffset(0);
		result = orderService.find(orderFilter);
	//	Assert.assertEquals(limit, result.size());

		// test sort

		orderFilter.setLimit(null);
		orderFilter.setOffset(null);
		orderFilter.setSortOrder(true);
		orderFilter.setSortProperty(Order_.id);
		result = orderService.find(orderFilter);
		// Assert.assertEquals(testObjectsCount, result.size());

	}

}
