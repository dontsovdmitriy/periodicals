<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.error.error" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="error.label.header" /></title>
</head>
<body>
	<div class="container">
		<h3><strong><fmt:message key="error.label.error" /></strong></h3>
	</div>
</body>
</html>