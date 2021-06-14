<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Content list</title>
	<!-- root Route  -->
	<c:set var="root" value="${pageContext.request.contextPath}" />
 	<!-- Bootstrap core CSS -->
	<link href="${root}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
   	<!-- custom styles for this template -->
    <link href="${root}/css/board/dashboard.css" rel="stylesheet">
     <!-- Bootstrap js -->
    <script src="${root}/js/bootstrap/bootstrap.bundle.min.js" defer></script>
    <!-- icon js -->
    <script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous" defer></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js" integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha" crossorigin="anonymous" defer></script>
    <!-- Dashboard js -->
    <script src="${root}/js/board/dashboard.js" defer></script>

<c:set var="root" value="${pageContext.request.contextPath}" />
</head>
<body>
	<header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
  	 <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3" href="#">Company name</a>
  	<button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
    	<span class="navbar-toggler-icon"></span>
  	</button>
  	<input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
  	<ul class="navbar-nav px-3">
    	<li class="nav-item text-nowrap">
    	<a class="nav-link" href="/user/logout">Sign out</a>
    	</li>
  	</ul>
</header>


<div class="container-fluid">
  <div class="row">
    <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
      <div class="position-sticky pt-3">
        <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">
              <span data-feather="home"></span>
              Dashboard
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="file"></span>
              Orders
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="shopping-cart"></span>
              Products
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="users"></span>
              Customers
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="bar-chart-2"></span>
              Reports
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="layers"></span>
              Integrations
            </a>
          </li>
        </ul>

        <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
          <span>Saved reports</span>
          <a class="link-secondary" href="#" aria-label="Add a new report">
            <span data-feather="plus-circle"></span>
          </a>
        </h6>
        <ul class="nav flex-column mb-2">
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="file-text"></span>
              Current month
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="file-text"></span>
              Last quarter
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="file-text"></span>
              Social engagement
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="file-text"></span>
              Year-end sale
            </a>
          </li>
        </ul>
      </div>
    </nav>

    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
      <h2>Section title</h2>
      <div class="table-responsive">
        <table class="table table-striped table-sm">
        <!-- grid title  -->
          <thead>
            <tr>
              <th>ID</th>
              <th>TITLE</th>
              <th>NAME</th>
              <th>DATE</th>
              <th>HIT</th>
            </tr>
          </thead>
          <!-- Data grid Start  -->
          <c:forEach var="li" items="${list}">
			<tr>
				<td>${li.id}</td>
				<td><a href="detail?id=${li.id}&q=${param.q}&f=${param.f}">${li.title}</a></td>
				<td>${li.writeid}</td>
				<td><fmt:formatDate pattern="yyyy년 MM월 dd일 hh시 mm분" value="${li.regdate}" /></td>
				<td>${li.hit}</td>
			</tr>
			</c:forEach>
        </table>
        </div>
        <div class="container">
        	<div class = "col-12">
        		<div class="col-11"></div></div>
        		<div class="col-1"></div>
        		
       
	<%-- ${count}건이 조회되었습니다. --%>
	<!-- 변수선언 -->
	<c:set var="page" value="${empty param.p?1:param.p}"></c:set>
	<c:set var="startNum" value="${page-(page-1)%5}"></c:set>
	<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10),'.')}"></c:set>
						 
	<!-- 현재 페이지 -->
	<div>
		<div>
			<span> ${page} </span> / ${lastNum} pages
		</div>
	</div>
				
	<!-- 페이징처리 시작 -->
					
	<nav aria-label="Page navigation example">
	  	<ul class="pagination">
	
	<!-- 이전 페이지 -->
					
			<li class="page-item">
				<c:if test="${startNum > 1 }">
				<a class="page-link" href="?p=${startNum-1}&f=${param.f}&q=${param.q}">Prev</a>
				</c:if>
				<c:if test="${startNum <= 1 }">
				<a class="page-link" href="#" onclick="alert('첫 페이지입니다.');">Prev</a>
				</c:if>
			</li>
				
	<!-- 숫자 페이지 -->
	<c:forEach var="i" begin="0" end="4">
		<li class="page-item">
	<c:if test="${param.p==(startNum+i)}">
		<c:set var="style" value="font-weight:bold; color:red;" />
	</c:if>
	<c:if test="${param.p!=(startNum+i)}">
		<c:set var="style" value="" />
	</c:if>
	<c:if test="${(startNum+i) <=lastNum }">
	<a style="${style}" class="page-link" href="?p=${startNum+i}&f=${param.f}&q=${param.q}">${startNum+i}</a>
	</c:if>
	</li>
	</c:forEach>
	<!-- 다음 페이지 -->
					
	<li class="page-item">
		<c:if test="${startNum+5 <= lastNum }">
		<a class="page-link" href="?p=${startNum+5}&f=${param.f}&q=${param.q}">Next</a>
		</c:if>
		<c:if test="${startNum+5 >lastNum }">
		<a class="page-link" href="#" onclick="alert('마지막 페이지입니다.');">Next</a>
		</c:if>
	</li>
					
	<!-- ----------------- -->
	</ul>
	
	</nav>
	
	 

	<!-- 검색 -->

	<form action="" method="get">
		<div>
			<select name="f">
				<option ${(param.f=="title")?"selected":""} value="title">제목</option>
				<option ${(param.f=="writeid")?"selected":""} value="writeid">글쓴이</option>

			</select> <input type="text" name="q" /> <span><input type="submit"
				value="검색"></span>
		</div>
	</form>
	<!-- 글쓰기  -->
	<div>
		<button class="btn btn-primary btn-success" type="button" onclick="location.href='regedit'" >글쓰기</button>
	</div>
      </div>
    </main>
  </div>
</div>	
</body>
</html>