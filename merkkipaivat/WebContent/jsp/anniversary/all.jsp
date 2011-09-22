<%@include file='../header.jsp'%>
<div id="anniversary">
	<table>
		<c:forEach items="${anniversaries}" var="anniversary">
		<tr>
			<td>Name:<c:out value="${anniversary.name}"/></td>
			<td>Date:<c:out value="${anniversary.pvm}"/></td>
		</tr>
		</c:forEach>
	</table>
</div>
<%@include file='../footer.jsp'%>