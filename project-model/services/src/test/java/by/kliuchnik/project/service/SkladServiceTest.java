package by.kliuchnik.project.service;

import java.lang.reflect.Field;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.kliuchnik.project.dataaccess.SkladDao;
import by.kliuchnik.project.dataaccess.filters.SkladFilter;

import by.kliuchnik.project.dataaccess.impl.AbstractDaoImpl;

import by.kliuchnik.project.datamodel.Sklad;
import by.kliuchnik.project.datamodel.Sklad_;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })

public class SkladServiceTest {

	@Inject
	private SkladService skladService;

	@Inject
	private SkladDao skladDao;

	@Test
	public void test() {
		Assert.assertNotNull(skladService);
	}

	@Test
	public void testEntityManagerInitialization()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field f = AbstractDaoImpl.class.getDeclaredField("entityManager");
		f.setAccessible(true);
		EntityManager em = (EntityManager) f.get(skladDao);

		Assert.assertNotNull(em);
	}

	@Test
	public void testSklad() {
		Sklad sklad = new Sklad();

		sklad.setName("clothes");

		skladService.register(sklad);

		Sklad registredSklad = skladService.getSklad(sklad.getId());

		Assert.assertNotNull(registredSklad);

		String updatedFName = "updatedName";
		sklad.setName(updatedFName);
		skladService.update(sklad);

		// Assert.assertEquals(updatedFName,
		// skladService.getSklad(sklad.getId()).getName());

		skladService.delete(sklad.getId());

		// Assert.assertNull(skladService.getSklad(sklad.getId()));
	}

	@Test
	public void testSearch() {
		// clean all data from sklad
		List<Sklad> all = skladService.getAll();
		for (Sklad sklad : all) {
			skladService.delete(sklad.getId());
		}

		// start create new data
		int testObjectsCount = 5;
		for (int i = 0; i < testObjectsCount; i++) {
			Sklad sklad = new Sklad();

			sklad.setName("clothes" + i);

			skladService.register(sklad);

		}

		SkladFilter skladFilter = new SkladFilter();
		List<Sklad> result = skladService.find( skladFilter);
		Assert.assertEquals(testObjectsCount, result.size());
		
		// test paging
	     skladFilter.setFetchProduct(true);
		 int limit = 3;
		 skladFilter.setLimit(limit);
		 skladFilter.setOffset(0);
		 result = skladService.find( skladFilter);
		 Assert.assertEquals(limit, result.size());

		// test sort

		 skladFilter.setLimit(null);
		 skladFilter.setOffset(null);
		 skladFilter.setSortOrder(true);
		 skladFilter.setSortProperty(Sklad_.name);
		result = skladService.find( skladFilter);
		Assert.assertEquals(testObjectsCount, result.size());

	}
}
