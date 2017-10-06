package model.dao.interfaces;

import model.entity.periodical.PeriodicalCategory;

public interface PeriodicalCategoryDao extends GenericDao<PeriodicalCategory> {
	
	public boolean categoryExistsInDb(String category);

}
