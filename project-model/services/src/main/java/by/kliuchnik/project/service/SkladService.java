package by.kliuchnik.project.service;

import java.util.List;

import javax.transaction.Transactional;

import by.kliuchnik.project.dataaccess.filters.SkladFilter;
import by.kliuchnik.project.datamodel.Sklad;

public interface SkladService {

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
