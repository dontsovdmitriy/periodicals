package model.service;

import java.util.*;

import model.entity.periodical.Publisher;

/**
 * The {@code PublisherService} interface provides methods for {@code Publisher} object.
 */
public interface PublisherService {
	
	/**
     * Method add publisher
     * @param publisher publisher
     * @return true if the addition is successful
     */
	boolean addPublisher(Publisher publisher);
	
	/**
     * Method find all publisher 
     * @return List of periodical 
     */
	List<Publisher> findAll();
	
	/**
     * Method find publisher by id
     * @param id
     * @return Optional<Publisher>
     */
	Optional<Publisher> findPublisher(long id);
}
