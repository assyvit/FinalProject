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
    <title><fmt:message key="title.main"/></title>

</head>
<body>
<c:set var="pagePath" value="/jsp/main.jsp" scope="session" />
<%@ include file="/jsp/static/header.jsp" %>

<a class="nav-link" href="${pageContext.request.contextPath}/jsp/client/clientCabinet.jsp">
    <fmt:message key="account.title"/></a>
MAIN PAGE

<a class="nav-link" href="${pageContext.request.contextPath}/jsp/cleaner/cleanerCabinet.jsp">
    <fmt:message key="account.title"/></a>

<a class="nav-link" href="${pageContext.request.contextPath}/jsp/admin/adminCabinet.jsp">
    <fmt:message key="account.title"/></a>
<%--<div>--%>
<%--    <a href="${pageContext.request.contextPath}/jsp/registration.jsp" class="btn btn-dark"> <fmt:message--%>
<%--            key="registration.submit"/> </a>--%>
<%--</div>--%>

<%--<div>--%>
<%--    <a href="${pageContext.request.contextPath}/jsp/login.jsp" class="btn btn-dark"> <fmt:message--%>
<%--            key="login.title"/> </a>--%>
<%--</div>--%


<%--<a href="${pageContext.request.contextPath}/jsp/main.jsp"--%>
<%--   class="btn btn-dark"><fmt:message key="button.goMain"/></a>--%>

<%--<a href="controller? command=login" class="client">--%>
<%--    <fmt:message key="login.title"/>--%>
<%--</a>--%>

<%--<a href="controller? command=re" class="client">--%>
<%--    <fmt:message key="registration.title"/>--%>
<%--</a>--%>
<%--<a href="/login.jsp"> Login </a> <br/>--%>
<%--<a href="/registration.jsp"> Sign up</a> <br/>--%>
<footer class=" fixed-bottom">
    <%@ include file="/jsp/static/footer.jsp" %>
</footer>


</body>
</html>
