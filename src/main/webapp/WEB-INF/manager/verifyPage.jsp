<%--
  Created by IntelliJ IDEA.
  User: kvitn
  Date: 2/14/2022
  Time: 7:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="/css/style.css"%></style>
<html>
<head>
    <title>Order verify</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/verifyReceipt" method="post">
    <p>
        <input hidden name="orderId" value="${orderVerify.getId()}">
        <strong>Order №: </strong>${orderVerify.getId()} <br>
        <strong>Client: </strong>${orderUser.getName()} ${orderUser.getSurname()}<br>
        <strong>Scooter: </strong>${orderScooter.getName()} ${orderScooter.getScooterMark()} ${orderScooter.getScooterClass()}<br>
        <label for="repair"><strong>Repair bill</strong></label>
        <input id="repair" type="number" name="repair" min="0"><br>
        <button type="submit">Submit</button>
    </p>
</form>
</body>
</html>
