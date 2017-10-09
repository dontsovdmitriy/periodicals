<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.navbar.navbar" />
<!DOCTYPE html>
<html lang="${language}">
<head>
  <title><fmt:message key="navbar.label.title" /></title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
  <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
   <%-- <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script> --%>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand"><fmt:message key="navbar.label.brand" /></a>
    </div>
    <div class="collapse navbar-collapse myNavbar">
    <c:if test="${not empty sessionScope.user}">
      <ul class="nav navbar-nav">
        <li class="active"><a href="${pageContext.request.contextPath}/rest/home"><fmt:message key="navbar.label.home" /></a></li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#"><fmt:message key="navbar.label.add" /><span class="caret"></span></a>
          <ul class="dropdown-menu">
       		<li><a href="${pageContext.request.contextPath}/rest/subscription"><fmt:message key="navbar.label.subscription" /></a></li>
       		 <c:if test="${sessionScope.user.type == 'ADMIN'}">
      			<li><a href="${pageContext.request.contextPath}/rest/category"><fmt:message key="navbar.label.category" /></a></li>
        		<li><a href="${pageContext.request.contextPath}/rest/publisher"><fmt:message key="navbar.label.publisher" /></a></li>
        		<li><a href="${pageContext.request.contextPath}/rest/periodical"><fmt:message key="navbar.label.periodical" /></a></li>
        	</c:if>
          </ul>
        </li> 
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#"><fmt:message key="navbar.label.edit" /><span class="caret"></span></a>
          <ul class="dropdown-menu">
           <c:if test="${sessionScope.user.type == 'ADMIN'}">
             <li><a href="${pageContext.request.contextPath}/rest/editPeriodicalView"><fmt:message key="navbar.label.periodical" /></a></li>
           </c:if>
            <li><a href="${pageContext.request.contextPath}/rest/invoicePayView"><fmt:message key="navbar.label.invoicePaying" /> </a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#"><fmt:message key="navbar.label.subscription" /><span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="${pageContext.request.contextPath}/rest/showInvoiceByTypePaid"><fmt:message key="navbar.label.paidSubscription" /></a></li>
            <li><a href="${pageContext.request.contextPath}/rest/showInvoiceByTypeUnpaid"><fmt:message key="navbar.label.unPaidSubscription" /> </a></li>
            <li><a href="${pageContext.request.contextPath}/rest/activeSubscription"><fmt:message key="navbar.label.activeSubscription" /></a></li>
             <c:if test="${sessionScope.user.type == 'ADMIN'}">
               <li><a href="${pageContext.request.contextPath}/rest/activeSubscriptionAll"><fmt:message key="navbar.label.activeSubscriptionAll" /></a></li> 
             </c:if>           
          </ul>
        </li>     
      </ul>
      </c:if>
      <ul class="nav navbar-nav navbar-right">
      	<c:choose>
                <c:when test="${not empty sessionScope.user}">
                   <li><a href="${pageContext.request.contextPath}/rest/logout"><span class="glyphicon glyphicon-log-out"></span> <fmt:message key="navbar.label.logout" /></a></li>
               	</c:when>
               	<c:otherwise>      
        <li><a href="${pageContext.request.contextPath}/rest/registration"><span class="glyphicon glyphicon-user"></span> <fmt:message key="navbar.label.signup" /></a></li>
        <li><a href="${pageContext.request.contextPath}/rest/login"><span class="glyphicon glyphicon-log-in"></span><fmt:message key="navbar.label.login" /></a></li>
          	</c:otherwise>
        </c:choose>
        <li>
        <form>
        	<select class = "form-control" id="language" name="language" onchange="submit()">
				<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
				<option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
			</select>
		</form>
        </li>
      </ul>
    </div>
  </div>
</nav>
</body>
</html>