<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page import="java.util.List,model.entity.Examinees" %>
    <%
    //セッションスコープに保存されたユーザ情報を取得
    Examinees loginExaminee = (Examinees) session.getAttribute("loginExaminee");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>English Study</title>
</head>
<body>
<h1>Main Page</h1>
	<p>ようこそ<%=loginExaminee.getName() %>さん</p>
	<a href = "/EnglishStudyApp/Questions">問題を解く</a>
	<a href = "/EnglishStudyApp/resultView">ランキングを見る</a>
	<a href = "/EnglishStudyApp">ログイン画面に戻る</a>
</body>
</html>