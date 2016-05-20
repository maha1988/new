package by.kliuchnik.project.dataaccess.filters;

import javax.persistence.metamodel.SingularAttribute;

import by.kliuchnik.project.datamodel.Product;

public class SkladFilter {
	
	        private String name;
	        private Product products;
	       		    
		    
		    private SingularAttribute sortProperty;
		    private boolean sortOrder;
		    private Integer offset;
		    private Integer limit;

		    private boolean isFetchProduct;

		    public Product getProducts() {
				return products;
			}

			public void setProducts(Product products) {
				this.products = products;
			}

					public String getName() {
		        return name;
		    }

		    public void setName(String name) {
		        this.name = name;
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

			public void setFetchProduct(boolean isFetchProduct)
			{
				this.isFetchProduct = isFetchProduct;
			}

		}




