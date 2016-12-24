package org.ngo.web.front.action;

import com.opensymphony.xwork2.ActionContext;


public class LogOffAction{

	
	public String execute() {		
		ActionContext.getContext().getSession().remove("LOGIN_USER_SESSION");
		return "SUCCESS";
	}
}