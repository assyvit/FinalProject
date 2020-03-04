<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 02.02.2020
  Time: 15:14
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
    <title> <fmt:message key="account.title"/></title>
</head>
<body>

<c:set var="pagePath" value="/jsp/client/clientCabinet.jsp" scope="session"/>
<%@ include file="/jsp/static/header.jsp"%>

<h5><a class="badge badge-info" href="${pageContext.request.contextPath}/controller?command=show_catalog">
    <fmt:message key="title.showCleanings"/></a></h5>

<h5> <a class="badge badge-info" href="${pageContext.request.contextPath}/controller?command=show_client_orders">
    <fmt:message key="title.showOrders"/> </a> </h5>


<h5> <a class="badge badge-info" href="${pageContext.request.contextPath}/controller?command=go_client_profile" >
    <fmt:message key="profile.title"/> </a> </h5>

<strong><p class="text-danger"> ${orderError} </p></strong>
<strong><p class="text-danger"> ${blockedUserError} </p></strong>
<strong><p class="text-danger"> ${editProfileError} </p></strong>

<footer class=" fixed-bottom">
    <%@ include file="/jsp/static/footer.jsp" %>
</footer>
</body>
</html>
