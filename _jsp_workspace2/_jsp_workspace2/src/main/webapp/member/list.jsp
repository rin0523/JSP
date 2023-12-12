<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <style>
        body {
            background-color: #f0f0f0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            width: 80%;
            text-align: center;
        }

        table {
            width: 100%;
            margin: auto;
        }
    </style>
</head>
<body>

    <div class="container">
	<h1>Member List Page(관리자용)</h1>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>PW</th>
			<th>E-mail</th>
			<th>Age</th>
			<th>Reg-date</th>
			<th>Last-login</th>
		</tr>

		<c:forEach items="${list }" var="mvo">
			<tr>
				<td>${mvo.id}</td>
				<td>${mvo.pwd}</td>
				<td>${mvo.email }</td>
				<td>${mvo.age}</td>
				<td>${mvo.regdate}</td>
				<td>${mvo.lastlogin }</td>
			</tr>
		</c:forEach>
		


	</table>
	    <br>
		<a href="/brd/list"><button type="button">list</button>
</body>
</html>