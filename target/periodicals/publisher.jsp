<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>publisher</title>
</head>
<body>
	<form method="POST" action="./rest/publisher">
		<h1>Добавление нового издателя</h1>
		<table>
			<tr>
				<td>Издатель:</td>
				<td><input type="text" name="publisher"></td>
			</tr>
			<tr>
				<td align="right" colspan="2"><input type="submit" value="Добавить"></td>
			</tr>
			<tr>
				<td align=left colspan="2"><a href= index.jsp>На главную </a></td>
			</tr>
		</table>
	</form>
</body>
</html>