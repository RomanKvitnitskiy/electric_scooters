<%--
  Created by IntelliJ IDEA.
  User: kvitn
  Date: 2/14/2022
  Time: 7:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="/css/style.css"%></style>
<html>
<head>
    <title>Order rejection</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/rejectReceipt" method="post">
    <input hidden name="orderId" value="${order.getId()}">
    <label for="comm">Rejection comment</label>
    <input id="comm" type="text" name="comm">
    <button type="submit">Submit</button>
</form>
<a href="${pageContext.request.contextPath}/manager">Return</a>
</body>
</html>
