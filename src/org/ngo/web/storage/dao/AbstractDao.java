package org.ngo.web.storage.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

//XHO: abstract Service & DAO approach to be involved
//http://www.cnblogs.com/leiOOlei/p/3780290.html


public abstract class AbstractDao <T extends Serializable> implements IOperations<T>{
	
    
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    
    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
        //return sessionFactory.openSession();
    }

    @Override
    public final T findOne(Class<T> clazz, final long id) {
    	Session session = getCurrentSession();
    	return (T)session.load(clazz, id);
    }
    
    @Override
    public final T findOne(Class<T> clazz, final long id, LockMode lock) {
    	Session session = getCurrentSession();
    	return (T)session.load(clazz, id, lock);
    }

    @Override
    public final List<T> findAll(Class<T> clazz) {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    @Override
    public final void create(final T entity) {
         //getCurrentSession().persist(entity);
         getCurrentSession().save(entity);
    }

    @Override
    public final T update(final T entity) {
        getCurrentSession().update(entity);
        return entity;
        //return (T)getCurrentSession().merge(entity);
    }

    @Override
    public final void delete(final T entity) {
         getCurrentSession().delete(entity);
    }

    @Override
    public final void deleteById(Class<T> clazz, final long entityId) {
        final T entity = findOne(clazz, entityId);
        delete(entity);
    }
}
