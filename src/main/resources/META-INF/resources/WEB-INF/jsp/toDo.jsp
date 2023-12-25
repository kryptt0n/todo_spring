<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Add</title>
        <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" >
        <link href="webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.css" rel="stylesheet">
        <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
        <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
        <script src="webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
        <script type="text/javascript">
            $('#targetDate').datepicker({
                format: 'yyyy-mm-dd'
            });
        </script>
    </head>
    <body>
        <%@ include file="common/navigation.jspf"%>
        <div class="container">
            <h1>Enter to do details:</h1>
            <hr>
            <form:form method="post" modelAttribute="todo">
                <fieldset>
                    <form:label path="description">Description</form:label>
                    <form:input type="text" path="description" required="required"></form:input>
                    <form:errors path="description" cssClass="text-warning"></form:errors>
                </fieldset>

                <fieldset>
                    <form:label path="targetDate">Date</form:label>
                    <form:input type="text" data-provide="datepicker"  data-date-format="yyyy-mm-dd" path="targetDate" required="required"></form:input>
                    <form:errors path="targetDate" cssClass="text-warning"></form:errors>
                </fieldset>

                <form:input type="hidden" path="id"></form:input>

                <form:input type="hidden" path="done"></form:input>

                <br>
                <button type="submit" class="btn btn-success">Submit</button>
            </form:form>
        </div>
    </body>
</html>