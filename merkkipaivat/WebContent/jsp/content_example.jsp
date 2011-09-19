<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<body>
	<div class="main">
	  <div class="header">
	    <div class="header_resize">
	      <div class="logo">
	        <h1><a href="#">Merkki<span>paeivaet</span></a><small><i>"Muista mitä vittuu millonki tapahtuu"</i></small></h1>
	      </div>
	      <div class="clr"></div>
	      <div class="menu_nav">
	        <ul>
	          <c:if test="${empty auth}">
	          		<li><a href="#">Login</a></li>
	          		<li><a href="#">Create Account</a></li>
          	  </c:if>
          	  <c:if test="${not empty auth}">
		          <li><a href="#">Anniversary</a></li>
		          <li><a href="#">Edit profile</a></li>
		          <li><a href="#"></a></li>
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
	        <div class="article">
	          <h2><span>Merkkipaeivaet aloitti toimintansa!</span></h2>
	          <div class="clr"></div>
	          <p>-boubbin 15.9.2011<a href="#"> Kommentit (18)</a></p>
	          <img src="images/img_1.jpg" width="613" height="193" alt="" />
	          <div class="clr"></div>
	          <p>Lorem ipsum dolor
	Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec libero. Suspendisse bibendum. Cras id urna. Morbi tincidunt, orci ac convallis aliquam, lectus turpis varius lorem, eu posuere nunc justo tempus leo. Donec mattis, purus nec placerat bibendum, dui pede condimentum odio, ac blandit ante orci ut diam.</p><p>Lorem ipsum dolor
	Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec libero. Suspendisse bibendum. Cras id urna. Morbi tincidunt, orci ac convallis aliquam, lectus turpis varius lorem, eu posuere nunc justo tempus leo. Donec mattis, purus nec placerat bibendum, dui pede condimentum odio, ac blandit ante orci ut diam.</p>
	        </div>
	        <div class="article">
	          <h2><span>Vitun java, vitun Juslin...</span></h2>
	          <div class="clr"></div>
	          <p>-boubbin 15.9.2011 <a href="#">Kommentit -1</a></p>
	          <img src="images/img_2.jpg" width="613" height="193" alt="" />
	          <div class="clr"></div>
	          <p>vittu kun mikään ei toimi</p>
	
	        </div>
	        <div class="article" style="padding:5px 20px 2px 20px; background:none; border:0;">
	          <p>Page 1 of 2 <span class="butons"><a href="#">3</a><a href="#">2</a> <a href="#" class="active">1</a></span></p>
	        </div>
	      </div>
	
	      </div>
	      <div class="clr"></div>
	    </div>
	  </div>
	  <div class="fbg">
	    <div class="fbg_resize">
	      <div class="col c1">
	        <h2><span>Image Gallery</span></h2>
	        <a href="#"><img src="images/gallery_1.jpg" width="58" height="58" alt="" /></a> <a href="#"><img src="images/gallery_2.jpg" width="58" height="58" alt="" /></a> <a href="#"><img src="images/gallery_3.jpg" width="58" height="58" alt="" /></a> <a href="#"><img src="images/gallery_4.jpg" width="58" height="58" alt="" /></a> <a href="#"><img src="images/gallery_5.jpg" width="58" height="58" alt="" /></a> <a href="#"><img src="images/gallery_6.jpg" width="58" height="58" alt="" /></a> </div>
	      <div class="col c2">
	        <h2><span>Lorem Ipsum</span></h2>
	        <p>Lorem ipsum dolor<br />
	          Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec libero. Suspendisse bibendum. Cras id urna. <a href="#">Morbi tincidunt, orci ac convallis aliquam</a>, lectus turpis varius lorem, eu posuere nunc justo tempus leo. Donec mattis, purus nec placerat bibendum, dui pede condimentum odio, ac blandit ante orci ut diam.</p>
	      </div>
	      <div class="col c3">
	        <h2><span>Contact</span></h2>
	        <p>Nullam quam lorem, tristique non vestibulum nec, consectetur in risus. Aliquam a quam vel leo gravida gravida eu porttitor dui. Nulla pharetra, mauris vitae interdum dignissim, justo nunc suscipit ipsum. <a href="#">mail@example.com</a><br />
	          <a href="#">+1 (123) 444-5677</a></p>
	      </div>
	      <div class="clr"></div>
	    </div>