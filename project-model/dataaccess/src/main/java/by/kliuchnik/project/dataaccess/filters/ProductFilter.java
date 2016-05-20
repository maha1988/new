package by.kliuchnik.project.dataaccess.filters;

import java.math.BigDecimal;

import javax.persistence.metamodel.SingularAttribute;

import by.kliuchnik.project.datamodel.Sklad;
import by.kliuchnik.project.datamodel.Unit;

public class ProductFilter {
	
		    private String productName;
		    private Unit unit;
		    private Long currentQuantity;
		    private BigDecimal price;
		    private Sklad sklad;
		    
		    
		    private SingularAttribute sortProperty;
		    private boolean sortOrder;
		    private Integer offset;
		    private Integer limit;

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

			public boolean isFetchSklad () {
				return isFetchSklad;
			}

			public void setFetchSklad (boolean isFetchSklad ) {
				this.isFetchSklad = isFetchSklad;
			}
}


		 




