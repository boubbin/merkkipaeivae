<c:if test="${anniversaryCreateMessage != ' '}">
	<div id="success"><c:out value="${anniversaryCreateMessage}"/></div>
</c:if>
<div id="anniversary">
	<script>
	$(function() {
		$( "#date" ).datepicker({ dateFormat: 'dd.mm.yy' });
	});
	</script>
	<form method="POST">
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
</div>