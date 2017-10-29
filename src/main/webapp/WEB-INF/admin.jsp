<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Адмін</title>
<link href="css/style.css" type="text/css" rel="stylesheet">
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/onlinelibrary.js" type="text/javascript"></script>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="header.jsp" />
		<div id="content">
			<div id="new-book">
				<h3>Додати книгу</h3>
				<div id="book-edit-form">
				<form action="Createbook" method="post" name="create-new-book">
					<p>
						Назва:<br>
						<input class="online-library-input" type="text" name="name" required><br>
					</p>
					<div id="author-block">
						Автори:<br>
						<div class="book-buttons-block">
							<button type="button" id="add-new-author" class="online-library-buttton">Додати нового</button>
						</div>
					</div>
					<div id="genre-block">
						Жанри:<br>
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
						<input class="online-library-input" type="text" name="description"><br>
					</p>
					<p>
						Зображення обкладинки:<br>
						<input class="online-library-input" type="text" name="books-cover-path" >
					</p>
					<p>
						Файл книжки:<br>
						<input class="online-library-input" type="text" name="books-file-path" required>
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