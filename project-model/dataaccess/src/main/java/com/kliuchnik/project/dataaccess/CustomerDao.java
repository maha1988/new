package com.kliuchnik.project.dataaccess;

import java.util.List;

import com.kliuchnik.project.dataaccess.filters.CustomerFilter;

import com.kliuchnik.project.datamodel.Customer;

public interface CustomerDao extends AbstractDao<Customer, Long> {
	Long count(CustomerFilter filter);

	List<Customer> find(CustomerFilter filter);
}
