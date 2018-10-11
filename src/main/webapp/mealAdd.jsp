<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2><a href="index.html">Home</a></h2>
    <h1>Add Meal</h1>
    <hr/>
    <form method="post" action="meals">
        <label>DateTime:
            <input type="datetime-local" name="dateTime">
        </label>
        <label>Description:
            <input type="text" name="description">
        </label>
        <label>Calories:
            <input type="number" name="calories">
        </label>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</body>
</html>
