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
		<%if ("allExamineesScore".equals(request.getAttribute("actionInRanking"))) {%>
		<!-- ランキング表示モード -->
		<div style="overflow-y:auto;">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th scope="col">順位</th>
					<th scope="col">名前</th>
					<th scope="col">点数</th>
					<th scope="col">記録時間</th>
				</tr>
			</thead>
			<tbody>
				<%for (Examinees examineeScore : examineeScoreList) {%>
				<tr>
					<td rowspan="2"><%=examineeScore.getRankingNumber()%></td>
					<td><%=examineeScore.getName()%></td>
					<td><%=examineeScore.getScore()%></td>
					<td><%=examineeScore.getTimestampStr()%></td>
				</tr>
				<tr>
					<td colspan="3"><%=examineeScore.getTweet()%></td>
				</tr>
				<%}%>
			</tbody>
		</table>
		</div>
		<%
			} else {
		%>
		<!-- 個人の履歴表示モード -->
		<%-- <h1><%=loginExaminee.getName()%>'s History</h1> --%>
		<div style="overflow-y:auto;">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th scope="col">記録時間</th>
					<th scope="col">点数</th>
					<th scope="col">Tweet</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Examinees examinee : examineeScoreList) {
				%>

				<tr class="table table-hover">
					<td><%=examinee.getTimestampStr()%></td>
					<td><%=examinee.getScore()%></td>
					<td><%=examinee.getTweet()%></td>
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