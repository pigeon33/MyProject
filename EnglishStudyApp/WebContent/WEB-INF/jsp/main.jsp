<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List,model.entity.Examinees"%>
<%
	//セッションスコープに保存されたユーザ情報を取得
	Examinees loginExaminee = (Examinees) session.getAttribute("loginExaminee");
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/header.jsp"%>
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<h4>ようこそ<%=loginExaminee.getName()%>さん</h4>

			<form action="/EnglishStudyApp/Questions" method="get">

			<p>
			<select class="form-control" name="TotalQuestionNum">
				<option value="5">５問</option>
				<option value="10">１０問</option>
				<option value="15">１５問</option>
			</select>
				<button class="btn btn-primary btn-lg btn-block" type="submit" name="action" value="問題を解く">問題を解く</button>
			</form>
			</p>

			<form action="/EnglishStudyApp/Ranking" method="get">
				<p>
				<button class="btn btn-success btn-lg btn-block" type="submit" name="action" value="履歴を見る"><%=loginExaminee.getName()%>さんの履歴を見る</button>
				</p>
				<p>
				<button class="btn btn-info btn-lg btn-block" type="submit" name="action" value="ランキングを見る">ランキングを見る</button>
				</p>
			</form>

	<form action="/EnglishStudyApp/Logout" method="get">
		<p><button class="btn btn-warning btn-lg btn-block" type="submit" name="action">ログアウトする</button><p>
	</form>
		<%@include file="../common/footer.jsp"%>
		</div>
	</div>
</body>
</html>