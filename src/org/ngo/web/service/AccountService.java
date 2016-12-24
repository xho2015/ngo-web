package org.ngo.web.service;

import org.ngo.web.common.MyException;

public interface AccountService {
	
	public boolean decrease(int accountId, int amount) throws MyException;
	
	public void increase(int accountId, int amount) throws MyException;
	
	public void create(int accountId, int amount) throws MyException;
}
