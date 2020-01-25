<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 1/24/20
  Time: 7:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>luv2code Company Home Page</title>
</head>
<body>
<h2>luv2code Company Home Page - Yoohoo!</h2>
<hr>
<p>Welcome to the luv2code Company Home Page!</p>

<hr>
<!-- Add logout button -->
<form:form action="${pageContext.request.contextPath}/logout" method="post">
    <input type="submit" value="Logout">
</form:form>
</body>
</html>
