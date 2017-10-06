<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.home.home" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="home.label.header" /></title>
</head>
<body>
	<div class="container">
		<c:choose>
			<c:when test="${not empty sessionScope.user}">
				<p><fmt:message key="home.label.welcome" /> ${sessionScope.user.name}</p><br>
			</c:when>
		</c:choose>
		${message}
	</div>
</body>
</html>