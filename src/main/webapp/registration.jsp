<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" type="text/css" rel="stylesheet">
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/onlinelibrary.js" type="text/javascript"></script>
<title>Реєстрація</title>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="WEB-INF/header.jsp" />
		<div id="content">
			<h3>Реєстрація</h3>
			<div id="registration-form">
				<c:if test="${requestScope.isUserExist ne null and not requestScope.isUserExist}">
					<p class="error-message">Введений Email уже зареєстрований</p>
				</c:if>
				<form action="Registration" method="post" name="registration">
					<p>
						Ім'я:<br>
						<input class="online-library-input" type="text" name="name" required><br>
					</p>
					<p>
						Email:<br>
						<input class="online-library-input" type="text" name="email" required><br>
					</p>
					<p>
						Пароль:<br>
						<input class="online-library-input" type="password" name="password" maxlength="32" required>
					</p>
					<p>
						Підтвердіть пароль:<br>
						<input class="online-library-input" type="password" name="conf-password" maxlength="32" required>
					</p>
					<p id="correct-conf-pass" class="error-message">Паролі не співпадають</p>
					<div id="registration-buttons-block">
						<input class="online-library-buttton" type="submit" name="registration" value="Зареєструватися">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>