<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<h2>${board}list</h2>

		<form action="./${board}List" class="form-inline" id="frm">
			<input type="hidden" name="page" id="p">
			<div class="input-group input-group-sm col-xs-2">
				<select class="form-control" id="sel1" name="kind">
					<option id="title" value="bt">Title</option>
					<option id="writer" value="bc">Contents</option>
					<option id="contents" value="bw">Writer</option>
				</select>
			</div>
			<div class="input-group input-group-sm col-xs-4">

				<input type="text" class="form-control" placeholder="Search"
					name="search" value="${param.search}">
				<div class="input-group-btn">
					<button class="btn btn-default" type="submit">
						<i class="glyphicon glyphicon-search"></i>
					</button>
				</div>
			</div>
		</form>

		<table class="table table-hover">
			<tr>
				<td>Num</td>
				<td>Title</td>
				<td>Writer</td>
				<td>Date</td>
				<td>Hit</td>
			</tr>

			<c:forEach items="${page.content}" var="vo">

				<tr>
					<td>${vo.num}</td>
					<td>
						<%-- <c:catch>
							<c:forEach begin="1" end="${vo.depth}">--</c:forEach>
						</c:catch> <a href="${board}Select?num=${vo.num}"> --%>${vo.title}<!-- </a> -->
					</td>
					<td>${vo.writer}</td>
					<td>${vo.regDate}</td>
					<td>${vo.hit}</td>
				</tr>
			</c:forEach>
		</table>

		<div>

			<span><a href="#" class="custompager" title="0">&lt;&lt;</a></span> <span><a
				href="#" class="custompager" title="${page.getNumber()-1}">&lt;</a></span>
			<c:forEach begin="${page.number}" end="${page.number+4}" var="i">
				<c:if test="${i lt page.getTotalPages()}">
					<a href="#" class="custompager" title="${i}">${i+1}</a>
				</c:if>
			</c:forEach>

			<span><a href="#" class="custompager"
				title="${page.getNumber()+1}">&gt;</a></span> <span><a href="#"
				class="custompager" title="${page.getTotalPages()-1}">&gt;&gt;</a></span>

			<hr>

			<%-- 			<c:if test="${not page.isFirst()}">
				<a href="./${board}List?page=${page.getNumber()-1}">[이전페이지]</a>
			</c:if>
			<h1>${page.getNumber()+1}</h1>
			<c:if test="${not page.isLast()}">
				<a href="./${board}List?page=${page.getNumber()+1}">[다음페이지]</a>
			</c:if> --%>

		</div>



		<a href="./${board}Write" class="btn btn-danger">Write</a>

	</div>

	<%-- 	<div>
		<ul class="pagination" style="margin-left: 399px;">
			<c:if test="${pager.curBlock gt 1}">
				<li><a
					href="./${board}List?curPage=${pager.startNum-1}&kind=${pager.kind}&search=${pager.search}">이전</a></li>
			</c:if>
			<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
				<li><a
					href="./${board}List?curPage=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a></li>
			</c:forEach>
			<c:if test="${pager.curBlock lt pager.totalBlock}">
				<li><a
					href="./${board}List?curPage=${pager.lastNum+1}&kind=${pager.kind}&search=${pager.search}">다음</a></li>
			</c:if>
		</ul>
	</div> --%>

	<script type="text/javascript">
		$(".custompager").click(function() {
			var page = $(this).attr("title");
			$('#p').val(page);
			$('#frm').submit();
		});

		var kind = "${param.kind}";
		if (kind == '') {
			$('#title').prop("selected", true);
		} else {
			$("#" + kind).prop("selected", true);
		}

		var result = '${result}';
		if (result != '') {
			if (result == '1') {
				alert('성공');
			} else {
				alert('실패');
			}
		}
	</script>

</body>
</html>