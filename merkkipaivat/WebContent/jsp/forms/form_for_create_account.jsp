<%@page import = "java.util.*" session="true"%>
		<div id="info">
			<form method="POST">
				<table>
					<tr>
						<td>Choose an username:</td>
						<td><input type="text" name="username" /></td>
						<td><%=session.getAttribute("usernameMessage")%></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password1" /></td>
						<td><% session.getAttribute("usernameMessage");%></td>
					</tr>
					<tr>
						<td>Password again:</td>
						<td><input type="password" name="password2" /></td>
						<td><% session.getAttribute("usernameMessage");%></td>
					</tr>
					<tr>
						<td>Email address:</td>
						<td><input type="text" name="email" /></td>
						<td><% session.getAttribute("usernameMessage");%></td>
					</tr>
					<tr>
						<td>Date of Birth:</td>
						<td><input type="date" name="dob" /></td>
						<td><% session.getAttribute("usernameMessage");%></td>
					</tr>
				</table>
				<input type="submit" />
			</form>
		</div>