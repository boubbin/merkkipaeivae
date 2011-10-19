<%@include file='../header.jsp'%>
<script type="text/javascript">
function confirm_delete(id)
{
var choice=confirm("Confirm Delete?");
if (choice==true)
 	{
	var ctx = "${pageContext.request.contextPath}";
	window.location = ctx + '/anniversary/delete?id=' + id;
}
}
</script>
<c:if test="${not empty anniversaryEditMessage}">
	<div id="success"><c:out value="${anniversaryEditMessage}"/></div>
</c:if>
<c:if test="${not empty anniversaryDeleteMessage}">
	<div id="success"><c:out value="${anniversaryDeleteMessage}"/></div>
</c:if>
<div id="anniversary">
	<a href="<%= request.getContextPath() %>/anniversary/create">Create new anniversary</a>
</div>
<div id="anniversary">
	<table>
		<c:forEach items="${anniversaries}" var="anniversary">
		<tr>
			<td><b>Name:</b> <c:out value="${anniversary.name}"/></td>
			<td><b>Date:</b> <c:out value="${anniversary.date}"/></td>
			<td><form method=GET action="<%= request.getContextPath() %>/anniversary/edit">
					<input type="submit" value="Edit" />
					<input type="hidden" name="id" value="<c:out value="${anniversary.id}"/>"/>
				</form></td>
			<td>
				<input type="submit" onclick="confirm_delete(<c:out value="${anniversary.id}"/>)" value="Delete"/>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
<%@include file='../footer.jsp'%>