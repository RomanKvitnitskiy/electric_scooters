<%--
  Created by IntelliJ IDEA.
  User: kvitn
  Date: 2/16/2022
  Time: 3:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/css/style.css"%></style>
<html>
<head>
    <title>Order</title>
</head>
<body>
<main>
    <div class="register-form-container">
        <h1 class="form-title">
            Take order
        </h1>
        <div class="form-fields">
            <form action="${pageContext.request.contextPath}/takeOrder" method="post">
                <input hidden name="scooterId" value="${scooter.getId()}">
                <input hidden name="userId" value="${user.getId()}">
                <label for="pass" >Passport </label>
                <input id="pass" name="pass" type="text" pattern="\d*" required placeholder="Passport" minlength="9"><br>
                <label for="option">Option</label>
                <select required id="option" name="option">
                    <option></option>
                    <option value="Sitting">Sitting</option>
                    <option value="Standing">Standing</option>
                </select><br>
                <label for="duration">Duration (days)</label>
                <input id="duration" name="duration" type="date" required placeholder="Order duration" ><br>
                <label for="cost">Receipt cost</label>
                <input id="cost" name="cost" type="text" value="${scooter.getCost()}$" readonly><br>
                <br>
                <button type="submit" class="button">Take order</button>
                <br>
                <a href="${pageContext.request.contextPath}/login">Return</a>
            </form>
        </div>
    </div>

    <%--<form action="${pageContext.request.contextPath}/takeOrder" method="post">
        <input hidden name="scooterId" value="${scooter.getId()}">
        <input hidden name="userId" value="${user.getId()}">
        <label for="pass">Passport </label>
        <input id="pass" name="pass" type="text" pattern="\d*" required placeholder="Passport" minlength="9"><br>
        <label for="option">Option</label>
        <select required id="option" name="option">
            <option></option>
            <option value="WITHOUT_DRIVER">Without driver</option>
            <option value="WITH_A_DRIVER">With a driver</option>
        </select><br>
        <label for="duration">Duration (days)</label>
        <input id="duration" name="duration" type="date" required placeholder="Order duration" ><br>
        <label for="cost">Receipt cost</label>
        <input id="cost" name="cost" type="text" value="${scooter.getCost()}$" readonly><br>
        <br>
        <button type="submit">Take order</button>
    </form>--%>
    <%--<a href="${pageContext.request.contextPath}/login">Return</a>--%>
</main>
</body>
</html>
