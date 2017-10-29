<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" type="text/css" rel="stylesheet">
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/onlinelibrary.js" type="text/javascript"></script>
<title>Особистий кабінет</title>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="header.jsp" />
		<div id="content">
			<h3>Особистий кабінет</h3>
			<div id="favorite-list">
				<h3>Список обраного</h3>
				<c:if test="${empty sessionScope.favoritesList}">
					<h4>Список порожній</h4>
				</c:if>
				<c:forEach var="book" items="${sessionScope.favoritesList}">
					<div class="favorite-book">
						<div class="favorite-book-cover">
							<a href="Book?bookId=${book.key}"><img src="Image?image=${book.value.coverImgPath}" width="100" height="100"></a>
						</div>
						<div class="favorite-book-name"><a href="Book?bookId=${book.key}">${book.value.name}</a></div>
						<div class="favorite-book-button">
							<button class="online-library-buttton" onClick="location.href='File?file=${book.value.booksFilePath}'">Завантажити</button>
						</div>
					</div>
					<div class="clear"></div>
				</c:forEach>
			</div>
			<div id="personal-info">
				<h3>Особиста інформація</h3>
				<p><span class="bold">Ім'я: </span>${sessionScope.currentSessionUser.name}</p>
				<p><span class="bold">Email: </span>${sessionScope.currentSessionUser.email}</p>
			</div>
		</div>
	</div>
</body>
</html>