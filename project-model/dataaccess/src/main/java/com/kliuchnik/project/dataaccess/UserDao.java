package com.kliuchnik.project.dataaccess;

import java.util.List;

import com.kliuchnik.project.dataaccess.filters.CustomerFilter;
import com.kliuchnik.project.dataaccess.filters.UserFilter;
import com.kliuchnik.project.datamodel.User;

public interface UserDao extends AbstractDao<User, Long> {
	User find(String userName, String password);

	List<User> find(UserFilter userFilter);

	long count(UserFilter userFilter);
	

	
	
}