package model.dao.interfaces;

import model.entity.periodical.PeriodicalCategory;

/**
 * The interface describes the behavior of the {@code PeriodicalCategoryDao} object.
 */
public interface PeriodicalCategoryDao extends GenericDao<PeriodicalCategory> {

	/**
	 * Returns true if a category with this name is already contained in the database
	 * 
	 * @param category name
	 */
	public boolean categoryExistsInDb(String category);
}
