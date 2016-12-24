package org.ngo.web.storage.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category implements Serializable {

	@Id
	@GeneratedValue
	private long Id;
	
	private String name;
	
	@javax.persistence.ManyToOne
	private Category parent;
	
	/*@javax.persistence.OneToMany(fetch = FetchType.LAZY)
	private Set<Category> childs = new HashSet<Category>(0);

	public Set getChilds() {
		return childs;
	}

	public void setChilds(Set childs) {
		this.childs = childs;
	}*/

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}
	
	public String toString()
	{
		return String.format("[Category,id=%d,name=%s,parentId=%d]",Id,name,parent.Id);
	}

}
