<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 09.02.2020
  Time: 21:42
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
    <title><fmt:message key="addCleaner"/></title>
</head>
<body>
<c:set var="pagePath" value="/jsp/addCleaner.jsp" scope="session"/>
<%@ include file="/jsp/static/header.jsp" %>
<div class="container">
    <form class="well form-horizontal" name="registrationCleanerForm"
          method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="add_cleaner"/>
        <fieldset>
            <legend>
                <h5><b><fmt:message key="registration.legend"/></b></h5>
            </legend>
            <div>
                <label class="col-md-6 control-label"><fmt:message key="registration.login"/> <span class="text-danger">*</span></label>
                <div class="form-group col-md-4">
                    <input class="form-control" type="email" name="login" value="${fn:escapeXml(map.login)}"
                           required
                           maxlength="45"
                           minlength="1"
                           placeholder="<fmt:message key="registration.email"/>"/>
                </div>
            </div>
            <strong><p class="text-danger"> ${map.incorrectLogin} </p></strong>
            <strong><p class="text-danger"> ${errorNotUniqLogin} </p></strong>
            <div>
                <label class="col-md-6 control-label"><fmt:message key="registration.password"/> <span
                        class="text-danger">*</span></label>
                <div class="form-group col-md-4">
                    <input class="form-control" type="password" name="password" value="${fn:escapeXml(map.password)}"
                           required
                           maxlength="45"
                           minlength="8"
                           pattern="((?=.*[a-z])(?=.*\d)(?=.*[A-Z])(?=.*[#$%!\-]).{8,45})"
                           placeholder="<fmt:message key="registration.password"/>"/> <br/>
                    <small><fmt:message key="registration.passwordLegend"/> </small>
                </div>
            </div>
            <strong><p class="text-danger"> ${map.incorrectPassword} </p></strong>
            <div>
                <label class="col-md-4 control-label"><fmt:message key="registration.passwordConfirmation"/> <span
                        class="text-danger">*</span></label>
                <div class="form-group col-md-4">
                    <input class="form-control" type="password" name="passwordConfirmation"
                           value="${fn:escapeXml(map.passwordConfirmation)}"
                           required
                           maxlength="45"
                           minlength="8"
                           pattern="((?=.*[a-z])(?=.*\d)(?=.*[A-Z])(?=.*[#$%!\-]).{8,45})"
                           placeholder="<fmt:message key="registration.passwordConfirmation"/>"/>
                </div>
            </div>
            <strong><p class="text-danger"> ${map.passwordMatch} </p></strong>
            <div>
                <label class="col-md-4 control-label"><fmt:message key="registration.firstName"/> <span
                        class="text-danger">*</span></label>
                <div class="form-group col-md-4">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input class="form-control" type="text" name="firstName" value="${fn:escapeXml(map.firstName)}"
                           required
                           maxlength="45"
                           minlength="1"
                           placeholder="<fmt:message key="registration.firstName"/>"/>
                </div>
            </div>
            <strong><p class="text-danger"> ${map.incorrectFirstName} </p></strong>
            <div>
                <label class="col-md-4 control-label"><fmt:message key="registration.lastName"/> <span
                        class="text-danger">*</span></label>
                <div class="form-group col-md-4">
                    <input class="form-control" type="text" name="lastName" value="${fn:escapeXml(map.lastName)}"
                           required
                           maxlength="45"
                           minlength="1"
                           placeholder="<fmt:message key="registration.lastName"/>"/>
                </div>
            </div>
            <strong><p class="text-danger"> ${map.incorrectLastName} </p></strong>
            <div>
                <label class="col-md-4 control-label"><fmt:message key="registration.telephoneNumber"/> <span
                        class="text-danger">*</span></label>
                <div class="form-group col-md-4">
                    <input class="form-control" type="text" name="telephoneNumber"
                           value="${fn:escapeXml(map.telephoneNumber)}"
                           required
                           maxlength="45"
                           minlength="1"
                           placeholder="<fmt:message key="registration.telephoneNumber"/>"/>
                </div>
            </div>
            <strong><p class="text-danger"> ${map.incorrectTelephoneNumber} </p></strong>
            <div>
                <label class="col-md-4 control-label"><fmt:message key="registration.address"/> <span
                        class="text-danger">*</span></label>
                <div class="form-group col-md-4">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input class="form-control" type="text" name="address" value="${fn:escapeXml(map.address)}"
                           required
                           maxlength="45"
                           minlength="1"
                           placeholder="<fmt:message key="registration.address"/>"/>
                </div>
            </div>
            <strong><p class="text-danger"> ${map.incorrectAddress} </p></strong>
        </fieldset>
        <strong><p class="text-danger"> ${errorRegistrationMessage} </p></strong>

        <br/>
        <input type="submit" class="btn btn-dark" value="<fmt:message key="registration.submit"/>"/>
    </form>
</div>

<%@ include file="/jsp/static/footer.jsp" %>

</body>
</html>
