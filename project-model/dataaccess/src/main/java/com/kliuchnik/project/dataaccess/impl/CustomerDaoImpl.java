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

import com.kliuchnik.project.dataaccess.CustomerDao;
import com.kliuchnik.project.dataaccess.filters.CustomerFilter;
import com.kliuchnik.project.dataaccess.filters.SkladFilter;
import com.kliuchnik.project.datamodel.Customer;
import com.kliuchnik.project.datamodel.Customer_;
import com.kliuchnik.project.datamodel.Sklad;
import com.kliuchnik.project.datamodel.User_;


@Repository
public class CustomerDaoImpl extends AbstractDaoImpl<Customer, Long> implements CustomerDao {

	protected CustomerDaoImpl() {
		super(Customer.class);
	}
	@Override
    public Long count(CustomerFilter customerFilter) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Customer> from = cq.from(Customer.class);
        cq.select(cb.count(from));
        TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult();
	}
	@Override

	public List<Customer> find (CustomerFilter filter) {
        EntityManager em = getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);

        Root<Customer> from = cq.from(Customer.class);
     // set selection
        cq.select(from);// Указывает что селектать SELECT *. from - это
		// таблица,
		// а from.get... - это конкретная колонка
        
        boolean name = (filter.getName() != null);
		boolean password = (filter.getPassword() != null);
		boolean role = (filter.getRole() != null);
		boolean address = (filter.getAddress() != null);
		boolean bankR = (filter.getBankR() != null);
		
		boolean filtr = (name || password || role || address || bankR );

        if (filtr) {
        	Predicate nameEqualCondition = cb.equal(from.get(Customer_.user).get(User_.name),filter.getName());
            Predicate passwordEqualCondition = cb.equal(from.get(Customer_.user).get(User_.password),filter.getPassword());
            Predicate roleEqualCondition = cb.equal(from.get(Customer_.user).get(User_.role),filter.getRole());
            	            
	       Predicate addressEqualCondition = cb.equal(from.get(Customer_.address), filter.getAddress());
	       Predicate bankREqualCondition = cb.equal(from.get(Customer_.bankR), filter.getBankR());
	       
	        cq.where(cb.or(nameEqualCondition,passwordEqualCondition,roleEqualCondition,addressEqualCondition, bankREqualCondition));    
        }
	                       
         // set fetching
        if (filter.isFetchUser()) {
            from.fetch(Customer_.user, JoinType.LEFT);
        }
        if (filter.isFetchOrder()) {
			from.fetch(Customer_.orders, JoinType.LEFT);
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
