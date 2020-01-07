<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Spring Security Demo Home</title>
</head>
<body>
	<h2>Singh Company Home Page</h2>
	<hr>
	<p>Welcome to the Spring Security demo Homepage!</p>
	<!-- Add Logout Button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
		<input type="submit" value="Logout">
	</form:form>
</body>
</html>