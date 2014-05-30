<%-- 
    Document   : addQuestion
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
        <title>Add New Question</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <section class="main">
            <div class="row">
                <div class="small-12 columns">
                    <h1>Add New Question</h1>
                </div>
            </div>
            <!-- Breadcrumbs -->
            <div class="row" style="margin-bottom: 1em;">
                <div class="small-12 columns">
                    <nav class="breadcrumbs">
                        <a href="Home"><i class="fa fa-home"></i> Home</a>
                        <a class="unavailable">Admin</a>
                        <a href="ViewQuestions">Questions</a>
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
                    <form action="AddQuestion" method="post">
                        <fieldset>
                            <legend>Add New Question</legend>
                            <input type="hidden" name="pageNo" value="1"/>
                            <div class="row" style="margin-top: 1em;">
                                <div class="small-3 columns">
                                    <label for="questionId" class="right inline">Question No.</label>
                                </div>
                                <div class="small-6 left columns">
                                    <input type="number" id="questionId" name="questionId" value="${param.questionId}" required/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="small-3 columns">
                                    <label for="fullMark" class="right inline">Mark</label>
                                </div>
                                <div class="small-6 left columns">
                                    <input type="number" id="fullMark" name="fullMark" value="${param.fullMark}" min="1" required/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="small-3 columns">
                                    <label for="question" class="right inline">Question</label>
                                </div>
                                <div class="small-6 left columns">
                                    <textarea name="question" id="question" style="font-size: 0.875em;" required>${requestScope.question}</textarea>
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
