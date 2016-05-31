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

import com.kliuchnik.project.dataaccess.SkladDao;
import com.kliuchnik.project.dataaccess.filters.SkladFilter;
import com.kliuchnik.project.datamodel.Sklad;
import com.kliuchnik.project.datamodel.Sklad_;

@Repository
public class SkladDaoImpl extends AbstractDaoImpl<Sklad, Long> implements SkladDao {

	public SkladDaoImpl() {
		super(Sklad.class);

	}

	@Override
	public Long count(SkladFilter skladFilter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Sklad> from = cq.from(Sklad.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<Sklad> find(SkladFilter skladFilter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Sklad> cq = cb.createQuery(Sklad.class);
		Root<Sklad> from = cq.from(Sklad.class);
		// set selection
		cq.select(from);

		boolean nameSklad = (skladFilter.getName() != null);
		boolean product = (skladFilter.getProduct() != null);
		boolean filter = (nameSklad || product);
		if (filter) {
			Predicate nameEqualCondition = cb.equal(from.get(Sklad_.name), skladFilter.getName());
			if (product == true) {
				Predicate productEqualCondition = cb.isMember(skladFilter.getProduct(), from.get(Sklad_.products));
				cq.where(cb.or( nameEqualCondition,productEqualCondition)).distinct(true);
			} else {
				cq.where(cb.or( nameEqualCondition)).distinct(true);
			}

		}
		// set fetching
		if (skladFilter.isFetchProduct()) {
			from.fetch(Sklad_.products, JoinType.LEFT);
		}

		// set sort params
		if (skladFilter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(skladFilter.getSortProperty()), skladFilter.isSortOrder()));
		}

		TypedQuery<Sklad> q = em.createQuery(cq);

		// set paging
		if (skladFilter.getOffset() != null && skladFilter.getLimit() != null) {
			q.setFirstResult(skladFilter.getOffset());
			q.setMaxResults(skladFilter.getLimit());
		}

		// set execute query
		List<Sklad> allitems = q.getResultList();
		return allitems;
	}

}
