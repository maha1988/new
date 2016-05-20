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

import com.kliuchnik.project.dataaccess.ProductDao;
import com.kliuchnik.project.dataaccess.filters.ProductFilter;
import com.kliuchnik.project.datamodel.Product;
import com.kliuchnik.project.datamodel.Product_;



@Repository
public class ProductDaoImpl extends AbstractDaoImpl<Product, Long> implements ProductDao {

	public ProductDaoImpl() {
		super(Product.class);
		
	}

	 @Override
	    public Long count(ProductFilter productFilter) {
	        EntityManager em = getEntityManager();
	        CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
	        Root<Product> from = cq.from(Product.class);
	        cq.select(cb.count(from));
	        TypedQuery<Long> q = em.createQuery(cq);
	        return q.getSingleResult();
	 }
	@Override
    public List<Product>find (ProductFilter productFilter) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> from = cq.from(Product.class);
     
        // set selection
        cq.select(from);
        
        boolean nameProduct = (productFilter.getProductName() != null);
		boolean unit = (productFilter.getUnit() != null);
		boolean price = (productFilter.getPrice() != null);
		boolean currentQuantity = (productFilter.getCurrentQuantity() != null);
		boolean sklad = (productFilter.getSklad() != null);
		boolean filter = (nameProduct || unit || price ||currentQuantity || sklad);
		
        if (filter) {
            Predicate nameEqualCondition = cb.equal(from.get(Product_.productName), productFilter.getProductName());
            Predicate unitEqualCondition = cb.equal(from.get(Product_.unit), productFilter.getUnit());
            Predicate priceEqualCondition = cb.equal(from.get(Product_.price), productFilter.getPrice());
            Predicate currentQuantityEqualCondition = cb.equal(from.get(Product_.currentQuantity), productFilter.getCurrentQuantity());
            Predicate skladEqualCondition = cb.equal(from.get(Product_.sklad), productFilter.getSklad());
           
            cq.where(cb.or(nameEqualCondition,unitEqualCondition,priceEqualCondition,currentQuantityEqualCondition,skladEqualCondition));
        }
        // set fetching
        if (productFilter.isFetchSklad()) {
            from.fetch(Product_.sklad, JoinType.LEFT);
        }
       
        // set sort params
        if (productFilter.getSortProperty() != null) {
            cq.orderBy(new OrderImpl(from.get(productFilter.getSortProperty()),productFilter.isSortOrder()));
        }

        TypedQuery<Product> q = em.createQuery(cq);

        // set paging
        if (productFilter.getOffset() != null && productFilter.getLimit() != null) {
            q.setFirstResult(productFilter.getOffset());
            q.setMaxResults(productFilter.getLimit());
        }

        // set execute query
        List<Product> allitems = q.getResultList();
        return allitems;
    }	



}
