package com.demo.services;

import com.demo.entities.Post;
import com.demo.entities.User;

public interface UserService {

	void addUser(User user);

	boolean validateUser(User user);

	boolean login(String username, String password);
	
	User getUser(String username);

	void updateUser(User user);


	
}
