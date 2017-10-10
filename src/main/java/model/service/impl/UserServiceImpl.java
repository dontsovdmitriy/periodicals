package model.service.impl;

import java.util.*;

import org.apache.log4j.Logger;

import model.dao.*;
import model.dao.interfaces.*;
import model.entity.user.User;
import model.service.UserService;

public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	private DaoFactory daoFactory;

	private static class Holder{
		static final UserService INSTANCE = new UserServiceImpl( DaoFactory.getInstance() ); 
	}

	private UserServiceImpl(DaoFactory instance) {
		this.daoFactory = instance;
	}

	public static UserService getInstance(){
		return Holder.INSTANCE;
	}

	public  boolean addUser(User user) {
		try(DaoConnection connection = daoFactory.getConnection()){

			UserDao userDao = daoFactory.createUserDao(connection);

			if(userDao.emailExistsInDb(user.getEmail())||userDao.loginExistsInDb(user.getLogin())){
				logger.info("User with email = " + user.getEmail() + " or login = " + user.getLogin() + " already exists in DB");
				return false;

			}
			if (userDao.add(user)>=0) {
				logger.info("User with email = " + user.getEmail() + " add to DB");
				return true;
			}
			logger.info("User with email = " + user.getEmail() + " didn't add to DB");
			return false;
		} 
	}

	public Optional<User> login(String email, String password) {
		try (DaoConnection connection = daoFactory.getConnection()){

			UserDao userDao = daoFactory.createUserDao(connection);
			
			Optional<User> user = userDao.findByEmail(email);
			
			if (!user.isPresent()) {
				logger.info("User with email = " + email + " didn't find in DB");
				return Optional.empty();
			}

			if (UtilDao.checkPassword(password, user.get().getPassword())) {
				logger.info("User with email = " + email + "password check is ok");
			return user;
			}
			return Optional.empty();
		}
	}

	public Optional<User> findOneById(long id)  {
		try(DaoConnection connection = daoFactory.getConnection()){
			UserDao userDao = daoFactory.createUserDao(connection);
			return userDao.find(id);
		}
	}
}
