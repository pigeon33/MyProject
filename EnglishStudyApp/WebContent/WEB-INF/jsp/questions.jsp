<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,model.entity.Question"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/header.jsp"%>
</head>
<body>
	<%@include file="../common/navBar.jsp"%>
	<div class="container">
		<div class="jumbotron">
			<form action="/EnglishStudyApp/Questions" method="get">
				<%
					Question question = (Question) request.getAttribute("question");
					int concern = question.getConserns();
					int currentQuestionNum = (Integer) (session.getAttribute("questionNum"));
					int maxQuestionNum = (Integer) (session.getAttribute("TotalQuestionNum"));
					String str[] = { "A", "B", "C", "D" };
					String choices[] = question.getChoices();
				%>
				<h4>Question<%=currentQuestionNum + 1%></h4>
				<p class="lead"><%=question.getQuestion()%></p>

				<%for (int i = 0; i < 4; i++) {	%>
				<div class="form-check">
					<label class="form-check-label">
						<p>
						<!-- statusが回答確認モードの場合はradiobuttonはdisble -->
						<%if ("checkAnswer".equals(session.getAttribute("status"))) {%>
							<input type="radio" class="form-check-input" name="radiobutton"
									value=<%=str[i]%> disabled="">
						<% }else{%>
							<input type="radio" class="form-check-input" name="radiobutton"
								value=<%=str[i]%> checked>
						<% }%>
							<%=choices[i]%>
						</p>
					</label>
				</div>
				<%} %>

        		<div class="custom-control custom-switch">
        		<%if( concern > 0){ %>
          			<input type="checkbox" name = "checkbox" value = 1 class="custom-control-input" id="customSwitch1" checked="">
          		<%  } else {%>
          			<input type="checkbox" name = "checkbox" value = 1 class="custom-control-input" id="customSwitch1">
          		<% } %>
          		<label class="custom-control-label" for="customSwitch1">要チェック</label>
        		</div>
				<p></p>
				<% if (currentQuestionNum > 0) {%>
				<button  class="btn btn-primary" type="submit" name="actionInQuestion" value="前の問題">Previous Question</button>
				<%} else {%>
				<button  class="btn btn-primary" type="submit" name="actionInQuestion" value="前の問題" disabled>Previous Question</button>
				<%}if (currentQuestionNum < maxQuestionNum - 1) {%>
				<button  class="btn btn-primary" type="submit" name="actionInQuestion" value="次の問題">Next Question</button>
				<%} else {%>
				<button  class="btn btn-primary" type="submit" name="actionInQuestion" value="次の問題" disabled>Next Question</button>
				<% } %>
				<p></p>
				<!-- statusが回答確認モードの場合 -->
				<%if ("checkAnswer".equals(session.getAttribute("status"))) {%>
				<p>	選んだ回答:<%=question.getChoosenAnswer()%></p>
				<p>	正解：<%=question.getAnswer()%></p>
				<p>	判定：<%=question.getJudge()%></p>
				<p>	解説：<%=question.getCommentary()%></p>
				<p><button  class="btn btn-info" type="submit" name="actionInQuestion" value="結果表示">Go Back to Result</button></p>
				<%} else if (currentQuestionNum >= maxQuestionNum - 1) {%>
				<p><button  class="btn btn-info" type="submit" name="actionInQuestion" value="結果表示">Check Result</button></p>
				<%}%>
			</form>
			<%@include file="../common/footer.jsp"%>
		</div>
	</div>
</body>
</html>