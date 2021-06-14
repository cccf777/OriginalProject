<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<% String userRank=request.getSession().getAttribute("userRank").toString(); %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>글쓴이</th>
			<td>${nt.writeid}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${nt.title}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${nt.hit}</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${nt.regdate}</td>
		</tr>
		<tr>
			<th colspan=2>내용</th>
		</tr>
		<tr>
			<td colspan=2>
			${nt.content}
			</td>
		</tr>
		<tr>
			<th>유저등급</th>
			<td><%=userRank %></td>
		</tr>
	</table>
	<div>
		<input type= "button" onclick="location.href='/board/content/list?p=${p}&q=${q}&f=${f }'" value="목록으로">
		<c:if test="${sessionScope.userID==nt.writeid}">
			<input type= "button" onclick="location.href='/board/content/modify?id=${nt.id}&q=${q}&f=${f}'" value="수정하기">
			<button class="btn btn-primary" type="button" onclick="location.href='../user/Ugrade'">철퇴의시간</button>
		</c:if>
		<c:if test="${sid!=nt.writeid}">
			<c:if test="${sessionScope.userRank=='A'}">
			<input type= "button" onclick="location.href='/board/content/modify?id=${nt.id}&q=${q}&f=${f}'" value="수정하기">
			<button class="btn btn-primary" type="button" onclick="location.href='../user/Ugrade'">철퇴의시간</button>
			</c:if>
			<c:if test="${sessionScope.userRank!='A'}">
			</c:if>
		</c:if>
	</div>
</body>
</html>