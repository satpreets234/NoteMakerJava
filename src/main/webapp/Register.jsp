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
<form  action="Register" method="POST">
	<label for="username">Enter username</label>
	<input name="username" required type="text" id="username">
	<label for="email">Enter email</label>	
	<input name="email" required type="email" id="email">
	<label for="password">Enter password</label>
	<input name="password" required type="password" id="password">
	<button type="submit"> Login</button>
</form>
<a href="Login.jsp">Already have an account?</a>
</div>
<%@include file="Footer.jsp" %>
</body>
</html>