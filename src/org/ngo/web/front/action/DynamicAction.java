package org.ngo.web.front.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.ngo.web.common.PlanTextAction;
import org.ngo.web.service.AccountService;
import org.ngo.web.service.CategoryService;
import org.ngo.web.service.UserService;
import org.ngo.web.service.impl.SimpleAccountService;
import org.ngo.web.storage.entity.Category;
import org.ngo.web.storage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DynamicAction extends PlanTextAction {

	@Resource(name = "simpleCategoryService")
	private CategoryService categoryService;

	

	
	public void create() throws IOException {

		String output = "OK" ;
		
		Map<String, String[]> parms = this.getParameters();
		int cateId = Integer.parseInt((String)parms.get("cateId")[0]);
		long parentId = parms.get("parentId") == null ? 0 : Long.parseLong(parms.get("parentId")[0]);
		String cateName = parms.get("cateName")[0];
		
		try
		{
			if (parentId > 0)
				categoryService.create(parentId, cateId, cateName);
			else
				categoryService.create(cateId, cateName);				
		} 
		catch (Exception e)
		{
			output = e.getMessage();
		}
		
		printText("create_with_parent.v2="+output);
		 
	}
	
	public void query() throws IOException {

		String output = "OK" ;
		
		Map<String, String[]> parms = this.getParameters();
		int cateId = Integer.parseInt((String)parms.get("cateId")[0]);
		
		try
		{
			if (cateId > 0) {
				List<Category> childs = categoryService.getChilds(cateId);
				output = childs.toString();
			} 
							
		} 
		catch (Exception e)
		{
			output = e.getMessage();
		}
		
		printText("query.v1="+output);
		 
	}
	
	
}