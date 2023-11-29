<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기 페이지</title>
</head>
<body>
	<h1>Board Register Page</h1>
	<form action="/brd/insert" method="post">
		제목:<input type="text" name="title"><br> 
		작성자:<input type="text" name="writer" value="${ses.id }"><br>
		내용:<br>
		<textarea rows="10" cols="30" name="content"></textarea>
		<br>

		<button type="submit">전송</button>

	</form>

	<button type="button">리스트로</button>


</body>
</html>