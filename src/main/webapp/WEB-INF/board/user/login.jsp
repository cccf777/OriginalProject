<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"   content="Mr.Lee">
<meta name="date" content="2021-06-09">
<title>Please SignIN</title>
 <!-- root Route -->
<c:set var="root" value="${pageContext.request.contextPath}" />
 <!-- Bootstrap core CSS -->
<link href="${root}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
 <!-- Custom styles for this template -->
<link href="${root}/css/user/signin.css" rel="stylesheet">
</head>
  <body class="text-center"><!--  bg-dark  -->
	<div>${ment}</div>
	<main class="form-signin">
		<form action="" method="post">
			<img class="mb-4" src="/img/logo2.png" alt="" width="72" height="57">
    		<h1 class="h3 mb-3 fw-normal text-white">GEM방에 어서오쇼</h1>
			<div class="form-floating">
     	   		<input type="text" class="form-control" id="id" name="id" placeholder="ID" value="<c:out value='${id}'/>">
     			<label for="floatingInput">ID</label>
    		</div>
    		<div class="form-floating">
      			<input type="password" class="form-control" id="pass" name="pass" placeholder="Password">
      			<label for="floatingPassword">Password</label>
    		</div>
    		<div class="checkbox mb-3 text-black">
      			<label>
        			<input type="checkbox" value="Y" <c:if test='${id !=null && id!=""}'>checked</c:if>> Remember me
      			</label>
    		</div>
    		<button class="w-100 btn btn-lg btn-success" type="submit">로그인</button>
    		 <p class="mt-5 mb-3 text-muted">아직 GEM방의 회원이 아니신가요?</p>
    		<button class="w-50 btn btn-lg btn-warning" onClick="location.href='/user/signup'"  type="button">회원가입</button>
		</form>
	</main>
</body>
</html>