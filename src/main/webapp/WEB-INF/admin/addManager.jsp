<%--
  Created by IntelliJ IDEA.
  User: kvitn
  Date: 2/15/2022
  Time: 7:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="/css/style.css"%></style>
<html>
<head>
    <title>Manager registration</title>
</head>
<body>
<main>
    <div class="register-form-container">
        <h1 class="form-title">
            Add manager
        </h1>
        <div class="form-fields">
            <form action="${pageContext.request.contextPath}/addManager" method="post">
                <input type="text" name="name" required placeholder="Name" minlength="2"><br>
                <input type="text" name="surname" required placeholder="Surname" minlength="2"><br>
                <input type="email" name="email" required placeholder="E-mail"><br>
                <input type="text" name="login" required placeholder="Login" minlength="2"><br>
                <input type="password" name="password" required placeholder="Password" minlength="8"><br>
                <input type="password" name="confirm_password" required placeholder="Confirm password" minlength="8"><br>
                <br>
                <button type="submit" class="button">Create new manager</button>
            </form>
            <a href="${pageContext.request.contextPath}/admin">Return</a>
        </div>
    </div>
</main>
</body>
</html>
