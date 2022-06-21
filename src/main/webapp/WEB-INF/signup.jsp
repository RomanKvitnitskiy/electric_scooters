<%--
  Created by IntelliJ IDEA.
  User: kvitn
  Date: 3/25/2022
  Time: 4:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<style><%@include file="/css/style.css"%></style>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<main>
    <div class="circle"></div>
    <div class="register-form-container">
        <h1 class="form-title">
            Sign up
        </h1>
        <div class="form-fields">
            <form action="${pageContext.request.contextPath}/signup" method="post">
                <input type="text" name="name" required placeholder="Name" minlength="2"><br>
                <input type="text" name="surname" required placeholder="Surname" minlength="2"><br>
                <input type="email" name="email" required placeholder="E-mail"><br>
                <input type="text" name="login" required placeholder="Login" minlength="2"><br>
                <input type="password" name="password" required placeholder="Password" minlength="8"><br>
                <input type="password" name="confirm_password" required placeholder="Confirm password" minlength="8"><br>
               <button type="submit" class="button">Sign up</button>
            </form>
        </div>
        <br>
        <div class="form-buttons">
            <br>
            <a href="${pageContext.request.contextPath}/login" class="button">Sign in</a>
        </div>
    </div>
</main>
<%--<div class="page">
    <h1>Sign up</h1>
    <form action="${pageContext.request.contextPath}/signup" method="post">
        <input type="text" name="name" required placeholder="Name" minlength="2"><br>
        <input type="text" name="surname" required placeholder="Surname" minlength="2"><br>
        <input type="email" name="email" required placeholder="E-mail"><br>
        <input type="text" name="login" required placeholder="Login" minlength="2"><br>
        <input type="password" name="password" required placeholder="Password" minlength="8"><br>
        <input type="password" name="confirm_password" required placeholder="Confirm password" minlength="8"><br>
        <button type="submit">Sign up</button>
    </form>
    <a href="${pageContext.request.contextPath}/login">Sign in</a>
</div>--%>
</body>
</html>
