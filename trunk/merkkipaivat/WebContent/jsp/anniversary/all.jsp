<%@include file='../header.jsp'%>

<div id="anniversary">
	<a href="anniversary?action=create">Create new anniversary</a>
</div>
<div id="anniversary">
	<table>
		<c:forEach items="${anniversaries}" var="anniversary">
		<tr>
			<td>Name:<c:out value="${anniversary.name}"/></td>
			<td>Date:<c:out value="${anniversary.pvm}"/></td>
			<td><a href="anniversary?action=edit&id=<c:out value="${anniversary.id}"/>">Edit</a></td>
		</tr>
		</c:forEach>
	</table>
</div>
<%@include file='../footer.jsp'%>