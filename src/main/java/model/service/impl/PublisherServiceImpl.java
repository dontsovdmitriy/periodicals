package model.service.impl;

import java.util.*;

import org.apache.log4j.Logger;

import model.dao.DaoFactory;
import model.dao.interfaces.*;
import model.entity.periodical.Publisher;
import model.service.PublisherService;

public class PublisherServiceImpl implements PublisherService {
	
	private static final Logger logger = Logger.getLogger(PublisherServiceImpl.class);

	DaoFactory daoFactory;

	private static class Holder{
		static final PublisherService INSTANCE = new PublisherServiceImpl( DaoFactory.getInstance() ); 
	}

	PublisherServiceImpl(DaoFactory instance) {
		this.daoFactory = instance;
	}

	public static PublisherService getInstance(){
		return Holder.INSTANCE;
	}

	public  boolean addPublisher(Publisher publisher) {
		try(DaoConnection connection = daoFactory.getConnection()){

			PublisherDao publisherDao = daoFactory.createPublisherDao(connection);

			if(publisherDao.publisherExistsInDb(publisher.getPublisher())){
				logger.info("Publisher with name = "  + publisher.getPublisher() + " already exists in DB");
				return false;
			}
			
			if (publisherDao.add(publisher)>=0) {
				logger.info("Publisher with name = "  + publisher.getPublisher() + " add to DB");
				return true;
			}
			logger.info("Publisher with name = "  + publisher.getPublisher() + " didn't add to DB");
			return false;

		}
	}
	public List<Publisher> findAll(){
		try(DaoConnection connection = daoFactory.getConnection()){			
			PublisherDao publisherDao = daoFactory.createPublisherDao(connection);
			return publisherDao.findAll();
		}
	}

	public Optional<Publisher> findPublisher(long id){
		try(DaoConnection connection = daoFactory.getConnection()){			
			PublisherDao publisherDao = daoFactory.createPublisherDao(connection);
			return publisherDao.find(id);
		}
	}
}
