package org.ngo.web.service;

import java.util.List;
import java.util.Set;

import org.ngo.web.common.MyException;
import org.ngo.web.storage.entity.Category;

public interface CategoryService {
	
	public void create(long categoryId, String name) throws MyException;
	
	public void create(long parentCategoryId, long categoryId, String name) throws MyException;
	
	public List<Category> getChilds(long categoryId) throws MyException;
	

}
