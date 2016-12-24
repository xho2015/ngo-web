package org.ngo.web.front.action;


import javax.annotation.Resource;

import org.ngo.web.service.UserService;
import org.ngo.web.storage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CreateUserAction  extends ActionSupport{

	//@Autowired
	@Resource(name="simpleUserService")
	private UserService userService;


	private String username;

	private String userpass;

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String execute() {
		User user = new User();
		user.setUsername(this.username);
		user.setUserPass(this.userpass);
		userService.insertUser(user);
		/*
		 * XHO: question here - what the difference between (1) and (2) ?
		 * looks like both can bring the data to the JSP page, and ognl can reader
		 * value from request scope.
		 * */
		
		// (1)
		//Map request = (Map) ActionContext.getContext().get("request");
		//request.put("result", true);
		
		// (2)
		//XHO: if we put one object in ActionContext and pass to anther chained action, 
		//     this object still exists in the chained action. looks like the chained action share the same ActionContext, 
		//     due to the corresponding ActionContext are cached in same ThreadLocal ?
		ActionContext.getContext().put("result", true);
	
		return "input";
	}

	public void validate()
	{
		if (StringUtils.isEmpty(username))
			super.addFieldError("username", "username can not be empty");
		if (StringUtils.isEmpty(userpass))
			super.addFieldError("userpass", "password can not be empty");
	}
}