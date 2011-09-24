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
			<td>Name:<c:out value="${anniversary.name}"/></td>
			<td>Date:<c:out value="${anniversary.pvm}"/></td>
			<td><a href="anniversary?action=edit&id=<c:out value="${anniversary.id}"/>">Edit</a></td>
			<td>
				<form method="post">
				<input type="submit" onclick="confirm_delete(<c:out value="${anniversary.id}"/>)" value="Delete"/>
				</form>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
<%@include file='../footer.jsp'%>