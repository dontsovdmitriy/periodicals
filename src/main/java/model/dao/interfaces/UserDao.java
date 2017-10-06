package model.dao.interfaces;

import java.util.Optional;

import model.entity.user.User;

public interface UserDao extends GenericDao<User>{
	
    boolean emailExistsInDb(String user);
	boolean loginExistsInDb(String user);
    Optional<User> findByEmail(String email);

}
