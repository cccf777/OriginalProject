<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Write</title>
<!-- root route -->
<c:set var="root" value="${pageContext.request.contextPath}" />
<!-- ck editor -->
<script type="text/javascript" src="${root}/js/ckeditor/ckeditor.js"></script>

</head>
<body>
<!-- header  -->
<jsp:include page="/WEB-INF/board/etc/header.jsp"></jsp:include>
<!-- sidebar  -->
<jsp:include page="/WEB-INF/board/etc/sidebar.jsp"></jsp:include>
	
	<div class="container">
		<div class="row">
			<div class="col-2"></div>
			<div class="col-9">
			<form action="regedit" method="post">
				<div class="col-10">
					<label for="title" class = "form=label">제목</label>
					<input type="text" name="title" id="title" placeholder="제목">
				</div>
				<div class="col-10">	
					<label for="content" class = "form=label">내용</label>
					<textarea class="form-control" name = "content"></textarea>
				</div>
			<div class="col-5"></div>
				<button class="btn btn-primary" type="submit">저장</button>
			</form>
		</div>
		</div>
	</div>
<script type="text/javascript" defer>CKEDITOR.replace('content',{height:500})</script>
</body>
</html>