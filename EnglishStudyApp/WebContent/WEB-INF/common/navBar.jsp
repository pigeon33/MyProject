<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String status = (String) (session.getAttribute("status"));
%>
<!-- ここからモーダル -->
<div class="modal" id="MODAL1">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Waning</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>メイン画面に戻ると、今までの経過が全て失われますが宜しいでしょうか？</p>
			</div>
			<div class="modal-footer">
				<a class="nav-link" href="/EnglishStudyApp/Main">
					<button type="button" class="btn btn-primary">Yes</button>
				</a>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
			</div>
		</div><!-- modal-content -->
	</div><!-- modal-dialog -->
</div><!-- modal fade -->


<!-- ここからNavBar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<!-- 問題を解いているとき以外はmodalを出さない -->
	<% if("inQuestion".equals(status) || "checkAnswer".equals(status)){%>
		<a class="navbar-brand" href="#" data-toggle="modal"
		data-target="#MODAL1">ちょっＴＯＥＩＣ</a>
		<%} else {%>
	<a class="navbar-brand" href="/EnglishStudyApp/Main">ちょっＴＯＥＩＣ</a>
		<%} %>

	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">

	<% if("inQuestion".equals(status) || "checkAnswer".equals(status)){%>
		<li class="nav-item active">
			<a class="nav-link" href="#"data-toggle="modal" data-target="#MODAL1">メイン画面</a>
		</li>
		<%} else {%>
			<li class="nav-item active">
				<a class="nav-link" href="/EnglishStudyApp/Main">メイン画面</a>
			</li>
		<%} %>
<!--
			<li class="nav-item active"><a class="nav-link" href="#"
				data-toggle="modal" data-target="#MODAL1">メイン画面</a></li>
 -->

			<li class="nav-item active"><a class="nav-link"
				href="/EnglishStudyApp/Logout">ログアウト <span class="sr-only">(current)</span></a>
			</li>


			<!--           <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Theme
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="#">Theme1</a>
                <a class="dropdown-item" href="#">Theme2</a>
              </div>
            </li> -->
		</ul>
	</div>
</nav>