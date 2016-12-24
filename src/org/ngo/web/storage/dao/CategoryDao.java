package org.ngo.web.storage.dao;

import java.util.List;

import org.ngo.web.common.MyException;
import org.ngo.web.storage.entity.Account;
import org.ngo.web.storage.entity.Category;


public interface CategoryDao extends IOperations<Category> {
	public List<Category> query(long parentId);
	
}
