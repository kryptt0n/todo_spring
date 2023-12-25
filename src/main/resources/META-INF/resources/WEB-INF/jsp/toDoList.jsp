<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>To do</title>
        <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" >
        <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
        <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
    </head>
    <body>
        <%@ include file="common/navigation.jspf"%>
        <div class="container">

            <h1>Hi! From JSP</h1>
            <div>
                Welcome: ${name}
                <hr>
                <h1>Here is your to do:</h1>

                <table class="table">
                    <thead>
                        <tr>
                            <th>Description</th>
                            <th>Target date</th>
                            <th>Is done</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${toDos}" var="toDo">
                            <tr>
                                <td> ${toDo.description} </td>
                                <td> ${toDo.targetDate} </td>
                                <td> ${toDo.done} </td>
                                <td><a href="delete-todo?id=${toDo.id}" class="btn btn-warning">Delete</a></td>
                                <td><a href="update-todo?id=${toDo.id}" class="btn btn-success">Update</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <a href="add-todo" class="btn btn-success">Add to do</a>
            </div>
        </div>
    </body>
</html>