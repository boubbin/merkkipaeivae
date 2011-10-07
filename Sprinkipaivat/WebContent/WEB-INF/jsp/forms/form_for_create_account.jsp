<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
		<script>
		$(function() {
			$( "#date" ).datepicker({ dateFormat: 'yy-mm-dd' });
		});
		</script>
<!-- 	<div id="info">
			<form method="POST" id="editForm">
				<table>
					<tr>
						<td>Choose an username:</td>
						<td><input type="text" name="username" class="required" /></td>
						<td><div id="form_warning"><c:out value="${usernameMessage}"/></div></td>
						
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password1" class="required" /></td>
						<td><div id="form_warning"><c:out value="${passwordMessage}"/></div></td>
					</tr>
					<tr>
						<td>Password again:</td>
						<td><input type="password" name="password2" class="required" /></td>
						<td></td>
					</tr>
					<tr>
						<td>Email address:</td>
						<td><input type="text" name="email" class="required email" /></td>
						<td><div id="form_warning"><c:out value="${emailMessage}"/></div></td>
					</tr>
					<tr>
						<td>Date of Birth:</td>
						<td><input id=date type="date" name="dob"  /> (yyyy-mm-dd)</td>
						<td><div id="form_warning"><c:out value="${dobMessage}"/></div></td>
					</tr>
				</table>
				<input type="submit" value="Create account" />
			</form>
		</div>
		 -->
		<div id="info">
		<form:form method="post">
				<table>
					<tr>
						<td>Choose an username:</td>
						<td><form:input path="username" /></td>
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
