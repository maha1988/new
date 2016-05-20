package by.kliuchnik.project.dataaccess.filters;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;

import by.kliuchnik.project.datamodel.Customer;

public class OrderFilter {
		
			        
			    private Date date;
			    private BigDecimal sum;
			    private Customer customer;
			    
			    
			    private SingularAttribute sortProperty;
			    private boolean sortOrder;
			    private Integer offset;
			    private Integer limit;

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
				public SingularAttribute getSortProperty() {
					return sortProperty;
				}
				public void setSortProperty(SingularAttribute sortProperty) {
					this.sortProperty = sortProperty;
				}
				public boolean isSortOrder() {
					return sortOrder;
				}
				public void setSortOrder(boolean sortOrder) {
					this.sortOrder = sortOrder;
				}
				public Integer getOffset() {
					return offset;
				}
				public void setOffset(Integer offset) {
					this.offset = offset;
				}
				public Integer getLimit() {
					return limit;
				}
				public void setLimit(Integer limit) {
					this.limit = limit;
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
