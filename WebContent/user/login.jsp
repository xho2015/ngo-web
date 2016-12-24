<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head></head>
<body>
	<h1>Login Page</h1>

	<s:if test="#session.LOGIN_RETRY !=null">

		<s:if test="#session.LOGIN_RETRY < 3">
			<h3>
				<font color="red">Login Failed <s:property
						value="#session.LOGIN_RETRY" /> times, Please try again.
				</font>
			</h3>
		</s:if>
		<s:else>
			<h3>
				<font color="red">You have tried too many times.</font>
			</h3>
		</s:else>
	</s:if>
	<s:form action="Login">
		<s:textfield name="username" label="Username" />
		<s:password name="password" label="Password" />
		<s:submit />
	</s:form>

</body>
</html>
