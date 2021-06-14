<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% String userRank=request.getSession().getAttribute("userRank").toString(); %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <script>
function checkOnlyOne(element) {
	  
	  const checkboxes 
	      = document.getElementsByName("mrank");
	  
	  checkboxes.forEach((cb) => {
	    cb.checked = false;
	  })
	  
	  element.checked = true;
	}

</script> -->


</head>
<body>
	<%-- <%
switch(userRank){
case "G":
	%>게스트임<%
	break;
}%> --%>

	<form action="" method="post">
		<table border="1">
			<tr>
				<td>이 유저를 어느등급으로 철퇴를? <br /> <!-- <input type='checkbox'
       name='mrank' 
       id='guest'
       onclick='checkOnlyOne(this)'/> 게스트
		<br />
	<input type='checkbox' 
       name='mrank' 
       id='admin' 
       onclick='checkOnlyOne(this)'/> 어드민
		<br />
	<input type='checkbox' 
       name='mrank' 
       id='user' 
       onclick='checkOnlyOne(this)'/> 유저
       </td> --> 
       <input type="checkbox" name="mrank" value="G">게스트
       <input type="checkbox" name="mrank" value="U">유저
       <input type="checkbox" name="mrank" value="A">어드민
       </td>
			</tr>
			<tr>
				<td><input type="submit" value="철퇴내리기"></td>
			</tr>

		</table>
	</form>
</body>
</html>