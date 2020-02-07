<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Toeic Study App</title>
</head>
<body>
<h1>Welcome</h1>
<form action="/EnglishStudyApp/Login" method = "post">
ユーザ名:<input type = "text" name = "name"><br>
パスワード:<input type = "password" name = "pass"><br>
<input type="submit" name="action" value="ログイン">
<input type="submit" name="action" value="新規登録">
<%if(request.getAttribute("result")!=null){%>
	<p><%=request.getAttribute("result")%></p>
	<%}%>
</form>
</body>
</html>