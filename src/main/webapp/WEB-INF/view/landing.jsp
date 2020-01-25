<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 25.01.2020
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Welcome to public page!</title>
</head>
<body>
<h2>Welcome to public page!</h2>
<p>This page is open and is accessible to everyone :)</p>

<hr>
<a href="${pageContext.request.contextPath}/employees">Proceed to the private part (requires authentication)</a>
</body>
</html>
