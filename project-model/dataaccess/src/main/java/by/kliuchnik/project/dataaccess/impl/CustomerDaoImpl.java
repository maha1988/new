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

import by.kliuchnik.project.dataaccess.CustomerDao;
import by.kliuchnik.project.dataaccess.filters.UserFilter;
import by.kliuchnik.project.datamodel.Customer;
import by.kliuchnik.project.datamodel.Customer_;

@Repository
public class CustomerDaoImpl extends AbstractDaoImpl<Customer, Long> implements CustomerDao {

	protected CustomerDaoImpl() {
		super(Customer.class);
		
	}
	 @Override
	    public List<Customer>find (UserFilter filter) {
	        EntityManager em = getEntityManager();

	        CriteriaBuilder cb = em.getCriteriaBuilder();

	        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);

	        Root<Customer> from = cq.from(Customer.class);
	     // set selection
	        cq.select(from);

	        if (filter.getUserName() != null) {
	            Predicate addressEqualCondition = cb.equal(from.get(Customer_.address), filter.getUserName());
	            Predicate bankREqualCondition = cb.equal(from.get(Customer_.bankR), filter.getUserName());
	            cq.where(cb.or(addressEqualCondition, bankREqualCondition));
	        }
	        // set fetching
	        if (filter.isFetchUser()) {
	            from.fetch(Customer_.user, JoinType.LEFT);
	        }

	        // set sort params
	        if (filter.getSortProperty() != null) {
	            cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
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
}

