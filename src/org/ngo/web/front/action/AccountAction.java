package org.ngo.web.front.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.ngo.web.common.PlanTextAction;
import org.ngo.web.service.AccountService;
import org.ngo.web.service.UserService;
import org.ngo.web.service.impl.SimpleAccountService;
import org.ngo.web.storage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AccountAction extends PlanTextAction {

	@Resource(name = "simpleAccountService")
	private AccountService accountService;

	private int accountId;

	private int amount;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	
	public void executeDecr() throws IOException {

		String output = "OK" ;
		
		try
		{
			accountService.decrease(accountId, amount);
		} 
		catch (Exception e)
		{
			output = e.getMessage();
		}
		
		printText("decr.v1="+output);
		 
	}
	
	public void executeIncr() throws IOException {

		String output = "OK" ;
		try
		{
			accountService.increase(accountId, amount);
		} 
		catch (Exception e)
		{
			output = e.getMessage();
		}
		
		printText("incr.v1="+output);
	}
	
	public void executeCreate() throws IOException {

		String output = "OK" ;
		try
		{
			accountService.create(accountId, amount);
		} 
		catch (Exception e)
		{
			output = e.getMessage();
		}
		
		printText("create.v1="+output);
	}
}