package by.kliuchnik.project.services.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import by.kliuchnik.project.dataaccess.CustomerDao;
import by.kliuchnik.project.dataaccess.UserDao;
import by.kliuchnik.project.datamodel.Customer;
import by.kliuchnik.project.datamodel.User;
import by.kliuchnik.project.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDao userDao;
	@Inject 
	private CustomerDao customerDao;

	@Override
	public void register(User user, Customer customer) {
		customer.setUser(user);
		userDao.insert(user);
		
		customerDao.insert(customer);
		 // LOGGER.info("User regirstred: {}", userUser);
		
	}

	@Override
	public User getUser(Long id) {
		
		return userDao.get(id);
	}

	@Override
	public Customer getCustomer(Long id) {
		
		return customerDao.get(id);
	}

	@Override
	public void update(User user) {
		userDao.update(user);

	}

	@Override
	public void delete(Long id) {
		
		//customerDao.delete(id);
		//userDao.delete(id);
        
	}

}
