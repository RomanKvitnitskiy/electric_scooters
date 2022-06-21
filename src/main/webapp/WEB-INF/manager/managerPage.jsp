<%--
  Created by IntelliJ IDEA.
  User: kvitn
  Date: 2/14/2022
  Time: 7:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/css/style.css"%></style>
<html>
<head>
    <title>Manager page</title>
</head>
<body>
<h1>MANAGER PAGE</h1>
<p>
    <strong>Name</strong>: ${user.getName()}
    <strong>Surname</strong>: ${user.getSurname()}
    <strong>Role</strong>: ${user.getUserRole()}
    <a href="${pageContext.request.contextPath}/logout">Log out</a>
</p>
<form action="${pageContext.request.contextPath}/checkReceipt" method="post">
    <br>
    <br>
    <label for="pending"><strong>Pending recipes</strong></label>
    <table id="pending" class="content-table">
        <c:forEach items="${pendingRecipes}" var = "pendingRecipes">
            <tr>
                <td>
                    <c:out value="${pendingRecipes}"/>
                    <button type="submit" name="approve" value="${pendingRecipes.getId()}">Approve</button>
                    <a href="${pageContext.request.contextPath}/rejectReceipt?orderId=${pendingRecipes.getId()}">Reject</a>
                </td>
            </tr>
        </c:forEach>
    </table> <%--<br>--%>
    <label for="paid"><strong>Paid recipes</strong></label>
    <table id="paid" class="content-table">
        <c:forEach items="${paidRecipes}" var = "paidRecipes">
            <tr>
                <td>
                    <c:out value="${paidRecipes}"/>
                </td>
            </tr>
        </c:forEach>
    </table> <%--<br>--%>
    <label for="returned"><strong>Returned recipes</strong></label>
    <table id="returned" class="content-table">
        <c:forEach items="${returnedRecipes}" var = "returnedRecipes">
            <tr>
                <td>
                    <c:out value="${returnedRecipes}"/>
                    <button type="submit" name="closeOrder" value="${returnedRecipes.getId()}">Close order</button>
                    <a href="${pageContext.request.contextPath}/verifyReceipt?orderId=${returnedRecipes.getId()}">Verify</a>
                </td>
            </tr>
        </c:forEach>
    </table> <%--<br>--%>
    <label for="closed"><strong>Closed recipes</strong></label>
    <table id="closed" class="content-table">
        <c:forEach items="${closedRecipes}" var = "closedRecipes">
            <tr>
                <td>
                    <c:out value="${closedRecipes}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
