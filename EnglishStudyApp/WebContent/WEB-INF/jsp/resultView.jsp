<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,model.entity.*"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/header.jsp"%>
</head>
<!-- 問題画面からリザルト画面に変わった時のみToastを表示 -->
<%if(request.getAttribute("InitialActionInResultView")!=null){%>
<body onload="$('#toast1').toast('show')">

<% } else {%>
<body>
<% }%>
	<%@include file="../common/navBar.jsp"%>
	<div class="container">
		<div class="jumbotron">
			<%
				List<Question> questionList = (List) session.getAttribute("questionList");
				Examinees examinee = (Examinees) session.getAttribute("loginExaminee");
			%>

			<h4><%=examinee.getMsg()%></h4>
			<%if(request.getAttribute("InitialActionInResultView")!=null){%>
		<div id="toast1" class="toast show" data-delay="3000" role="alert" aria-live="assertive" aria-atomic="true">
		<div class="toast-body">Your score has been updated.</div></div>
		<% }%>
 			<form action="/EnglishStudyApp/Result" method="post">
				<div class="container">
					<div class="d-flex">
						<input class="form-control mr-1" type="text" name="comment" class="form-control" placeholder=<%=request.getAttribute("placeholderFoTweet")%>>
						<input type="submit" class="btn btn-primary" name="actionInResultViewTweet" value="tweet">
					</div>
				</div>
			</form>
			<div style="overflow-y: auto;">
				<table
					class="table table-striped table-bordered table-hover table-condensed">
					<thead>
						<tr>
							<th scope="col">番号</th>
							<th scope="col">回答</th>
							<th scope="col">正解</th>
							<th scope="col">判定</th>
							<th scope="col">解説</th>
						</tr>
					</thead>
					<tbody>

						<%
							for (Question answers : questionList) {
								/* if ("〇".equals(answers.getJudge())) { */
								if (answers.getConserns() > 0) {
						%>
						<tr class="table table-success">
							<%} else {	%>

						<tr class="table table-dark">
							<%	}  %>

							<td><%=answers.getQuestionNumber()%></td>
							<td><%=answers.getChoosenAnswer()%></td>
							<td><%=answers.getAnswer()%></td>
							<td><%=answers.getJudge()%></td>
							<td>
								<form action="/EnglishStudyApp/Result" method="get">
									<button class="btn btn-primary type="submit" name="actionInResultView"
										value=<%=answers.getQuestionNumber()%>>解説</button>
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