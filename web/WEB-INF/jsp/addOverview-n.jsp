<%-- 
    Document   : addUser
    Created on : Oct 29, 2013, 10:36:50 AM
    Author     : CHANINZ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Overview | WIP Camp Applicant Scoring System</title>
    </head>
    <body>
        <h1>Add Overview</h1>
        <form action="AddOverview" method="post" >
            WIP ID : <input type="text" name="wipId"/> <br/>
            Is Pass : <input type="number" name="isPass"/> <br/>
            Reason : <textarea name="reason" cols="20" rows="5" ></textarea> <br/>
            User ID : <input type="number" name="userId"/>
            <input type="submit" value="Submit"/>
        </form>
        <a href="ViewOverview">View Quesions</a>
    </body>
</html>
