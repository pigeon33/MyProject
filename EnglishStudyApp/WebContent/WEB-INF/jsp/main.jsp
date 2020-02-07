<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page import="java.util.List,model.entity.Examinees" %>
    <%
    //セッションスコープに保存されたユーザ情報を取得
    Examinees loginExaminees = (Examinees) session.getAttribute("loginExaminees");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>English Study</title>
</head>
<body>
<h1>Main Page</h1>
	<p>ようこそ<%=loginExaminees.getName() %>さん</p>
	<a href = "/EnglishStudyApp/Questions">問題を解く</a>
</body>
</html>