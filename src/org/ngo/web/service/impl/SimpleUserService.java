package org.ngo.web.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.ngo.web.service.UserService;
import org.ngo.web.storage.dao.UserDao;
import org.ngo.web.storage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service(value="simpleUserService")
public class SimpleUserService implements UserService {

	private final static String SALT = "Random$SaltValue#WithSpecialCharacters12@$@4&#%^$*";

	@Resource(name="userDao")
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Transactional
	public void insertUser(User user) {
		user.setUserPass(DigestUtils.md5Hex(user.getUserPass() + SALT));
		userDao.create(user);
	}

	@Transactional(readOnly = true)
	public User getUserById(Long id) {
		return userDao.findOne(User.class, id);
	}

	@Transactional(readOnly = true)
	public User getUserByName(String name) {
		return userDao.getByName(name);
	}

	@Override
	public boolean validatePassword(User user, String password) {
		if (user == null || StringUtils.isEmpty(password))
			return false;
		
		return (user.getUserPass().equals(DigestUtils.md5Hex( password + SALT)));
	}

	@Transactional
	public void lastLogin(Long id) {
		userDao.findOne(User.class, id).setLastLogin(new Date());		
	}
	
	

}
