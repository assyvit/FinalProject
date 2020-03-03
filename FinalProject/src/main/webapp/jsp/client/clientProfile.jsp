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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <title><fmt:message key="profile.title"/></title>
</head>
<body>
<c:set var="pagePath" value="/jsp/client/clientProfile.jsp" scope="session"/>
<%@ include file="/jsp/static/header.jsp" %>
<div class="row">
    <div class="col">
        <form id="editCleanerProfile" method="post" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="edit_client_profile"/>
            <div class="form-group">
                <fieldset>
                    <legend><fmt:message key="profile.title"/></legend>
                    <div class="form-group col-md-6">
                        <label for="idLabel">Id</label>
                        <input type="text" class="form-control" id="idLabel" name="id"
                               value="${fn:escapeXml(user.userId)}"
                               maxlength="45"
                               readonly>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="idLogin"><fmt:message key="registration.login"/></label>
                        <input type="text" class="form-control" id="idLogin" name="login"
                               value="${fn:escapeXml(user.login)}"
                               maxlength="45"
                               readonly>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="idFirstName"><fmt:message key="registration.firstName"/></label>
                        <input type="text" class="form-control" id="idFirstName" name="firstName"
                               value="${fn:escapeXml(userProfile.firstName)}"
                               maxlength="45"
                               minlength="1"
                               required>
                    </div>
                    <strong><p class="text-danger"> ${map.incorrectFirstName} </p></strong>
                    <div class="form-group col-md-6">
                        <label for="idLastName"><fmt:message key="registration.lastName"/></label>
                        <input type="text" class="form-control" id="idLastName" name="lastName"
                               value="${fn:escapeXml(userProfile.lastName)}"
                               maxlength="45"
                               minlength="1"
                               required>
                    </div>
                    <strong><p class="text-danger"> ${map.incorrectLastName} </p></strong>
                    <div class="form-group col-md-6">
                        <label for="idAddress"><fmt:message key="registration.address"/></label>
                        <input type="text" class="form-control" id="idAddress" name="address"
                               value="${fn:escapeXml(userProfile.address)}"
                               maxlength="45"
                               minlength="1"
                               required>
                    </div>
                    <strong><p class="text-danger"> ${map.incorrectAddress} </p></strong>
                    <div class="form-group col-md-6">
                        <label for="idTelephone"><fmt:message key="registration.telephoneNumber"/></label>
                        <input type="text" class="form-control" id="idTelephone" name="telephoneNumber"
                               value="${fn:escapeXml(userProfile.telephoneNumber)}"
                               maxlength="45"
                               minlength="1"
                               required>
                    </div>
                </fieldset>
            </div>
            <strong><p class="text-danger"> ${map.incorrectTelephoneNumber} </p></strong>
            <input id="formSubmit" class="btn btn-dark" type="submit" name="Submit" value=" <fmt:message key="save"/> "
                   class="dpl-btn"/><br/>
        </form>
        <strong><p class="text-danger"> ${editProfileError} </p></strong>
        <br>
        <div>
            <a class="btn btn-dark" href="${pageContext.request.contextPath}/controller?command=go_to_change_password">
                <fmt:message key="changePassword.title"/>
        </div>

            <br>
        <div>
            <a href="${pageContext.request.contextPath}/controller?command=go_to_cabinet" class="btn btn-dark"> <fmt:message
                    key="back.toCabinet"/></a>
        </div>

    </div>
    <div class="col">
    <div>
        <h5><fmt:message key="upload.title"/></h5>
        <form method="post" action="${pageContext.request.contextPath}/Upload" enctype="multipart/form-data">
            <fmt:message key="upload.legend"/>
            <input type="file" name="uploadFile"/>
            <br/><br/>
            <input type="submit" value="<fmt:message key="upload.button"/>"/>
        </form>
        <strong><p class="text-success"> ${uploadResult} </p></strong>
        <strong><p class="text-danger"> ${uploadFail} </p></strong>
    </div>
    </div>
    <br>
   </div>

<%@ include file="/jsp/static/footer.jsp" %>

</body>
</html>
