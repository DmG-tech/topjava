<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <link type="text/css"
          href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
    <title>Edit Meal</title>
</head>
<body>

<form method="POST" action='meals' name="EditMeal">
    <input
            type="hidden" name="id"
            value="${meal.id}"/><br />
    Дата/Время : <input
        type="datetime-local" name="dateTime"
        value="${meal.dateTime}"/><br />
    Описание : <input
        type="text" name="description"
        value="<c:out value="${meal.description}" />" /> <br />
    Калории : <input
        type="text" name="calories"
        value="<c:out value="${meal.calories}" />" /> <br />
    <input
            type="submit" value="Submit" />
    <input
            type="reset" value="Reset" />
</form>
</body>
</html>