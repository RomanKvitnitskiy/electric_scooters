<%--
  Created by IntelliJ IDEA.
  User: kvitn
  Date: 2/16/2022
  Time: 3:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="/css/style.css"%></style>
<html>
<head>
    <title>Top Up</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/topUp" method="post">
    <input hidden name="userId" value="${user.getId()}">
    <input name="balance" type="number" required min="0">
    <button type="submit">Top up</button>
</form>
<a href="${pageContext.request.contextPath}/cabinet?userId=${user.getId()}">Return</a>
</body>
</html>
