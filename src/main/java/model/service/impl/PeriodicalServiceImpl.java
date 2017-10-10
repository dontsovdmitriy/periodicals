package model.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import model.dao.DaoFactory;
import model.dao.interfaces.*;
import model.entity.periodical.Periodical;
import model.service.PeriodicalService;

public class PeriodicalServiceImpl implements PeriodicalService {
	
	private static final Logger logger = Logger.getLogger(PeriodicalServiceImpl.class);

	private DaoFactory daoFactory;

	private static class Holder{
		static final PeriodicalService INSTANCE = new PeriodicalServiceImpl( DaoFactory.getInstance() ); 
	}

	private PeriodicalServiceImpl(DaoFactory instance) {
		this.daoFactory = instance;
	}

	public static PeriodicalService getInstance(){
		return Holder.INSTANCE;
	}

	public  boolean addPeriodical(Periodical periodical) {
		try(DaoConnection connection = daoFactory.getConnection()){

			PeriodicalDao periodicalDao = daoFactory.createPeriodicalDao(connection);

			if(periodicalDao.periodicalNameExistsInDb(periodical.getName())){
				logger.info("Periodical with name = "  + periodical.getName() + " already exists in DB");
				return false;
			}
			
			if (periodicalDao.add(periodical)>=0) {
				logger.info("Periodical with name = "  + periodical.getName() + " add to DB");
				return true;
			}
			logger.info("Periodical with name = "  + periodical.getName() + " didn't add to DB");
			return false;
		} 
	}	

	public  boolean editPeriodical(Periodical periodical) {
		try(DaoConnection connection = daoFactory.getConnection()){

			PeriodicalDao periodicalDao = daoFactory.createPeriodicalDao(connection);

			if (periodicalDao.update(periodical)>0) {
				logger.info("Periodical with name = "  + periodical.getName() + " edit");
				return true;
			} 
			logger.info("Periodical with name = "  + periodical.getName() + " didn't edit");
			return false;
		}
	}	

	public List<Periodical> findAll(){
		try(DaoConnection connection = daoFactory.getConnection()){			
			PeriodicalDao periodicalDao = daoFactory.createPeriodicalDao(connection);
			return periodicalDao.findAll();
		}
	}

	public  Optional<Periodical> findOneById(long id) {
		try(DaoConnection connection = daoFactory.getConnection()){
			PeriodicalDao periodicalDao = daoFactory.createPeriodicalDao(connection);
			return periodicalDao.find(id);
		}
	}
}
