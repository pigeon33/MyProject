<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,model.entity.Question"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/header.jsp"%>
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<form action="/EnglishStudyApp/Questions" method="get">
				<%
					Question question = (Question) request.getAttribute("question");
					int currentQuestionNum = (Integer) (session.getAttribute("questionNum"));
					int maxQuestionNum = (Integer) (session.getAttribute("TotalQuestionNum"));
					String str[] = { "A", "B", "C", "D" };
				%>

				<h4>
					Question<%=currentQuestionNum + 1%></h4>

				<p class="lead"><%=question.getQuestion()%></p>

				<%
					for (int i = 0; i < 4; i++) {
				%>
				<div class="form-check">
					<label class="form-check-label">
						<p>
							<input type="radio" class="form-check-input" name="radiobutton"
								value=<%=str[i]%> checked>
							<%=question.getChoices(i)%></p>
					</label>
				</div>
				<%
					}
				%>

				<%
				if (currentQuestionNum > 0) {
				%>
				<button  class="btn btn-primary" type="submit" name="actionInQuestion" value="前の問題">前の問題</button>
				<%
					} else {
				%>
				<button  class="btn btn-primary" type="submit" name="actionInQuestion" value="前の問題" disabled>前の問題</button>
				<%
					}
					if (currentQuestionNum < maxQuestionNum - 1) {
				%>
				<button  class="btn btn-primary" type="submit" name="actionInQuestion" value="次の問題">次の問題</button>
				<%
					} else {
				%>
				<button  class="btn btn-primary" type="submit" name="actionInQuestion" value="次の問題" disabled>次の問題</button>
				<%
					}
				%>
				<p></p>

				<!-- statusが回答確認モードの場合 -->
				<%
					if ("checkAnswer".equals(session.getAttribute("status"))) {
				%>
				<p>
					選んだ回答:<%=question.getChoosenAnswer()%></p>
				<p>
					正解：<%=question.getAnswer()%></p>
				<p>
					判定：<%=question.getJudge()%></p>
				<p>
					解説：<%=question.getCommentary()%></p>

				<p>
					<button  class="btn btn-info" type="submit" name="actionInQuestion" value="結果表示">結果表示に戻る</button>
				</p>
				<%
					} else if (currentQuestionNum >= maxQuestionNum - 1) {
				%>
				<p>
					<button  class="btn btn-info" type="submit" name="actionInQuestion" value="結果表示">結果表示</button>
				</p>
				<%
					}
				%>
				<p>
					<button  class="btn btn-success" type="submit" name="actionInQuestion" value="メイン">メイン画面に戻る</button>
				</p>
			</form>
			<%@include file="../common/footer.jsp"%>
		</div>
	</div>
</body>
</html>