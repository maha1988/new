package by.kliuchnik.project.dataaccess.impl;



import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import by.kliuchnik.project.dataaccess.UserDao;
import by.kliuchnik.project.dataaccess.filters.UserFilter;
import by.kliuchnik.project.datamodel.Customer;
import by.kliuchnik.project.datamodel.User;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User, Long> implements UserDao {

	protected UserDaoImpl() {
		super(User.class);
		
	}
	
}
