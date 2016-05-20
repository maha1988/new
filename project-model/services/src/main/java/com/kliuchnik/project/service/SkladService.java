package com.kliuchnik.project.service;

import java.util.List;

import javax.transaction.Transactional;

import com.kliuchnik.project.dataaccess.filters.ProductFilter;
import com.kliuchnik.project.dataaccess.filters.SkladFilter;
import com.kliuchnik.project.datamodel.Sklad;

public interface SkladService {
	
	 Long count(SkladFilter filter);
	 @Transactional
	    void register(Sklad sklad );

	    Sklad getSklad(Long id);

	    @Transactional
	    void update(Sklad sklad );

	    @Transactional
	    void delete(Long id);
	   
	    List<Sklad> find(SkladFilter skladFilter);

	    List<Sklad> getAll();
}
