<%-- 
    Document   : addUser
    Created on : Oct 29, 2013, 10:36:50 AM
    Author     : CHANINZ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]>         <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="includeAssets.jsp" %>
        <title>Register | WIP Camp Applicant Scoring System</title>
    </head>
    <body>
        <div class="row" style="margin-top: 5em;">
            <div class="small-6 small-centered columns">
                <div class="row">
                    <div class="small-12 columns" style="text-align: center;">
                        <img src="images/logo.jpg" />
                    </div>
                </div>
                <div class="row">
                    <div id="login-box" class="small-12 small-centered columns panel" style="padding-left: 50px; padding-right: 50px;/*background-color: #e9e9e9; border: 20px solid #e9e9e9;*/">
                        <h4 style="text-align: center; margin-bottom: 1em;">Register</h4>
                        <form action="Register" method="post">
                            <label for="email">Email</label>
                            <input type="email" id="email" name="email" placeholder="wippo@wipcamp.com" value="${param.email}" required/>
                            <div class="row">
                                <div class="small-6 columns">
                                    <label for="password">Password</label>
                                    <input type="password" id="password" name="password" required/>
                                </div>
                                <div class="small-6 columns">
                                    <label for="confirmPassword">Confirm Password</label>
                                    <input type="password" id="confirmPassword" name="confirmPassword" required/>
                                </div>
                            </div>
                            <label for="displayname">Display Name</label>
                            <input type="text" id="displayname" name="displayname" placeholder="Wippo" value="${param.displayname}" required/>

                            <script>
                                var error = "${msg}";
                                if (error.length > 0) {
                                    document.write("<div data-alert class=\"alert-box ${msgType == 1 ? "success" : "alert"}\" style=\"font-size: small;\">"
                                            + error +
                                            "<a href class=\"close\">×</a></div>");
                                }
                            </script>
                            <div style="text-align: center;">
                                <input class="small button" type="submit" value="Register"/>
                            </div>
                            <div style="text-align: center; font-size: x-small;">Want to <a href="Login">Sign In</a> ?</div>
                        </form>
                    </div>
                </div>
            </div>
            <%@include file="footer.jsp" %>
    </body>
</html>