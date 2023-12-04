<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Detail Page</h1>

	<form action="/memb/modify" method="post">
		<table border="1">
			<input type="hidden" name="id" value="${ses.id }">
			<tr>
				<th>ID</th>
				<td>${ses.id}</td>
			<tr>
				<th>PW</th>
				<td><input type="text" name="pwd" value="${ses.pwd }"></td>
			</tr>
			<tr>
				<th>E-mail</th>
				<td><input type="text" name="email" value="${ses.email }"></td>
			</tr>
			<tr>
				<th>Age</th>
				<td><input type="text" name="age" value="${ses.age }"></td>
			</tr>
			<tr>
				<th>Regdate</th>
				<td>${ses.regdate }</td>
			</tr>
			<tr>
				<th>lastlogin</th>
				<td>${ses.lastlogin }</td>
			</tr>
		</table>
		<button type="submit">수정</button>
		<a href="/memb/remove?id=${ses.id }"><button type="button">회원탈퇴</button></a>
	</form>

	<a href="/memb/list"><button>list</button></a>
</body>
</html>