<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.general.index" />

<html lang="${language}">
<head>
<title>Index jsp</title>
</head>
<body>
	<h1>Welcome to the main page </h1>
	 <form>
            <select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>EN</option>
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>RU</option>
            </select>
        </form>
        <form method="post">
            <label for="username"><fmt:message key="login.label.username" />:</label>
            <input type="text" id="username" name="username">
            <br>
            <label for="password"><fmt:message key="login.label.password" />:</label>
            <input type="password" id="password" name="password">
            <br>
            <fmt:message key="login.button.submit" var="buttonValue" />
            <input type="submit" name="submit" value="${buttonValue}">
        </form>
	
	
	
	
	
	
	
	
	
	
	
	<%-- 
	<table>
			<tr>
				<td>Логин:</td>
				<td><input type="text" name="login"></td>
			</tr>
			<tr>
				<td>Пароль:</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td align="right" colspan="2"><input type="submit" value="Вход"></td>
			</tr>
			<tr>
				<td><a href="">Регистрация</a></td>
			</tr>
	</table>
	--%>
</body>
</html>