package by.kliuchnik.project.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.kliuchnik.project.dataaccess.CustomerDao;
import by.kliuchnik.project.dataaccess.OrderDao;
import by.kliuchnik.project.dataaccess.filters.OrderFilter;

import by.kliuchnik.project.datamodel.Customer;
import by.kliuchnik.project.datamodel.Order;
import by.kliuchnik.project.datamodel.Product;
import by.kliuchnik.project.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	private static Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
	@Inject
	private OrderDao orderDao;
	@Inject 
	private CustomerDao customerDao;


	@Override
	public void register(Order order) {
		order.setDate(new Date());
		  BigDecimal b = new BigDecimal("0.00");
		  List<Product> list = order.getProducts();
		
		  if (list != null)
				  {
				   for (Product pr : list) {
		    b = b.add(pr.getPrice());
		   }
			   order.setSum(b);
			  }
	    
		orderDao.insert(order);
		customerDao.get(order.getCustomer().getId()).setOrders(order);
	    LOGGER.info("Order regirstred: {}", order);
	}

	@Override
	public Order getOrder(Long id) {
		LOGGER.info("Order select: {}", orderDao.get(id));
		return orderDao.get(id);
	}
	@Override
	public Customer getCustomer(Long id) {
		LOGGER.info("Customer select: {}", customerDao.get(id));
		return customerDao.get(id);
	}

	@Override
	public void update(Order order) {
		
		  BigDecimal b = new BigDecimal("0.00");
		  List<Product> list = order.getProducts();
		  if (list.size() != 0) {
		   for (Product pr : list) {
		    b = b.add(pr.getPrice());
		   }
		   order.setSum(b);
		  }
		  LOGGER.info("Order update, new and old: {}",order, orderDao.get(order.getId()));
			
		orderDao.update(order);

	}

	@Override
	public void delete(Long id) {
		LOGGER.info("Order delete: {}", orderDao.get(id));
		orderDao.delete(id);

	}
	@Override
	public List<Order> find(OrderFilter orderFilter) {
		LOGGER.info("Order find by filter: {}", orderFilter);
		return orderDao.find(orderFilter);
	}

	@Override
	public List<Order> getAll() {
		LOGGER.info("Order getAll: {}", "All orders");
		return orderDao.getAll();
	}
}
