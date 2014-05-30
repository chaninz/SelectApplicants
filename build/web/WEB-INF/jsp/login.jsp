<%-- 
    Document   : login
    Created on : Oct 27, 2013, 9:38:02 PM
    Author     : CHANINZ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 9]><html class="lt-ie10" lang="en" > <![endif]-->
<html class="no-js" lang="en" >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="includeAssets.jsp" %>
        <title>Sign In | WIP Camp Applicant Scoring System</title>
    </head>
    <body>
        <div class="row" style="margin-top: 5em;">
            <div class="small-5 small-centered columns">
                <div class="row">
                    <div class="small-12 columns" style="text-align: center;">
                        <img src="images/logo.jpg" />
                    </div>
                </div>
                <div class="row">
                    <div id="login-box" class="small-12 columns panel" style="padding-left: 50px; padding-right: 50px;/*background-color: #e9e9e9; border: 20px solid #e9e9e9;*/">
                        <h4 style="text-align: center; margin-bottom: 1em;">Applicant Scoring System</h4>
                        <form action="Login" method="post" class="custom">
                            <label for="email">Email</label>
                            <input type="email" id="email" name="email" placeholder="wippo@wipcamp.com" required/>
                            <label for="password">Password</label>
                            <input type="password" id="password" name="password" required/>
                            <input type="checkbox" id="remember" name="remember">
                            <label for="remember" style="margin-bottom: 1.25em">Stay signed in</label>
                            <div id="errorBox"></div>
                            <script>
                                var msg = "${msg}";
                                if (msg.length > 0) {
                                    document.getElementById("errorBox").setAttribute("data-alert", "");
                                    document.getElementById("errorBox").setAttribute("class", "alert-box ${msgType == 1 ? "success" : "warning"}");
                                    document.getElementById("errorBox").setAttribute("style", "font-size: small;");
                                    document.getElementById("errorBox").innerHTML = msg + "<a href=\"#\" class=\"close\">&times;</a>";
                                }
                            </script>
                            <div style="text-align: center;">
                                <input type="hidden" name="reqUri" value="${reqUri}"/>
                                <input class="small button" type="submit" value="Sign in" style="margin-bottom: 0px"/>
                            </div>
                        </form>
                        <div style="text-align: center; font-size: x-small;"><a href="Register">Create an account</a></div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
