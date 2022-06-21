<%--
  Created by IntelliJ IDEA.
  User: kvitn
  Date: 2/15/2022
  Time: 7:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/css/style.css"%></style>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<main>
    <div>
        <h1 class="form-title">
            Admin page
        </h1>
        <p>
            <strong>Name</strong>: ${user.getName()}
            <strong>Surname</strong>: ${user.getSurname()}
            <strong>Role</strong>: ${user.getUserRole()}
            <a href="${pageContext.request.contextPath}/logout">LogOut</a>
        </p>
        <br>
        <p>
            <a href="${pageContext.request.contextPath}/addNewScooter"><strong>Add scooter</strong></a>
            <strong> | </strong>
            <a href="${pageContext.request.contextPath}/addManager"><strong>New manager</strong></a>
        </p>
<%--        <label for="allScooters">Scooters</label>--%>
        <form id="allScooters" action="${pageContext.request.contextPath}/deleteScooter" method="post">
            <table class="content-table">
                <thead class="for_active">
                <tr>
                    <td>Name</td>
                    <td>Mark</td>
                    <td>Class</td>
                    <td>Cost</td>
                    <td>Edit</td>
                    <td>Delete</td>
                </tr>
                </thead>
                <c:forEach items="${scooters}" var = "scooter">
                    <tr>
                        <td> <c:out value="${scooter.getName()}"/> </td>
                        <td> <c:out value="${scooter.getScooterMark()}"/> </td>
                        <td> <c:out value="${scooter.getScooterClass()}"/> </td>
                        <td> <c:out value="${scooter.getCost()}"/> </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/editScooter?scooterId=${scooter.getId()}">Edit</a>
                        </td>
                        <td>
                            <button type="submit" name="scooterId" value="${scooter.getId()}">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>
        <label for="allUsers">Users</label>
        <form id="allUsers" action="${pageContext.request.contextPath}/changeStatus" method="post">
            <table class="content-table">
                <thead class="for_active">
                <tr>
                    <td>Name</td>
                    <td>Surname</td>
                    <td>Email</td>
                    <td>Status</td>
                    <td>Block</td>
                </tr>
                </thead>
                <c:forEach items="${users}" var = "user_unit">
                    <tr>
                        <td><c:out value="${user_unit.getName()}"/></td>
                        <td><c:out value="${user_unit.getSurname()}"/></td>
                        <td><c:out value="${user_unit.getEmail()}"/></td>
                        <td><c:out value="${user_unit.isStatus()}"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${user_unit.isStatus()}">
                                    <button type="submit" name="userId" value="${user_unit.getId()}">Block</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" name="userId" value="${user_unit.getId()}">Unblock</button>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>
</main>
</body>
</html>
