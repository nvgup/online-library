<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="header">
	<ul>
		<li><a href="/OnlineLibrary/Books">Головна</a></li>
		<c:choose>
			<c:when test="${sessionScope.currentSessionUser eq null}">
				<li><a href="login.jsp">Увійти</a></li>
				<li><a href="registration.jsp">Зареєструватися</a></li>
			</c:when>
			<c:otherwise>
				<li>Ласкаво просимо, ${sessionScope.currentSessionUser.name} (<a href="Logout">Вийти</a>)</li>
				<li><a href="Personal">Персональний кабінет</a></li>
				<c:if test="${fn:toLowerCase(sessionScope.currentSessionUser.userType.type) eq fn:toLowerCase(applicationScope.roles.ADMIN)}">
					<li><a href="Admin">Адмін</a></li>
				</c:if>
			</c:otherwise>
		</c:choose>
	</ul>
</div>