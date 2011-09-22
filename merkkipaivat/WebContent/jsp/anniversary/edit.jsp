<%@include file='../header.jsp'%>

<div id="anniversary">
	<form method="post">
	<table>
		<h2>Anniversary:</h2>
		<tr>
			<td>name:<input type="text" name="name" size="60" value="<c:out value="${anniversary.name}"/>"/></td>
			<td><div id="form_warning"><%=session.getAttribute("nameMessage")%></div></td>
		</tr>
		<tr>
			<td>date:<input type="text" name="name" value="<c:out value="${anniversary.pvm}"/>"/></td>
			<td><div id="form_warning"><%=session.getAttribute("dateMessage")%></div></td>
		</tr>
	</table>
	<input type="submit" value="Edit"/>
	</form>
</div>

<%@include file='../footer.jsp'%>