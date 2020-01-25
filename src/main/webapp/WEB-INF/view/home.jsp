<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 1/24/20
  Time: 7:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>luv2code Company Home Page</title>
</head>
<body>
<h2>luv2code Company Home Page - Yoohoo!</h2>
<hr>
<p>Welcome to the luv2code Company Home Page!</p>

<hr>

<!-- display user name and role -->
<p>
    User: <security:authentication property="principal.username" />
    <br><br>
    Role(s): <security:authentication property="principal.authorities" />
</p>

<hr>
<security:authorize access="hasAnyRole('ADMIN', 'MANAGER')">

    <security:authorize access="hasRole('MANAGER')">
        <!-- Add a link to point to /leaders ... this is for the managers -->
        <p>
            <a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
            (Only for manager peeps)
        </p>
    </security:authorize>

    <!-- Add a link to point to /systems ... this is for the admins only -->
    <p>
        <a href="${pageContext.request.contextPath}/systems">IT Systems  Meeting</a>
        (Only for admin peeps)
    </p>

    <hr>
</security:authorize>
<!-- Add logout button -->
<form:form action="${pageContext.request.contextPath}/logout" method="post">
    <input type="submit" value="Logout">
</form:form>
</body>
</html>
