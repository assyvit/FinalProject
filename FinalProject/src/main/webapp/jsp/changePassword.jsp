<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 16.02.2020
  Time: 15:39
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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <title><fmt:message key="changePassword.title"/></title>
</head>
<body>
<c:set var="pagePath" value="/jsp/changePassword.jsp" scope="session"/>
<%@ include file="/jsp/static/header.jsp"%>
<h2><fmt:message key="changePassword.title"/></h2><br>

<form name="passwordChange" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="change_password">

    <div>
        <label class="col-md-6 control-label"><fmt:message key="password.current"/> </label>
        <div class="form-group col-md-4">
            <input class="form-control" type="password" name="currentPassword" value="${fn:escapeXml(currentPassword)}"
                   required
                   pattern="((?=.*[a-z])(?=.*\d)(?=.*[A-Z])(?=.*[#$%!\-]).{8,45})"
                   maxlength="45"
                   minlength="8"
                   placeholder="<fmt:message key="password.current"/>"/>
        </div>
    </div >
    <div class="form-group col-md-4">
        <small><fmt:message key="registration.passwordLegend"/> </small>
    </div>

    <div>
        <label class="col-md-6 control-label"><fmt:message key="password.new"/> </label>
        <div class="form-group col-md-4">
            <input class="form-control" type="password" name="newPassword" value="${fn:escapeXml(newPassword)}"
                   required
                   pattern="((?=.*[a-z])(?=.*\d)(?=.*[A-Z])(?=.*[#$%!\-]).{8,45})"
                   maxlength="45"
                   minlength="8"
                   placeholder="<fmt:message key="password.new"/>"/>
        </div>
    </div>
    <div>
        <label class="col-md-6 control-label"><fmt:message key="password.newConfirm"/> </label>
        <div class="form-group col-md-4">
            <input class="form-control" type="password" name="passwordConfirmation"
                   value="${fn:escapeXml(passwordConfirmation)}"
                   required
                   pattern="((?=.*[a-z])(?=.*\d)(?=.*[A-Z])(?=.*[#$%!\-]).{8,45})"
                   maxlength="45"
                   min="8"
                   placeholder="<fmt:message key="password.newConfirm"/>"/>
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-4 control-label"></label>
        <div class="col-md-4"><br>

            <input type="submit" class="btn btn-dark" value="<fmt:message key="changePassword.title"/>"/>
        </div>
    </div>
</form>
<strong><p class="text-danger"> ${currentPasswordError} </p></strong>
<strong><p class="text-danger"> ${changePasswordError} </p></strong>
<strong><p class="text-danger"> ${passwordMatch} </p></strong>
<strong><p class="text-danger"> ${changeSuccess} </p></strong>

<div>
    <a href="${pageContext.request.contextPath}/controller?command=go_to_cabinet" class="btn btn-dark"> <fmt:message
            key="back.toCabinet"/></a>
</div>


    <%@ include file="/jsp/static/footer.jsp" %>

</body>
</html>
