package com.kliuchnik.project.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kliuchnik.project.dataaccess.CustomerDao;
import com.kliuchnik.project.dataaccess.UserDao;
import com.kliuchnik.project.dataaccess.filters.CustomerFilter;
import com.kliuchnik.project.dataaccess.filters.UserFilter;
import com.kliuchnik.project.datamodel.Customer;
import com.kliuchnik.project.datamodel.User;
import com.kliuchnik.project.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	@Inject
	private UserDao userDao;
	@Inject
	private CustomerDao customerDao;
	

	@Override
	public void register(User user, Customer customer) {
		customer.setUser(user);
		
		
		customerDao.insert(customer);
		userDao.insert(user);
		
		LOGGER.info("User regirstred: {}",user, customer);
	}

	@Override
	public User getUser(Long id) {
		
		LOGGER.info("User select: {}", userDao.get(id));
		
		return userDao.get(id);
	}

	@Override
	public Customer getCustomer(Long id) {
		LOGGER.info("Customer select: {}", customerDao.get(id));
		return customerDao.get(id);
	}

	@Override
	public void update(User user) {
		LOGGER.info("User update, new and old: {}", user, userDao.get(user.getId()));
		userDao.update(user);

	}

	@Override
	public void update(Customer customer) {
		LOGGER.info("Customer update, new and old: {}", customer, customerDao.get(customer.getId()));
		customerDao.update(customer);

	}
	
	@Override
	public void saveOrUpdate(User user) {
		if (user.getId() == null) {
			userDao.insert(user);
		} else {
			userDao.update(user);
		}
	}
	
	@Override
	public void saveOrUpdate(Customer customer) {
		if (customer.getId() == null) {
			customerDao.insert(customer);
		} else {
			customerDao.update(customer);
		}
	}
	@Override
	public List<Customer> find(CustomerFilter userFilter) {
		
		LOGGER.info("User find by filter: {}", userFilter);

		return customerDao.find(userFilter);
	}
	@Override
	public List<User> find(UserFilter userFilter) {
		
		LOGGER.info("User find by filter: {}", userFilter);

		return userDao.find(userFilter);
	}

	@Override
	public List<User> getAll() {
		
		LOGGER.info("User getAll: {}", "Alls users");
		
		return userDao.getAll();
	}
	@Override
    public long count(CustomerFilter customerFilter) {
        return customerDao.count(customerFilter);
    }
	@Override
    public long count(UserFilter userFilter) {
        return userDao.count(userFilter);
    }
	
	@Override
    public User getByNameAndPassword(String userName, String password) {
        return userDao.find(userName, password);
    }

    @Override
    public Collection<? extends String> resolveRoles(Long id) {
        User user = userDao.get(id);
        return Collections.singletonList(user.getRole().name());
    }

	
   
	@Override
	public void delete(Customer customer, User user ) {
		LOGGER.info("Customer delete: {}", customerDao.get(customer.getId()));
		customerDao.delete(customer.getId());
		userDao.delete(user.getId());	
	
	}
	@Override
	public void delete( User user ) {
		LOGGER.info("User delete: {}", userDao.get(user.getId()));
		userDao.delete(user.getId());	
	
	}
	
}
