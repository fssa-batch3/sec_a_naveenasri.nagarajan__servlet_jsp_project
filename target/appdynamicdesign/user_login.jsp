<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./assets/css/universe.css" />
<link rel="stylesheet" href="./assets/css/form.css">

<title>user login</title>
</head>

<body>

	<jsp:include page="header.jsp"></jsp:include>


	<h1>Login Page</h1>
	<form action="login" method="post" id="form3" class="onlylog">
		<div class="head_div">
			<h2 class="head" style="margin-top: 0px">User Login</h2>
		</div>
		<%
		String errorMessage = request.getParameter("error");
		if (errorMessage != null) {
		%>

		<div class="styledbutton" id="styledbuttonlogin">
			<%=errorMessage%>
			<!-- this will change based on invalid field entered -->
		</div>
		<br /> <br />
		<%
		}
		%>
		<label>Email:</label> <input type="email" name="email"
			style="margin-left: 45px" id="email" placeholder="Enter email"
			value="${email}" /> <br /> <br /> <label>password:</label> <input
			placeholder="Enter password" type="password" name="password"
			id="password" value="${password}" /> <br /> <br />
		<div>
			<button type="submit">Log in</button>

		</div>
		<div class="signupbtn_div">
			<button>
				<a class="signupbtn" href="user_register.jsp"> Sign up </a>
			</button>
		</div>
	</form>

</body>

</html>