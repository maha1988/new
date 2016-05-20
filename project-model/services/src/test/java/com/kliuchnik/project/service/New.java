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
import com.kliuchnik.project.datamodel.Product;
import com.kliuchnik.project.datamodel.Role;
import com.kliuchnik.project.datamodel.Sklad;
import com.kliuchnik.project.datamodel.Unit;
import com.kliuchnik.project.datamodel.User;
import com.kliuchnik.project.service.OrderService;
import com.kliuchnik.project.service.ProductService;
import com.kliuchnik.project.service.UserService;

import com.kliuchnik.project.datamodel.Order_;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })

public class New {
		
	
	   @Inject
	    private ProductService productService;
	   	@Inject
	    private OrderService orderService;

	    @Inject
	    private OrderDao orderDao;
	    @Inject
	    private UserService userService;
	    @Inject
	    private  SkladService skladServise;
	    @Test
	    public void test() {
	        Assert.assertNotNull(orderService);
	    }

	    @Test
	    public void testEntityManagerInitialization() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	       Field f = AbstractDaoImpl.class.getDeclaredField("entityManager");
	      f.setAccessible(true);
	       EntityManager em = (EntityManager) f.get(orderDao);

	     Assert.assertNotNull(em);
	   }
	
	    @Test
	    public void testOrder() {
	        Order order = new Order();
	        Customer customer = new Customer();
	        User user= new User ();
	        Sklad sklad= new Sklad();
	       
	        
	        user.setRole(Role.ADMIN);
	        user.setName("name");
	        user.setPassword("1212");
	        
	        customer.setAddress("grodno");
	        customer.setBankR("r/s 434341212121");
	        order.setCustomer(customer); 
	        order.setDate(null);  

	        userService.register(user, customer);             
	     //   orderService.register(order);
	        skladServise.register(sklad);
	        	        

	        Order registredProfile = orderService.getOrder(order.getId());
	       // Customer registredOrder = orderService.getCustomer(customer.getId());

	        Assert.assertNotNull(registredProfile);
	    //    Assert.assertNotNull(registredOrder);
                             
	        
	        Product product1 = new Product();
	        Product product2 = new Product();
	        
	        
	        product1.setCurrentQuantity(4L);
	        product1.setPrice(new BigDecimal("2500.00"));
	        product1.setProductName("Pants");
	        product1.setUnit(Unit.PIECE);
	       // product1.setSklad(sklad);      
	            
	                
	        product2.setCurrentQuantity(3L);
	        product2.setPrice(new BigDecimal("1000.00"));
	        product2.setProductName("Cout");
	        product2.setUnit(Unit.PACKAGE);
	       // product2.setSklad(sklad);
	       
	                
	            	  
	        productService.register(product1,sklad);
	        productService.register(product2,sklad);
	        
//	        order.setProducts(product1);
//	        order.setProducts(product2);
//	        
	        orderService.update(order);
	        
	        List<Product> allProducts= productService.getAll();
	        for (Product pr : allProducts) {
				productService.delete(pr.getId());
			}
	                   
	        orderService.delete(order.getId());
                       
	      //Assert.assertNull(orderService.getOrder(order.getId()));	              	          
	      
	    }
	       
	    @Test
		public void testSearch() {
			// clean all data from users
			  List<Order> all =orderService.getAll();
		        for (Order order : all) {
		            orderService.delete(order.getId());
			}

			// start create new data
			int testObjectsCount = 5;
			for (int i = 0; i < testObjectsCount; i++) {
				    Order order = new Order();
			        Customer customer = new Customer();
			        User user= new User ();
			        Sklad sklad= new Sklad();
			        
			        user.setRole(Role.ADMIN);
			        user.setName("Vasin C.D.");
			        user.setPassword("1212");
			        
			        customer.setAddress("grodno");
			        customer.setBankR("r/s 434341212121");
			        order.setCustomer(customer); 
			        order.setDate(null);  

			        userService.register(user, customer);             
			      //  orderService.register(order);
			        skladServise.register(sklad);
        	        
			        
			      		        
			        Product product1 = new Product();
			        Product product2 = new Product();
			        
			        
			        product1.setCurrentQuantity(4L);
			        product1.setPrice(new BigDecimal("2500.00"));
			        product1.setProductName("Pants");
			        product1.setUnit(Unit.PIECE);
			        product1.setSklad(sklad);
			          
			              
			        		        
			        product2.setCurrentQuantity(3L);
			        product2.setPrice(new BigDecimal("1000.00"));
			        product2.setProductName("Dress");
			        product2.setUnit(Unit.PACKAGE);
			          
			        product2.setSklad(sklad);     
			       
			        			        
			        productService.register(product1,sklad);
			        productService.register(product2,sklad);
			        
//			        order.setProducts(product1);
//			        order.setProducts(product2);
			        orderService.update(order);
			        
			       		           
			      
			}

			OrderFilter orderFilter = new OrderFilter();
			List<Order> result = orderService.find(orderFilter);
			//Assert.assertEquals(testObjectsCount, result.size());
			
			
			// test paging
			orderFilter.setFetchProduct(true);
			orderFilter.setFetchCustomer(true);
			
			int limit = 3;
			orderFilter.setLimit(limit);
			orderFilter.setOffset(0);
			result = orderService.find(orderFilter);
			Assert.assertEquals(limit, result.size());

			// test sort

			orderFilter.setLimit(null);
			orderFilter.setOffset(null);
			orderFilter.setSortOrder(true);
			orderFilter.setSortProperty(Order_.id);
			result = orderService.find(orderFilter);
			//Assert.assertEquals(testObjectsCount, result.size());

		}

	    
	    
	    }

	


