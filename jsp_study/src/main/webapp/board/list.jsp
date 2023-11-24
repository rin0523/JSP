<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list page</title>
</head>
<body>
	<h1>List Page</h1>
	<table border="1">
		<tr>
			<th>bno</th>
			<th>title</th>
			<th>writer</th>
			<th>regdate</th>
			<th>readCount</th>
		</tr>

		<!-- DB에서 가져온 리스트를 c:foreach를 통해 반복 -->
		<c:forEach items="${list }" var="bvo">
		<tr>
		<td><a href="/brd/detail?bno=${bvo.bno }">${bvo.bno }</a></td>
		<td><a href="/brd/detail?bno=${bvo.bno }">${bvo.title }</a></td>
		<td>${bvo.writer }</td>
		<td>${bvo.regdate }</td>
		<td>${bvo.readcount }</td>
		</tr>
        </c:forEach>
		
		

	</table>
	
	<a href="/brd/register"><button>register</button></a>
	<a href="/index.jsp"><button>index</button></a>
	
	
	
</body>
</html>