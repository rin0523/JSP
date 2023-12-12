<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
<style>
body {
	background-color: #f0f0f0; /* 원하는 배경색으로 변경하세요. */
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100vh;
    margin: 0;
}

.container {
    width: 80%;
}

table {
    width: 100%; /* 테이블의 너비를 100%로 지정 */
    margin: auto; /* 가운데 정렬 */
}

</style>
</head>
<body>

<div class="container">

	<h1>Detail Page</h1>

	<form action="/memb/modify" method="post">
		<table>
			<input type="hidden" name="id" value="${ses.id }">
			<tr>
				<th>ID</th>
				<td>${ses.id}</td>
			<tr>
			    <label for="pwd" class="form-label">
				<th>PW</th>
				<td><input type="text" name="pwd" value="${ses.pwd }" class="form-control"></td>
			</tr>
			<tr>
			    <label for="email" class="form-label">
				<th>E-mail</th>
				<td><input type="text" name="email" value="${ses.email }"class="form-control"></td>
			</tr>
			<tr> 
			    <label for="age" class="form-label">
				<th>Age</th>
				<td><input type="text" name="age" value="${ses.age }"class="form-control"></td>
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
		   <div style="text-align: center;">
        <button type="submit"class="btn btn-secondary">
        <span class="badge text-bg-secondary">수정</span></button>
        <a href="/memb/remove?id=${ses.id }">
        <button type="button"class="btn btn-secondary">
        <span class="badge text-bg-secondary">회원탈퇴</span></button></a>
        <a href="/memb/list"><button class="btn btn-secondary">
        <span class="badge text-bg-secondary">list</span></button></a>
    </div>
</form><br>

</div>
</body>
</html>