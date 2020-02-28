<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,model.entity.Examinees"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/header.jsp"%>
</head>
<body>
	<a href="/EnglishStudyApp/Main">メイン画面に戻る</a>
	<%
		//セッションスコープに保存されたユーザ情報を取得
		Examinees loginExaminee = (Examinees) session.getAttribute("loginExaminee");

		List<Examinees> examineeScoreList = (List) request.getAttribute("examineeScoreList");
	%>
	<%
		if ("allExamineesScore".equals(request.getAttribute("actionInRanking"))) {
	%>
	<h1>Ranking</h1>
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">Number</th>
				<th scope="col">Name</th>
				<th scope="col">Score</th>
				<th scope="col">Recorded Time</th>
			</tr>
		</thead>
		<%
			for (Examinees examineeScore : examineeScoreList) {
		%>
		<tbody>
			<tr class="table table-hover">
				<td><%=examineeScore.getRankingNumber()%></td>
				<td><%=examineeScore.getName()%></td>
				<td><%=examineeScore.getScore()%></td>
				<td><%=examineeScore.getTimestamp()%></td>
			</tr>
		</tbody>
		<%
			}
		%>
	</table>
	<%
		} else {
	%>
	<h1><%=loginExaminee.getName()%>さんのHistory</h1>
	<table class="table table-hover">
		<thead>
		<tr>
			<th scope="col">Recorded Time</th>
			<th scope="col">Score</th>

		</tr>
				</thead>
		<%
			for (Examinees examinee : examineeScoreList) {
		%>
				<tbody>
		<tr class="table table-hover">
			<td><%=examinee.getTimestamp()%></td>
			<td><%=examinee.getScore()%></td>
		</tr>
				</tbody>
		<%
			}
		%>
	</table>
	<%
		}
	%>
	<%@include file="../common/footer.jsp"%>
</body>
</html>