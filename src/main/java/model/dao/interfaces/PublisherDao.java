package model.dao.interfaces;

import model.entity.periodical.Publisher;

/**
 * The interface describes the behavior of the {@code PublisherDao} object.
 */
public interface PublisherDao extends GenericDao<Publisher>{
	
	/**
     * Returns true if a publisher with this name is already contained in the database
     * 
     * @param publisher name
     */
	public boolean publisherExistsInDb(String publisher);
}
