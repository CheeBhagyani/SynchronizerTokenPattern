<%-- 
    Document   : home
    Created on : Oct 4, 2018, 12:59:01 PM
    Author     : Chee Bhagyani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <h1>Hello User!!</h1>
        <h2>You are successfully logged in</h2>
    <form action="validate" method="POST">
        
        <label>Enter subject code :</label>
        <input type="text" name="code" placeholder="Subject Code"><br/>
        
        <label>Enter your enrollment key :</label>
        <input type="text" name="enKey" placeholder="Enrollment Key"><br/>

        <input type="hidden" name="token" id="token"/>

        <input type="submit" value="Submit"/>

    </form>
    <script src="./jquery.min.js"></script>
    <script>
        $.ajax({
            type: 'GET',
            dataType: 'json',
            url: '/CrossSiteRequestForgeryPrevention/checkToken',
            success: function (data) {
                alert(data);
                $("#tokentxt").val(data.csrfToken);
            },
            error: function (xhr, status, error) {
                alert(status);
            }
        });
    </script>
    </body>
</html>
