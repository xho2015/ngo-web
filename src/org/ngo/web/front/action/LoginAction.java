package org.ngo.web.front.action;


import java.util.Map;

import javax.annotation.Resource;

import org.ngo.web.service.UserService;
import org.ngo.web.storage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;

public class LoginAction{

	//@Autowired
	//XHO: if two userService defined. then Autowired stop working
	// nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException: 
	//No qualifying bean of type [com.tutorial.service.UserService] is defined: expected single matching bean but found 2: advancedUserService,sampleUserService
	@Resource(name="simpleUserService")
	private UserService userService;

	//XHO: there is no need of a setter below. spring can do injection well.
	/*
	public void setUsertService(UserService usertService) {
		this.userService = usertService;
	}*/
	
	private String username;
	
	private String password;
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String execute() {
		
		//validate retries 
		Integer retry = (Integer)ActionContext.getContext().getSession().get("LOGIN_RETRY");
		if (retry !=null && retry >= 3)
			return "input";
	
		//load user from backend and validate credential
		User user = userService.getUserByName(this.username);
		if (user != null && userService.validatePassword(user, password))
		{
			ActionContext.getContext().getSession().put("LOGIN_USER_SESSION", user);
			ActionContext.getContext().getSession().remove("LOGIN_RETRY");
			userService.lastLogin(user.getUserId());
			return "SUCCESS";
		} 
		else
		{
			ActionContext.getContext().getSession().put("LOGIN_RETRY", retry == null ? new Integer(1) : new Integer(retry + 1));
			return "input";				
		}	
	}
}