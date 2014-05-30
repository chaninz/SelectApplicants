<%-- 
    Document   : viewOverview
    Created on : Nov 11, 2013, 10:31:16 PM
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
        <title>View Applicant | WIP Camp Applicant Scoring System</title>
        <style>
            table thead tr th {
                text-align: center;
                /*height: 40px;
                background-color: rgb(231, 231, 231);*/
            }
            /*table tr td {
                height: 45px;
            }*/
        </style>
    </head>
    <body>
        <section class="main">
            <div class="row">
                <div class="small-12 small-centered columns">
                    <h3>Overview</h3>
                    <hr/>
                </div>
            </div>
            <div class="row">
                <div class="small-12 columns">
                    <form id="overview">
                        <div class="row">
                            <div class="small-12 columns">
                                <label for="wipId">WIP ID</label>
                                <input type="text" name="wipId" id="wipId" value="${empty overview ? param.wipId : overview.wipId}" readonly/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="small-12 columns">
                                <label for="isPass">Evaluate</label>
                                <select id="isPass" name="isPass" required>
                                    <option value="1" ${overview.isPass == 1 ? "selected" : ""}>Pass</option>
                                    <option value="2" ${overview.isPass == 2 ? "selected" : ""}>Alternate</option>
                                    <option value="3" ${overview.isPass == 3 ? "selected" : ""}>Fail</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="small-12 columns">
                                <label for="reason">Reason</label>
                                <textarea name="reason" id="reason" rows="5" style="font-size: 0.875em;" required>${overview.reason}</textarea>
                                <c:choose>
                                    <c:when test="${not empty overview}">
                                        <span style="font-size: small;">Last updated by ${overview.user.displayname} at <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${overview.timestamp1}" /></span>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 1em;">
                            <div class="small-12 columns" style="text-align: center;">
                                <div id="errorBoxO"></div>
                                <script>
                                    var msg = "${msg}";
                                    if (msg.length > 0) {
                                        document.getElementById("errorBoxO").setAttribute("data-alert", "");
                                        document.getElementById("errorBoxO").setAttribute("class", "alert-box ${msgType == 1 ? "success" : "alert"}");
                                        document.getElementById("errorBoxO").setAttribute("style", "font-size: small; text-align: left;");
                                        document.getElementById("errorBoxO").innerHTML = msg + "<a href class=\"close\">×</a>";
                                    }
                                </script>
                                <input type="submit" value="${empty overview ? "Submit" : "Update"}" class="small button"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <c:choose>
                <c:when test="${empty overview}">
                    <c:set var="servletAction" value="AddOverview" scope="page"/>
                </c:when>
                <c:otherwise>
                    <c:set var="servletAction" value="EditOverview" scope="page"/>
                </c:otherwise>
            </c:choose>

        </section>
        <script>
            $('#errorBoxO').foundation();
        </script>
        <script>
            $(document).ready(function() {
                $('#overview').unbind('submit').submit(function(e) {
                    e.preventDefault();
                    $.get('${servletAction}',
                            $('#overview').serialize(),
                            function(data, status, xhr) {
                                $('#overviewModal').html(data);
                            });

                });
            });
        </script>
    </body>
</html>

