<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.Answer"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ranking</title>
</head>
<body>
<a href = "/EnglishStudyApp/Main">メイン画面に戻る</a>
 	<%
		List<Answer> answerList = (List)session.getAttribute("answerList");
	%>
<h1>Resultview</h1>
	<table border="5" rules="all">
		<tr>
			<th>問題番号</th>
			<th>回答</th>
			<th>正解</th>
			<th>合否判定</th>
		</tr>
 		<%
			for (Answer answers : answerList) {
		%>
		<tr>
			<td><%=answers.getNum()%></td>
			<td><%=answers.getAns()%></td>
			<td><%=answers.getCollectAns()%></td>
			<td><%=answers.getJudge()%></td>
			<td>
				<input type="submit" name="actionInQuestion" value=<%=answers.getNum()%>>はい
			</td>
		</tr>
		<%
			}
		%>
	</table>
	</body>
</html>