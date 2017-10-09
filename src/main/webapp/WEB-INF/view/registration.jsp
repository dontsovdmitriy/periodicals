<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.registration.registration" />
<!DOCTYPE html>
<html>
<head>
<title>Registration page</title>
</head>
<body>
	<h2><fmt:message key="login.label.header" /></h2>
	<form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/rest/addRegistration">
		<div class="form-group">
     		 <label class="control-label col-sm-2" for="name"><fmt:message key="login.label.name" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="name" name="name">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for="surname"><fmt:message key="login.label.surname" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="surname" name="surname">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for="mobilePhone"><fmt:message key="login.label.mobilePhone" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="mobilePhone" name=mobilePhone>
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for="email"><fmt:message key="login.label.email" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="email" name="email">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for="login"><fmt:message key="login.label.login" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="login" name="login">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for="password"><fmt:message key="login.label.password" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="password" class="form-control" id="password" name="password">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for="passwordConfirm"><fmt:message key="login.label.passwordConfirm" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm">
     		 </div>
   		</div>
   		 <div class="form-group">        
      		<div class="col-sm-offset-2 col-sm-10">
       			 <button type="submit" class="btn btn-default"><fmt:message key="login.button.submit" /></button>
     		</div>
   		</div>
   	</form>	
</body>
</html>