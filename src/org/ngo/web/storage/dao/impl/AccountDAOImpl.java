package org.ngo.web.storage.dao.impl;



import java.util.List;
import org.hibernate.Query;
import org.ngo.web.storage.dao.AbstractDao;
import org.ngo.web.storage.dao.AccountDao;
import org.ngo.web.storage.entity.Account;
import org.springframework.stereotype.Repository;


@Repository("accountDao")
public class AccountDAOImpl extends AbstractDao<Account> implements AccountDao {

	@Override
	public List<Account> query(long id)
	{
		Query q = this.getCurrentSession().createQuery("from Account a where a.id = :p1");
		q.setParameter("p1", id);
		return q.list();
	}
}
