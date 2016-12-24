package org.ngo.web.storage.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.LockMode;

public interface IOperations <T extends Serializable> {
    
    T findOne(Class<T> clazz, final long id);

    List<T> findAll(Class<T> clazz);

    void create(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(Class<T> clazz, final long entityId);

	T findOne(Class<T> clazz, long id, LockMode lock);

}
