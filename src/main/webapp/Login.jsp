<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="all_css_js.jsp" %>
<title>Insert title here</title>
</head>
<body>
<%@include file="Header.jsp" %>
<div style="margin:20px">
<form method="POST" action="Login">
	<label for="email">Enter email</label>
	<input name="email" required type="email" id="email">
	<label for="password">Enter password</label>
	<input name="password" required type="password" id="password">
	<button type="submit"> Login</button>
</form>
<a href="Register.jsp">Don't have an account?</a></div>
<%@include file="Footer.jsp" %>
</body>
</html>