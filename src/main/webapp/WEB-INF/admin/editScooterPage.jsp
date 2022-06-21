<%--
  Created by IntelliJ IDEA.
  User: kvitn
  Date: 2/15/2022
  Time: 7:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<style><%@include file="/css/style.css"%></style>
<html>
<head>
    <title>Edit scooter</title>
</head>
<main>
    <div class="register-form-container">
        <h1 class="form-title">
            Edit scooter
        </h1>
        <div class="form-fields">
            <form action="${pageContext.request.contextPath}/editScooter" method="post">
                <input name="scooterName" type="text" required placeholder="Name" value="${scooter.getName()}"><br>
                <input name="scooterMark" type="text" required placeholder="Mark" value="${scooter.getScooterMark()}"><br>
                <input name="scooterClass" type="text" required placeholder="Class" value="${scooter.getScooterClass()}"><br>
                <input name="scooterCost" type="number" required placeholder="Cost" value="${scooter.getCost()}"><br>
                <br>
                <button class="button" type="submit" name="scooterId" value="${scooter.getId()}">Save changes</button>
            </form>
            <a href="${pageContext.request.contextPath}/admin">Return</a>
        </div>
    </div>
</main>
</body>
</html>
