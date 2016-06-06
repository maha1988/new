package com.kliuchnik.project.dataaccess.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.kliuchnik.project.dataaccess.UserDao;
import com.kliuchnik.project.dataaccess.filters.UserFilter;
import com.kliuchnik.project.datamodel.User;
import com.kliuchnik.project.datamodel.User_;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User, Long> implements UserDao {

	protected UserDaoImpl() {
		super(User.class);
		
	}
	@Override
    public User find(String userName, String password) {
        EntityManager em = getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> from = cq.from(User.class);

        cq.select(from);
        Predicate usernameEqualCondition = cb.equal(from.get(User_.name), userName);
        Predicate passwEqualCondition = cb.equal(from.get(User_.password), password);
        cq.where(cb.and(usernameEqualCondition, passwEqualCondition));

        TypedQuery<User> q = em.createQuery(cq);

        List<User> allitems = q.getResultList();

        if (allitems.isEmpty()) {
            return null;
        } else if (allitems.size() == 1) {
            return allitems.get(0);
        } else {
            throw new IllegalArgumentException("more than 1 user found ");
        }
    }
	@Override
	public List<User> find(UserFilter userFilter) {
		
		return null;
	}
	@Override
	public long count(UserFilter userFilter) {
		
		return 0;
	}
} 
	 	 	  

