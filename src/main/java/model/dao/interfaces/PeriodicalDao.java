package model.dao.interfaces;

import model.entity.periodical.Periodical;

/**
 * The interface describes the behavior of the {@code PeriodicalDao} object.
 */
public interface PeriodicalDao extends GenericDao<Periodical> {
	
	/**
     * Returns true if a periodical with this name is already contained in the database
     * 
     * @param periodical name
     */
	boolean periodicalNameExistsInDb(String name);
}
