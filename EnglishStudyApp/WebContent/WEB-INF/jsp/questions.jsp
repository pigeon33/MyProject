<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Questions</title>
</head>
<body>
<p>好きな果物ひとつを選んでください。</p>
<form action="/EnglishStudyApp/Questions" method="get">
    <input type="radio" name="radiobutton" value="apple"> りんご
    <input type="radio" name="radiobutton" value="peach"> もも
    <input type="radio" name="radiobutton" value="banana"> ばなな
    <!-- <p><input type="submit" value="RESULT" style="WIDTH: 200px; HEIGHT: 20px"></p> -->
    <p><input type="submit" name="action" value="Result"></p>
    <p><input type="submit" name="action" value="Next"></p>
</form>
<p>
</body>
</html>