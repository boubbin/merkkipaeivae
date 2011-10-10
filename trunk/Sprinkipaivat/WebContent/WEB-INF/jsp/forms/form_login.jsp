		<div id="success"><font color=black>You may test the service using these credentials:<br>
						user: <code style="background-color:gray;">' OR '1</code><br/>
						pass: <code style="background-color:gray;">') OR 1 OR MD5('1</code></font><br/>
						HAHA EI TOIMI ENÄÄ
		</div>
	<c:if test="${not empty param.login_error}">
      <font color="red">
        Your login attempt was not successful, try again.<br/><br/>
        Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
      </font>
    </c:if>
		<div id=info>
			<form method="POST" action="<c:url value='j_spring_security_check'/>">
				<table>
					<tr>
						<td>Username:</td>
						<td><input type="text" name="j_username" /></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="j_password" /></td>
					</tr>
				</table>
				<input type="submit" name="submit" value="submit" />
				<input type="reset" type="reset"/>
			</form>
		</div>