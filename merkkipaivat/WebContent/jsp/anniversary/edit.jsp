<%@include file='../header.jsp'%>
<c:if test="${anniversaryEditMessage != ' '}">
	<div id="success"><c:out value="${anniversaryEditMessage}"/></div>
</c:if>
<div id="anniversary">
	<form method="post">
	<table>
		<h2>Anniversary:</h2>
		<tr>
			<td>name:<input type="text" name="name" size="40" value="<c:out value="${anniversary.name}"/>"/></td>
			<td><div id="form_warning"><c:out value="${nameMessage}"/></div></td>
		</tr>
		<tr>
			<td>date:<input type="text" name="date" value="<c:out value="${anniversary.pvm}"/>"/> (dd.mm.yyyy)</td>
			<td><div id="form_warning"><c:out value="${dateMessage}"/></div></td>
		</tr>
	</table>
	<input type="submit" value="Edit"/>
	</form>
</div>

<%@include file='../footer.jsp'%>