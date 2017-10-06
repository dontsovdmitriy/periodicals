<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.invoiceView.invoiceView" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="invoiceView.label.header" /></title>
</head>
<body>
	<div class="container">
		<h2><fmt:message key="invoiceView.label.subscription" /></h2>
		<table class="table">
			<thead>
				<tr>
					<th><fmt:message key="invoiceView.label.periodical" /></th>
					<th><fmt:message key="invoiceView.label.startDate" /></th>
					<th><fmt:message key="invoiceView.label.numberMonth" /></th>
					<th><fmt:message key="invoiceView.label.cost" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sessionScope.invoiceList}" var="invoice">
					<tr>
						<td>${invoice.getSubscription().getPeriodical().getName()}</td>
						<td>${invoice.getSubscription().getStartDate()}</td>
						<td>${invoice.getSubscription().getNumberMonth()}</td>
						<td><ctg:cost-format cost="${invoice.cost}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>