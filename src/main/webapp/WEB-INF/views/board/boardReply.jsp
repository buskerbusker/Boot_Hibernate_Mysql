<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>


<c:import url="../template/boot.jsp"></c:import>
</head>
<body>

	<div class="container">
		<h1>${board}replyForm</h1>


		<form action="./${board}Reply" id="frm" method="post">
			<input type="hidden" name="num" value="${num}">
			<div class="form-group">
				<label for="title">Title:</label> <input type="text"
					class="form-control" id="title" name="title">
			</div>
			<div class="form-group">
				<label for="Writer">Writer:</label> <input type="text"
					class="form-control" id="reply" name="writer">
			</div>
			<div class="form-group">
				<label for="contents">Contents:</label>
				<textarea rows="5" cols="" class="form-control" id="contents"
					name="contents"></textarea>
			</div>




			<input type="submit" id="btn" class="btn btn-default" value="write">
		</form>

	</div>



</body>
</html>