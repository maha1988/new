package com.kliuchnik.project.dataaccess.filters;

import com.kliuchnik.project.datamodel.Product;

public class SkladFilter extends AbstractFilter {
	
	        private String name;
	        private Product product;
	       		    
		    
		   

		    private boolean isFetchProduct;

		    public Product getProduct() {
				return product;
			}

			public void setProduct(Product product) {
				this.product = product;
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




