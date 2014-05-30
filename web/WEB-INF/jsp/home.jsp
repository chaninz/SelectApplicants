<%-- 
    Document   : home
    Created on : Nov 5, 2013, 12:58:33 AM
    Author     : CHANINZ
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html class="no-js" lang="en" >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="includeAssets.jsp" %>
        <title>Home | WIP Camp Applicant Scoring System</title>
        <style>
            table thead tr th {
                text-align: center;
                height: 40px;
                background-color: rgb(231, 231, 231);
            }
            table tr td {
                height: 40px;
                max-height: 45px;
            }
            tr:hover {
                background-color: whitesmoke !important;
            }

            tr:hover td {
                background-color: transparent !important;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <section class="main">
            <div class="row">
                <div class="small-12 columns">
                    <h1>All Applicants (${appListSize})</h1>
                </div>
            </div>
            <div class="row">
                <div class="small-12 columns">
                    <!-- Filter -->
                    <dl class="right sub-nav" style="margin-bottom: 0px;">
                        <dt>Filter:</dt>
                        <dd ${empty param.filter or param.filter == "all" ? "class=\"active\"" : ""}><a href="Home">All</a></dd> 
                        <dd ${param.filter == "checked" ? "class=\"active\"" : ""}><a href="Home?filter=checked">Checked</a></dd>
                        <dd ${param.filter == "unchecked" ? "class=\"active\"" : ""}><a href="Home?filter=unchecked">Unchecked</a></dd>
                    </dl>
                    <!-- End Filter -->
                    <table width="100%">
                        <thead>
                            <tr>
                                <th width="10%">WIP ID</th>
                                <th width="25%">Name - Surname</th>
                                <th width="10%">Nickname</th>
                                <th width="5%">Gender</th>
                                <th width="10%">Grade</th>
                                <th width="30%">School</th>
                                <th width="10%">Status</th>
                            </tr>
                        </thead>
                        <tbody class="applicant-row">
                            <c:choose>
                                <c:when test="${not empty appList}">
                                    <c:set var="count" value="1" />
                                    <c:forEach items="${appList}" var="applicant">
                                        <tr style="height: 30px; " onclick="window.location = 'ViewApplicant?wipId=${applicant.wipId}';">
                                            <td style="text-align: center;">${applicant.wipId}</td>
                                            <td>${applicant.firstnameTh}  ${applicant.lastnameTh}</td>
                                            <td style="text-align: center;">${applicant.nickname}</td>
                                            <td style="text-align: center;">${applicant.gender}</td>
                                            <td style="text-align: center;">${applicant.educationLevel}</td>
                                            <td>${applicant.schoolName}</td>
                                            <c:choose>
                                                <c:when test="${applicant.status == 1}">
                                                    <td style="text-align: center;"><span class="success label">Checked</span></td>
                                                </c:when>
                                                <c:when test="${applicant.status == 2}">
                                                    <td style="text-align: center;"><span class="alert label">Unchecked</span></td>
                                                </c:when>
                                            </c:choose>
                                            <!--                                <td style="text-align: center;">
                                                                                <a href="ViewApplicant?wipId=${applicant.wipId}" title="ดูข้อมูล"><i class="fa fa-file-text-o"></i></a>
                                                                            </td>-->
                                        </tr>
                                        <c:set var="count" value="${count+1}"/>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="7" style="text-align: center;">No data found.</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>

                        </tbody>
                    </table>

                    <!--pagination-->
                    <c:choose>
                        <c:when test="${not empty appList}">
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
                                            <li><a href="Home?${not empty param.filter ? "filter=".concat(param.filter).concat("&") : ""}page=${currentPage - 1}">&laquo;</a></li>
                                            </c:otherwise>
                                        </c:choose>

                                    <c:set var="i" value="1"/>
                                    <c:forEach begin="1" end="${amountOfPages}">
                                        <li ${i == currentPage ? "class=\"current\"" : ""}><a href="Home?${not empty param.filter ? "filter=".concat(param.filter).concat("&") : ""}page=${i}">${i}</a></li>
                                            <c:set var="i" value="${i+1}"/>
                                        </c:forEach>

                                    <!--Check for last page arrow-->
                                    <c:choose>
                                        <c:when test="${currentPage == amountOfPages}">
                                            <li class="arrow unavailable"><a href="#">&raquo;</a></li>
                                            </c:when>
                                            <c:otherwise>
                                            <li><a href="Home?${not empty param.filter ? "filter=".concat(param.filter).concat("&") : ""}page=${currentPage + 1}">&raquo;</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                </ul>
                            </div>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </section>
        <%@include file="footer.jsp" %>
    </body>
</html>
