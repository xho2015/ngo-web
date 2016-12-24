package org.ngo.web.unittest;


import javax.annotation.Resource;

import org.apache.struts2.StrutsSpringTestCase;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.ngo.web.storage.entity.Account;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath*:applicationContext.xml" })
public class HibernateLockTest extends StrutsSpringTestCase {

	@Override
    protected void setUp() throws Exception {
        super.setUp();
        System.out.println("setup()");
    }
	
	
	private long createTestAccount() throws Exception {
		Session session = lookupSessionFactory().openSession();
		session.beginTransaction();

		Account a = new Account();
		a.setBalance(10000);
		session.save(a);

		session.getTransaction().commit();
		session.close();
		return a.getId();
	}
	
	 private SessionFactory lookupSessionFactory() {
	     return (SessionFactory)this.applicationContext.getBean("sessionFactory");
	}
	 
	@Test
	public void testDecrBalance1() throws Exception {
		long id = createTestAccount();
		
		Session session = lookupSessionFactory().openSession();
		session.beginTransaction();

		Account a = (Account)session.get(Account.class, id);
		a.setBalance(a.getBalance() - 10);
		//XHO: protential concurrent update may happen here,
		//and therefore the balance might be overwrite unexpectedly.
		session.getTransaction().commit();
		session.close();
	}
	
	//@Test
	public void testDecrBalanceByPessimisticLock() throws Exception {
		long id = createTestAccount();
		
		Session session = lookupSessionFactory().openSession();
		session.beginTransaction();

		Account a = (Account)session.get(Account.class, id, LockMode.UPGRADE_NOWAIT);
		a.setBalance(a.getBalance() - 20);
		//XHO: protential concurrent update may happen here,
		//and therefore the balance might be overwrite unexpectedly.
		session.getTransaction().commit();
		session.close();
	}

}