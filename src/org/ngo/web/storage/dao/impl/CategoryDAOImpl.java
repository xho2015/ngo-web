package org.ngo.web.storage.dao.impl;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.ngo.web.storage.dao.AbstractDao;
import org.ngo.web.storage.dao.AccountDao;
import org.ngo.web.storage.dao.CategoryDao;
import org.ngo.web.storage.entity.Account;
import org.ngo.web.storage.entity.Category;
import org.springframework.stereotype.Repository;


@Repository("categoryDao")
public class CategoryDAOImpl extends AbstractDao<Category> implements CategoryDao {

	public List<Category> query(long parentId)
	{
		Query query = this.getCurrentSession().createQuery("from Category c where c.parent.Id in (:p1)");
		List params = new ArrayList();
		params.add(parentId);
		query.setParameterList("p1", params);
		return query.list();
	}

}
