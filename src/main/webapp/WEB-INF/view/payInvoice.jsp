<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.payInvoice.payInvoice" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="payInvoice.label.title" /></title>
</head>
<body>
	<h2><fmt:message key="payInvoice.label.title" /></h2>
	<form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/rest/payInvoice">
	<div class="form-group">
     		 <label class="control-label col-sm-2" for="invoice"><fmt:message key="payInvoice.label.subscription" />:</label>
     		  <div class="col-sm-6">		  
     			 <select class = "form-control" id="invoice" name="invoice">
     			 	 <c:forEach items="${sessionScope.invoiceList}" var="invoice">
     			 	 	<option value="${invoice.id}"><fmt:message key="payInvoice.label.periodical" />: ${invoice.getSubscription().getPeriodical().getName()}, <fmt:message key="payInvoice.label.startDate" />: ${invoice.getSubscription().getStartDate()}, <fmt:message key="payInvoice.label.numberMonth" />: ${invoice.getSubscription().getNumberMonth()} , <fmt:message key="payInvoice.label.cost" />: <ctg:cost-format cost="${invoice.getCost()}"/></option>
     			 	 </c:forEach>
				</select>
     		 </div>
   		</div>
   		 <div class="form-group">        
      		<div class="col-sm-offset-2 col-sm-10">
       			 <button type="submit" class="btn btn-default"><fmt:message key="payInvoice.button.pay" /></button>
     		</div>
   		</div>
   	</form>	
</body>
</html>