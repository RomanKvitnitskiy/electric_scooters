<%--
  Created by IntelliJ IDEA.
  User: kvitn
  Date: 2/15/2022
  Time: 7:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/css/style.css"%></style>
<html>
<head>
    <title>Add scooter</title>
</head>
<body>
<main>
    <div class="register-form-container">
        <h1 class="form-title">
            Add scooter
        </h1>
        <div class="form-fields">
            <form action="${pageContext.request.contextPath}/addNewScooter" method="post">
                <input name="scooterName" type="text" required placeholder="Scooter name"><br>
                <br>
                <label>Mark: </label>
                <select name="mark">
                    <option></option>
                    <c:forEach items="${scooterMarks}" var="mark">
                        <option value="${mark.name()}">${mark.name()}</option>
                    </c:forEach>
                </select>
                <br>
                <br>
                <label>Class: </label>
                <select name="scooter_class">
                    <option></option>
                    <c:forEach items="${scooterClass}" var="scooter_class">
                        <option value="${scooter_class.name()}">${scooter_class.name()}</option>
                    </c:forEach>
                </select>
                <br>
                <br>
                <input name="scooterCost" type="number" required placeholder="Cost" min="0">
                <br>
                <br>
                <button type="submit" class="button">Add scooter</button>
                <br>
                <a href="${pageContext.request.contextPath}/admin">Return</a>
            </form>
        </div>
    </div>
</main>
</body>
</html>
