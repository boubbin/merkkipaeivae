<%@include file='../header.jsp'%>

<table>
	<c:forEach items="${anniversaries}" var="anniversary">
	<tr>
		<td>Name:<c:out value="${anniversary.name}"/></td>
		<td>Date:<c:out value="${anniversary.pvm}"/></td>
	</tr>
	</c:forEach>
</table>

<%@include file='../footer.jsp'%>