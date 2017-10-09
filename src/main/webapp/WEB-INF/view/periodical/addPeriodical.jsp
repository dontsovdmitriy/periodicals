<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.addPeriodical.addPeriodical" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="addPeriodical.label.header" /></title>
</head>
<body>
	<h2><fmt:message key="addPeriodical.label.header" /></h2>
	<form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/rest/addPeriodical">
		<div class="form-group">
     		 <label class="control-label col-sm-2" for="name"><fmt:message key="addPeriodical.label.name" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="name" name="name">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for="description"><fmt:message key="addPeriodical.label.description" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="description" name="description">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for="costPerMonth"><fmt:message key="addPeriodical.label.cost" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="costPerMonth" name="costPerMonth">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for="publisher"><fmt:message key="addPeriodical.label.publisher" />:</label>
     		  <div class="col-sm-6">		  
     			 <select class = "form-control" id="publisher" name="publisher">
     			 	 <c:forEach items="${sessionScope.publisherList}" var="publisher">
     			 	 	<option value="${publisher.id}">${publisher.publisher}</option>
     			 	 </c:forEach>
				</select>
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for="category"><fmt:message key="addPeriodical.label.category" />:</label>
     		  <div class="col-sm-6">		  
     			 <select class = "form-control" id="category" name="category">
     			 	<c:forEach items="${sessionScope.periodicalCategoryList}" var="category">
							<option value="${category.id}">${category.categoryName}</option>
					</c:forEach>
				</select>
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for="status"><fmt:message key="addPeriodical.label.status" />:</label>
     		  <div class="col-sm-6">		  
     			 <select class = "form-control" id="status" name="status">
     			 	<option selected value="ACTIV"><fmt:message key="addPeriodical.select.activ" /></option>
					<option value="INACTIV"><fmt:message key="addPeriodical.select.inactiv" /></option>
				</select>
     		 </div>
   		</div>
   		 <div class="form-group">        
      		<div class="col-sm-offset-2 col-sm-10">
       			 <button type="submit" class="btn btn-default"><fmt:message key="addPeriodical.button.add" /></button>
     		</div>
   		</div>
   	</form>	
</body>
</html>