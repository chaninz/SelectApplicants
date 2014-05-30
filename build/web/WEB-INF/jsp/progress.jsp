<%-- 
    Document   : progress
    Created on : Dec 9, 2013, 3:38:10 PM
    Author     : CHANINZ
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]>         <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="includeAssets.jsp" %>
        <title>Progress | WIP Camp Applicant Scoring System</title>
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
                    <h1>Progress (${applicantSize})</h1>
                </div>
            </div>
            <!-- Breadcrumbs -->
            <div class="row" style="margin-bottom: 1em;">
                <div class="small-12 columns">
                    <nav class="breadcrumbs">
                        <a href="Home"><i class="fa fa-home"></i> Home</a>
                        <a class="unavailable">${user.type == 1 ? "Admin" : "Manage"}</a>
                        <a class="current" href="#">Summary</a>
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
                    <!-- Filter -->
                    <div class="row">
                        <div class="small-12 columns" style="height: 30px; text-align: right;">
                            <dl class="right sub-nav">
                                <dt>Filter:</dt>
                                <dd ${empty param.filter or param.filter == "all" ? "class=\"active\"" : ""}><a href="Progress">All</a></dd>
                                <dd ${param.filter == "complete" ? "class=\"active\"" : ""}><a href="Progress?filter=complete">Complete</a></dd>
                                <dd ${param.filter == "incomplete" ? "class=\"active\"" : ""}><a href="Progress?filter=incomplete">Incomplete</a></dd>
                            </dl>
                        </div>
                    </div>
                    <!-- End Filter -->
                    <div class="row">
                        <div class="small-12 columns">
                            <table width="100%">
                                <thead>
                                    <tr>
                                        <th width="10%">No.</th>
                                        <th width="10%">WIP ID</th>
                                        <th width="40%">Name - Surname</th>
                                        <th width="15%">Nickname</th>
                                        <th width="5%">Gender</th>
                                        <th width="20%">Status</th>
                                    </tr>
                                </thead>
                                <!-- Loop for shows progress -->
                                <c:choose>
                                    <c:when test="${not empty applicantsList}">
                                        <c:set var="count" value="1" />
                                        <c:forEach items="${applicantsList}" var="applicant">
                                            <tr style="height: 30px;">
                                                <td style="text-align: center;">${count}</td>
                                                <td style="text-align: center;"><a href="ViewApplicant?wipId=${applicant.wipId}">${applicant.wipId}</a></td>
                                                <td>${applicant.firstnameTh}  ${applicant.lastnameTh}</td>
                                                <td style="text-align: center;">${applicant.nickname}</td>
                                                <td style="text-align: center;">${applicant.gender}</td>    
                                                <td style="text-align: center;">
                                                    <c:choose>
                                                        <c:when test="${applicant.status == 1 and applicant.statusSec == 1}">
                                                            <span class="success label">Complete</span>
                                                        </c:when>
                                                        <c:when test="${applicant.status == 0 and applicant.statusSec == 0}">
                                                            <span class="alert label">Incomplete</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span style="color: ${applicant.status == 1 ? "#43ac6a" : "#f04124"}"><i class="fa fa-eye"></i></span> <span style="color: ${applicant.statusSec == 1 ? "#43ac6a" : "#f04124"}"><i class="fa fa-question"></i></span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                </td>
                                            </tr>
                                            <c:set var="count" value="${count+1}"/>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td colspan="6" style="text-align: center;">No data found.</td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                            </table>

                            <!--pagination-->
                            <c:choose>
                                <c:when test="${not empty applicantsList}">
                                    <div class="pagination-centered" style="text-align: right">
                                        <ul class="pagination">
                                            <!--Check for receieve current page value from query string-->
                                            <c:choose>
                                                <c:when test="${currentPage == null}">
                                                    <c:set var="currentPage" value="1" scope="page"/>
                                                </c:when>
                                            </c:choose>

                                            <c:choose>
                                                <c:when test="${currentPage == null}">
                                                    <c:set var="currentPage" value="1" scope="page"/>
                                                </c:when>
                                            </c:choose>

                                            <!--Check for first page arrow-->
                                            <c:choose>
                                                <c:when test="${currentPage == 1}">
                                                    <li class="arrow unavailable"><a href="#">&laquo;</a></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <li><a href="Progress?${not empty param.filter ? "filter=".concat(param.filter).concat("&") : ""}{currentPage - 1}">&laquo;</a></li>
                                                    </c:otherwise>
                                                </c:choose>

                                            <c:set var="i" value="1"/>
                                            <c:forEach begin="1" end="${amountOfPages}">
                                                <li ${i == currentPage ? "class=\"current\"" : ""}><a href="Progress?${not empty param.filter ? "filter=".concat(param.filter).concat("&") : ""}page=${i}">${i}</a></li>
                                                    <c:set var="i" value="${i+1}"/>
                                                </c:forEach>

                                            <!--Check for last page arrow-->
                                            <c:choose>
                                                <c:when test="${currentPage == amountOfPages}">
                                                    <li class="arrow unavailable"><a href="#">&raquo;</a></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <li><a href="Progress?${not empty param.filter ? "filter=".concat(param.filter).concat("&") : ""}page=${currentPage + 1}">&raquo;</a></li>
                                                    </c:otherwise>
                                                </c:choose>
                                        </ul>
                                    </div>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="footer.jsp" %>
    </body>
</html>