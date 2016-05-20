package com.kliuchnik.project.dataaccess.filters;

import java.math.BigDecimal;

import javax.persistence.metamodel.SingularAttribute;

import com.kliuchnik.project.datamodel.Sklad;
import com.kliuchnik.project.datamodel.Unit;

public class ProductFilter extends AbstractFilter {
	 
	
		    private String productName;
		    private Unit unit;
		    private Long currentQuantity;
		    private BigDecimal price;
		    private Sklad sklad;
		    
		    
		    private boolean isFetchSklad;
		    private boolean isFetchOrder;

			public boolean isFetchOrder() {
				return isFetchOrder;
			}

			public void setFetchOrder(boolean isFetchOrder) {
				this.isFetchOrder = isFetchOrder;
			}

			public String getProductName() {
				return productName;
			}

			public void setProductName(String productName) {
				this.productName = productName;
			}

			public Unit getUnit() {
				return unit;
			}

			public void setUnit(Unit unit) {
				this.unit = unit;
			}

			public Long getCurrentQuantity() {
				return currentQuantity;
			}

			public void setCurrentQuantity(Long currentQuantity) {
				this.currentQuantity = currentQuantity;
			}

			public BigDecimal getPrice() {
				return price;
			}

			public void setPrice(BigDecimal price) {
				this.price = price;
			}

			public Sklad getSklad() {
				return sklad;
			}

			public void setSklad(Sklad sklad) {
				this.sklad = sklad;
			}

			
			public boolean isFetchSklad () {
				return isFetchSklad;
			}

			public void setFetchSklad (boolean isFetchSklad ) {
				this.isFetchSklad = isFetchSklad;
			}
}


		 




