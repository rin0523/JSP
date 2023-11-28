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
	<form action="/brd/edit" method="post">
	<input type="hidden" name="bno" value="${bvo.bno }">
	<table border="1">
		<tr>
			<th>bno</th>
			<%-- <td><input type="text" name="bno" value="${bvo.bno }" readonly="readonly"></td> --%>
			<td>${bvo.bno }</td>
		</tr>
		<tr>
			<th>title</th>
			<td><input type="text" name="title" value="${bvo.title }"></td>
		</tr>
		<tr>
			<th>writer</th>
			<td>${bvo.writer }</td>
		</tr>
		<tr>
			<th>content</th>
			<td><textarea rows="10" cols="30" name="content">${bvo.content }</textarea></td>
		</tr>
		<tr>
			<th>regdate</th>
			<td>${bvo.regdate }</td>
		</tr>
		<tr>
			<th>moddate</th>
			<td>${bvo.moddate }</td>
		</tr>
		<tr>
			<th>readCount</th>
			<td>${bvo.readcount }</td>
		</tr>
	</table>
	
	<button type="submit">modify</button>
	</form>
	
	<a href="/brd/remove?bno=${bvo.bno }"><button type="button">remove</button></a>
	<a href="/brd/list"><button>list</button></a>
</body>
</html>