<%-- 
    Document   : editProfile
    Created on : Dec 8, 2013, 3:02:05 AM
    Author     : CHANINZ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html class="no-js" lang="en" >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="includeAssets.jsp" %>
        <title>Edit Profile | WIP Camp Applicant Scoring System</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <section class="main">
            <div class="row" style="margin-top: 5em;">
            <div class="small-6 small-centered columns">
                <div class="row">
                    <div id="login-box" class="small-12 small-centered columns panel" style="padding-left: 50px; padding-right: 50px;/*background-color: #e9e9e9; border: 20px solid #e9e9e9;*/">
                        <h4 style="text-align: center; margin-bottom: 1em;">Edit Profile</h4>
                        <form action="EditProfile" method="post">
                            <label for="email">Email</label>
                            <input type="email" id="email" name="email" value="${not empty param.email ? param.email : user.email}" required/>
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
                            <input type="text" id="displayname" name="displayname" value="${not empty param.displayname ? param.displayname : user.displayname}" required/>

                            <script>
                                var error = "${msg}";
                                if (error.length > 0) {
                                    document.write("<div data-alert class=\"alert-box ${msgType == 1 ? "success" : "alert"}\" style=\"font-size: small;\">"
                                            + error +
                                            "<a href class=\"close\">×</a></div>");
                                }
                            </script>
                            <div style="text-align: center;">
                                <input class="small button" type="submit" value="Update" style="margin-bottom: 0px"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="footer.jsp" %>
    </body>
</html>
