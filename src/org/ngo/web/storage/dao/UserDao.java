package org.ngo.web.storage.dao;

import org.ngo.web.storage.entity.User;


public interface UserDao extends IOperations<User>{

	//public void insert(User user);
	
	//public User getById(Long id);
	
	public User getByName(String name);
	
}