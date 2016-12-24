package org.ngo.web.storage.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="END_USER")
public class User implements Serializable{
	
	@Id
	@GeneratedValue
	private long userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_pass")
	private String userPass;

	@Column(name="user_lastlogin")
	private Date lastLogin;
	
	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public void setUsername(String username2) {
		this.userName = username2;		
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
	

}
