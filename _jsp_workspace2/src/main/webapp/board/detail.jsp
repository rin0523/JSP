<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Detail page</h1>
<table border="1">
<tr>
<th>bno</th>
<th>title</th>
<th>writer</th>
<th>content</th>
<th>regdate</th>
<th>moddate</th>
<th>readcount</th>

</tr>

<tr>
<td><a href="/brd/detail?bno=${bvo.bno }">${bvo.bno }</a></td>
<td><a href="/brd/detail?bno=${bvo.bno }">${bvo.title }</a></td>
<td>${bvo.writer }</td>
<td>${bvo.content }</td>
<td>${bvo.regdate }</td>
<td>${bvo.moddate }</td>
<td>${bvo.readcount }</td>



</tr>

</table>
</body>
</html>