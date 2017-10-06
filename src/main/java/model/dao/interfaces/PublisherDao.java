package model.dao.interfaces;

import model.entity.periodical.Publisher;

public interface PublisherDao extends GenericDao<Publisher>{
	
	public boolean publisherExistsInDb(String publisher);

}
