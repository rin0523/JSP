<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> Modify Page</h1>
	<form action="/brd/edit" method="post">
	<table border="1">
	<input type="hidden" name="bno" value="${bvo.bno }">
		<tr>
			<th>bno</th>
			<th>title</th>
			<th>writer</th>
			<th>content</th>
			<th>regdate</th>
			<th>moddate</th>
			<th>readCount</th>
		</tr>

	
			<tr>
				<td><input type="text" name="bno" value= "${bvo.bno }" readonly = "reaonly"></td>
				<td><input type="text" name="title" value= "${bvo.title}"></td>
				<td>${bvo.writer }</td>
				<td><textarea rows="10" cols="30" name="content">${bvo.content }></textarea></td>
				<td>${bvo.regdate }</td>
				<td>${bvo.moddate }</td>
				<td>${bvo.readcount }</td>
			</tr>
	
	</table>
	<button type="submit">modify</button>
	</form>

	
	<a href="/brd/remove? bno=${bvo.bno }"><button>delete</button></a>
	<a href="/brd/list"><button>list</button></a>


</body>
</html>