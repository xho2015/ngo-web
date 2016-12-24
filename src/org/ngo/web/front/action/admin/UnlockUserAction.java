package org.ngo.web.front.action.admin;

import com.opensymphony.xwork2.ActionContext;

public class UnlockUserAction {

	
	private String username;
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String execute() {
		Integer retry = (Integer)ActionContext.getContext().getSession().get("LOGIN_RETRY");
		return "SUCCESS";
		
	}
}
