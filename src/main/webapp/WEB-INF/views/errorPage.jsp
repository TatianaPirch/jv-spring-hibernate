<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Error page</title>
</head>
<body>
<h3>${message}</h3>
<a href="${pageContext.request.contextPath}/book/all">all books</a>
</body>
</html>
