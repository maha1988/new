package by.kliuchnik.project.dataaccess;

import java.util.List;

import by.kliuchnik.project.dataaccess.filters.SkladFilter;
import by.kliuchnik.project.datamodel.Sklad;


public interface SkladDao extends AbstractDao<Sklad, Long> {
	 List<Sklad> find(SkladFilter skladFilter);
}
