<%@ page import="java.util.*,model.Expense"%>

<!DOCTYPE html>
<html>
<head>
<title>Dashboard</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>

<body class="bg-light">

	<!-- NAVBAR -->
	<nav class="navbar navbar-dark bg-dark">
		<div class="container-fluid">
			<span class="navbar-brand">Expense Tracker</span> <a href="logout"
				class="btn btn-outline-light">Logout</a>
		</div>
	</nav>

	<div class="container mt-4">

		<h3>Dashboard</h3>

		<!-- ADD EXPENSE -->
		<div class="card p-3 shadow mb-4">
			<form action="expense" method="post">
				<input type="hidden" name="action" value="add"> <input
					type="number" step="0.01" name="amount" placeholder="Amount"
					class="form-control mb-2" required> <select name="category"
					class="form-control mb-2">
					<option>Food</option>
					<option>Travel</option>
					<option>Bills</option>
					<option>Shopping</option>
					<option>Others</option>
				</select> <input type="text" name="description" placeholder="Description"
					class="form-control mb-2">

				<button class="btn btn-primary">Add Expense</button>
			</form>
		</div>

		<%
		List<Expense> list = (List<Expense>) request.getAttribute("expenses");
		Map<String, Double> map = new HashMap<>();
		double total = 0;
		%>

		<!-- SUMMARY -->
		<div class="row mb-4">
			<div class="col-md-4">
				<div class="card bg-success text-white p-3 shadow">
					<h5>Total Expenses</h5>
					<%
					for (Expense e : list) {
						total += e.getAmount();
					}
					%>
					<h3>

						<%=total%></h3>
				</div>
			</div>
		</div>

		<!-- TABLE -->
		<table class="table table-bordered bg-white shadow">
			<tr>
				<th>Amount</th>
				<th>Category</th>
				<th>Description</th>
				<th>Action</th>
			</tr>

			<%
			for (Expense e : list) {
				map.put(e.getCategory(), map.getOrDefault(e.getCategory(), 0.0) + e.getAmount());
			%>
			<tr>
				<td><%=e.getAmount()%></td>
				<td><%=e.getCategory()%></td>
				<td><%=e.getDescription()%></td>

				<td><a
					href="<%=request.getContextPath()%>/editExpense?id=<%=e.getId()%>"
					class="btn btn-warning btn-sm">Edit</a> <a
					href="<%=request.getContextPath()%>/deleteExpense?id=<%=e.getId()%>"
					class="btn btn-danger btn-sm"
					onclick="return confirm('Delete this expense?')"> Delete </a></td>
			</tr>
			<%
			}
			%>
		</table>

		<!-- EXPORT BUTTONS -->
		<div class="mb-3">
			<a href="<%=request.getContextPath()%>/exportCSV"
				class="btn btn-success"> Download CSV </a> <a
				href="<%=request.getContextPath()%>/exportPDF"
				class="btn btn-danger"> Download PDF </a>
		</div>

		<!-- CHART -->
		<div class="card p-3 shadow">
			<canvas id="chart"></canvas>
		</div>

		<script>
			const labels = [
		<%for (String key : map.keySet()) {
	out.print("'" + key + "',");
}%>
			];

			const data = [
		<%for (Double val : map.values()) {
	out.print(val + ",");
}%>
			];

			new Chart(document.getElementById("chart"), {
				type : 'pie',
				data : {
					labels : labels,
					datasets : [ {
						data : data
					} ]
				}
			});
		</script>

	</div>
</body>
</html>