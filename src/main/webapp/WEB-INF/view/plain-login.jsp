<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 25.01.2020
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Custom Login Page</title>
</head>
<body>
<h3>My Custom Login Page</h3>
<form:form action="${pageContext.request.contextPath}/authenticateUser" method="post">
    <p>
        User name: <input type="text" name="username">
    </p>

    <p>
        Password: <input type="password" name="password">
    </p>

    <input type="submit" value="Login">

</form:form>
</body>
</html>
