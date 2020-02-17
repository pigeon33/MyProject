<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,model.entity.Question"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Questions</title>
</head>
<body>
	<form action="/EnglishStudyApp/Questions" method="post">
		<%
			Question question = (Question) request.getAttribute("question");
			int currentQuestionNum = (Integer)(session.getAttribute("questionNum"));
			int maxQuestionNum = (Integer)(session.getAttribute("TotalQuestionNum"));

			%>

		<h1>Question<%=currentQuestionNum+1%></h1>
		<p><%=question.getQuestion()%></p>
		<p>	<input type="radio" name="radiobutton" value="a">
			<%=question.getChoiceA()%></p>
		<p>	<input type="radio" name="radiobutton" value="b">
			<%=question.getChoiceB()%></p>
		<p>	<input type="radio" name="radiobutton" value="c">
			<%=question.getChoiceC()%></p>
		<p>	<input type="radio" name="radiobutton" value="d">
			<%=question.getChoiceD()%></p>
		<p>
		<%if(currentQuestionNum>0){%>
			<input type="submit" name="actionInQuestion" value="前の問題">
		<%} else {%>
			<input type="submit" name="actionInQuestion" value="前の問題" disabled>
		<% }%>

		<%
		/* if((Integer)(session.getAttribute("questionNum"))<=(Integer)(session.getAttribute("TotalQuestionNum"))) */
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
	</form>
</body>
</html>