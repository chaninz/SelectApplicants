<%-- 
    Document   : uploadApplication
    Created on : Nov 4, 2013, 2:22:28 PM
    Author     : CHANINZ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="includeAssets.jsp" %>
        <title>Upload Application | WIP Camp Applicant Scoring System</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <section class="main">
            <div class="row">
                <div class="small-8 small-centered columns">
                    <h1>Upload Application</h1>
                    <hr/>
                </div>
            </div>
            <div class="row">
                <div class="small-6 columns">
                    <form action="UploadApplication" method="post" enctype="multipart/form-data" class="custom">
                        <label for="wipId">WIP ID</label>
                        <input type="number" id="wipId" name="wipId" value="${param.wipId}"/>
                        <label for="page">Page</label>
                        <input type="number" id="page" name="page" value="${param.page}"/>
                        <label for="application">Application Form</label>
                        <input type="file" id="application" name="application" />
                        <input type="submit" value="Upload" class="small button"/>
                    </form>
                </div>
            </div>
        </section>
        <%@include file="footer.jsp" %>
    </body>
</html>
