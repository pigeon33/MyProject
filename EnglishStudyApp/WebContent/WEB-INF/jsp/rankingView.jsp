<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,model.entity.Examinees"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/header.jsp"%>
</head>
<body>
	<%@include file="../common/navBar.jsp"%>

	<div class="container">
		<%
			//セッションスコープに保存されたユーザ情報を取得
			Examinees loginExaminee = (Examinees) session.getAttribute("loginExaminee");

			List<Examinees> examineeScoreList = (List) request.getAttribute("examineeScoreList");
		%>
		<%
			if ("allExamineesScore".equals(request.getAttribute("actionInRanking"))) {
		%>


		<h1>Ranking</h1>
		<div style="overflow-y:auto;">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th scope="col">Number</th>
					<th scope="col">Name</th>
					<th scope="col">Score</th>
					<th scope="col">Recorded Time</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Examinees examineeScore : examineeScoreList) {
				%>
				<tr>
					<td><%=examineeScore.getRankingNumber()%></td>
					<td><%=examineeScore.getName()%></td>
					<td><%=examineeScore.getScore()%></td>
					<td><%=examineeScore.getTimestampStr()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		</div>
		<%
			} else {
		%>
		<h1><%=loginExaminee.getName()%>'s History
		</h1>
		<div style="overflow-y:auto;">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th scope="col">Recorded Time</th>
					<th scope="col">Score</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Examinees examinee : examineeScoreList) {
				%>

				<tr class="table table-hover">
					<td><%=examinee.getTimestampStr()%></td>
					<td><%=examinee.getScore()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<%
			}
		%>

	</div>
	</div>
	<%@include file="../common/footer.jsp"%>
</body>
</html>