<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Todo Application</title>
</head>
<body>
	<a href="j_spring_security_logout">Logout: <security:authentication
			property="principal.username" />
	</a>
	<h1>Todo Application</h1>
	Current todo items:
	<c:forEach items="${todos}" var="todo" varStatus="row">
		${todo.id} - ${todo.text} - ${todo.done}
		<form action="close.html" method="post">
			<input name="todoId" value="${todo.id}" type="hidden"> <input
				type="submit" value="Close">
		</form>
		<form action="open.html" method="post">
			<input name="todoId" value="${todo.id}" type="hidden"> <input
				type="submit" value="Open">
		</form>
	</c:forEach>
	<form action="create.html" method="post">
		<input name="text"> <input type="submit" value="Create">
	</form>
</body>
</html>