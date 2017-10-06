package model.service;

import java.util.*;

import model.entity.periodical.Publisher;

public interface PublisherService {
	
	boolean addPublisher(Publisher publisher);
	List<Publisher> findAll();
	Optional<Publisher> findPublisher(long id);
}
