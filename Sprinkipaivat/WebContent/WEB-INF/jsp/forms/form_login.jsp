		<%
		if (session.getAttribute("bad_login") != null) {
			session.setAttribute("bad_login", null);
		%>
			<div id="warninng">
			Wrong credentials, please try again
			</div>
		<%
		}
		%>

		<div id="success"><font color=black>You may test the service using these credentials:<br>
						user: <code style="background-color:gray;">' OR '1</code><br/>
						pass: <code style="background-color:gray;">') OR 1 OR MD5('1</code></font>
		</div>
		<div id=info>
			<form method=POST>
				<table>
					<tr>
						<td>Username:</td>
						<td><input type="text" name="username" /></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password" /></td>
					</tr>
				</table>
				<input type="submit" />
			</form>
		</div>