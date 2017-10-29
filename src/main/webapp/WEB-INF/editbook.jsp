<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Редагувати - ${requestScope.book.name}</title>
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
				<h3>Редагувати</h3>
				<div id="book-edit-form">
				<form action="Editbook" method="post">
					<p>
						Код:<br>
						<input class="online-library-input" type="text" name="bookId" value="${requestScope.book.bookId}" readonly="readonly"><br>
					</p>
					<p>
						Назва:<br>
						<input class="online-library-input" type="text" name="name" value="${requestScope.book.name}" required><br>
					</p>
					<div id="author-block">
						Автори:<br>
						<c:forEach var="author" items="${requestScope.book.authors}">
							<input class="book-edit-input online-library-input" type="text" name="author_${author.authorId}" value="${author.name}" readonly="readonly">
							<button class="delete-author online-library-buttton" type="button" value="author_${author.authorId}"><span>x</span></button>
						</c:forEach>
						<div class="book-buttons-block">
							<button type="button" id="add-new-author" class="online-library-buttton">Додати нового</button>
						</div>
					</div>
					<div id="genre-block">
						Жанри:<br>
						<c:forEach var="genre" items="${requestScope.book.genres}">
							<input class="book-edit-input online-library-input" type="text" name="genre_${genre.genreId}" value="${genre.name}" readonly="readonly">
							<button class="delete-genre online-library-buttton" type="button" value="genre_${genre.genreId}"><span>x</span></button>
						</c:forEach>
						<div class="book-buttons-block">
							<select name="chose-genre">
								<c:forEach var="genre" items="${requestScope.genres}">
  									<option value="genre_${genre.genreId}">${genre.name}</option>
  								</c:forEach>
							</select>
							<button id="add-exist-genre" class="online-library-buttton" type="button">Додати</button>
						</div>
						<div class="book-buttons-block">
							<button id="add-new-genre" class="online-library-buttton" type="button">Додати новий</button>
						</div>
					</div>
					<p>
						Опис:<br>
						<input class="online-library-input" type="text" name="description" value="${requestScope.book.description}"><br>
					</p>
					<p>
						Зображення обкладинки:<br>
						<input class="online-library-input" type="text" name="books-cover-path" value="${requestScope.book.coverImgPath}">
					</p>
					<p>
						Файл книжки:<br>
						<input class="online-library-input" type="text" name="books-file-path" value="${requestScope.book.booksFilePath}" required>
					</p>
					<div class="book-buttons-block">
						<input class="online-library-buttton" type="submit" name="sava-change" value="Зберегти">
					</div>
				</form>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
</body>
</html>