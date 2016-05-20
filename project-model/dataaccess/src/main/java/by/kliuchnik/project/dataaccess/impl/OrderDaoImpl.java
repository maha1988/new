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

import by.kliuchnik.project.dataaccess.OrderDao;
import by.kliuchnik.project.dataaccess.filters.OrderFilter;

import by.kliuchnik.project.datamodel.Order;
import by.kliuchnik.project.datamodel.Order_;


@Repository
public class OrderDaoImpl extends AbstractDaoImpl<Order, Long> implements OrderDao {

	protected OrderDaoImpl() {
		super(Order.class);
	
	}

	 @Override
	    public List<Order>find (OrderFilter orderFilter) {
	        EntityManager em = getEntityManager();

	        CriteriaBuilder cb = em.getCriteriaBuilder();

	        CriteriaQuery<Order> cq = cb.createQuery(Order.class);

	        Root<Order> from = cq.from(Order.class);
	     // set selection
	        cq.select(from);

	        if (orderFilter.getCustomer() != null) {
	            Predicate customerEqualCondition = cb.equal(from.get(Order_.customer), orderFilter.getCustomer());
	            Predicate dateEqualCondition = cb.equal(from.get(Order_.date), orderFilter.getDate());
	            Predicate sumEqualCondition = cb.equal(from.get(Order_.sum), orderFilter.getSum());
	           
	            cq.where(cb.or(customerEqualCondition,dateEqualCondition,sumEqualCondition));
	        }
	        // set fetching
	        if (orderFilter.isFetchCustomer()) {
	            from.fetch(Order_.customer, JoinType.LEFT);
	        }

	        // set sort params
	        if (orderFilter.getSortProperty() != null) {
	            cq.orderBy(new OrderImpl(from.get(orderFilter.getSortProperty()), orderFilter.isSortOrder()));
	        }

	        TypedQuery<Order> q = em.createQuery(cq);

	        // set paging
	        if (orderFilter.getOffset() != null && orderFilter.getLimit() != null) {
	            q.setFirstResult(orderFilter.getOffset());
	            q.setMaxResults(orderFilter.getLimit());
	        }

	        // set execute query
	        List<Order> allitems = q.getResultList();
	            return allitems;
	    }	


}
