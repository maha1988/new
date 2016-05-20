package com.kliuchnik.project.dataaccess.filters;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;

import com.kliuchnik.project.datamodel.Customer;

public class OrderFilter extends AbstractFilter{
		
			        
			    private Date date;
			    private BigDecimal sum;
			    private Customer customer;
			    
			    
			    private boolean isFetchProduct;
			    private boolean isFetchCustomer;
				public Date getDate() {
					return date;
				}
				public void setDate(Date date) {
					this.date = date;
				}
				public BigDecimal getSum() {
					return sum;
				}
				public void setSum(BigDecimal sum) {
					this.sum = sum;
				}
				public Customer getCustomer() {
					return customer;
				}
				public void setCustomer(Customer customer) {
					this.customer = customer;
				}
				
				public boolean isFetchProduct() {
					return isFetchProduct;
				}
				public void setFetchProduct(boolean isFetchProduct) {
					this.isFetchProduct = isFetchProduct;
				}
				public boolean isFetchCustomer() {
					return isFetchCustomer;
				}
				public void setFetchCustomer(boolean isFetchCustomer) {
					this.isFetchCustomer = isFetchCustomer;
				}

				
			




}
