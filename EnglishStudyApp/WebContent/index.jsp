<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="../WEB-INF/common/header.jsp"%>

</head>
<body>
<div class="container">
	<div class="jumbotron">
		<h4>ちょっＴＯＥＩＣ</h4>
		<form action="/EnglishStudyApp/Login" method="post">
			<div class="form-group">
				<div class="mb-3">
				<input type="text" name="name" class="form-control"
					placeholder="UserName">
				</div>
				<input type="password" name="pass"
					class="form-control" placeholder="Password">
			</div>

			<div class="center-block">
			<input type="submit" class="btn btn-primary" name="action"
				value="ログイン">
			<input type="submit" class="btn btn-primary"
				name="action" value="新規登録">

			<%if (request.getAttribute("result") != null) {	%>
			<p><%=request.getAttribute("result")%></p>
			<%} else {%>
			<p>初めての場合は、ユーザ名とパスワードを入力して新規登録ボタンを押してください。</p>
			<%}%>
			</div>
		</form>
	</div>
	<%@include file="../WEB-INF/common/footer.jsp"%>
	</div>
</body>
</html>