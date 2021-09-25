<%--
  Created by IntelliJ IDEA.
  User: gusah
  Date: 2021-09-26
  Time: 오전 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>1ilsang.blog.me</title>
</head>
<body>
<h4><c:out value="${exception.getMessage()}"></c:out></h4>
<ul>
    <c:forEach items="${exception.getStackTrace()}" var="stack">
        <li>
            <c:out value="${stack}"></c:out>
        </li>
    </c:forEach>
</ul>
</body>
</html>