<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,model.entity.Question"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Questions</title>
</head>
<body>
	<form action="/EnglishStudyApp/Questions" method="get">
		<%
			Question question = (Question) request.getAttribute("question");
			int currentQuestionNum = (Integer)(session.getAttribute("questionNum"));
			int maxQuestionNum = (Integer)(session.getAttribute("TotalQuestionNum"));
		%>

		<h1>Question<%=currentQuestionNum+1%></h1>
		<p><%=question.getQuestion()%></p>
		<p>	<input type="radio" name="radiobutton" value="A" checked>
			<%=question.getChoiceA()%></p>
		<p>	<input type="radio" name="radiobutton" value="B">
			<%=question.getChoiceB()%></p>
		<p>	<input type="radio" name="radiobutton" value="C">
			<%=question.getChoiceC()%></p>
		<p>	<input type="radio" name="radiobutton" value="D">
			<%=question.getChoiceD()%></p>
		<p>
		<%if(currentQuestionNum>0){%>
			<input type="submit" name="actionInQuestion" value="前の問題">
		<%} else {%>
			<input type="submit" name="actionInQuestion" value="前の問題" disabled>
		<% }
		if(currentQuestionNum < maxQuestionNum-1)
		{%>
			<input type="submit" name="actionInQuestion" value="次の問題">
		<%} else {%>
			<input type="submit" name="actionInQuestion" value="次の問題" disabled>
		<% }%>

		<input type="submit" name="actionInQuestion" value="回答解説を見る">
		</p>
		<%if("回答解説を見る".equals(request.getParameter("actionInQuestion"))){%>
			<p>回答：<%=question.getAnswer()%></p>
			<p>解説：<%=question.getCommentary()%></p>
		<%}%>

		<% if(currentQuestionNum >= maxQuestionNum-1){%>
			<p><input type="submit" name="actionInQuestion" value="結果表示"></p>
		<%}%>
	</form>
</body>
</html>