package model.dao.interfaces;

import model.entity.periodical.Periodical;

public interface PeriodicalDao extends GenericDao<Periodical> {
	
	public boolean periodicalNameExistsInDb(String name);

}
