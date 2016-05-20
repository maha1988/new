package com.kliuchnik.project.service;

import java.util.List;

import javax.transaction.Transactional;

import com.kliuchnik.project.dataaccess.filters.ProductFilter;
import com.kliuchnik.project.datamodel.Product;
import com.kliuchnik.project.datamodel.Sklad;

public interface ProductService {
	 Long count(ProductFilter productFilter);
	
	 @Transactional
	    void register(Product product, Sklad sklad );

	    Product getProduct(Long id);
	    Sklad getSklad(Long id);

	    @Transactional
	   void update(Product product );

	    @Transactional
	    void delete(Long id);
	    List<Product> find(ProductFilter productFilter);

	    List<Product> getAll();
}
