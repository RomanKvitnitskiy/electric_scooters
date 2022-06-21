<%--
  Created by IntelliJ IDEA.
  User: kvitn
  Date: 2/16/2022
  Time: 3:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/css/style.css"%></style>
<html>
<head>
    <title>Scooter</title>
</head>
<body>
<header>
    <a href="${pageContext.request.contextPath}/login">
        <img src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjQiIGhlaWdodD0iMjQiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgZmlsbC1ydWxlPSJldmVub2RkIiBjbGlwLXJ1bGU9ImV2ZW5vZGQiPjxwYXRoIGQ9Ik01IDIzaC0ydi0xMGw4Ljk5MS04LjAwNWMxLjEyNC45OTggMi4yNSAxLjk5NyAzLjM3OCAyLjk5NmwyLjI1NSAxLjk5N2MxLjEyNy45OTkgMi4yNTIgMi4wMTMgMy4zNzYgMy4wMTJ2MTBoLTJ2LTkuMTE4bC03LjAwOS02LjIxNS02Ljk5MSA2LjIydjkuMTEzem0yLTJoMTB2MmgtMTB2LTJ6bTAtM2gxMHYyaC0xMHYtMnptMTAtM3YyaC0xMHYtMmgxMHptLTUtMTRsMTIgMTAuNjMyLTEuMzI4IDEuNDkzLTEwLjY3Mi05LjQ4MS0xMC42NzIgOS40ODEtMS4zMjgtMS40OTMgMTItMTAuNjMyeiIvPjwvc3ZnPg==" alt="Main page">
    </a>
</header>
<p>
    <strong>Name</strong>: ${user.getName()}
    <strong>Surname</strong>: ${user.getSurname()}
    <a href="${pageContext.request.contextPath}/logout">LogOut</a>
</p>
<p>
    <strong>Balance</strong>: ${user.getBalance()}
    <a href="${pageContext.request.contextPath}/topUp?userId=${user.getId()}">
        <img src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyNCIgaGVpZ2h0PSIyNCIgdmlld0JveD0iMCAwIDI0IDI0Ij48cGF0aCBkPSJNMCAxMmMwIDYuNjI3IDUuMzczIDEyIDEyIDEyczEyLTUuMzczIDEyLTEyLTUuMzczLTEyLTEyLTEyLTEyIDUuMzczLTEyIDEyem0xOC0xaC00djdoLTR2LTdoLTRsNi02IDYgNnoiLz48L3N2Zz4=" alt="Top up">
    </a>
</p>
<form action="${pageContext.request.contextPath}/receiptAction" method="post">
    <label for="pending"><strong>Pending recipes</strong></label>
    <table id="pending">
        <c:forEach items="${pending}" var = "pending">
            <tr>
                <td>
                    <c:out value="${pending}"/>
                </td>
            </tr>
        </c:forEach>
    </table> <br>
    <label for="active"><strong>Active recipes</strong></label>
    <table id="active">
        <c:forEach items="${active}" var = "active">
            <tr>
                <td>
                    <c:out value="${active}"/>
                    <c:choose>
                        <c:when test="${active.getRepairBill() > 0}">
                            <c:out value="Repair: ${active.getRepairBill()}"/>
                            <button type="submit" name="repair" value="${active.getId()}">Pay repair</button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" name="return" value="${active.getId()}">Return scooter</button>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table> <br>
    <label for="rejected"><strong>Rejected recipes</strong></label>
    <table id="rejected">
        <c:forEach items="${rejected}" var = "rejected">
            <c:choose>
                <c:when test="${rejected != null}">
                    <tr>
                        <td>
                            <c:out value="${rejected}"/>
                            <a href="${pageContext.request.contextPath}/checkComment?orderId=${rejected.getId()}">Check comment</a>
                        </td>
                    </tr>
                </c:when>
            </c:choose>
        </c:forEach>
    </table> <br>
    <label for="closed"><strong>Closed recipes</strong></label>
    <table id="closed">
        <c:forEach items="${closed}" var = "closed">
            <tr>
                <td>
                    <c:out value="${closed}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
