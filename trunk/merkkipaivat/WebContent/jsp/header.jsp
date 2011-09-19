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
	        <h1><a href="#">Merkki <span>paeivaet</span></a><small><i>"Muista mitä vittuu millonki tapahtuu"</i></small></h1>
	      </div>
	      <div class="clr"></div>
	      <div class="menu_nav">
	        <ul>
	          <li class="active"><a href="#">Home</a></li>
	          <li><a href="#">Support</a></li>
	          <li><a href="#">About Us</a></li>
	          <li><a href="#">Blog</a></li>
	          <li><a href="#">Contact Us</a></li>
	          <li><a href="#"><c:out value="${authed}" /></a></li>
	        </ul>
	        <div class="search">
	          <form id="form" name="form" method="post" action="#">
	            <span>
	            <input name="q" type="text" class="keywords" id="textfield" maxlength="50" value="Search..." />
	            <input name="b" type="image" src="images/search.gif" class="button" />
	            </span>
	          </form>
	        </div>
	        <!--/search -->
	      </div>
	      <div class="clr"></div>
	    </div>
	  </div>
	  <div class="clr"></div>