<%@include file='../header.jsp'%>
<script type="text/javascript">
function confirm_delete(id)
{
var choice=confirm("Confirm Delete?");
if (choice==true)
  {
  window.location.search = '?action=delete&id=' + id; 
  }
}
</script>
<c:if test="${anniversaryEditMessage != ' '}">
	<div id="success"><c:out value="${anniversaryEditMessage}"/></div>
</c:if>
<c:if test="${anniversaryDeleteMessage != ' '}">
	<div id="success"><c:out value="${anniversaryDeleteMessage}"/></div>
</c:if>
<div id="anniversary">
	<a href="anniversary?action=create">Create new anniversary</a>
</div>
<div id="anniversary">
	<table>
		<c:forEach items="${anniversaries}" var="anniversary">
		<tr>
			<td><b>Name:</b> <c:out value="${anniversary.name}"/></td>
			<td><b>Date:</b> <c:out value="${anniversary.pvm}"/></td>
			<td><form method=GET action="anniversary">
					<input type="submit" value="Edit" />
					<input type="hidden" name="action" value="edit" />
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