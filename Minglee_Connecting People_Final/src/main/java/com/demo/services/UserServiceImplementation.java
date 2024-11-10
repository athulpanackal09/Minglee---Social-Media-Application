package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.User;
import com.demo.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository repo;

	@Override
	public void addUser(User user) {
		repo.save(user);
	}

	@Override
	public boolean validateUser(User user) {
		
		String username=user.getUsername();
		String email=user.getEmail();
		User user1=repo.findByUsername(username);
		User user2=repo.findByEmail(email);
		if(user1!=null || user2!=null)
		{
			return false;
		}
		else
		{
			return true;
		}
		
	}

	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		User user=repo.findByUsername(username);
		String db_password=user.getPassword();
		if(db_password.equals(password))
		{
			return true;
		}
		return false;
		
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return repo.findByUsername(username);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		repo.save(user);
		
	}



	
	
}
