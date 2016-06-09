package com.kliuchnik.project.dataaccess.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
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
	public List<User> find(UserFilter filter) {
		EntityManager em = getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<User> cq = cb.createQuery(User.class);

		Root<User> from = cq.from(User.class); // SELECT .. FROM
																// ...

		cq.select(from); // Указывает что селектать SELECT *. from - это
							// таблица,
							// а from.get... - это конкретная колонка

		boolean name = (filter.getName() != null);
		boolean password = (filter.getPassword() != null);
		boolean role = (filter.getRole() != null);
		
	
		boolean filt = (name || password || role );

		if (filt) {
			
			Predicate nameEqualCondition = cb.equal(from.get(User_.name), filter.getName());
			Predicate passwordEqualCondition = cb.equal(from.get(User_.password), filter.getPassword());
			Predicate roleEqualCondition = cb.equal(from.get(User_.role), filter.getRole());
			
			cq.where(cb.or(nameEqualCondition, passwordEqualCondition, roleEqualCondition));
		}

		// set fetching
		if (filter.isFetchCustomer()) {
			from.fetch(User_.customer, JoinType.LEFT);
		}

		

		// set sort params
		if (filter.getSortProperty() != null) {
			boolean nam = filter.getSortProperty() == User_.name;
			boolean rol = filter.getSortProperty() == User_.role;
			boolean pass = filter.getSortProperty() == User_.password;
			if (nam || rol || pass) {
				cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
			} else {
				cq.orderBy(new OrderImpl(from.get(User_.customer).get(filter.getSortProperty()),
						filter.isSortOrder()));
			}
		}

		TypedQuery<User> q = em.createQuery(cq);

		// set paging
		if (filter.getOffset() != null && filter.getLimit() != null) {
			q.setFirstResult(filter.getOffset());
			q.setMaxResults(filter.getLimit());
		}

		// set execute query
		List<User> allitems = q.getResultList();

		return allitems;
	}

	@Override
	public long count(UserFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<User> from = cq.from(User.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	protected void setPaging(UserFilter filter, TypedQuery<UserFilter> q) {
		if (filter.getOffset() != null && filter.getLimit() != null) {
			q.setFirstResult(filter.getOffset());
			q.setMaxResults(filter.getLimit());
		}
	}

	@Override
	public User find(String login, String password) {

		EntityManager em = getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<User> cq = cb.createQuery(User.class);

		Root<User> from = cq.from(User.class);

		cq.select(from);
		Predicate usernameEqualCondition = cb.equal(from.get(User_.name), login);
		Predicate passwEqualCondition = cb.equal(from.get(User_.password), password);
		
		cq.where(cb.and(usernameEqualCondition, passwEqualCondition	));

		TypedQuery<User> q = em.createQuery(cq);

		List<User> allitems = q.getResultList();

		if (allitems.isEmpty()) {
			return null;
		} else if (allitems.size() == 1) {
			return allitems.get(0);
		} else {
			throw new IllegalArgumentException("==more than 1 user found==");
		}
	}


} 
	 	 	  

