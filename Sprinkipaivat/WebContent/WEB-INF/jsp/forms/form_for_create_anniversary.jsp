<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

	<script>
	$(function() {
		$( "#date" ).datepicker({ dateFormat: 'dd.mm.yy' });
	});
	</script>
<!--	<form method="POST">
		<table>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="name" class="required" size="40" /></td>
				<td><div id="form_warning"><c:out value="${nameMessage}"/></div></td>
			</tr>
			<tr>
				<td>Anniversary date:</td>
				<td><input type="text" name="date" id="date" /> (dd.mm.yyyy)</td>
				<td><div id="form_warning"><c:out value="${dateMessage}"/></div></td>
			</tr>
		</table>
		<input type="submit" value="Create anniversary" />
	</form>
	-->
	<form:form method="post" commandName="anniversary">
	<h3>New Anniversary</h3>
		<table>
			<tr>
				<td>Name:</td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name" cssClass="form_error"/></td>
			</tr>
			<tr>
				<td>Anniversary date:</td>
				<td><form:input path="date" /></td>
				<td><form:errors path="date" cssClass="form_error"/></td>
			</tr>
			<tr>
				<td><input type="submit" /></td>
		</table>
	</form:form>