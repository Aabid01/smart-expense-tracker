<%@ page import="model.Expense" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Expense</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="container mt-5">

<h2>Edit Expense</h2>

<%
    Expense e = (Expense) request.getAttribute("expense");
    if (e == null) {
%>
    <p>No expense found</p>
<%
    return;
    }
%>

<form action="<%=request.getContextPath()%>/expense" method="post">

    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="<%= e.getId() %>">

    <div class="mb-3">
        <label>Amount</label>
        <input type="number" step="0.01" name="amount"
               value="<%= e.getAmount() %>" class="form-control" required>
    </div>

    <div class="mb-3">
        <label>Category</label>
        <select name="category" class="form-control">
            <option <%= e.getCategory().equals("Food")?"selected":"" %>>Food</option>
            <option <%= e.getCategory().equals("Travel")?"selected":"" %>>Travel</option>
            <option <%= e.getCategory().equals("Bills")?"selected":"" %>>Bills</option>
            <option <%= e.getCategory().equals("Shopping")?"selected":"" %>>Shopping</option>
            <option <%= e.getCategory().equals("Others")?"selected":"" %>>Others</option>
        </select>
    </div>

    <div class="mb-3">
        <label>Description</label>
        <input type="text" name="description"
               value="<%= e.getDescription() %>" class="form-control">
    </div>

    <button class="btn btn-primary">Update Expense</button>
    <a href="../dashboard" class="btn btn-secondary">Cancel</a>

</form>

</body>
</html>