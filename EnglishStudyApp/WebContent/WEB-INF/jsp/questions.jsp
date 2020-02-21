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
			int currentQuestionNum = (Integer) (session.getAttribute("questionNum"));
			int maxQuestionNum = (Integer) (session.getAttribute("TotalQuestionNum"));
		%>

		<h1>Question<%=currentQuestionNum + 1%></h1>
		<p><%=question.getQuestion()%></p>
		<p><input type="radio" name="radiobutton" value="A" checked>
			<%=question.getChoiceA()%></p>
		<p><input type="radio" name="radiobutton" value="B">
			<%=question.getChoiceB()%></p>
		<p><input type="radio" name="radiobutton" value="C">
			<%=question.getChoiceC()%></p>
		<p><input type="radio" name="radiobutton" value="D">
			<%=question.getChoiceD()%></p>
		<%if (currentQuestionNum > 0) {%>
			<button type="submit" name="actionInQuestion" value="前の問題">前の問題</button>
			<%} else {%>
			<button type="submit" name="actionInQuestion" value="前の問題" disabled>前の問題</button>
			<%}if (currentQuestionNum < maxQuestionNum - 1) {%>
			<button type="submit" name="actionInQuestion" value="次の問題">次の問題</button>
			<%} else {%>
			<button type="submit" name="actionInQuestion" value="次の問題" disabled>次の問題</button>
			<%}%>


			<%if ("checkAnswer".equals(session.getAttribute("status"))) {%>
			<button type="submit" name="actionInQuestion" value="結果表示">結果表示</button>
			<%}	%>

			<!-- statusが回答確認モードの場合 -->
			<%if ("checkAnswer".equals(session.getAttribute("status"))) {%>

		<p>	回答：<%=question.getAnswer()%></p>
		<p>	解説：<%=question.getCommentary()%></p>
		<%} else if (currentQuestionNum >= maxQuestionNum - 1) {%>
		<p><button type="submit" name="actionInQuestion" value="結果表示">結果表示</button></p>
		<%}%>
		<p><button type="submit" name="actionInQuestion" value="メイン">メイン画面に戻る</button></p>
	</form>
</body>
</html>