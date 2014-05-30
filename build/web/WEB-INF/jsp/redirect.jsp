<%-- 
    Document   : redirect
    Created on : Dec 5, 2013, 8:46:05 PM
    Author     : CHANINZ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Redirect</title>
        <script>
                window.location.assign("${not empty requestScope.uri ? requestScope.uri : "Home"}");
        </script>
    </head>
    <body>
    </body>
</html>
