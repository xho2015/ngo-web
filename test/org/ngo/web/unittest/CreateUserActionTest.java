package org.ngo.web.unittest;


import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import org.apache.struts2.StrutsSpringTestCase;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.junit.Test;
import org.ngo.web.front.action.CreateUserAction;
import org.springframework.test.context.ContextConfiguration;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

@ContextConfiguration(locations = { "classpath:applicationContext.xml","classpath*:applicationContext.xml"})
public class CreateUserActionTest extends StrutsSpringTestCase  {

	@Test
    public void testGetActionProxy() throws Exception {
        request.setParameter("username", "JUTuser1");
        request.setParameter("userpass", "1234");
        
        executeAction("/user/Create.action");
        ActionProxy proxy = getActionProxy("/user/Create.action");
        assertNotNull(proxy);
        
        CreateUserAction action = (CreateUserAction) proxy.getAction();
        assertNotNull(action);
        
        //XHO-[issue1]: bellow action's execute method will throw NPE if try to get request Map from action context.
        //     but no issue if simply using action context instead.
        String result = proxy.execute();
        
        assertEquals(Action.INPUT.toUpperCase(), result.toUpperCase());
    }
	
	@Test
	public void testGetActionMapping() {
        ActionMapping mapping = getActionMapping("/user/Create.action");
        assertNotNull(mapping);
        assertEquals("/user", mapping.getNamespace());
        assertEquals("Create", mapping.getName());
    }
 
	@Test
    public void testGetValueFromStack() throws ServletException, UnsupportedEncodingException {
        request.setParameter("username", "JUnitTestUser2");
        request.setParameter("userpass", "6666");
        executeAction("/user/Create.action");
        //XHO: similar issue with [issue1] above
        boolean result = (boolean) findValueAfterExecute("result");
        assertEquals(result, true);
    }
    
	
}
