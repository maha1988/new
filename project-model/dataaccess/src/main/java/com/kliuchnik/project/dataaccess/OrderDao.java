package com.kliuchnik.project.dataaccess;

import java.util.List;

import com.kliuchnik.project.dataaccess.filters.OrderFilter;

import com.kliuchnik.project.datamodel.Order;

public interface OrderDao extends AbstractDao<Order, Long> {
	Long count(OrderFilter filter);
	List<Order> find(OrderFilter filter);
}
