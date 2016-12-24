<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head></head>
<body>
	<h2>Unlock user</h2>

	<s:if test="#request.result==true">
    	User <b><s:property value="username" /></b> Unlocked!
	</s:if>
	
	<s:form action="Unlock">
		<s:textfield name="username" label="Username" />
		<s:submit />
	</s:form>

</body>
</html>
