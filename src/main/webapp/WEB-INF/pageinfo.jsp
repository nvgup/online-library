<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" type="text/css" rel="stylesheet">
<title>Помилка</title>
<script language="javascript" type="text/javascript">
  		window.setTimeout('window.location="Books";', 10000);
</script>
</head>
<body>
	<div id="wrapper">
		<div id="content">
			<h3>${requestScope.message}</h3>
			<h3><a href="Books">На головну</a></h3>
		</div>
	</div>
</body>
</html>