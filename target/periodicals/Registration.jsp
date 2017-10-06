<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration page</title>
</head>
<body>
	<form method="POST" action="./rest/registration">
		<h1>Добавление нового пользователя</h1>
		<table>
			<tr>
				<td>Имя:</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>Фамилия:</td>
				<td><input type="text" name="surname"></td>
			</tr>
			<tr>
				<td>Мобильный номер:</td>
				<td><input type="text" name="mobilePhone"></td>
			</tr>
			<tr>
				<td>email:</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>Логин:</td>
				<td><input type="text" name="login"></td>
			</tr>
			<tr>
				<td>Пароль:</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td>Повторите пароль:</td>
				<td><input type="password" name="passwordConfirm"></td>
			</tr>
			<tr>
				<td align="right" colspan="2"><input type="submit" value="Зарегестрировать"></td>
			</tr>
			<tr>
				<td align=left colspan="2"><a href= index.jsp>На главную </a></td>
			</tr>
		</table>
	</form>
</body>
</html>