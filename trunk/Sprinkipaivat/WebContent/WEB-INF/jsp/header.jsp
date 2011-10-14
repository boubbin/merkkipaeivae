<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<link href="<%= request.getContextPath() %>/css/styles.css" rel="stylesheet" type="text/css" />
	<link href="<%= request.getContextPath() %>/css/jquery-ui.css" rel="stylesheet" type="text/css" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/cufon-yui.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/arial.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/cuf_run.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/jquery/jquery.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/jquery/jquery-ui.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/jquery/jquery-validate.js"></script>
	<script>
	$(document).ready(function(){
		    $("#editForm").validate({
		    	rules: {
		        dob: {
		            required: true,
		            dateISO: true
		          }
		        }
		    });
		    $('#donot').click(function() {
		        $('#nsfw').fadeIn('slow', function() {
		        });
		    });
	});
	</script>
	<title>Merkkipaeivaet</title>
</head>
<body>
<div id="nsfw"><img src="<%= request.getContextPath() %>/images/apple.jpg"></div>
	<div class="main">
	  <div class="header">
	    <div class="header_resize">
	      <div class="logo">
	        <h1><a href="mainpage">Merkki<span>paeivaet</span></a><small><i>"Muista mitä vittuu millonki tapahtuu"</i></small></h1>
	      </div>
	      <div class="clr"></div>
	      <div class="menu_nav">
	        <ul>
	          <c:if test="${authed != 0}">
	          		<li><a href="account?action=login">Login</a></li>
	          		<li><a href="account?action=create">Create Account</a></li>
          	  </c:if>
          	  <c:if test="${authed != 0}">
		          <li><a href="anniversary?action=all">Anniversary</a></li>
		          <li><a href="account?action=edit">Edit profile</a></li>
		          <li><a href="j_spring_security_logout"><font color=red>Logout (<c:out value="${username}"/>)</font></a></li>
		          <li><a href="lol?action=gayness"></a></li>
			  </c:if>	
	        </ul>
	      </div>
	      <div class="clr"></div>
	    </div>
	  </div>
	  	  <div class="clr"></div>
	  <div class="content">
	    <div class="content_resize">
	      <div class="mainbar">