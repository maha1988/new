package com.kliuchnik.project.dataaccess.filters;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;

import com.kliuchnik.project.datamodel.Customer;
import com.kliuchnik.project.datamodel.User;

public class OrderFilter extends AbstractFilter{
		
			        
			    private Date date;
			    private BigDecimal sum;
			    private Customer customer;
			    private User user;
			    
			   
				private boolean isFetchUser;
			    private boolean isFetchProduct;
			    private boolean isFetchCustomer;
			   
			    
			    public User getUser() {
					return user;
				}
				public void setUser(User user) {
					this.user = user;
				}
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

				public boolean isFetchUser() {
					return isFetchUser;
				}
				public void setFetchUser(boolean isFetchUser) {
					this.isFetchUser = isFetchUser;
				}
			




}
