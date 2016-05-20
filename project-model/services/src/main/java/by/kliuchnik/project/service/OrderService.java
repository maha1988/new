package by.kliuchnik.project.service;

import java.util.List;

import javax.transaction.Transactional;

import by.kliuchnik.project.dataaccess.filters.OrderFilter;

import by.kliuchnik.project.datamodel.Customer;
import by.kliuchnik.project.datamodel.Order;



public interface OrderService {
	 @Transactional
	    void register(Order order );
	 
	    Order getOrder(Long id);
	    Customer getCustomer(Long id);

	    @Transactional
	    void update(Order order );

	   @Transactional
	    void delete(Long id);

	   List<Order> find(OrderFilter orderFilter);

	    List<Order> getAll();
}

