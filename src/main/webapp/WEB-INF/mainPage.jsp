<%--
  Created by IntelliJ IDEA.
  User: kvitn
  Date: 2/16/2022
  Time: 3:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/css/style.css"%></style>
<html>
<head>
    <title>Scooter rental</title>
</head>
<body>
<header>
    <a href="${pageContext.request.contextPath}/login">
        <img src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjQiIGhlaWdodD0iMjQiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgZmlsbC1ydWxlPSJldmVub2RkIiBjbGlwLXJ1bGU9ImV2ZW5vZGQiPjxwYXRoIGQ9Ik01IDIzaC0ydi0xMGw4Ljk5MS04LjAwNWMxLjEyNC45OTggMi4yNSAxLjk5NyAzLjM3OCAyLjk5NmwyLjI1NSAxLjk5N2MxLjEyNy45OTkgMi4yNTIgMi4wMTMgMy4zNzYgMy4wMTJ2MTBoLTJ2LTkuMTE4bC03LjAwOS02LjIxNS02Ljk5MSA2LjIydjkuMTEzem0yLTJoMTB2MmgtMTB2LTJ6bTAtM2gxMHYyaC0xMHYtMnptMTAtM3YyaC0xMHYtMmgxMHptLTUtMTRsMTIgMTAuNjMyLTEuMzI4IDEuNDkzLTEwLjY3Mi05LjQ4MS0xMC42NzIgOS40ODEtMS4zMjgtMS40OTMgMTItMTAuNjMyeiIvPjwvc3ZnPg==">
    </a>
    <a href="${pageContext.request.contextPath}/cabinet?userId=${user.getId()}">
        <img src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjQiIGhlaWdodD0iMjQiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgZmlsbC1ydWxlPSJldmVub2RkIiBjbGlwLXJ1bGU9ImV2ZW5vZGQiPjxwYXRoIGQ9Ik0xMiAwYzYuNjIzIDAgMTIgNS4zNzcgMTIgMTJzLTUuMzc3IDEyLTEyIDEyLTEyLTUuMzc3LTEyLTEyIDUuMzc3LTEyIDEyLTEyem04LjEyNyAxOS40MWMtLjI4Mi0uNDAxLS43NzItLjY1NC0xLjYyNC0uODUtMy44NDgtLjkwNi00LjA5Ny0xLjUwMS00LjM1Mi0yLjA1OS0uMjU5LS41NjUtLjE5LTEuMjMuMjA1LTEuOTc3IDEuNzI2LTMuMjU3IDIuMDktNi4wMjQgMS4wMjctNy43OS0uNjc0LTEuMTE5LTEuODc1LTEuNzM0LTMuMzgzLTEuNzM0LTEuNTIxIDAtMi43MzIuNjI2LTMuNDA5IDEuNzYzLTEuMDY2IDEuNzg5LS42OTMgNC41NDQgMS4wNDkgNy43NTcuNDAyLjc0Mi40NzYgMS40MDYuMjIgMS45NzQtLjI2NS41ODYtLjYxMSAxLjE5LTQuMzY1IDIuMDY2LS44NTIuMTk2LTEuMzQyLjQ0OS0xLjYyMy44NDggMi4wMTIgMi4yMDcgNC45MSAzLjU5MiA4LjEyOCAzLjU5MnM2LjExNS0xLjM4NSA4LjEyNy0zLjU5em0uNjUtLjc4MmMxLjM5NS0xLjg0NCAyLjIyMy00LjE0IDIuMjIzLTYuNjI4IDAtNi4wNzEtNC45MjktMTEtMTEtMTFzLTExIDQuOTI5LTExIDExYzAgMi40ODcuODI3IDQuNzgzIDIuMjIyIDYuNjI2LjQwOS0uNDUyIDEuMDQ5LS44MSAyLjA0OS0xLjA0MSAyLjAyNS0uNDYyIDMuMzc2LS44MzYgMy42NzgtMS41MDIuMTIyLS4yNzIuMDYxLS42MjgtLjE4OC0xLjA4Ny0xLjkxNy0zLjUzNS0yLjI4Mi02LjY0MS0xLjAzLTguNzQ1Ljg1My0xLjQzMSAyLjQwOC0yLjI1MSA0LjI2OS0yLjI1MSAxLjg0NSAwIDMuMzkxLjgwOCA0LjI0IDIuMjE4IDEuMjUxIDIuMDc5Ljg5NiA1LjE5NS0xIDguNzc0LS4yNDUuNDYzLS4zMDQuODIxLS4xNzkgMS4wOTQuMzA1LjY2OCAxLjY0NCAxLjAzOCAzLjY2NyAxLjQ5OSAxIC4yMyAxLjY0LjU5IDIuMDQ5IDEuMDQzeiIvPjwvc3ZnPg==">
    </a>
    <a href="${pageContext.request.contextPath}/logout">Log out</a>
</header>
<menu>
    <div>
        <form action="${pageContext.request.contextPath}/getScooterByMark">
            <label>Filter by mark:</label>
            <select onchange="this.form.submit()" name="mark">
                <option></option>
                <c:forEach items="${scooterMarks}" var="mark">
                    <c:choose>
                        <c:when test="${mark.name().equals(markName)}">
                            <option selected value="${mark.name()}">${mark.name()}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${mark.name()}">${mark.name()}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </form>
        <form action="${pageContext.request.contextPath}/getScooterByClass">
            <label>Filter by class:</label>
            <select onchange="this.form.submit()" name="scooter_class">
                <option></option>
                <c:forEach items="${scooterClass}" var="scooter_class">
                    <option value="${scooter_class.name()}">${scooter_class.name()}</option>
                </c:forEach>
            </select>
        </form>
        <p>
            <strong>Sort:</strong>
            <c:choose>
                <c:when test="${markName != null}">
                    <a href="${pageContext.request.contextPath}/getScooterByNameAZ?mark=${markName}">Name A-z</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/getScooterByNameAZ">Name A-z</a>
                </c:otherwise>
            </c:choose>
            <strong>|</strong>
            <a href="${pageContext.request.contextPath}/getScooterByNameZA">Name z-A</a>
            <strong>|</strong>
            <a href="${pageContext.request.contextPath}/getScooterByCostEC">Cost E-c</a>
            <strong>|</strong>
            <a href="${pageContext.request.contextPath}/getScooterByCostCE">Cost C-e</a>
        </p>
    </div>
    <c:choose>
        <c:when test="${scooters != null}">
            <table class="content-table">
                <thead class="for_active">
                <tr>
                    <td>Name</td>
                    <td>Mark</td>
                    <td>Class</td>
                    <td>Cost</td>
                    <td>Order</td>
                </tr>
                </thead>
                <c:forEach items="${scooters}" var = "scooter">
                    <tr>
                        <td> <c:out value="${scooter.getName()}"/> </td>
                        <td> <c:out value="${scooter.getScooterMark()}"/> </td>
                        <td> <c:out value="${scooter.getScooterClass()}"/> </td>
                        <td> <c:out value="${scooter.getCost()}"/> </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/takeOrder?carId=${scooter.getId()}&userId=${user.getId()}">Order</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
    </c:choose>
    <table class="content-table">
        <thead class="for_hide">
        <tr>
            <td>Name</td>
            <td>Mark</td>
            <td>Class</td>
            <td>Cost</td>
            <td>Order</td>
        </tr>
        </thead>
        <c:forEach items="${scootersByClassList}" var = "scooterClass">
            <tr>
                <td> <c:out value="${scooterClass.getName()}"/> </td>
                <td> <c:out value="${scooterClass.getScooterMark()}"/> </td>
                <td> <c:out value="${scooterClass.getScooterClass()}"/> </td>
                <td> <c:out value="${scooterClass.getCost()}"/> </td>
                <td>
                    <a href="${pageContext.request.contextPath}/takeOrder?carId=${scooterClass.getId()}&userId=${user.getId()}">Order</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <table class="content-table">
        <thead class="for_hide">
        <tr>
            <td>Name</td>
            <td>Mark</td>
            <td>Class</td>
            <td>Cost</td>
            <td>Order</td>
        </tr>
        </thead>
        <c:forEach items="${scootersByMarkList}" var = "scooterMark">
            <tr>
                <td> <c:out value="${scooterMark.getName()}"/> </td>
                <td> <c:out value="${scooterMark.getScooterMark()}"/> </td>
                <td> <c:out value="${scooterMark.getScooterClass()}"/> </td>
                <td> <c:out value="${scooterMark.getCost()}"/> </td>
                <td>
                    <a href="${pageContext.request.contextPath}/takeOrder?scooterId=${scooterMark.getId()}&userId=${user.getId()}">Order</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <table class="content-table">
        <thead class="for_hide">
        <tr>
            <td>Name</td>
            <td>Mark</td>
            <td>Class</td>
            <td>Cost</td>
            <td>Order</td>
        </tr>
        </thead>
        <c:forEach items="${scooterByNameAZ}" var="scooterNameAZ">
            <tr>
                <td> <c:out value="${scooterNameAZ.getName()}"/> </td>
                <td> <c:out value="${scooterNameAZ.getScooterMark()}"/> </td>
                <td> <c:out value="${scooterNameAZ.getScooterClass()}"/> </td>
                <td> <c:out value="${scooterNameAZ.getCost()}"/> </td>
                <td>
                    <a href="${pageContext.request.contextPath}/takeOrder?scooterId=${scooterNameAZ.getId()}&userId=${user.getId()}">Order</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <table class="content-table">
        <thead class="for_hide">
        <tr>
            <td>Name</td>
            <td>Mark</td>
            <td>Class</td>
            <td>Cost</td>
            <td>Order</td>
        </tr>
        </thead>
        <c:forEach items="${scooterByNameZA}" var="scooterNameZA">
            <tr>
                <td> <c:out value="${scooterNameZA.getName()}"/> </td>
                <td> <c:out value="${scooterNameZA.getScooterMark()}"/> </td>
                <td> <c:out value="${scooterNameZA.getScooterClass()}"/> </td>
                <td> <c:out value="${scooterNameZA.getCost()}"/> </td>
                <td>
                    <a href="${pageContext.request.contextPath}/takeOrder?scooterId=${scooterNameZA.getId()}&userId=${user.getId()}">Order</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <table class="content-table">
        <thead class="for_hide">
        <tr>
            <td>Name</td>
            <td>Mark</td>
            <td>Class</td>
            <td>Cost</td>
            <td>Order</td>
        </tr>
        </thead>
        <c:forEach items="${scooterByCostEC}" var="scooterCostEC">
            <tr>
                <td> <c:out value="${scooterCostEC.getName()}"/> </td>
                <td> <c:out value="${scooterCostEC.getScooterMark()}"/> </td>
                <td> <c:out value="${scooterCostEC.getScooterClass()}"/> </td>
                <td> <c:out value="${scooterCostEC.getCost()}"/> </td>
                <td>
                    <a href="${pageContext.request.contextPath}/takeOrder?scooterId=${scooterCostEC.getId()}&userId=${user.getId()}">Order</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <table class="content-table">
        <thead class="for_hide">
        <tr>
            <td>Name</td>
            <td>Mark</td>
            <td>Class</td>
            <td>Cost</td>
            <td>Order</td>
        </tr>
        </thead>
        <c:forEach items="${scooterByCostCE}" var="scooterCostCE">
            <tr>
                <td> <c:out value="${scooterCostCE.getName()}"/> </td>
                <td> <c:out value="${scooterCostCE.getScooterMark()}"/> </td>
                <td> <c:out value="${scooterCostCE.getScooterClass()}"/> </td>
                <td> <c:out value="${scooterCostCE.getCost()}"/> </td>
                <td>
                    <a href="${pageContext.request.contextPath}/takeOrder?scooterId=${scooterCostCE.getId()}&userId=${user.getId()}">Order</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</menu>
</body>
</html>
