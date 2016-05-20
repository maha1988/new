package com.kliuchnik.project.dataaccess.filters;

import javax.persistence.metamodel.SingularAttribute;

import com.kliuchnik.project.datamodel.Product;

public class SkladFilter extends AbstractFilter {
	
	        private String name;
	        private Product products;
	       		    
		    
		   

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

		   			public boolean isFetchProduct() {
				return isFetchProduct;
			}

			public void setFetchProduct(boolean isFetchProduct)
			{
				this.isFetchProduct = isFetchProduct;
			}

		}




