<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${requestScope.book.name}</title>
<link href="css/style.css" type="text/css" rel="stylesheet">
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/onlinelibrary.js" type="text/javascript"></script>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="header.jsp" />
		<div id="content">
			<div id="book-left-block">
				<img src="Image?image=${book.coverImgPath}" width="400" height="400">
			</div>
			<div id="book-right-block">
				<h3>${requestScope.book.name}</h3>
				<h4>Автори:</h4>
				<p>
					<c:forEach var="author" items="${requestScope.book.authors}">
						${author.name}<br>
					</c:forEach>
				</p>
				<h4>Жанри:</h4>
				<p>
					<c:forEach var="genre" items="${requestScope.book.genres}">
						${genre.name}<br>
					</c:forEach>
				</p>
				<h4>Опис</h4>
				<div id="book-description">
					${requestScope.book.description}
				</div>
				<div class="book-buttons-block">
					<button value="${book.bookId}" class="add-to-fav-list online-library-buttton">В обране</button>
					<c:if test="${sessionScope.currentSessionUser ne null}">
						<button class="online-library-buttton" onClick="location.href='File?file=${book.booksFilePath}'">Завантажити</button>
					</c:if>	
					<c:if test="${fn:toLowerCase(sessionScope.currentSessionUser.userType.type) eq fn:toLowerCase(applicationScope.roles.ADMIN)}">
						<button class="online-library-buttton" onClick="location.href='Editbook?bookId=${book.bookId}'">Редагувати</button>
						<button id="delete-book" class="online-library-buttton" value="${book.bookId}">Видалити</button>
					</c:if>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
</body>
</html>