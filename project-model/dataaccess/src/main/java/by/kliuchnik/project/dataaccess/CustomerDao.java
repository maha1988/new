package by.kliuchnik.project.dataaccess;

import java.util.List;

import by.kliuchnik.project.dataaccess.filters.UserFilter;
import by.kliuchnik.project.datamodel.Customer;

public interface CustomerDao extends AbstractDao<Customer, Long> {
	 List<Customer> find(UserFilter filter);

}
