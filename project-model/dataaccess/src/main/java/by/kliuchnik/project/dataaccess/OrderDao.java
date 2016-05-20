package by.kliuchnik.project.dataaccess;

import java.util.List;

import by.kliuchnik.project.dataaccess.filters.OrderFilter;
import by.kliuchnik.project.datamodel.Order;

public interface OrderDao extends AbstractDao<Order, Long> {
	 List<Order> find(OrderFilter orderFilter);
}
