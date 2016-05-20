package by.kliuchnik.project.service.impl;



import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import by.kliuchnik.project.dataaccess.ProductDao;
import by.kliuchnik.project.dataaccess.SkladDao;
import by.kliuchnik.project.dataaccess.filters.ProductFilter;

import by.kliuchnik.project.datamodel.Product;
import by.kliuchnik.project.datamodel.Sklad;
import by.kliuchnik.project.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private static Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Inject
	private ProductDao productDao;
	@Inject
	private SkladDao skladDao;

	
	@Override
	public void register(Product product, Sklad sklad) {
		productDao.insert(product);
		sklad.setProducts(null);

		skladDao.insert(sklad);
		 LOGGER.info("Product regirstred: {}", product);

	}

	@Override
	public Product getProduct(Long id) {
		LOGGER.info("Product select: {}", productDao.get(id));
		return productDao.get(id);
	}

	@Override
	public Sklad getSklad(Long id) {
		LOGGER.info("Sklad select: {}", skladDao.get(id));
		return skladDao.get(id);
	}
	@Override
	public void update(Product product) {
		LOGGER.info("Product update, new and old: {}", product, productDao.get(product.getId()));
	productDao.update(product);

	}

	@Override
	public void delete(Long id) {
		LOGGER.info("Product delete: {}", productDao.get(id));
		//productDao.delete(id);

	}
	@Override
	public List<Product> find(ProductFilter productFilter) {
		LOGGER.info("Product find by filter: {}", productFilter);
		return productDao.find(productFilter);
	}

	@Override
	public List<Product> getAll() {
		LOGGER.info("Product getAll: {}", "All products");
		return productDao.getAll();
	}
}
