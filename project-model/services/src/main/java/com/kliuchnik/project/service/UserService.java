package com.kliuchnik.project.service;

import java.util.List;

import javax.transaction.Transactional;

import com.kliuchnik.project.dataaccess.filters.CustomerFilter;
import com.kliuchnik.project.dataaccess.filters.SkladFilter;
import com.kliuchnik.project.datamodel.Customer;
import com.kliuchnik.project.datamodel.User;

public interface UserService {

	 Long count(CustomerFilter filter);
    @Transactional
    void register(User user, Customer customer);

    User getUser(Long id);

    Customer getCustomer(Long id);

    @Transactional
    void update(User user);

    @Transactional
	void update(Customer customer);

	@Transactional
	void delete(Long id);

	List<Customer> find(CustomerFilter userFilter);

	List<User> getAll();
}