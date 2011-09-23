		<%
		if (session.getAttribute("account_created_ok") == "1") {
		%>
			<div id="success">
			Account created, please login
			</div>
		<%
		}
		if (session.getAttribute("account_created_ok") == "0") {
			%>
			<div id="error">
			Error happened while creating your account!<br>
			Username might be already in use, we are too lazy to check that for you.. fuck<br>
			</div>	
			<%		
		}
		session.setAttribute("account_created_ok", null);
		%>
		<div id="info">
			<form method="POST">
				<table>
					<tr>
						<td>Choose an username:</td>
						<td><input type="text" name="username" /></td>
						<td><div id="form_warning"><c:out value="${usernameMessage}"/></div></td>
						
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password1" /></td>
						<td><div id="form_warning"><c:out value="${passwordMessage}"/></div></td>
					</tr>
					<tr>
						<td>Password again:</td>
						<td><input type="password" name="password2" /></td>
						<td></td>
					</tr>
					<tr>
						<td>Email address:</td>
						<td><input type="text" name="email" /></td>
						<td><div id="form_warning"><c:out value="${emailMessage}"/></div></td>
					</tr>
					<tr>
						<td>Date of Birth:</td>
						<td><input type="date" name="dob" /></td>
						<td><div id="form_warning"><c:out value="${dobMessage}"/></div></td>
					</tr>
				</table>
				<input type="submit" value="Create account" />
			</form>
		</div>
		<% 		
			session.setAttribute("usernameMessage", " ");
			session.setAttribute("passwordMessage", " ");
			session.setAttribute("emailMessage", " ");
			session.setAttribute("dobMessage", " ");
		%>
