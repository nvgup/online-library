$(document).ready(function() {
	
	$("form[name=registration]").submit(function(){
		if($("input[name=password]").val() !== $("input[name=conf-password]").val()){
			$("#correct-conf-pass").show();
			return false;
		}
	});
	
	$("form[name=create-new-book]").submit(function(e){
		console.log("subm");
		$.ajax({
			type: "POST",
			url: "Admin",
			data: $(this).serialize(), 
			success: function(){
				alert("Книгу додано");
				window.location.href = "Admin";
			},
			error: function(){
				alert("Сталася помилка"); 
			}
		});
	    e.preventDefault();
	});
	
	$(document).on('click', '.delete-genre, .delete-author', function() {
		var val = $(this).val();
		$("input[name=" + val + "]").remove();
		$(this).remove();
	});
	
	$("#add-exist-genre").click(function(){
		var name = $("select[name=chose-genre]").val();
		if($("input[name=" + name).length) {
			return;
		}
		var value = $("select[name=chose-genre] :selected").text();
		$(this).parent().before('<input class="book-edit-input online-library-input" type="text" name="' + name + '" value=' + value + ' readonly="readonly">');
		$(this).parent().before('<button class="delete-genre online-library-buttton" type="button" value="' + name + '"><span>x</span></button>');
	});
	
	$("#add-new-author").click(function(){
		var children = $("#author-block").children().length;
		$(this).parent().before('<input class="book-edit-input online-library-input" type="text" name="new-author_' + children + '">');
		$(this).parent().before('<button class="delete-author online-library-buttton" type="button" value="new-author_' + children + '"><span>x</span></button>');
	});
	
	$("#add-new-genre").click(function(){
		var children = $("#genre-block").children().length;
		$(this).parent().prev().before('<input class="book-edit-input online-library-input" type="text" name="new-genre_' + children + '">');
		$(this).parent().prev().before('<button class="delete-genre online-library-buttton" type="button" value="new-genre_' + children + '"><span>x</span></button>');
	});
	
	$("#delete-book").click(function() {
		var del = confirm("Видалити книгу ?");
		if(!del) {
			return;
		}
		var bookId = $(this).val();
		var data = "bookId=" + bookId;
		$.ajax({
			type : "GET",
			url : "Deletebook",
			data : data,
			dataType : "json",
			success : function(result) {
				alert("Книгу видалено");
				window.location.href = "Books";
			},
			error : function() {
				alert("Сталася помилка");
			}
		});
	});
	
	$(".add-to-fav-list").click(function() {
		var bookId = $(this).val();
		var data = "bookId=" + bookId;
		$.ajax({
			type : "POST",
			url : "Addbook",
			data : data,
			dataType : "json",
			success : function(result) {
				if(result.status === 'exist') {
					alert("Книга уже знаходиться в обраному");
				}
				if(result.status === 'added') {
					alert("Книгу додано");
				}
			},
			error : function() {
				alert("Сталася помилка");
			}
		});
	});
});