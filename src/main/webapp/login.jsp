<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" type="text/css" rel="stylesheet">
<title>Увійти</title>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="WEB-INF/header.jsp" />
		<div id="content">
			<h3>Увійти</h3>
			<div id="login-form">
				<c:if test="${requestScope.isUserExist ne null and not requestScope.isUserExist}">
					<p class="error-message">Користувача не знайдено</p>
				</c:if>
				<form action="Login" method="post">
					<p>
						Email:<br>
						<input class="online-library-input" type="text" name="email" required><br>
					</p>
					<p>
						Пароль:<br>
						<input class="online-library-input" type="password" name="password" maxlength="32" required>
					</p>
					<div id="login-buttons-block">
						<input class="online-library-buttton" type="submit" name="login" value="Увійти">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>