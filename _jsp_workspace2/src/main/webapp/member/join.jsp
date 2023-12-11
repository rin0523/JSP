<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join Page</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
<style>
body {
	background-color: #f0f0f0; /* 원하는 배경색으로 변경하세요. */
}
</style>
</head>
<body>
	<div class="container">
		<h1>Join Page</h1>
		<br>

		<form action="/memb/register" method="post">
			<div class="mb-3">
				<label for="id" class="form-label">
				ID:</label> <input type="text" name="id" class="form-control">
			</div>
			<div class="mb-3">
				<label for="pwd" class="form-label">
				Password:</label> <input type="password" name="pwd" class="form-control">
			</div>
			<div class="mb-3">
				<label for="email" class="form-label">
				E-Mail:</label> <input type="text" name="email" class="form-control">
			</div>
			<div class="mb-3">
				<label for="age" class="form-label">
				Age:</label> <input type="text" name="age" class="form-control">
			</div>


			<button type="submit" class="btn btn-primary">Join</button>
		</form>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>