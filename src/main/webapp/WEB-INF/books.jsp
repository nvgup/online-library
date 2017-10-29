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
<title>Всі книги</title>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="header.jsp" />
		<div id="search-block">
			<form action="Books" method="get">
				Пошук за:
				<select name="searchBy">
  					<option value="name">Назвою</option>
  					<option value="author">Автором</option>
  					<option value="genre">Жанром</option>
				</select>
				<input class="online-library-input" type="text" name="searchQuery" maxlength="50">
				<input class="online-library-buttton" type="submit" name="search-button" value="Пошук">
			</form>
		</div>
		<div id="content">
			<c:forEach var="book" items="${requestScope.books}">
				<div class="book">
					<a href="Book?bookId=${book.bookId}"><img src="Image?image=${book.coverImgPath}" width="252" height="252"></a>
					<div class="book-name">
						<a href="Book?bookId=${book.bookId}">${book.name}</a>
					</div>
					<div class = "book-buttons-block">
					<c:if test="${sessionScope.currentSessionUser ne null}">
						<button class="online-library-buttton" onClick="location.href='File?file=${book.booksFilePath}'">Завантажити</button>
					</c:if>	
						<button value="${book.bookId}" class="add-to-fav-list online-library-buttton">В обране</button>
					</div>
				</div>
			</c:forEach>
			<c:if test="${empty requestScope.books}">
				Книги не знайдені
			</c:if>
		</div>
	</div>
</body>
</html>