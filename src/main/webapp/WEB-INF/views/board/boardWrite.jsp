<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/boot.jsp"></c:import>

<style type="text/css">
.r {
	color: red;
}
</style>

<!-- include summernote css/js -->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>


</head>
<body>
	<c:import url="../template/nav.jsp"></c:import>
	<div class="container">

		<h2>${board}${path}</h2>

		<c:import url="../template/springForm.jsp"></c:import>

	</div>

	<script type="text/javascript">
		var board = '${path}';
		if (board == 'Write') {
			$("#num").remove();
		}

		$("#add").click(function() {
			$("#f").append('<input type="file" name="files">');
		})
	</script>

</body>
</html>