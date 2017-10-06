package model.service.impl;

import java.util.*;

import org.apache.log4j.Logger;

import model.dao.DaoFactory;
import model.dao.interfaces.*;
import model.entity.periodical.PeriodicalCategory;
import model.service.PeriodicalCategoryService;

public class PeriodicalCategoryServiceImpl implements PeriodicalCategoryService {
	
	private static final Logger logger = Logger.getLogger(PeriodicalCategoryServiceImpl.class);

	DaoFactory daoFactory;

	private static class Holder{
		static final PeriodicalCategoryService INSTANCE = new PeriodicalCategoryServiceImpl( DaoFactory.getInstance() ); 
	}

	PeriodicalCategoryServiceImpl(DaoFactory instance) {
		this.daoFactory = instance;
	}

	public static PeriodicalCategoryService getInstance(){
		return Holder.INSTANCE;
	}

	public  boolean addCategory(PeriodicalCategory category) {
		try(DaoConnection connection = daoFactory.getConnection()){

			PeriodicalCategoryDao categoryDao = daoFactory.createPeriodicalCategoryDao(connection);

			if(categoryDao.categoryExistsInDb(category.getCategoryName())){
				logger.info("Periodical category with name = "  + category.getCategoryName() + " already exists in DB");
				return false;
			}
			if (categoryDao.add(category)>=0) {
				logger.info("Periodical category with name = " + category.getCategoryName() + " add to DB");
				return true; 
			}
			logger.info("Periodical category with name = " + category.getCategoryName() + " didn't add to DB");
			return false;
		} 
	}

	public List<PeriodicalCategory> findAll(){
		try(DaoConnection connection = daoFactory.getConnection()){			
			PeriodicalCategoryDao categoryDao = daoFactory.createPeriodicalCategoryDao(connection);
			return categoryDao.findAll();
		}
	}

	public  Optional<PeriodicalCategory> findOneById(long id) {
		try(DaoConnection connection = daoFactory.getConnection()){
			PeriodicalCategoryDao categoryDao = daoFactory.createPeriodicalCategoryDao(connection);
			return categoryDao.find(id);
		}
	}
}
