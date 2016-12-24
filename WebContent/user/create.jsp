<%@ page contentType="text/html; charset=UTF-8"%>  
<%@ taglib prefix="s" uri="/struts-tags"%>  
<html>  
<head></head>  
<body>  
    
    <s:if test="#request.result==true">
    	User <b><s:property value="username" /></b> Created!
	</s:if>
    <br/>
    <hr/>
    <h1>Create User</h1>  
   
    <s:form action="Create">  
        <s:textfield name="username" label="Username" />  
        <s:password name="userpass" label="Password" />  
        <s:submit />  
    </s:form>  
    
    
   
</body>  
</html>  