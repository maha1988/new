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

import com.kliuchnik.project.dataaccess.OrderDao;
import com.kliuchnik.project.dataaccess.filters.OrderFilter;
import com.kliuchnik.project.dataaccess.filters.ProductFilter;
import com.kliuchnik.project.datamodel.Order;
import com.kliuchnik.project.datamodel.Order_;
import com.kliuchnik.project.datamodel.Product;




@Repository
public class OrderDaoImpl extends AbstractDaoImpl<Order, Long> implements OrderDao {

	protected OrderDaoImpl() {
		super(Order.class);
	
	}
	 public Long count(OrderFilter orderFilter) {
	        EntityManager em = getEntityManager();
	        CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
	        Root<Order> from = cq.from(Order.class);
	        cq.select(cb.count(from));
	        TypedQuery<Long> q = em.createQuery(cq);
	        return q.getSingleResult();
	 }
	 @Override
	    public List<Order>find (OrderFilter orderFilter) {
	        EntityManager em = getEntityManager();

	        CriteriaBuilder cb = em.getCriteriaBuilder();

	        CriteriaQuery<Order> cq = cb.createQuery(Order.class);

	        Root<Order> from = cq.from(Order.class);
	     // set selection
	        cq.select(from);
	        
			boolean customer = (orderFilter.getCustomer() != null);
			boolean date = (orderFilter.getDate() != null);
			boolean sum = (orderFilter.getSum() != null);
			boolean filter = (customer || date || sum );

	        if (filter) {
	            Predicate customerEqualCondition = cb.equal(from.get(Order_.customer), orderFilter.getCustomer());
	            Predicate dateEqualCondition = cb.equal(from.get(Order_.date), orderFilter.getDate());
	            Predicate sumEqualCondition = cb.equal(from.get(Order_.sum), orderFilter.getSum());
	           
	            cq.where(cb.or(customerEqualCondition,dateEqualCondition,sumEqualCondition));
	        }
	        // set fetching
	        if (orderFilter.isFetchCustomer()) {
	            from.fetch(Order_.customer, JoinType.LEFT);
	        }
	        if (orderFilter.isFetchProduct()) {
	            from.fetch(Order_.products, JoinType.LEFT);
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
