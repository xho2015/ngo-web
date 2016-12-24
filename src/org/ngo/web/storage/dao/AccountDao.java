package org.ngo.web.storage.dao;

import java.util.List;

import org.ngo.web.common.MyException;
import org.ngo.web.storage.entity.Account;


public interface AccountDao extends IOperations<Account> {

	public List<Account> query(long accountId);
}
