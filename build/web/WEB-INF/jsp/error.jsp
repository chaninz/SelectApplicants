<%-- 
    Document   : error
    Created on : Oct 28, 2013, 11:15:32 AM
    Author     : CHANINZ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if IE 8]>         <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="includeAssets.jsp" %>
        <title>Error | WIP Camp Applicant Scoring System</title>
        <style>

        </style>
    </head>
    <body>

        <section class="main">
            <div class="row" style="margin-top: 120px;">
                <div class="row">
                    <div class="small-12 columns" style="text-align: center;">
                        <img src="images/logo.jpg"/>
                    </div>
                </div>
                <div class="row">
                    <div id="login-box" class="small-6 small-centered columns" style="padding: 20px; margin-top: 30px; background-color: #f6f6f6; border: 10px solid #d9d9d9; text-align: center;">
                        <h5 style="font-weight: bold;">Error</h5>
                        <h6>${msg}</h6>
                    </div>
                </div>
        </section>
        <%@include file="footer.jsp" %>
    </body>
</html>