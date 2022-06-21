<%--
  Created by IntelliJ IDEA.
  User: kvitn
  Date: 3/25/2022
  Time: 4:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<style><%@include file="/css/style.css"%></style>
<html>
<head>
    <title>Login</title>
</head>
<body>
<main>
    <div class="register-form-container">
        <h1 class="form-title">
            Sign in
        </h1>
        <div class="form-fields">
            <form action="${pageContext.request.contextPath}/login" method="post">
                <input type="text" name="login" required placeholder="Login"><br>
                <input type="password" name="password" required placeholder="Password" <%--minlength="8"--%>><br>
                <br>
                <button type="submit" class="button">Sign in</button>
            </form>
            <br>
            <a href="${pageContext.request.contextPath}/signup" class="button">Sign Up</a>
        </div>
    </div>
</main>
</body>
</html>
