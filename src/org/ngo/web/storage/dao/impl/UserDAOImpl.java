package org.ngo.web.storage.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.ngo.web.storage.dao.AbstractDao;
import org.ngo.web.storage.dao.UserDao;
import org.ngo.web.storage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("userDao")
public class UserDAOImpl extends AbstractDao<User> implements UserDao {

	//XHO: @Autowired also works here
	//@Resource(name="sessionFactory")
	//private SessionFactory sessionFactory;

    //public void setSessionFactory(SessionFactory sessionFactory) {
    //    this.sessionFactory = sessionFactory;
    //}
    
	
	@Override
	public User getByName(String name) {
		 List<User> result = getCurrentSession().createQuery("from User u where u.userName = (:p1)").setParameter("p1", name).list();
		 return result.size() > 0 ? result.get(0) : null;
	}
}
