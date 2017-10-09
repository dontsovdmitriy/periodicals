<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.subscriptionView.subscriptionView" />
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="subscriptionView.label.header" /></title>
</head>
<body>
<div class="container">
		<h2><fmt:message key="subscriptionView.label.header" /></h2>
		<table class="table">
			<thead>
				<tr>
					<th><fmt:message key="subscriptionView.label.subscription" /></th>
					<th><fmt:message key="subscriptionView.label.startDate" /></th>
					<th><fmt:message key="subscriptionView.label.numberMonth" /></th>
					<th><fmt:message key="subscriptionView.label.surname" /></th>
				 	<th><fmt:message key="subscriptionView.label.name" /></th>
					<th><fmt:message key="subscriptionView.label.address" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sessionScope.subscriptionList}" var="subscription">
					<tr>
						<td>${subscription.getPeriodical().getName()}</td>
						<td>${subscription.getStartDate()}</td>
						<td>${subscription.getNumberMonth()}</td>
						<td>${subscription.getUser().getSurname()}</td>
						<td>${subscription.getUser().getName()}</td>
						<td>${subscription.getAddress()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>