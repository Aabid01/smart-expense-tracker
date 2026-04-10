<!DOCTYPE html>
<html>
<head>
    <title>Add Expense</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="container mt-5">

<h2>Add Expense</h2>

<form action="../expense" method="post">

    <input type="hidden" name="action" value="add">

    <div class="mb-3">
        <label>Amount</label>
        <input type="number" step="0.01" name="amount" class="form-control" required>
    </div>

    <div class="mb-3">
        <label>Category</label>
        <select name="category" class="form-control">
            <option>Food</option>
            <option>Travel</option>
            <option>Bills</option>
            <option>Shopping</option>
            <option>Others</option>
        </select>
    </div>

    <div class="mb-3">
        <label>Description</label>
        <input type="text" name="description" class="form-control">
    </div>

    <button class="btn btn-success">Add Expense</button>
    <a href="../dashboard" class="btn btn-secondary">Back</a>

</form>

</body>
</html>