package com.kliuchnik.project.dataaccess.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.kliuchnik.project.dataaccess.CustomerDao;
import com.kliuchnik.project.dataaccess.filters.CustomerFilter;
import com.kliuchnik.project.datamodel.Customer;
import com.kliuchnik.project.datamodel.Customer_;
import com.kliuchnik.project.datamodel.User_;

@Repository
public class CustomerDaoImpl extends AbstractDaoImpl<Customer, Long> implements CustomerDao {

	protected CustomerDaoImpl() {
		super(Customer.class);
	}

	@Override

	public List<Customer> find(CustomerFilter filter) {
		EntityManager em = getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);

		Root<Customer> from = cq.from(Customer.class);
		// set selection
		cq.select(from);// Указывает что селектать SELECT *. from - это
		// таблица,
		// а from.get... - это конкретная колонка

		handleFilterParameters(filter, cb, cq, from);

		// set fetching
		if (filter.isFetchUser()) {
			from.fetch(Customer_.user, JoinType.INNER);
		}
		if (filter.isFetchOrder()) {
			from.fetch(Customer_.orders, JoinType.LEFT);
		}

		// set sort params
		if (filter.getSortProperty() != null) {
			Path<Object> expression;
			if (User_.name.equals(filter.getSortProperty())) {
				expression = from.get(Customer_.user).get(filter.getSortProperty());
			} else {
				expression = from.get(filter.getSortProperty());
			}
			cq.orderBy(new OrderImpl(expression, filter.isSortOrder()));
		}

		TypedQuery<Customer> q = em.createQuery(cq);

		// set paging
		if (filter.getOffset() != null && filter.getLimit() != null) {
			q.setFirstResult(filter.getOffset());
			q.setMaxResults(filter.getLimit());
		}

		// set execute query
		List<Customer> allitems = q.getResultList();
		return allitems;
	}

	@Override
	public Long count(CustomerFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Customer> from = cq.from(Customer.class);
		// set selection
		cq.select(cb.count(from));
		handleFilterParameters(filter, cb, cq, from);
		TypedQuery<Long> q = em.createQuery(cq);
		// set execute query
		return q.getSingleResult();
	}

	private void handleFilterParameters(CustomerFilter filter, CriteriaBuilder cb, CriteriaQuery<?> cq, Root<Customer> from) {
        if (filter.getAddress() != null) {
            Predicate nameEqualCondition = cb.equal(from.get(Customer_.address), filter.getAddress());
           
            cq.where(cb.or(nameEqualCondition));
        }

    
    }
}
