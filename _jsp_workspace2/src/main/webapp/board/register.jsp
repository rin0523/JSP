<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기 페이지</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
<style>
body {
	background-color: #f0f0f0; /* 원하는 배경색으로 변경하세요. */
}
</style>
</head>
<body>
	<h1>Board Register Page</h1>
	


	<form action="/brd/insert" method="post" enctype="multipart/form-data">
	<label for="title" class="form-label">
		제목:<input type="text" name="title"class="form-control"><br> 
		<label for="writer" class="form-label">
		작성자: <input type="text" name="writer" value="${ses.id }" readonly="readonly" class="form-control">
		<br> 
		내용:<br><label for="textarea" class="form-label">
		<textarea rows="10" cols="30" name="content" class="form-control"></textarea>
		<br> 첨부파일:<input type="file" name="image_file"
			accept="image/png,image/jpg,image/gif,image/jpeg">
		<button type="submit"class="btn btn-secondary">
        <span class="badge text-bg-secondary">submit</span></button>
	    <a href="/brd/list">
	    <button type="button"class="btn btn-secondary">
        <span class="badge text-bg-secondary">list</span></button>
		<br>


	</form>


</body>
</html>