<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:if test="${userCreateMessage != ' '}">
	<div id="success"><c:out value="${userCreateMessage}"/></div>
</c:if>
		<div id="info">
		<form:form method="post" commandName="registrationForm">
		<form:errors path="*"/>
				<table>
					<tr>
						<td>Choose an username:</td>
						<td><form:input path="username"/></td>
						<td><form:errors path="username" cssClass="form_error"/></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><form:input path="formPassword1" /></td>
						<td><form:errors path="formPassword1" cssClass="form_error"/></td>
					</tr>
					<tr>
						<td>Password again:</td>
						<td><form:input path="formPassword2" /></td>
						<td><form:errors path="formPassword2" cssClass="form_error"/></td>
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
						<td><input type="submit" value="Create" /></td>
					</tr>
				</table>
		</form:form>
		</div>
