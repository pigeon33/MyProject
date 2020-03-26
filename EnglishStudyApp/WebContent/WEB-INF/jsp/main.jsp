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
	<%@include file="../common/navBar.jsp"%>
	<div class="container">
		<div class="jumbotron">
			<h4>Wellcome <%=loginExaminee.getName()%>!</h4>

			<form action="/EnglishStudyApp/Questions" method="get">
				<p>
				<div style="display: flex;">
					<select class="form-control input-lg" name="TotalQuestionNum">
						<option value="5">5 questions</option>
						<option value="10">10 questions</option>
						<option value="15">15 questions</option>
					</select>
					<button class="btn btn-primary btn-lg btn-block" type="submit"
						name="action" value="問題を解く">WORK OUT!
						<i class="fas fa-dumbbell"></i>
						</button>
				</div>
				</p>
			</form>

			<form action="/EnglishStudyApp/Ranking" method="get">
				<p>
					<button class="btn btn-success btn-lg btn-block" type="submit"
						name="action" value="履歴を見る">SHOW MY HISTORY
						<i class="fas fa-list-ol"></i>
					</button>
				</p>
				<p>
					<button class="btn btn-info btn-lg btn-block" type="submit"
						name="action" value="ランキングを見る">SHOW ME RANKING
						<i class="fas fa-crown"></i>
						</button>
				</p>
			</form>

			<form action="/EnglishStudyApp/Main" method="get">
				<p>
					<button class="btn btn-warning btn-lg btn-block" type="submit"
						name="action" value="Theme1">CHANGE THEME
						<i class="fas fa-person-booth"></i>
						</button><p>
			</form>

			<%@include file="../common/footer.jsp"%>
		</div>
	</div>
</body>
</html>