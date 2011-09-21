<%@page import = "java.util.*" session="true"%>
		<%
		if (session.getAttribute("account_created_ok") != null) {
			session.setAttribute("account_created_ok", null);
		%>
			<div id="success">
			Account created, please login
			</div>
		<%
		}
		%>
		<div id="info">
			<form method="POST">
				<table>
					<tr>
						<td>Choose an username:</td>
						<td><input type="text" name="username" /></td>
						<td><div id="form_warning"><%=session.getAttribute("usernameMessage")%></div></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password1" /></td>
						<td><div id="form_warning"><%=session.getAttribute("passwordMessage")%></div></td>
					</tr>
					<tr>
						<td>Password again:</td>
						<td><input type="password" name="password2" /></td>
						<td></td>
					</tr>
					<tr>
						<td>Email address:</td>
						<td><input type="text" name="email" /></td>
						<td><div id="form_warning"><%=session.getAttribute("emailMessage")%></div></td>
					</tr>
					<tr>
						<td>Date of Birth:</td>
						<td><input type="date" name="dob" /></td>
						<td><div id="form_warning"><%=session.getAttribute("dobMessage")%></div></td>
					</tr>
				</table>
				<input type="submit" value="Create account" />
			</form>
		</div>
		<% %>
