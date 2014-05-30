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
        <title>All questions | WIP Camp Applicant Scoring System</title>
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
                    <h1 class="">Questions (${questions.size()})</h1>
                </div>
            </div>
            <!-- Breadcrumbs -->
            <div class="row" style="margin-bottom: 1em;">
                <div class="small-12 columns">
                    <nav class="breadcrumbs">
                        <a href="Home"><i class="fa fa-home"></i> Home</a>
                        <a class="unavailable">Admin</a>
                        <a href="#">Questions</a>
                        <a class="current" href="#">All Questions</a>
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
                                <th width="10%">No.</th>
                                <th width="60%">Question</th>
                                <th width="15%">Mark</th>
                                <th width="15%"></th>
                            </tr>
                        </thead>
                        <!-- Loop for shows questions -->
                        <c:choose>
                            <c:when test="${not empty questions}">
                                <c:set var="count" value="1" />
                                <c:forEach items="${questions}" var="question">
                                    <tr style="height: 30px; ${count % 2 == 0 ? "background-color: whitesmoke" : ""};">
                                        <td style="text-align: center;">${question.questionId}</td>
                                        <td>${question.question}</td>
                                        <td style="text-align: center;">${question.fullMark}</td>
                                        <td style="text-align: center;"><a href="EditQuestion?questionId=${question.questionId}" title="Edit Question"><i class="fa fa-pencil-square-o"></i></a> <a href="DeleteQuestion?questionId=${question.questionId}" title="Delete Question" onClick="return confirm('Do you want to delete this questions?\nAll answer of this question will be deleted.')"><i class="fa fa-trash-o"></i></a></td>
                                    </tr>
                                    <c:set var="count" value="${count+1}"/>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="4" style="text-align: center;">No data found.</td>
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

