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
        <title>Add New User | WIP Camp Applicant Scoring System</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <section class="main">
            <div class="row">
                <div class="small-12 columns">
                    <h1>Add New User</h1>
                </div>
            </div>
            <!-- Breadcrumbs -->
            <div class="row" style="margin-bottom: 1em;">
                <div class="small-12 columns">
                    <nav class="breadcrumbs">
                        <a href="Home"><i class="fa fa-home"></i> Home</a>
                        <a class="unavailable">Admin</a>
                        <a href="ViewUsers">Users</a>
                        <a class="current" href="#">Add New</a>
                    </nav>
                </div>
            </div>
            <!-- End Breadcrumbs -->
            <div class="row">
                <!-- Sidebar -->
                <div class="small-3 columns">
                    <%@include file="sidebar.jsp" %>
                </div>
                <!-- End Sidebar -->
                <div class="small-9 columns">
                    <form action="AddUser" method="post">
                        <fieldset>
                            <legend>Add New User</legend>
                            <div class="row" style="margin-top: 1em;">
                                <div class="small-3 columns">
                                    <label for="email" class="right inline">Email</label>
                                </div>
                                <div class="small-6 left columns">
                                    <input type="email" id="email" name="email" value="${param.email}" required/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="small-3 columns">
                                    <label for="displayname" class="right inline">Display Name</label>
                                </div>
                                <div class="small-6 left columns">
                                    <input type="text" id="displayname" name="displayname" value="${param.displayname}" required/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="small-3 columns">
                                    <label for="password" class="right inline">Password</label>
                                </div>
                                <div class="small-6 left columns">
                                    <input type="password" id="password" name="password" required/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="small-3 columns">
                                    <label for="confirmPassword" class="right inline">Confirm Password</label>
                                </div>
                                <div class="small-6 left columns">
                                    <input type="password" id="confirmPassword" name="confirmPassword" required/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="small-3 columns">
                                    <label for="type" class="right inline">Type</label>
                                </div>
                                <div class="small-6 left columns">
                                    <select id="type" name="type" required>
                                        <option value="1">Admin</option>
                                        <option value="2">Manager</option>
                                        <option value="3" selected>User</option>
                                    </select>
<!--                                    <label for="type1">
                                        <input name="type" id="type1" type="radio" value="1" ${editedUser.type == 1 ? "checked" : ""}/> Admin 
                                    </label>
                                    <label for="type2">
                                        <input name="type" id="type2" type="radio" value="2" ${editedUser.type == 2 ? "checked" : ""}/> User
                                    </label>-->
                                </div>
                            </div>
                            <div class="row" style="margin-top: 1em;">
                                <div class="small-3 columns">&nbsp;</div>
                                <div class="small-6 left columns">
                                    <script>
                                        var msg = "${msg}";
                                        if (msg.length > 0) {
                                            document.write("<div data-alert class=\"alert-box ${msgType == 1 ? "success" : "alert"}\" style=\"font-size: small;\">"
                                                    + msg +
                                                    "<a href class=\"close\">×</a></div>");
                                        }
                                    </script>
                                    <input class="button small" type="submit" value="Add"/>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </section>
        <%@include file="footer.jsp" %>
    </body>
</html>
