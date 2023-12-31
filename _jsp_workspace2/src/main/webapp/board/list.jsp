<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list page</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
	<style>
    body {
        background-color: #f0f0f0; /* 원하는 배경색으로 변경하세요. */
    }
</style>
</head>
<body>
	<h1>List Page</h1>


	<!-- search line -->
	<div>
		<form action="/brd/list" method="get">
			<c:set value="${ph.pgvo.type }" var="typed"></c:set>
			<select name="type">
				<option ${ph.pgvo.type==null? 'selected' : ''}>Choose..</option>
				<option value="t" ${typed eq 't'? 'selected' : ''}>title</option>
				<option value="w" ${typed eq 'w'? 'selected' : ''}>writer</option>
				<option value="c" ${typed eq 'c'? 'selected' : ''}>content</option>
				<option value="tc" ${typed eq 'tc'? 'selected' : ''}>title&content</option>
				<option value="tw" ${typed eq 'tw'? 'selected' : ''}>title&writer</option>
				<option value="wc" ${typed eq 'wc'? 'selected' : ''}>writer&content</option>
				<option value="twc" ${typed eq 'twc'? 'selected' : ''}>title&writer&content</option>
			</select> 
			<input type="text" name="keyword" placeholder="Search"value="${ph.pgvo.keyword }"> 
			<input type="hidden" name="pageNo" value="1"> 
			<input type="hidden" name="qty" value="${ph.pgvo.qty }"> 
			<button type="submit" class="btn btn-primary position-relative">
				<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
					99+ <span class="visually-hidden">Search</span>
				</span>Search
			</button>
			
		</form>


	</div>



	<table class="table table-hover">
		<tr>
			<th>bno</th>
			<th>thumbnail</th>
			<th>title</th>
			<th>writer</th>
			<th>regdate</th>
			<th>readCount</th>
		</tr>

		<!-- DB에서 가져온 리스트를 c:foreach를 통해 반복 -->
		<c:forEach items="${list }" var="bvo">
			<tr>
				<td><a href="/brd/detail?bno=${bvo.bno }">${bvo.bno }</a></td>
				<td><img alt="" src="/_fileUpload/th_${bvo.imageFile }"></td>
				<td><a href="/brd/detail?bno=${bvo.bno }">${bvo.title }</a></td>
				<td>${bvo.writer }</td>
				<td>${bvo.regdate }</td>
				<td>${bvo.readcount }</td>
			</tr>
		</c:forEach>



	</table>

	<!-- 페이지네이션 표시 구역 -->
	<div>
	
	<nav aria-label="Page navigation example">
    <ul class="pagination">
        <c:if test="${ph.prev}">
            <li class="page-item">
                <a class="page-link" href="/brd/list?pageNo=${ph.startPage - 1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>

        <c:forEach begin="${ph.startPage}" end="${ph.endPage}" var="i">
            <li class="page-item">
                <a class="page-link" href="/brd/list?pageNo=${i}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i}</a>
            </li>
        </c:forEach>

        <c:if test="${ph.next}">
            <li class="page-item">
                <a class="page-link" href="/brd/list?pageNo=${ph.endPage + 1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>
    </ul>
</nav>
	
	
	
	</div>

	<a href="/brd/register"><button type="button" class="btn btn-secondary">
			<span class="badge text-bg-secondary">register</span>
		</button></a>
	<a href="/index.jsp"><button type="button"class="btn btn-secondary">
			<span class="badge text-bg-secondary">index</span>
		</button></a>







</body>
</html>