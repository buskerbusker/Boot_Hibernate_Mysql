<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/boot.jsp"></c:import>
</head>
<body>

	<c:import url="../template/nav.jsp"></c:import>

	<div class="container">

		<h3>Title : ${vo.title}</h3>
		<h3>Writer : ${vo.writer}</h3>

		<div>
			<c:catch>
				<c:forEach items="${vo.noticeFileVOs}" var="noticefile">
					<div>${noticefile.oriName}</div>
				</c:forEach>
			</c:catch>
		</div>


		<a href="${board}Update?num=${vo.num}" class="btn btn-primary">수정</a>
		<a href="${board}Delete?num=${vo.num}" class="btn btn-danger">삭제</a> <a
			href="${board}Reply?num=${vo.num}" class="btn btn-danger">답글달기</a>
		<%-- <a href="${board}Delete?num=${vo.num}" class="btn btn-danger">삭제</a> --%>
	</div>


</body>
</html>