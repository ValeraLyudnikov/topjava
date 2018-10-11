<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2><a href="index.html">Home</a></h2>
    <h1>Edit Meal</h1>
    <hr/>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request" />
    <form method="post" action="meals">
        <input type="hidden" name="id" value="${meal.id}">
        <label>DateTime:
            <input type="datetime-local" value="${meal.dateTime}" name="dateTime">
        </label>
        <label>Description:
            <input type="text" value="${meal.description}" name="description">
        </label>
        <label>Calories:
            <input type="number" value="${meal.calories}" name="calories">
        </label>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</body>
</html>
