package model.service;

import java.util.*;

import model.entity.user.User;

public interface UserService {
	
	boolean addUser(User user);
	Optional<User> login(String email, String password);
	Optional<User> findOneById(long id);
}


