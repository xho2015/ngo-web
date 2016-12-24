<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head></head>
<body>
	<h2>Welcome</h2>

	<s:if test="#session.LOGIN_USER_SESSION==null">
   		You haven't logged in. try this link to 
   		<s:a href="login.jsp">login</s:a>
			
	</s:if>
	
	<s:else>
		<h4>
			Hi,
			<s:property value="#session.LOGIN_USER_SESSION.userName" />
			, You can logoff <a href="<s:url action='Logoff'/>">here</a>
		</h4>
	</s:else>

</body>
</html>
