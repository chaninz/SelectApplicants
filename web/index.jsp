<%-- 
    Document   : index
    Created on : Oct 28, 2013, 12:03:18 AM
    Author     : CHANINZ
--%>

<%@page import="com.sa.model.Applicant"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.jasper.tagplugins.jstl.ForEach"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--Check if has object of user in session.
If user is not log-in, forward to log-in page.-->
<c:choose>
    <c:when test="${user == null}" >
        <c:redirect url="Login"/>
    </c:when>
    <c:otherwise>
        <c:redirect url="Home"/>
    </c:otherwise>
</c:choose>