package com.kliuchnik.project.dataaccess.impl;

import org.springframework.stereotype.Repository;

import com.kliuchnik.project.dataaccess.UserDao;
import com.kliuchnik.project.datamodel.User;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User, Long> implements UserDao {

	protected UserDaoImpl() {
		super(User.class);
		
	}
	 
	 	 	  
}
