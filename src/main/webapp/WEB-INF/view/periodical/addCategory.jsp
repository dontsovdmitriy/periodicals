<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.addCategory.addCategory" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="addCategory.label.title" /></title>
</head>
<body>
	<h2><fmt:message key="addCategory.label.title" /></h2>
	<form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/rest/addCategory">
		<div class="form-group">
     		 <label class="control-label col-sm-2" for="category"><fmt:message key="addCategory.label.category" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="category" name="category">
     		 </div>
   		</div>
		 <div class="form-group">        
      		<div class="col-sm-offset-2 col-sm-10">
       			 <button type="submit" class="btn btn-default"><fmt:message key="addCategory.label.add" /></button>
     		</div>
   		</div>
	</form>
</body>
</html>