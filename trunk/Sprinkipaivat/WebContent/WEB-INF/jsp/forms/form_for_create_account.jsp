<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
		<script>
		$(function() {
			$( "#date" ).datepicker({ dateFormat: 'yy-mm-dd' });
		});
		</script>
		<div id="info">
		<form:form method="post" modelattribute="accountFormController">
				<table>
					<tr>
						<td>Choose an username:</td>
						<td><form:input path="username"/></td>
						<td><form:errors path="username" cssClass="form_error"/></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><form:input path="password" /></td>
						<td><form:errors path="password" cssClass="form_error"/></td>
					</tr>
					<tr>
						<td>Password again:</td>
						<td><form:input path="password2" /></td>
						<td><form:errors path="password2" cssClass="form_error"/></td>
					</tr>
					<tr>
						<td>Email address:</td>
						<td><form:input path="email" /></td>
						<td><form:errors path="email" cssClass="form_error"/></td>
					</tr>
					<tr>
						<td>Date of Birth:</td>
						<td><form:input path="dob" /></td>
						<td><form:errors path="dob" cssClass="form_error"/></td>
					</tr>
				</table>
		</form:form>
		</div>
