<%-- 
    Document   : viewMark
    Created on : Nov 18, 2013, 2:45:16 AM
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
        <title>Evaluate | WIP Camp Applicant Scoring System</title>
        <style>
            table thead tr th {
                text-align: center;
                height: 40px;
                background-color: rgb(231, 231, 231);
            }
            /*table tr td {
                height: 45px;
            }*/
        </style>
    </head>
    <body>
        <section class="main">
            <!-- header -->
            <div class="row">
                <div class="small-12 small-centered columns">
                    <h3>Evaluate</h3>
                    <hr/>
                </div>
            </div>
            <c:choose>
                <c:when test="${empty application}">
                    <!-- If no application -->
                    <div class="row">
                        <div class="small-12 columns">
                            No application found.
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <!-- Question -->
                    <div class="row">
                        <div class="small-12 columns">
                            <!--                            <ul class="no-bullet" style="margin-bottom: 0px;">
                                                            <li style="font-weight: bold;"></li>
                                                        </ul>-->
                            <p style="margin-bottom: 0px; font-weight: bold;">${application.question.question} (${application.question.fullMark} marks)</p>
                            <blockquote style="font-size: small;">${application.answer}</blockquote>
                        </div>
                    </div>

                    <!-- Comments -->
                    <div class="row">
                        <div class="small-12 columns">
                            <table width="100%" class="mark">
                                <thead>
                                    <tr>
                                        <th width="10%">No.</th>
                                        <th width="10%">Mark</th>
                                        <th width="45%">Reason</th>
                                        <th width="20%">Evaluated by</th>
                                        <th width="15%">When</th>
                                    </tr>
                                </thead>
                                <c:choose>
                                    <c:when test="${empty marks}">
                                        <!-- If not receives data from servlet -->
                                        <td style="text-align: center;" colspan="5">No data found.</td>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="count" value="1" scope="page"/>
                                        <c:forEach items="${marks}" var="mark">
                                            <tr style=" ${mark.user.userId == sessionScope.user.userId ? "background-color: lavenderblush" : ""};">
                                                <td style="text-align: center;">${count}</td>
                                                <td style="text-align: center;">${mark.mark}</td>
                                                <td style="text-align: left;">${mark.reason}</td>
                                                <td style="text-align: center;">${mark.user.displayname}</td>
                                                <td style="text-align: center; font-size: smaller"><fmt:formatDate type="date" dateStyle="short" value="${mark.timestamp}" /><br/><fmt:formatDate type="time" timeStyle="short" value="${mark.timestamp}" /></td>
                                            </tr>
                                            <c:set var="count" value="${count+1}"/>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </table>
                        </div>
                    </div>

                    <!-- Form -->
                    <div class="row">
                        <div class="small-12 columns">
                            <form id="markForm">
                                <!--                                <fieldset>-->
                                <div class="row" style="margin-top: 1em;">
                                    <div class="small-3 columns">
                                        <label for="mark" class="right inline">Mark</label>
                                    </div>
                                    <div class="small-7 left columns">
                                        <input type="number" name="mark" id="mark" value="${myMark.mark}" max="${application.question.fullMark}" required/>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="small-3 columns">
                                        <label for="reason" class="right inline">Reason</label>
                                    </div>
                                    <div class="small-7 left columns">
                                        <textarea name="reason" id="reason" rows="5" style="font-size: 0.875em;" required>${myMark.reason}</textarea>
                                    </div>
                                </div>

                                <input type="hidden" name="wipId" id="wipId" value="${param.wipId}"/>
                                <input type="hidden" name="question" id="question" value="${param.question}"/>

                                <div class="row" style="margin-top: 1em;">
                                    <div class="small-12 columns" style="text-align: center;">
                                        <div id="errorBoxM"></div>
                                        <script>
                                            var msg = "${msg}";
                                            if (msg.length > 0) {
                                                document.getElementById("errorBoxM").setAttribute("data-alert", "");
                                                document.getElementById("errorBoxM").setAttribute("class", "alert-box ${msgType == 1 ? "success" : "alert"}");
                                                document.getElementById("errorBoxM").setAttribute("style", "font-size: small; text-align: left;");
                                                document.getElementById("errorBoxM").innerHTML = msg + "<a href class=\"close\">×</a>";
                                            }
                                        </script>
                                        <input type="submit" value="${empty myMark ? "Submit" : "Update"}" class="small button"/>
                                    </div>
                                </div>
                                <!--                                </fieldset>-->
                            </form>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${empty myMark}">
                    <c:set var="servletAction" value="AddMark" scope="page"/>
                </c:when>
                <c:otherwise>
                    <c:set var="servletAction" value="EditMark" scope="page"/>
                </c:otherwise>
            </c:choose>
        </section>
        <!--                <script src="assets/js/foundation.min.js"></script>
                        <script>
                                                    $(document).foundation();
                        </script>-->
        <script>
            $('#errorBoxM').foundation();
        </script>
        <script>
            $(document).ready(function() {
                $('#markForm').unbind('submit').submit(function(e) {
                    e.preventDefault();
                    $.get('${servletAction}',
                            $('#markForm').serialize(),
                            function(data, status, xhr) {
                                $('#markModal').html(data);
                            });

                });
            });
        </script>
    </body>
</html>