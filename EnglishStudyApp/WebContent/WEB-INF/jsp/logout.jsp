<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/header.jsp"%>
</head>
<body>
 	<div class="container">
		<div class="jumbotron">
			<h5>you successfully logged out</h5>
			<input type="button" class="btn btn-primary"
				onclick="location.href='/EnglishStudyApp/'" value="Go Back to Login">
			<%@include file="../common/footer.jsp"%>
		</div>
	</div>
</body>
</html>