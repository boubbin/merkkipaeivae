<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="info">
	<form:form method="POST" commandName="user">
	<form:errors path="*"/>
				<table>
					<tr>
						<td>Password:</td>
						<td><form:password path="formPassword1"/></td>
						<td><form:errors path="formPassword1" cssClass="form_error"/></td>
					</tr>
					<tr>
						<td>Email address:</td>
						<td><form:input path="email" /></td>
						<td><form:errors path="email" cssClass="form_error"/></td>
					</tr>
					<tr>
						<td>Date of Birth:</td>
						<td><form:input path="dateofbirth" /></td>
						<td>(format: dd.mm.yyy)</td>
						<td><form:errors path="dateofbirth" cssClass="form_error"/></td>
					</tr>
					<tr>
						<td><input type="submit" value="Edit" /></td>
					</tr>
				</table>
	</form:form>
</div>
