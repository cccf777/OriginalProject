<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form action = "calc2" method="post">
        <div>
            <label>w  :</label>
            <input type="text" name="num">
        </div>
        <div>
            <input type= "submit" name="operator" value = "Plus">

        </div>
        <div>
            <input type= "submit" name="operator" value = "Minus">

        </div>
        <div>
            <input type= "submit" name="operator" value = "Multiple">

        </div>
        
    </form>
    <div>
        결과는 :${result}
        
    </div>
</body>
</html>