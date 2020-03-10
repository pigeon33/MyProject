<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="jp">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<%if(session.getAttribute("Theme")==null){%>
	<style>
	<%@include file="../css/bootstrap_cyborg.min.css"%>
	</style>
<% }else{
	int i = (int)session.getAttribute("Theme");
	switch (i) {
		case 1:%>
		<style>
			<%@include file="../css/bootstrap_sketchy.min.css"%>
		</style>
		<%break;

		case 2:%>
		<style>
			<%@include file="../css/bootstrap_cyborg.min.css"%>
		</style>

		<%break;
		default:%>
		<style>
			<%@include file="../css/bootstrap_cosmo.min.css"%>
		</style>
		<% }
		} %>

<title>tyottoeic</title>
