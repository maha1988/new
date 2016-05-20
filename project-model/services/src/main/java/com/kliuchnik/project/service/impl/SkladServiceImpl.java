package com.kliuchnik.project.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kliuchnik.project.dataaccess.SkladDao;
import com.kliuchnik.project.dataaccess.filters.ProductFilter;
import com.kliuchnik.project.dataaccess.filters.SkladFilter;
import com.kliuchnik.project.datamodel.Sklad;
import com.kliuchnik.project.service.SkladService;

@Service
public class SkladServiceImpl implements SkladService {
	private static Logger LOGGER = LoggerFactory.getLogger(SkladServiceImpl.class);
	@Inject
	private SkladDao skladDao;

	@Override

	public void register(Sklad sklad) {
		skladDao.insert(sklad);
		 LOGGER.info("Sklad regirstred: {}", sklad);
	}

	@Override
	public Sklad getSklad(Long id) {
		 LOGGER.info("Sklad select: {}", skladDao.get(id));
			
		return skladDao.get(id);
	}

	@Override
	public void update(Sklad sklad) {
		LOGGER.info("Sklad update, new and old: {}", sklad, skladDao.get(sklad.getId()));
		
		skladDao.update(sklad);

	}

	@Override
	public void delete(Long id) {
		LOGGER.info("Sklad delete: {}",skladDao.get(id));
		skladDao.delete(id);

	}
	@Override
    public Long count(SkladFilter skladFilter) {
        return skladDao.count(skladFilter);
    }
	@Override
	public List<Sklad>find (SkladFilter skladFilter) { 
		LOGGER.info("Sklad find by filter: {}", skladFilter);
		return skladDao.find(skladFilter);
	}

	@Override
	public List<Sklad> getAll() {
		LOGGER.info("Sklad getAll: {}", "All Sklad");
		return skladDao.getAll();
	}
}
