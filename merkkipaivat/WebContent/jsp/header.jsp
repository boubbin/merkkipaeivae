<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<link href="css/styles.css" rel="stylesheet" type="text/css" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="js/cufon-yui.js"></script>
	<script type="text/javascript" src="js/arial.js"></script>
	<script type="text/javascript" src="js/cuf_run.js"></script>
	<title>Merkkipaeivaet</title>
</head>
<body>
	<div class="main">
	  <div class="header">
	    <div class="header_resize">
	      <div class="logo">
	        <h1><a href="mainpage">Merkki<span>paeivaet</span></a><small><i>"Muista mitä vittuu millonki tapahtuu"</i></small></h1>
	      </div>
	      <div class="clr"></div>
	      <div class="menu_nav">
	        <ul>
	          <c:if test="${not empty authed}">
	          		<li><a href="account?action=login">Login</a></li>
	          		<li><a href="account?action=create">Create Account</a></li>
          	  </c:if>
          	  <c:if test="${not empty authed}">
		          <li><a href="anniversary?action=all">Anniversary</a></li>
		          <li><a href="account?action=edit">Edit profile</a></li>
		          <li><a href="lol?action=gayness"></a></li>
			  </c:if>	
	        </ul>
	      </div>
	      <div class="clr"></div>
	    </div>
	  </div>