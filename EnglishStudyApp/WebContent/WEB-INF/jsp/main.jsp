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
	<p>ようこそ<%=loginExaminee.getName() %>さん</p>
		<form action="/EnglishStudyApp/Questions" method="get">
	<p>
		<select name="TotalQuestionNum">
			<option value="5">５問</option>
			<option value="10">１０問</option>
			<option value="15">１５問</option>
		</select>
		<!-- <input type="submit" name="action" value="問題を解く"> -->
		<button type="submit" name="action" value="問題を解く">問題を解く</button>
		</form>
	</p>
	<p>
		<form action="/EnglishStudyApp/Ranking" method="get">
		<button type="submit" name="action" value="履歴を見る">履歴を見る</button>
		<button type="submit" name="action" value="ランキングを見る">ランキングを見る</button>
		</form>
	</p>
	<!-- <p><a href = "/EnglishStudyApp">ログイン画面に戻る</a></p> -->
	<form action = "/EnglishStudyApp/Logout" method="get">
		<button type="submit" name="action" >ログアウトする</button>
	</form>
</body>
</html>