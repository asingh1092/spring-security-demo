<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Custom Security Login Page</title>
<style>
.failed {
	color: red;
}
</style>
</head>
<body>
	<h2>Custom Security Login Page</h2>
	<hr>
	<form:form
		action="${pageContext.request.contextPath}/authenticateTheUser"
		method="POST">
		<p>
			User Name: <input type="text" name="username" />
		</p>
		<p>
			Password: <input type="text" name="password" />
		</p>
		<!-- JSTL error message -->
		<c:if test="${param.error != null}">
			<i class="failed">You've entered an invalid username/password.</i>
		</c:if>
		<br><br>
		<input type="submit" value="Login">
	</form:form>
</body>
</html>