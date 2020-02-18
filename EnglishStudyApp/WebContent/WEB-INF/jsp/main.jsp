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
		<form action="/EnglishStudyApp/Questions" method="get">
	<p>
		<select name="TotalQuestionNum">
			<option value="5">５問</option>
			<option value="10">１０問</option>
			<option value="15">１５問</option>
		</select>
		<input type="submit" name="action" value="問題を解く">
		</form>
	</p>
	<p>
		<form action="/EnglishStudyApp/Ranking" method="get">
		<input type="submit" name="action" value="ランキングを見る">
		</form>
	</p>
	<p><a href = "/EnglishStudyApp">ログイン画面に戻る</a></p>
</body>
</html>