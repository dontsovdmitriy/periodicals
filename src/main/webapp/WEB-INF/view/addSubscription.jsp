<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.addSubscription.addSubscription" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="addSubscription.label.header" /></title>
</head>
<body>
<h2><fmt:message key="addSubscription.label.header" /></h2>
	<form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/rest/addSubscription">
		<div class="form-group">
     		 <label class="control-label col-sm-2" for="periodical"><fmt:message key="addSubscription.label.periodical" />:</label>
     		  <div class="col-sm-6">		  
     			 <select class = "form-control" id="periodical" name="periodical">
     			 	 <c:forEach items="${sessionScope.periodicalsList}" var="periodical">
     			 	 	<option value="${periodical.id}">${periodical.name}</option>
     			 	 </c:forEach>
				</select>
     		 </div>
   		</div>
		<div class="form-group">
     		 <label class="control-label col-sm-2" for="startDate"><fmt:message key="addSubscription.label.startDate" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="startDate" name="startDate">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for="numberMonth"><fmt:message key="addSubscription.label.numberMonth" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="numberMonth" name="numberMonth">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for="address"><fmt:message key="addSubscription.label.address" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="address" name="address">
     		 </div>
   		</div>
   		 <div class="form-group">        
      		<div class="col-sm-offset-2 col-sm-10">
       			 <button type="submit" class="btn btn-default"><fmt:message key="addSubscription.label.add" /></button>
     		</div>
   		</div>
   	</form>	  	
</body>
</html>