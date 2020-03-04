<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 23.02.2020
  Time: 2:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="messages"/>
<%--<c:set var="language" value="${not empty param.language ? param.language--%>
<%-- : not empty language ? language : pageContext.request.locale}" scope="session" />--%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <title><fmt:message key="cleaning.edit"/></title>
</head>
<body>
<c:set var="pagePath" value="jsp/cleaner/cleaningProfile.jsp" scope="session"/>
<%@ include file="/jsp/static/header.jsp" %>


<div class="container">
    <form class="well form-horizontal" name="edit_cleaning"
          method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="edit_cleaning"/>
        <fieldset>
            <legend>
                <h5><b><p><fmt:message key="cleaning.edit"/></b></h5>
            </legend>
            <div>
                <label class="col-md-6 control-label"><fmt:message key="cleaning.name"/> <span
                        class="text-danger">*</span></label>
                <div class="form-group col-md-4">
                    <input class="form-control" type="text" name="name" value="${fn:escapeXml(cleaning.name)}"
                           required
                           maxlength="45"
                           minlength="1"
                           placeholder="<fmt:message key="cleaning.name"/>"/>
                </div>
            </div>
            <strong><p class="text-danger"> ${map.incorrectName} </p></strong>
            <div>
                <label class="col-md-6 control-label"><fmt:message key="cleaning.price"/> <span
                        class="text-danger">*</span></label>
                <div class="form-group col-md-4">
                    <input class="form-control" type="number" name="price" value="${fn:escapeXml(cleaning.price)}"
                           required
                           maxlength="12"
                           min="0"
                           step="0.1"
                           placeholder="<fmt:message key="cleaning.price"/>"/>
                </div>
            </div>
            <strong><p class="text-danger"> ${map.incorrectPrice} </p></strong>
            <div>
                <label class="col-md-6 control-label"><fmt:message key="cleaning.quantity"/><span
                        class="text-danger">*</span></label>
                <div class="form-group col-md-4">
                    <input class="form-control" type="number" name="quantity"
                           value="${fn:escapeXml(cleaning.quantity)}"
                           required
                           maxlength="3"
                           min="1"
                           step="1"
                           placeholder="<fmt:message key="cleaning.quantity" />"/>
                </div>
            </div>
            <strong><p class="text-danger"> ${map.incorrectQuantity} </p></strong>
            <div>
                <label class="col-md-6 control-label" for=<fmt:message key="cleaning.type"/>>
                    <fmt:message key="cleaning.type"/><span class="text-danger">*</span></label>
                <div class="form-group col-md-4">
                    <select class="form-control" id="type" name="type" required>
                        <option disabled><fmt:message key="cleaning.choose"/></option>
                        <option value="CLEANING"><fmt:message key="type.cleaning"/></option>
                        <option value="CARPET_CLEANING"><fmt:message key="type.carpet"/></option>
                        <option value="LEATHER_CLEANING"><fmt:message key="type.leather"/></option>
                        <option value="CURTAIN_CLEANING"><fmt:message key="type.curtain"/></option>
                        <option value="MATTRESS_CLEANING"><fmt:message key="type.mattress"/></option>
                        <option value="WASHING"><fmt:message key="type.washing"/></option>
                        <option value="OTHER"><fmt:message key="type.other"/></option>
                    </select>
                </div>
            </div>
            <strong><p class="text-danger"> ${incorrectType} </p></strong>
            <div>
                <label class="col-md-6 control-label"><fmt:message key="cleaning.description"/><span
                        class="text-danger">*</span></label>
                <div class="form-group col-md-4">
                              <textarea id="description" name="description" cols="80" rows="7"
                                        placeholder="<fmt:message key="cleaning.description"/>" required
                                        minlength="1"
                                        maxlength="500">
                                  ${fn:escapeXml(cleaning.description)}</textarea>
                </div>
            </div>
            <strong><p class="text-danger"> ${incorrectDescription} </p></strong>

            <input type="submit" class="btn btn-dark" value="<fmt:message key="save"/> ">

            <strong><p class="text-danger"> ${cleaningEditError} </p></strong>
            <strong><p class="text-danger"> ${errorValidateCleaning} </p></strong>
            <strong><p class="text-danger"> ${textError} </p></strong>

        </fieldset>
    </form>

    <a class="btn btn-dark"
           href="${pageContext.request.contextPath}/controller?command=show_cleaner_cleanings&start=${start}">
        <fmt:message key="button.backToCleanings"/> </a>
</div>


<%@ include file="/jsp/static/footer.jsp" %>

</body>
</html>
