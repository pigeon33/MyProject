<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.Examinees"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href = "/EnglishStudyApp/Main">メイン画面に戻る</a>
	<% List<Examinees> examineeScoreList = (List)request.getAttribute("examineeScoreList");%>

<h1>Ranking</h1>
	<table border="1">
		<tr>
			<th>Number</th>
			<th>Name</th>
			<th>Score</th>
			<th>Recorded Time</th>
		</tr>
		<%
			for (Examinees examineeScore : examineeScoreList) {
		%>
		<tr>
			<td><%=examineeScore.getRankingNumber()%></td>
			<td><%=examineeScore.getName()%></td>
			<td><%=examineeScore.getScore()%></td>
			<td><%=examineeScore.getTimestamp()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>