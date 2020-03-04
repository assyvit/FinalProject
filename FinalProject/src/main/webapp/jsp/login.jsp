<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 24.01.2020
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
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
      <title><fmt:message key="login.title"/></title>
</head>
<body>
<c:set var="pagePath" value="/jsp/login.jsp" scope="session"/>
<%@ include file="/jsp/static/header.jsp" %>
<br/>
<div class="container">
    <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="login"/>
        <fieldset>
            <legend>
                <h5><b></b></h5>
            </legend>
            <div>
                <label class="col-md-6 control-label"><fmt:message key="login.title"/> </label>
                <div class="form-group col-md-4">
                    <input class="form-control" type="email" name="login" value="${fn:escapeXml(login)}"
                           required
                           maxlength="45"
                           min="1"
                           placeholder="<fmt:message key="login.placeholder"/>"/>
                </div>
            </div>
            <div>
                <label class="col-md-6 control-label"><fmt:message key="login.password"/></label>
                <div class="form-group col-md-4">
                    <input class="form-control" type="password" name="password" value="${fn:escapeXml(password)}"
                           required
                           maxlength="45"
                           min="8"
                           placeholder="<fmt:message key="login.password" />"/>
                </div>
            </div>
                     <input type="submit" class="btn btn-dark" value="<fmt:message key="login.submit"/>"/>
        </fieldset>
    </form>

    <strong><p class="text-danger"> ${blockedUserError} </p></strong>
    <strong><p class="text-danger"> ${fn:escapeXml(loginError)} </p></strong>
    <strong><p class="text-danger">  ${wrongAction} </p></strong>
    <strong><p class="text-danger">  ${nullPage} </p></strong>
    <strong><p class="text-danger"> ${recoverSuccess} </p></strong>

    <a href="${pageContext.request.contextPath}/jsp/registration.jsp"> <fmt:message
            key="registration.account"/> </a>
    <br>
    <a href="${pageContext.request.contextPath}/jsp/recoverPassword.jsp"><fmt:message
            key="password.forgot"/></a>
</div>




<footer class=" fixed-bottom">
    <%@ include file="/jsp/static/footer.jsp" %>
</footer>
</body>
</html>
