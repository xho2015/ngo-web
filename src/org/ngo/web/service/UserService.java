package org.ngo.web.service;

import org.ngo.web.storage.entity.User;

public interface UserService {
	
	public void insertUser(User user);
	
	public User getUserById(Long id);
	
	public User getUserByName(String id);
	
	public void lastLogin(Long id);

	public boolean validatePassword(User user, String password);
}
