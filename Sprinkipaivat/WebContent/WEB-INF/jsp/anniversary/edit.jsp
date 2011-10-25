<%@include file='../header.jsp'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script>
	$(function() {
		$( "#date" ).datepicker({ dateFormat: 'dd.mm.yy' });
	});
</script>
	<form:form method="post" commandName="anniversary">
	<table>
		<h2>Anniversary:</h2>
		<tr>
			<td>name:<form:input path="name" size="40"/></td>
			<td><form:errors path="name"/></td>
		</tr>
		<tr>
			<td>date:<form:input path="date"/> (dd.mm.yyyy)</td>
			<td><form:errors path="date"/></td>
		</tr>
	</table>
	<input type="submit" value="Edit"/>
	</form:form>

<%@include file='../footer.jsp'%>