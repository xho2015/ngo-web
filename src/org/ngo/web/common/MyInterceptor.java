package org.ngo.web.common;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.ServletDispatcherResult;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class MyInterceptor implements Interceptor {

	private static Logger LOGGER = Logger.getLogger(MyInterceptor.class);
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	public String intercept(ActionInvocation invocation) throws Exception {  
        String log = "ThreadId=[%s], Action=%s, Method=%s, Result=%s";
        String action = invocation.getAction().getClass().getName();
        String method = invocation.getProxy().getMethod();  
        /*String parameter = "";
        Map<String, Object> params = invocation.getInvocationContext().getParameters();  
        for (String key:params.keySet()){  
            Object obj = params.get(key);  
              
            if(obj instanceof String[]){  
                String[] arr = (String[]) obj;  
                parameter += ",Param:"+key+",";  
                for (String value:arr){  
                	parameter+=value;  
                }  
            }  
        }  */
          
        String resultCode = invocation.invoke();  
        /*String jsp;
        Result rst = invocation.getResult();  
        if (rst instanceof ServletDispatcherResult){  
            ServletDispatcherResult result = (ServletDispatcherResult) rst;  
            jsp= result.getLastFinalLocation();  
        }  */
        //LOGGER.info(String.format(log, Thread.currentThread().getId(), action, method));
        return resultCode;  
    }  
}
