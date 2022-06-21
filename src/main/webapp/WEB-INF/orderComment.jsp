<%--
  Created by IntelliJ IDEA.
  User: kvitn
  Date: 2/16/2022
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/css/style.css"%></style>
<html>
<head>
    <title>Order info</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/login">Return</a>
<p>
    <strong>Order №: </strong>${order.getId()} <br>
    <strong>Client: </strong>${userByReceipt.getName()} ${userByReceipt.getSurname()}<br>
    <strong>Scooter: </strong>${scooterByReceipt.getName()} ${scooterByReceipt.getCarMark()} ${scooterByReceipt.getScooterClass()}<br>
    <%--<strong>Order status: </strong>${order.getOrderStatus()}<br>--%>
    <strong>Manager notification</strong><br>
    <input type="text" readonly value="${order.getComment()}"><br>
</p>
</body>
</html>
