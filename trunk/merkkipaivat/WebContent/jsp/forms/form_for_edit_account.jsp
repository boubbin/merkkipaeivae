<c:if test="${accountEditMessage != ' '}">
	<div id="success"><c:out value="${accountEditMessage}"/></div>
</c:if>
<script>	
	$(function() {
		$( "#date" ).datepicker({ dateFormat: 'yy-mm-dd' });
	});
</script>
<div id="info">
	<form method="POST" class="cmxform" id="editForm">
		<table>
			<tr>
				<td>Email address:</td>
				<td><input type="text" name="email" size="30" class="required email" value="<c:out value="${user.email}"/>"/></td>
				<td><div id="form_warning"><c:out value="${emailMessage}"/></div></td>
			</tr>
			<tr>
				<td>Date of Birth:</td>
				<td><input type="text" id=date name="dob" size="30" value="<c:out value="${dob}"/>"/></td>
				<td><div id="form_warning"><c:out value="${dobMessage}"/></div></td>
			</tr>
		</table>
		<input type="submit" value="Edit Account" />
	</form>
</div>
