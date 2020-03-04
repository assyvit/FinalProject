<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 28.01.2020
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <title><fmt:message key="title.main"/></title>
</head>
<body>
<c:set var="pagePath" value="/jsp/main.jsp" scope="session" />
<%@ include file="/jsp/static/header.jsp" %>

<%--<a class="nav-link" href="${pageContext.request.contextPath}/jsp/client/clientCabinet.jsp">--%>
<%--    <fmt:message key="account.title"/></a>--%>
<%--MAIN PAGE--%>

<%--<a class="nav-link" href="${pageContext.request.contextPath}/jsp/cleaner/cleanerCabinet.jsp">--%>
<%--    <fmt:message key="account.title"/></a>--%>

<%--<a class="nav-link" href="${pageContext.request.contextPath}/jsp/admin/adminCabinet.jsp">--%>
<%--    <fmt:message key="account.title"/></a>--%>

<footer class=" fixed-bottom">
    <%@ include file="/jsp/static/footer.jsp" %>
</footer>


</body>
</html>
