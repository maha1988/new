package by.kliuchnik.project.service;

import java.util.List;

import javax.transaction.Transactional;

import by.kliuchnik.project.dataaccess.filters.ProductFilter;

import by.kliuchnik.project.datamodel.Product;
import by.kliuchnik.project.datamodel.Sklad;

public interface ProductService {

	
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
