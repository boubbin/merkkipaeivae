<c:if test="${accountEditMessage != ' '}">
	<div id="success"><c:out value="${accountEditMessage}"/></div>
</c:if>
<div id="info">
	<form method="POST">
		<table>
			<tr>
				<td>Email address:</td>
				<td><input type="text" name="email" size="30" value="<c:out value="${user.email}"/>"/></td>
				<td><div id="form_warning"><c:out value="${emailMessage}"/></div></td>
			</tr>
			<tr>
				<td>Date of Birth:</td>
				<td><input type="text" name="dob" size="30" value="<c:out value="${dob}"/>"/> (yyyy-mm-dd)</td>
				<td><div id="form_warning"><c:out value="${dobMessage}"/></div></td>
			</tr>
		</table>
		<input type="submit" value="Edit Account" />
	</form>
</div>
