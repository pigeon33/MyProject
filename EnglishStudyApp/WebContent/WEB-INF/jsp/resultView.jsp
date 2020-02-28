<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,model.entity.*"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="../common/header.jsp"%>
</head>
<body>
	<div class="container">
		<div class="jumbotron">
	<a href="/EnglishStudyApp/Main">メイン画面に戻る</a>
	<%
		List<Question> questionList = (List) session.getAttribute("questionList");
		Examinees examinee = (Examinees) session.getAttribute("loginExaminee");
	%>
	<h4><%=examinee.getName()%>さんのスコアは<%=examinee.getScore() %>点です</h4>

	<table class="table table-hover">
		<thead>
		<tr>
			<th scope="col">問題番号</th>
			<th scope="col">回答</th>
			<th scope="col">正解</th>
			<th scope="col">合否判定</th>
		</tr>
		</thead>
		<%
			for (Question answers : questionList) {
		%>
	<tbody>
			<tr class="table table-hover">
			<td><%=answers.getQuestionNumber()%></td>
			<td><%=answers.getChoosenAnswer()%></td>
			<td><%=answers.getAnswer()%></td>
			<td><%=answers.getJudge()%></td>
			<td>
				<form action="/EnglishStudyApp/Result" method="get">
					<button class="btn btn-primary type="submit" name="actionInResultView"
						value=<%=answers.getQuestionNumber()%>>解説を見る</button>
				</form>
			</td>
			</tr>
		</tbody>
		<%
			}
		%>
	</table>
		<%@include file="../common/footer.jsp"%>
				</div>
	</div>
</body>
</html>