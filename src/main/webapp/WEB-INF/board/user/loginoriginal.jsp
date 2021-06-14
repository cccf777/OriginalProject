<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" href="./css/bootstrap.min.css" />
<script src="./js/bootstrap.bundle.js">


</script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.83.1">
    <title>Signin Template · Bootstrap v5.0</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sign-in/">

    

    <!-- Bootstrap core CSS -->
<link href="/docs/5.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

    <!-- Favicons -->
<link rel="apple-touch-icon" href="/docs/5.0/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
<link rel="icon" href="/docs/5.0/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
<link rel="icon" href="/docs/5.0/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
<link rel="manifest" href="/docs/5.0/assets/img/favicons/manifest.json">
<link rel="mask-icon" href="/docs/5.0/assets/img/favicons/safari-pinned-tab.svg" color="#7952b3">
<link rel="icon" href="/docs/5.0/assets/img/favicons/favicon.ico">
<meta name="theme-color" content="#7952b3">


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>

    
    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">
  </head>

</head>


<body class="text-center">

	<main class="form-signin">
		<form action="" method="post">
			<img class="mb-4" src="/docs/5.0/assets/brand/bootstrap-logo.svg"
				alt="" width="72" height="57">
			<h1 class="h3 mb-3 fw-normal">Please sign in</h1>

			<div class="form-floating">
				<input placeholder="ID" type="text" name="id" class="form-control"
				id="floatingInput" value="<c:out value='${id}'/>"> <label for="floatingInput">ID</label>
			</div>
			<div class="form-floating">
				<input placeholder="PASSWORD" type="password" name="pass" class="form-control"
					id="floatingPassword" > <label
					for="floatingPassword">Password</label>
			</div>

			<div class="checkbox mb-3">
				
			</div>
			<input type="submit" value="로그인">
			<input type="button" value="회원가입" onclick="location.href='/user/signup'">
			<input type="checkbox" name = "remember" value="Y" <c:if test='${id !=null && id!=""}'>checked</c:if>>Remember me
		</form>
	</main>
</html>