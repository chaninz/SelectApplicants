<%-- 
    Document   : exportSummary
    Created on : Dec 6, 2013, 1:36:38 AM
    Author     : CHANINZ
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "inline; filename=WIP_Summary.xls");
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>WIP ID</th>
                    <th>Result</th>
                    <th>Reason</th>
                    <th>Marks (${sumFullMark})</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Nickname</th>
                    <th>School</th>
                    <th>Mobile</th>
                    <th>Religious</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="count" value="1" />
                <c:set var="pass" value="0" />
                <c:set var="alternate" value="0" />
                <c:set var="fail" value="0" />
                <c:forEach items="${summaryList}" var="summary">
                    <tr>
                        <td style="text-align: center;">${count}</td>
                        <td style="text-align: center;">${summary.wipId}</td>
                        <c:choose>
                            <c:when test="${summary.overview.isPass == 1}">
                                <td style="background-color: lightgreen; text-align: center;">Pass</td>
                                <c:set var="pass" value="${pass+1}" />
                            </c:when>
                            <c:when test="${summary.overview.isPass == 2}">
                                <td style="background-color: lightcyan; text-align: center;">Alternate</td>
                                <c:set var="alternate" value="${alternate+1}" />
                            </c:when>
                            <c:when test="${summary.overview.isPass == 3}">
                                <td style="background-color: lightcoral; text-align: center;">Fail</td>
                                <c:set var="fail" value="${fail+1}" />
                            </c:when>
                        </c:choose>
                        <td>${summary.overview.reason}</td>
                        <td style="text-align: right;"><fmt:formatNumber type="number" pattern="0.00" maxFractionDigits="2" value="${summary.sumMark}" /></td>
                        <td>${summary.applicant.firstnameTh}</td>
                        <td>${summary.applicant.lastnameTh}</td>
                        <td>${summary.applicant.nickname}</td>
                        <td>${summary.applicant.schoolName}</td>
                        <td>${summary.applicant.mobileTel}</td>
                        <td>${summary.applicant.religion}</td>
                    </tr>
                    <c:set var="count" value="${count+1}"/>
                </c:forEach>
                <tr>
                    <td colspan="2" style="text-align: right; font-weight: bold; background-color: gainsboro;">Pass</td>
                    <td>${not empty pass ? pass : "-"}</td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: right; font-weight: bold; background-color: gainsboro;">Alternate</td>
                    <td>${not empty fail ? alternate : "-"}</td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: right; font-weight: bold; background-color: gainsboro;">Fail</td>
                    <td>${not empty fail ? fail : "-"}</td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: right; font-weight: bold; background-color: gainsboro;">Total</td>
                    <td>${summaryList.size()}</td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
</body>
</html>