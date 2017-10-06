package model.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import model.dao.DaoFactory;
import model.dao.UtilDao;
import model.dao.interfaces.DaoConnection;
import model.dao.interfaces.UserDao;
import model.dao.jdbc.JdbcDaoConnection;
import model.dao.jdbc.JdbcUserDao;
import model.entity.user.User;
import model.service.UserService;


@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilDao.class)
@PowerMockIgnore("javax.management.*")
public class UserServiceImplTest {
	private DaoFactory daoFactory;
	private DaoConnection connection;
	private UserDao userDao;
	private UserService userService;
//	private User user;
//	private Optional<User> userOptional;


	

	@Before
	public void init() {
		daoFactory = mock(DaoFactory.class);
		connection = mock(JdbcDaoConnection.class);
		userDao = mock(JdbcUserDao.class);
//		user = mock(User.class);
		userService = new UserServiceImpl(daoFactory);
//		userOptional = Optional.of(user);

	}
	
	 @Test
	    public void findOneById_Test() throws Exception {
		 	User user = new User();
	        Optional<User> userOptional = Optional.of(user);
	        when(daoFactory.getConnection()).thenReturn(connection);
	        when(daoFactory.createUserDao(connection)).thenReturn(userDao);
	        when(userDao.find(1l)).thenReturn(userOptional);      
	        Optional<User> resultUser = userService.findOneById(1l);
	        verify(daoFactory).getConnection();
	        verify(daoFactory).createUserDao(connection);
	        verify(userDao).find(1l);
	        assertEquals(userOptional, resultUser);
	    }
	 
	 @Test
	    public void addUser_Test() throws Exception {
		 	User user = new User.Builder()
		 			.setEmail("egor@gmail.com")
		 			.setLogin("egor123")
		 			.build();
	        when(daoFactory.getConnection()).thenReturn(connection);
	        when(daoFactory.createUserDao(connection)).thenReturn(userDao);
	        when(userDao.emailExistsInDb("egor@gmail.com")).thenReturn(false);
	        when(userDao.loginExistsInDb("egor123")).thenReturn(false);
	        when(userDao.add(user)).thenReturn(1l);
	        boolean result = userService.addUser(user);
	        verify(daoFactory).getConnection();
	        verify(daoFactory).createUserDao(connection);
	        verify(userDao).emailExistsInDb(user.getEmail());
	        verify(userDao).loginExistsInDb(user.getLogin());
	        verify(userDao).add(user);
	        assertTrue(result);
	    }
	
	 @Test
	    public void login_Test() throws Exception {
	        PowerMockito.mockStatic(UtilDao.class);
	        User user = new User();
	        Optional<User> userOptionalLogin = Optional.of(user);
	        
	        when(daoFactory.getConnection()).thenReturn(connection);
	        when(daoFactory.createUserDao(connection)).thenReturn(userDao);
	        when(userDao.findByEmail("egor@gmail.com")).thenReturn(userOptionalLogin);
	 //       when(userDao.findByEmail("egor@gmail.com").isPresent()).thenReturn(false);
	        PowerMockito.when(UtilDao.checkPassword("egor123", user.getPassword())).thenReturn(true);
	        Optional<User> userResult = userService.login("egor@gmail.com", "egor123");
	       /* verify(daoFactory).getConnection();
	        verify(daoFactory).createUserDao(connection);
	        verify(userDao).findByEmail("egor@gmail.com");	 
	        verify(userDao).findByEmail("egor@gmail.com");	*/        
	        assertEquals(userOptionalLogin, userResult);
	    }
}
