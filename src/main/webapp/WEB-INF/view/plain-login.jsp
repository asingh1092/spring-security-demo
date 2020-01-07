<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Testy McTest</title>
</head>
<body>
	<h2>Testy McTest</h2>
	<hr>
	This is a test
	<form:form
		action="${pageContext.request.contextPath}/authenticateTheUser"
		method="POST">
		<p>
			User Name: <input type="text" name="username" />
		</p>
		<p>
			Password: <input type="text" name="password" />
		</p>
		<input type="submit" value="Login">
	</form:form>
</body>
</html>