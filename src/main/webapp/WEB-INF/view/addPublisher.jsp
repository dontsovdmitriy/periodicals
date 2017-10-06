<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.addPublisher.addPublisher" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="addPublisher.label.title" /></title>
</head>
<body>
		<h2><fmt:message key="addPublisher.label.title" /></h2>
	<form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/rest/addPublisher">
		<div class="form-group">
     		 <label class="control-label col-sm-2" for="publisher"><fmt:message key="addPublisher.label.publisher" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="publisher" name="publisher">
     		 </div>
   		</div>
		 <div class="form-group">        
      		<div class="col-sm-offset-2 col-sm-10">
       			 <button type="submit" class="btn btn-default"><fmt:message key="addPublisher.button.add" /></button>
     		</div>
   		</div>
	</form>
</body>
</html>