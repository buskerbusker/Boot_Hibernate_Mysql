<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/boot.jsp"></c:import>
</head>
<body>
	<c:import url="../template/nav.jsp"></c:import>

	<!-- MemberVO 를  세션 혹은 조회해서 보내줘야함 -->

	<div class="container">
		<form:form modelAttribute="memberVO" action="./memberUpdate"
			method="post" enctype="multipart/form-data">

			<div class="form-group">
				<label for="id">아이디:</label>
				<form:input path="id" type="text" class="form-control" id="id"></form:input>
				<form:errors path="id"></form:errors>
			</div>
			<div class="form-group">
				<label for="pw">패스워드:</label>
				<form:input type="password" path="pw" class="form-control" id="pw" />
				<form:errors path="pw"></form:errors>
			</div>
			<div class="form-group">
				<label for="pwCheck">패스워드 확인:</label>
				<form:input type="password" path="pwCheck" class="form-control"
					id="pwCheck" />
				<form:errors path="pwCheck"></form:errors>
			</div>
			<div class="form-group">
				<label for="age">나이</label>
				<form:input type="number" path="age" class="form-control" id="age" />
				<form:errors path="age"></form:errors>
			</div>
			<div class="form-group">
				<label for="email">이메일</label>
				<form:input type="email" path="email" class="form-control"
					id="email" />
				<form:errors path="email"></form:errors>
			</div>
			<div class="form-group">
				<label for="name">성명</label>
				<form:input type="text" path="name" class="form-control" id="name" />
				<form:errors path="name"></form:errors>
			</div>
			<div class="form-group">
				<label for="phone">전화번호</label>
				<form:input type="text" path="phone" class="form-control" id="phone" />
				<form:errors path="phone"></form:errors>
			</div>



			<input type="button" class="btn btn-info" id="add" value="FileAdd">
			<div class="form-group" id="f"></div>

			<button type="submit" class="btn btn-default">Submit</button>
		</form:form>
	</div>
</body>
</html>