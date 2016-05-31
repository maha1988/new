package com.kliuchnik.project.service;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import com.kliuchnik.project.dataaccess.filters.CustomerFilter;
import com.kliuchnik.project.datamodel.Customer;
import com.kliuchnik.project.datamodel.User;

public interface UserService {

	@Transactional
	void register(User user, Customer customer);

	User getUser(Long id);

	Customer getCustomer(Long id);

	@Transactional
	void update(User user);

	@Transactional
	void update(Customer customer);

	@Transactional
	void saveOrUpdate(Customer customer);
	
	@Transactional
	void saveOrUpdate(User user);

	@Transactional
	void delete(Customer customer, User user);

	List<Customer> find(CustomerFilter customerFilter);
	
	List<User> getAll();
	
	long count(CustomerFilter customerFilter);

	User getByNameAndPassword(String userName, String password);

	Collection<? extends String> resolveRoles(Long id);

}