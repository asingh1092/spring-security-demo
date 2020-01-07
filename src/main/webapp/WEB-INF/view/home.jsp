<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Spring Security Demo Home</title>
</head>
<body>
	<h2>Singh Company Home Page</h2>
	<hr>
	<!-- display username and role -->
	<p>
		User: <security:authentication property="principal.username"/>
		<br><br>
		Role(s): <security:authentication property="principal.authorities"/>
	</p>
	<!-- Add a link to point to /leaders which is for managers -->
	<security:authorize access="hasRole('MANAGER')">
	<hr>
		<p>
			<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
			(Only for Managers)
		</p>
	</security:authorize>
	<!-- Add a link to point to /systems which is for admins -->
	<security:authorize access="hasRole('ADMIN')">
	<hr>
		<p>
			<a href="${pageContext.request.contextPath}/systems">Admin Meeting</a>
			(Only for Admins)
		</p>
	</security:authorize>
	<hr>
	<p>Welcome to the Spring Security demo Homepage!</p>
	<!-- Add Logout Button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
		<input type="submit" value="Logout">
	</form:form>
</body>
</html>