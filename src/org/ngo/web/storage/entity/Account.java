package org.ngo.web.storage.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Version;

@Entity
public class Account implements Serializable{
	private long Id;
	private int balance;
	private long version;
	
	@javax.persistence.Id
	@GeneratedValue
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	@Version
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	
	
	
}
