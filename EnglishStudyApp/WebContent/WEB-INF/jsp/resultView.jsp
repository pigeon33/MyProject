<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.Examinees"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ranking</title>
</head>
<body>
<a href = "/EnglishStudyApp/Main">メイン画面に戻る</a>
	<%
		List<Examinees> examineeList = (List)request.getAttribute("examineeList");
	%>
<h1 style="text-align:center">Ranking</h1>
	<table border="5" rules="all">
		<tr>
			<th>Number</th>
			<th>Name</th>
			<th>Score</th>
			<th>Recorded Time</th>
		</tr>
		<%
			for (Examinees examinees : examineeList) {
		%>
		<tr>
			<td><%=examinees.getRankingNumber()%></td>
			<td><%=examinees.getName()%></td>
			<td><%=examinees.getScore()%></td>
			<td><%=examinees.getTimestamp()%></td>
		</tr>
		<%
			}
		%>
	</table>
	</body>
</html>