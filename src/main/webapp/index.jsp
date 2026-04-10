<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String context = request.getContextPath();

if (session.getAttribute("user") != null) {
    response.sendRedirect(context + "/dashboard");
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Smart Expense Tracker</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body class="bg-light">

	<div class="container text-center mt-5">

		<h1 class="mb-4">💰 Smart Expense Tracker</h1>
		<p class="lead">Track your expenses, manage your budget, and stay in control.</p>

		<div class="mt-4">
			<a href="<%=context%>/login" class="btn btn-primary btn-lg me-2">Login</a>
			<a href="<%=context%>/views/signup.jsp" class="btn btn-success btn-lg">Signup</a>
		</div>

	</div>

</body>
</html>