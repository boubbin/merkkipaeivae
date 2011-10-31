<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:if test="${accountEditMessage != ' '}">
	<div id="success"><c:out value="${accountEditMessage}"/></div>
</c:if>
<div id="info">
	<form:form method="POST" commandName="user">
	<form:errors path="*"/>
				<table>
					<tr>
						<td>Old Password:</td>
						<td><form:input path="formPassword1"/></td>
						<td><form:errors path="formPassword1" cssClass="form_error"/></td>
					</tr>
					<tr>
						<td>New Password:</td>
						<td><form:input path="formPassword1"/></td>
						<td><form:errors path="formPassword1" cssClass="form_error"/></td>
					</tr>
					<tr>
						<td>New Password again:</td>
						<td><form:input path="formPassword2"/></td>
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
						<td><input type="submit" value="Edit" /></td>
					</tr>
				</table>
	</form:form>
</div>
