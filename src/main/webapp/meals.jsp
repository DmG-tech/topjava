<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Моя еда</h2>

<style type="text/css">
    body {
        background: #f2f2f2;
    }
     h2 {
         font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
         color: #1a1a1a;
         text-align: center;
     }
    table {
        font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
        border-collapse: collapse;
        color: #1a1a1a;
        margin: auto;
        width: 50%;
        border: 0px;
    }
    th {
        border-bottom: 5px solid #a6a6a6;
        padding: 10px;
        text-align: center;
        border: 0px;
    }
    td {
        padding: 10px;
        text-align: center;
        border: 0px;
    }
    tr:nth-child(odd) {
        background: #f2f2f2;
    }
    tr:nth-child(even) {
        background: #cccccc;
    }
</style>


<p><a href="meals?action=add">Add User</a></p>
<table class="meal_table" border="1">
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th></th>
        <th></th>
    </tr>
    <c:set var="meals" value="${requestScope.mealsList}"/>

    <c:forEach var="meal" items="${meals}">
        <tr style="color:${meal.excess ? 'green' : 'red'}">
            <td><javatime:format pattern="dd-MM-yyyy HH:mm" value="${meal.dateTime}"/></td>
            <td><c:out value="${meal.description}" /></td>
            <td><c:out value="${meal.calories}" /></td>
            <td><a href="meals?action=update&id=<c:out value="${meal.id}"/>">Update</a></td>
            <td><a href="meals?action=delete&id=<c:out value="${meal.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
