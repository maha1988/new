package com.kliuchnik.project.dataaccess;

import java.util.List;

import com.kliuchnik.project.dataaccess.filters.ProductFilter;
import com.kliuchnik.project.datamodel.Product;

public interface ProductDao extends AbstractDao<Product, Long> {
	
	Long count(ProductFilter filter);
	
	 List<Product> find(ProductFilter filter);
}
