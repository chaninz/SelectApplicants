<%-- 
    Document   : summary
    Created on : Nov 21, 2013, 12:47:27 AM
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
        <title>Summary | WIP Camp Applicant Scoring System</title>
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
                    <h1>Summary (${summarySize})</h1>
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
                            <a class="tiny secondary radius button" style="margin-right: 1em; padding: 0.22222rem 0.44444rem 0.33333rem;" href="ExportSummary?${not empty param.filter ? "filter=".concat(param.filter).concat("&") : ""}">Export to xls</a>
                            <dl class="right sub-nav">
                                <dt>Filter:</dt>
                                <dd ${empty param.filter or param.filter == "all" ? "class=\"active\"" : ""}><a href="Summary">All</a></dd>
                                <dd ${param.filter == "pass" ? "class=\"active\"" : ""}><a href="Summary?filter=pass">Pass</a></dd>
                                <dd ${param.filter == "alternate" ? "class=\"active\"" : ""}><a href="Summary?filter=alternate">Alternate</a></dd>
                                <dd ${param.filter == "fail" ? "class=\"active\"" : ""}><a href="Summary?filter=fail">Fail</a></dd>
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
                                        <th width="13%">Result</th>
                                        <th width="52%">Reason</th>
                                        <th width="15%">Marks (${sumFullMark})</th>
                                    </tr>
                                </thead>
                                <!-- Loop for shows summary -->
                                <c:choose>
                                    <c:when test="${not empty summaryList}">
                                        <c:set var="count" value="1" />
                                        <c:forEach items="${summaryList}" var="summary">
                                            <tr style="height: 30px;">
                                                <td style="text-align: center;">${count}</td>
                                                <td style="text-align: center;"><a href="ViewApplicant?wipId=${summary.wipId}">${summary.wipId}</a></td>
                                                    <c:choose>
                                                        <c:when test="${summary.overview.isPass == 1}">
                                                        <td style="/*background-color: lightgreen;*/ text-align: center;"><span class="success label">Pass</span></td>
                                                    </c:when>
                                                    <c:when test="${summary.overview.isPass == 2}">
                                                        <td style="/*background-color: lightcyan;*/ text-align: center;"><span class="secondary label">Alternate</span></td>
                                                    </c:when>
                                                    <c:when test="${summary.overview.isPass == 3}">
                                                        <td style="/*background-color: lightcoral;*/ text-align: center;"><span class="alert label">Fail</span></td>
                                                    </c:when>
                                                </c:choose>
                <!--                                <td style="">${summary.overview.isPass}</td>-->
                                                <td style="text-align: left;">${summary.overview.reason}</td>
                                                <td style="text-align: center;"><fmt:formatNumber type="number" pattern="0.00" maxFractionDigits="2" value="${summary.sumMark}" /></td>

<!--                                <td style="text-align: center;">${user.type == 1 ? "<span class=\"success label\">Admin</span>" : "<span class=\"secondary label\">User</span>"}</td>-->
                                            </tr>
                                            <c:set var="count" value="${count+1}"/>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <td colspan="5" style="text-align: center;">No data found.</td>
                                    </c:otherwise>
                                </c:choose>
                            </table>

                            <!--pagination-->
                            <c:choose>
                                <c:when test="${not empty summaryList}">
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
                                                    <li><a href="Summary?${not empty param.filter ? "filter=".concat(param.filter).concat("&") : ""}{currentPage - 1}">&laquo;</a></li>
                                                    </c:otherwise>
                                                </c:choose>

                                            <c:set var="i" value="1"/>
                                            <c:forEach begin="1" end="${amountOfPages}">
                                                <li ${i == currentPage ? "class=\"current\"" : ""}><a href="Summary?${not empty param.filter ? "filter=".concat(param.filter).concat("&") : ""}page=${i}">${i}</a></li>
                                                    <c:set var="i" value="${i+1}"/>
                                                </c:forEach>

                                            <!--Check for last page arrow-->
                                            <c:choose>
                                                <c:when test="${currentPage == amountOfPages}">
                                                    <li class="arrow unavailable"><a href="#">&raquo;</a></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <li><a href="Summary?${not empty param.filter ? "filter=".concat(param.filter).concat("&") : ""}page=${currentPage + 1}">&raquo;</a></li>
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