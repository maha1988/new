package by.kliuchnik.project.dataaccess;

import java.util.List;

import by.kliuchnik.project.dataaccess.filters.ProductFilter;
import by.kliuchnik.project.datamodel.Product;

public interface ProductDao extends AbstractDao<Product, Long> {
	 List<Product> find(ProductFilter productFilter);
}
