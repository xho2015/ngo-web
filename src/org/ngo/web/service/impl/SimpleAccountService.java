package org.ngo.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.ngo.web.common.MyException;
import org.ngo.web.service.AccountService;
import org.ngo.web.storage.dao.AccountDao;
import org.ngo.web.storage.dao.impl.AccountDAOImpl;
import org.ngo.web.storage.entity.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="simpleAccountService")
public class SimpleAccountService implements AccountService {
	private static Logger LOGGER = Logger.getLogger(SimpleAccountService.class);
	
	
	@Resource(name="accountDao")
	private AccountDao accountDao;
	
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={MyException.class, RuntimeException.class})
	public boolean decrease(int accountId, int amount) throws MyException {
		//XHO: (1)the optimistic lock mode can make sure the concurrent modification safe, however,
		// user might experience StaleObjectStateException, meaning that row was updated by another transaction.
		//Account account = accountDao.findOne(Account.class, accountId, LockMode.OPTIMISTIC);
		
		//XHO: (2) let's try PESSIMISTIC_WRITE,
		Account account = accountDao.findOne(Account.class, accountId, LockMode.PESSIMISTIC_WRITE);
		
		int balance = account.getBalance();
		LOGGER.info(String.format("LockMode=[PESSIMISTIC_WRITE], balance=%d", balance));
		if (balance - amount < 0)
			throw new MyException(String.format("balance %d is not sufficent",balance),1);
		
		//XHO: here emulate some long run task after database row loading
		/*try { 
			Thread.currentThread().sleep(10 * 1000);
		} catch (InterruptedException e) {
		}*/
		
		balance -= amount;
		account.setBalance(balance);
		return true;
	}

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={MyException.class, RuntimeException.class})
	public void create(int accountId, int amount) throws MyException {
		Account account = new Account();
		account.setId(accountId);
		account.setBalance(amount);
		accountDao.create(account);				
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={MyException.class, RuntimeException.class})
	public void increase(int accountId, int amount) throws MyException {
		//XHO: the lock Mode to be decided here ?
		Account account = accountDao.findOne(Account.class, accountId, LockMode.PESSIMISTIC_WRITE);
		int newbalance = account.getBalance() + amount;
		account.setBalance(newbalance);
		accountDao.update(account);	
	}

}
