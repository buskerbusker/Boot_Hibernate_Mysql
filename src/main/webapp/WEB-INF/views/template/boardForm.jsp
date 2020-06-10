<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form action="${board}${path}" method="post"
	enctype="multipart/form-data">
	<input type="hidden" name="num" id="num" value="${vo.num}">
	<div class="form-group">
		<label for="title">Title:</label> <input type="text"
			class="form-control" id="title" name="title" value="${vo.title}">
	</div>
	<div class="form-group">
		<label for="writer">Writer:</label> <input type="text"
			class="form-control" id="writer" name="writer" value="${vo.writer}">
	</div>
	<div class="form-group">
		<label for="contents">Contents:</label>
		<textarea class="form-control" id="contents" name="contents">${vo.contents}</textarea>
	</div>

	<input type="button" class="btn btn-info" id="add" value="FileAdd">
	<div class="form-group" id="f"></div>

	<button type="submit" class="btn btn-default">Submit</button>
</form>


<script type="text/javascript">
	$("#contents")
			.summernote(
					{
						height : 300,
						toolbar : [
								// [groupName, [list of button]]
								[
										'style',
										[ 'bold', 'italic', 'underline',
												'clear' ] ],
								[
										'font',
										[ 'strikethrough', 'superscript',
												'subscript' ] ],
								[ 'fontsize', [ 'fontsize' ] ],
								[ 'color', [ 'color' ] ],
								[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
								[ 'height', [ 'height' ] ] ]

					});
</script>