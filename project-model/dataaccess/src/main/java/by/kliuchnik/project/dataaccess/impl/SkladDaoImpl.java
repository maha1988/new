package by.kliuchnik.project.dataaccess.impl;



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

import by.kliuchnik.project.dataaccess.SkladDao;
import by.kliuchnik.project.dataaccess.filters.SkladFilter;
import by.kliuchnik.project.datamodel.Sklad;
import by.kliuchnik.project.datamodel.Sklad_;

@Repository
public class SkladDaoImpl extends AbstractDaoImpl<Sklad, Long> implements SkladDao {

	public SkladDaoImpl() {
		super(Sklad.class);
	
	}

	 @Override
	    public List<Sklad>find (SkladFilter skladFilter) {
	        EntityManager em = getEntityManager();

	        CriteriaBuilder cb = em.getCriteriaBuilder();

	        CriteriaQuery<Sklad> cq = cb.createQuery(Sklad.class);

	        Root<Sklad> from = cq.from(Sklad.class);
	     // set selection
	        cq.select(from);

	        if (skladFilter.getName() != null) {
	            Predicate nameEqualCondition = cb.equal(from.get(Sklad_.name), skladFilter.getName());
	           
	            cq.where(cb.or(nameEqualCondition));
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
