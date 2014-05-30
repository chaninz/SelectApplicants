<%-- 
    Document   : viewApplicant
    Created on : Nov 3, 2013, 8:16:06 PM
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
        <title>View Applicant | WIP Camp Applicant Scoring System</title>
        <style>
            table thead tr th {
                text-align: center;
                height: 40px;
                background-color: rgb(231, 231, 231);
            }
            .a-info tr {
                height: 30px;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <section class="main">
            <div class="row">
                <div class="small-12 columns">
                    <h1>View Applicant</h1>
                </div>
            </div>

            <!-- Breadcrumbs -->
            <div class="row" style="margin-bottom: 1em;">
                <div class="small-12 columns">
                    <nav class="breadcrumbs">
                        <a href="Home"><i class="fa fa-home"></i> Home</a>
                        <a class="unavailable">Applicants</a>
                        <a class="current" href="#">WIP${applicant.wipId}</a>
                    </nav>
                </div>
            </div>
            <!-- End Breadcrumbs -->

            <div class="row">
                <!-- Profile -->
                <div class="small-8 columns">
                    <div class="row">
                        <div class="small-12 columns">
                            <ul class="no-bullet">
                                <li><h4>WIP${applicant.wipId} ${applicant.firstnameTh}  ${applicant.lastnameTh}  (น้อง${applicant.nickname})</h4></li>
                                <!--                                <li><ul>
                                                                        <li><b>WIP ID :</b> ${applicant.wipId}</li>
                                                                        <li><b>เพศ :</b> ${applicant.gender}</li>
                                                                        <li><b>วันเกิด :</b> ${applicant.dob}</li>
                                                                        <li><b>ศาสนา :</b> ${applicant.religion}</li>
                                                                        <li><b>โทรศัพท์ :</b> ${applicant.tel}</li>
                                                                        <li><b>โทรศัพท์มือถือ :</b> ${applicant.mobileTel}</li>
                                                                        <li><b>อีเมล์ :</b> ${applicant.email}</li>
                                                                        <li><b>โรงเรียน :</b> ${applicant.schoolName}</li>
                                                                        <li><b>แผนการเรียน :</b> ${applicant.educationPlan}</li>
                                                                        <li><b>เกรดเฉลี่ยสะสม :</b> ${applicant.gpax}</li>
                                                                        <li><b>โรคประจำตัว :</b> ${applicant.congenitalDisease}</li>
                                                                        <li><b>แพ้ยา :</b> ${applicant.allergicMedicine}</li>
                                                                        <li><b>แพ้อาหาร :</b> ${applicant.allergicFood}</li>
                                                                        <li><b>แพ้สิ่งของ :</b> ${applicant.allergicThing}</li>
                                                                        <li><b>ความสามารถด้านคอมพิวเตอร์ :</b> ${applicant.computerSkill}</li>
                                                                    </ul></li>-->
                            </ul>
                            <table class="a-info" style="width: 100%; border: none;">
                                <tr>
                                    <td style="width: 20%; font-weight: bold; text-align: right">เพศ</td>
                                    <td style="width: 30%;">${applicant.gender}</td>
                                    <td style="width: 20%; font-weight: bold; text-align: right">โรงเรียน</td>
                                    <td style="width: 30%;">${applicant.schoolName}</td>
                                </tr>
                                <tr>
                                    <td style="font-weight: bold; text-align: right">วันเกิด</td>
                                    <td>${applicant.dob}</td>
                                    <td style="font-weight: bold; text-align: right">ระดับชั้น</td>
                                    <td>${applicant.educationLevel}</td>
                                </tr>
                                <tr>
                                    <td style="font-weight: bold; text-align: right">ศาสนา</td>
                                    <td>${applicant.religion}</td>
                                    <td style="font-weight: bold; text-align: right">แผนการเรียน</td>
                                    <td>${applicant.educationPlan}</td>
                                </tr>
                                <tr>
                                    <td style="font-weight: bold; text-align: right">โทรศัพท์</td>
                                    <td>${applicant.tel}</td>
                                    <td style="font-weight: bold; text-align: right">เกรดเฉลี่ยสะสม</td>
                                    <td>${applicant.gpax}</td>
                                </tr>
                                <tr>
                                    <td style="font-weight: bold; text-align: right">โทรศัพท์มือถือ</td>
                                    <td colspan="3">${applicant.mobileTel}</td>
                                </tr>
                                <tr>
                                    <td style="font-weight: bold; text-align: right">อีเมล์</td>
                                    <td colspan="3">${applicant.email}</td>
                                </tr>
                                <tr>
                                    <td style="font-weight: bold; text-align: right">โรคประจำตัว</td>
                                    <td colspan="3">${applicant.congenitalDisease}</td>
                                </tr>
                                <tr>
                                    <td style="font-weight: bold; text-align: right">แพ้ยา</td>
                                    <td colspan="3">${applicant.allergicMedicine}</td>
                                </tr>
                                <tr>
                                    <td style="font-weight: bold; text-align: right">แพ้อาหาร</td>
                                    <td colspan="3">${applicant.allergicFood}</td>
                                </tr>
                                <tr>
                                    <td style="font-weight: bold; text-align: right">แพ้สิ่งของ</td>
                                    <td colspan="3">${applicant.allergicThing}</td>
                                </tr>
                                <tr>
                                    <td style="font-weight: bold; text-align: right">กิจกรรมที่เคยร่วม</td>
                                    <td colspan="3" style="vertical-align: text-top;">${applicant.activities}</td>
                                </tr>
                                <tr>
                                    <td style="font-weight: bold; text-align: right">ความสามารถด้านคอมพิวเตอร์</td>
                                    <td colspan="3" style="vertical-align: text-top;">${applicant.computerSkill}</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <!-- End Profile -->  
                    <div class="row">
                        <div class="small-12 columns">
                            <table style="margin-left: auto; margin-right: auto; width: 80%;">
                                <thead>
                                    <tr style="text-align: center; height: 40px; background-color: rgb(231, 231, 231);">
                                        <th style="width: 10%;">No.</th>
                                        <th style="width: 70%;">Question</th>
                                        <th style="width: 20%;">Average Mark</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:choose>
                                        <c:when test="${not empty questions}">
                                            <c:set var="i" value="1" scope="page"/>
                                            <c:set var="sum" value="0" scope="page"/>
                                            <c:forEach items="${questions}" var="question">
                                                <tr style="height: 40px;">
                                                    <td style="text-align: center">${question.questionId}</td>
                                                    <td>${question.question}</td>
                                                    <c:choose>
                                                        <c:when test="${not empty ms[question.questionId].avgMark}">
                                                            <td style="text-align: center"><fmt:formatNumber type="number" pattern="0.00" maxFractionDigits="2" value="${ms[question.questionId].avgMark}"/></td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td style="text-align: center">-</td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </tr>
                                                <c:set var="i" value="${i+1}" scope="page"/>
                                                <c:set var="totalMark" value="${totalMark + question.fullMark}" />
                                                <c:set var="totalAvgMark" value="${totalAvgMark + ms[question.questionId].avgMark}" />
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                                <td colspan="3" style="text-align: center;">No data found.</td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                    <tr style="height: 40px; background-color: rgb(231, 231, 231); font-weight: bold">
                                        <td colspan="2" style="text-align: right">Total (${not empty totalMark ? totalMark : "-" })</td>
                                        <td style="text-align: center"><fmt:formatNumber type="number" pattern="0.00" maxFractionDigits="2" value="${totalAvgMark}" /></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>


                <div class="small-4 columns">
                    <div class="row">
                        <div class="small-12 columns" style="text-align: center">
                            <img src="${applicant.imgThumb}" style="padding: 5px; border: #b3b3b3 solid 1px;"/>
                        </div>
                    </div>

                    <div class="row" style="margin-top: 1em;">
                        <div class="small-7 small-centered columns panel callout">
                            <ul class="side-nav" style="padding: 0px;">
                                <c:choose>
                                    <c:when test="${user.type == 2 || user.type == 1}">
                                        <li><a href="ViewOverview?wipId=${applicant.wipId}" data-reveal-id="overviewModal" data-reveal-ajax="true"><i class="fa fa-eye"></i> Overview</a></li>
                                        <li class="divider"></li>
                                        </c:when>
                                    </c:choose>
                                    <c:forEach items="${questions}" var="question">
                                    <li><a href="ViewMark?wipId=${applicant.wipId}&question=${question.questionId}" data-reveal-id="markModal" data-reveal-ajax="true"><i class="fa fa-question"></i> Question #${question.questionId}</a></li>
                                    </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div id="overviewModal" class="reveal-modal small" data-reveal>
            </div> 
            <div id="markModal" class="reveal-modal medium" data-reveal>
            </div> 
        </section>
        <%@include file="footer.jsp" %>
    </body>
</html>
