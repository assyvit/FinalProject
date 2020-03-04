<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 02.02.2020
  Time: 15:15
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
    <title><fmt:message key="account.title"/></title>
</head>
<body>
<c:set var="pagePath" scope="session" value="/jsp/admin/adminCabinet.jsp"/>
<%@ include file="/jsp/static/header.jsp" %>

<strong><p class="text-danger"> ${showCleanersError} </p></strong>
<strong><p class="text-danger"> ${showClientsError} </p></strong>
<strong><p class="text-danger"> ${showOrdersError} </p></strong>


<h5><a  class="badge badge-info" href="${pageContext.request.contextPath}/controller?command=show_clients">
    <fmt:message key="title.showClients"/></a></h5>
<h5><a class="badge badge-info" href="${pageContext.request.contextPath}/controller?command=show_cleaners">
    <fmt:message key="title.showCleaners"/></a></h5>
<h5><a class="badge badge-info" href="${pageContext.request.contextPath}/controller?command=show_orders">
    <fmt:message key="title.showOrders"/></a></h5>
<h5> <a class="badge badge-info" href="${pageContext.request.contextPath}/controller?command=show_blocked_clients">
    <fmt:message key="title.showClientsBlocked"/></a></h5>
<h5><a class="badge badge-info" href="${pageContext.request.contextPath}/controller?command=show_blocked_cleaners">
    <fmt:message key="title.showCleanersBlocked"/></a></h5>
<h5><a class="badge badge-info" href="${pageContext.request.contextPath}/controller?command=show_catalog">
    <fmt:message key="title.showCleanings"/></a></h5>
<h5><a class="badge badge-info" href="${pageContext.request.contextPath}/controller?command=go_to_change_password">
    <fmt:message key="changePassword.title"/> </a></h5>

<br>
<br>
<br>

<footer class=" fixed-bottom">
            <%@ include file="/jsp/static/footer.jsp" %>
</footer>
<%--        </div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</nav>--%>

</body>
</html>
