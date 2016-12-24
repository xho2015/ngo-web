package org.ngo.web.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PlanTextAction extends ActionSupport {
	
	private final String contentType = "text/html;charset=utf-8";
	
	protected Map<String, String[]> getParameters()
	{
		return ServletActionContext.getRequest().getParameterMap();
		//return (Map)ActionContext.getContext().get("request");
	}
	
	
	protected void printText(Object text) throws IOException
	{
		ServletActionContext.getResponse().setContentType(contentType);   
        PrintWriter out = ServletActionContext.getResponse().getWriter();   
        try{  
            out.print(text);  
            out.flush();  
            out.close();  
        }catch(Exception ex){  
            out.println(ex.toString());  
        }       
	}

}
