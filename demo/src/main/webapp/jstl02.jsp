<%--
  Created by IntelliJ IDEA.
  User: gusah
  Date: 2021-08-25
  Time: 오후 3:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setAttribute("n", 10);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<c:if test="${n == 0}">
    n은 과 1과 같습니다.
</c:if>

<c:if test="${n == 10}">
    n은 과 10과 같습니다.
</c:if>
</body>
</html>