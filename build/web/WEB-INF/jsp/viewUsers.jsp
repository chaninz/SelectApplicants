<%-- 
    Document   : viewUser
    Created on : Oct 28, 2013, 10:39:39 AM
    Author     : CHANINZ
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]>         <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="includeAssets.jsp" %>
        <title>All Users | WIP Camp Applicant Scoring System</title>
        <style>
            table thead tr th {
                text-align: center;
                height: 40px;
                background-color: rgb(231, 231, 231);
            }
            table tr td {
                height: 45px;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <section class="main">
            <div class="row">
                <div class="small-12 columns">
                    <h1 class="">Users (${users.size()})</h1>
                </div>
            </div>
            <!-- Breadcrumbs -->
            <div class="row" style="margin-bottom: 1em;">
                <div class="small-12 columns">
                    <nav class="breadcrumbs">
                        <a href="Home"><i class="fa fa-home"></i> Home</a>
                        <a class="unavailable">Admin</a>
                        <a href="#">Users</a>
                        <a class="current" href="#">All Users</a>
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
                    <table width="100%">
                        <thead>
                            <tr>
                                <th width="10%">User ID</th>
                                <th width="30%">Email</th>
                                <th width="25%">Display Name</th>
                                <th width="20%">Type</th>
                                <th width="15%"></th>
                            </tr>
                        </thead>

                        <!-- Loop for shows users -->
                        <c:choose>
                            <c:when test="${not empty users}">
                                <c:set var="count" value="1" />
                                <c:forEach items="${users}" var="user">
                                    <tr style="height: 30px; ${count % 2 == 0 ? "background-color: whitesmoke" : ""};">
                                        <td style="text-align: center;">${user.userId}</td>
                                        <td>${user.email}</td>
                                        <td style="text-align: center;">${user.displayname}</td>
                                        <td style="text-align: center;">
                                            <c:choose>
                                                <c:when test="${user.type == 1}">
                                                    <span class="success label">Admin</span>
                                                </c:when>
                                                <c:when test="${user.type == 2}">
                                                    <span class="label">Manager</span>
                                                </c:when>
                                                <c:when test="${user.type == 3}">
                                                    <span class="secondary label">User</span>
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td style="text-align: center;"><a href="EditUser?userId=${user.userId}" title="Edit User""><i class="fa fa-pencil-square-o"></i></a> <a href="DeleteUser?userId=${user.userId}" title="Delete User" onClick="return confirm('Do you want to delete this user?\nAll evaluate result of this user will be deleted.')"><i class="fa fa-trash-o"></i></a></td>
                                    </tr>
                                    <c:set var="count" value="${count+1}"/>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="5" style="text-align: center;">No data found.</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </table>


                </div>
            </div>
        </section>
        <%@include file="footer.jsp" %>
    </body>
</html>

