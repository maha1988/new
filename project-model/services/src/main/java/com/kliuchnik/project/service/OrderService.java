package com.kliuchnik.project.service;

import java.util.List;

import javax.transaction.Transactional;

import com.kliuchnik.project.dataaccess.filters.OrderFilter;
import com.kliuchnik.project.dataaccess.filters.ProductFilter;
import com.kliuchnik.project.datamodel.Customer;
import com.kliuchnik.project.datamodel.Order;
import com.kliuchnik.project.datamodel.Product;
import com.kliuchnik.project.datamodel.Sklad;

public interface OrderService {
	Long count(OrderFilter filter);

	@Transactional
	void register(Order order);

	Order getOrder(Long id);

	Customer getCustomer(Long id);

	@Transactional
	void update(Order order);

	@Transactional
	void saveOrUpdate(Order order);

	@Transactional
	void delete(Order order);

	List<Order> find(OrderFilter orderFilter);

	List<Order> getAll();
}
