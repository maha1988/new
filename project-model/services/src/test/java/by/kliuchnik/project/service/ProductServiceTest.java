package by.kliuchnik.project.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.kliuchnik.project.dataaccess.ProductDao;
import by.kliuchnik.project.dataaccess.filters.ProductFilter;
import by.kliuchnik.project.dataaccess.impl.AbstractDaoImpl;

import by.kliuchnik.project.datamodel.Product;
import by.kliuchnik.project.datamodel.Sklad;
import by.kliuchnik.project.datamodel.Sklad_;
import by.kliuchnik.project.datamodel.Unit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class ProductServiceTest {

    @Inject
    private ProductService productService;

    @Inject
    private ProductDao productDao;
    
	

    @Test
    public void test() {
        Assert.assertNotNull(productService);
    }

    @Test
    public void testEntityManagerInitialization() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
       Field f = AbstractDaoImpl.class.getDeclaredField("entityManager");
      f.setAccessible(true);
       EntityManager em = (EntityManager) f.get(productDao);

     Assert.assertNotNull(em);
   }

    @Test
    public void testRegistration() {
        Product product = new Product( );
        Sklad sklad = new Sklad();
        
    	product.setProductName("Dress");
        product.setUnit(Unit.PIECE);
        product.setCurrentQuantity(4L);
        product.setPrice(new BigDecimal("2000.00"));
        
        sklad.setName("clothes");
        product.setSklad(sklad);       
                     
        
        productService.register(product, sklad);

        Product registredProduct = productService.getProduct(product.getId());
        Sklad registredSklad = productService.getSklad(sklad.getId());

         Assert.assertNotNull(registredProduct);
         Assert.assertNotNull(registredSklad);
       
        String updatedPName = "updatedName";
        product.setProductName(updatedPName);
        productService.update(product);

       // Assert.assertEquals(updatedPName, productService.getProduct(product.getId()).getProductName());

         productService.delete(product.getId());
         
       //  Assert.assertNull(productService.getSklad(sklad.getId()));
       // Assert.assertNull(productService.getProduct(product.getId()));
    }
    @Test
	public void testSearch() {
		// clean all data from product
		List<Product> all = productService.getAll();
		for (Product product : all) {
			productService.delete(product.getId());
		}

		// start create new data
		int testObjectsCount = 5;
		for (int i = 0; i < testObjectsCount; i++) {
			Product product = new Product();
			 Sklad sklad = new Sklad();		        
			product.setProductName("Dress"+i);
			product.setUnit(Unit.PIECE);
	        product.setCurrentQuantity(4L);
	        product.setPrice(new BigDecimal("6000.00"));
	        sklad.setName("clothes");
	        product.setSklad(sklad);   
	        productService.register(product, sklad);

		}

		ProductFilter productFilter = new ProductFilter();
		List<Product> result = productService.find(productFilter);
		Assert.assertEquals(testObjectsCount, result.size());
		// test paging
		productFilter.setFetchSklad(true);
		int limit = 3;
		productFilter.setLimit(limit);
		productFilter.setOffset(0);
		result = productService.find(productFilter);
		Assert.assertEquals(limit, result.size());

		// test sort

		productFilter.setLimit(null);
		productFilter.setOffset(null);
		productFilter.setSortOrder(true);
		productFilter.setSortProperty(Sklad_.name);
		result = productService.find(productFilter);
		Assert.assertEquals(testObjectsCount, result.size());

	}
    
   	}

