<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/">WebSiteName</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a
						href="${pageContext.request.contextPath}/notice/noticeList">Notice</a></li>
					<li><a href="${pageContext.request.contextPath}/qna/qnaList">Qna</a></li>
					<li><a href="#">Page 1-3</a></li>
				</ul></li>
			<li><a href="#">Page 2</a></li>
		</ul>

		<c:if test="${ empty member }">
			<ul class="nav navbar-nav navbar-right">
				<li><a
					href="${pageContext.request.contextPath}/member/memberJoin"><span
						class="glyphicon glyphicon-user"></span> Sign Up</a></li>
				<li><a
					href="${pageContext.request.contextPath}/member/memberLogin"><span
						class="glyphicon glyphicon-log-in"></span> Login</a></li>
			</ul>
		</c:if>

		<c:if test="${not empty member }">
			<ul class="nav navbar-nav navbar-right">
				<li><a
					href="${pageContext.request.contextPath}/member/memberLogout"><span
						class="glyphicon glyphicon-log-in"></span> LogOut</a></li>
				<li><a
					href="${pageContext.request.contextPath}/member/memberDelete"><span
						class="glyphicon glyphicon-log-in"></span> 회원 탈퇴하기</a></li>
				<li><a
					href="${pageContext.request.contextPath}/member/memberUpdate?id=${member.id}"><span
						class="glyphicon glyphicon-log-in"></span> 회원 정보 수정하기</a></li>			
			</ul>
		</c:if>
	</div>
</nav>

<div class="container">
	<h3>Right Aligned Navbar</h3>
	<p>The .navbar-right class is used to right-align navigation bar
		buttons.</p>
</div>