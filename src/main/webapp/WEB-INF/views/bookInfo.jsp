<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
​
<html>
<head>
    <title>Book Info</title>
</head>
<body>
<p>Book "${book.title}" with ID ${book.id} </p>
<p>Book "${book.title}" was published in ${book.year} </p>
<p>"${book.title}" authors: </p>
<c:forEach var="author" items="${book.authors}">
    <p>${author}</p>
</c:forEach>
</body>
</html>
