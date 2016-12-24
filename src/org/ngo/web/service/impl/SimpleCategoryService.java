package org.ngo.web.service.impl;


import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.ngo.web.common.MyException;
import org.ngo.web.service.CategoryService;
import org.ngo.web.storage.dao.CategoryDao;
import org.ngo.web.storage.entity.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="simpleCategoryService")
public class SimpleCategoryService implements CategoryService {
	private static Logger LOGGER = Logger.getLogger(SimpleCategoryService.class);
	
	
	@Resource(name="categoryDao")
	private CategoryDao categoryDao;

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={MyException.class, RuntimeException.class})
	public void create(long categoryId, String name) throws MyException {
		Category cat = new Category();
		cat.setName(name);
		categoryDao.create(cat);	
		
	}

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={MyException.class, RuntimeException.class})
	public void create(long parentCategoryId, long categoryId, String name)
			throws MyException {
		Category parent = categoryDao.findOne(Category.class, parentCategoryId);
		Category cat = new Category();
		cat.setName(name);
		cat.setParent(parent);
		categoryDao.create(cat);
	}

	@Transactional
	public List<Category> getChilds(long categoryId) throws MyException {
		return categoryDao.query(categoryId);
	}

}
