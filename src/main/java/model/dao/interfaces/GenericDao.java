package model.dao.interfaces;

import java.util.*;

public interface GenericDao<E> {
	
	/**
     * Retrieves an entity by its id.
     *
     * @param id must not be null
     * @return object of the class Optional <User>
     */
    Optional<E> find(long id);
    
    /**
     * Creates a new entity taking values for the fields from the passed entity.
     * If a passed entity has the 'id' field it is ignored.
     *
     * @param entity an object to be persisted
     * @return a persisted entity's id
     */
    long add(E e);
   
    /**
    * Update entity taking values for the fields from the passed entity.
    *
    * @param entity an object to be persisted
    * @return a persisted entity's id
    */
    long update(E e);
    
    /**
     * Returns all entities from the db.
     *
     * @return all entities
     */
	List<E> findAll();
}
