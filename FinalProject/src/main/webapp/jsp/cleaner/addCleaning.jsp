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
    <title><fmt:message key="cleaning.tittle"/></title>
</head>
<body>
<c:set var="pagePath" scope="session" value="/jsp/cleaner/addCleaning.jsp"/>
<%@ include file="/jsp/static/header.jsp" %>
<div class="container">
    <form class="well form-horizontal" name="addCleaning"
          method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="add_cleaning"/>
        <fieldset>
            <legend>
                <h5><b><p><fmt:message key="registration.legend"/></b></h5>
            </legend>
            <div>
                <label class="col-md-6 control-label"><fmt:message key="cleaning.name"/> <span
                        class="text-danger">*</span></label>
                <div class="form-group col-md-4">
                    <input class="form-control" type="text" name="name" value="${fn:escapeXml(map.name)}"
                           required maxlength="45" minlength="1"
                           placeholder="<fmt:message key="cleaning.name"/>"/>
                </div>
            </div>
            <strong><p class="text-danger"> ${map.incorrectName} </p></strong>
            <div>
                <label class="col-md-6 control-label"><fmt:message key="cleaning.price"/> <span
                        class="text-danger">*</span></label>
                <div class="form-group col-md-4">
                    <input class="form-control" type="number" name="price" value="${fn:escapeXml(map.price)}"
                           required maxlength="12" min="0" step="0.1"
                           placeholder="<fmt:message key="cleaning.price"/>"/>
                </div>
            </div>
            <strong><p class="text-danger"> ${map.incorrectPrice} </p></strong>
            <div>
                <label class="col-md-6 control-label"><fmt:message key="cleaning.quantity"/><span
                        class="text-danger">*</span></label>
                <div class="form-group col-md-4">
                    <input class="form-control" type="number" name="quantity" value="${fn:escapeXml(map.quantity)}"
                           required maxlength="3" min="1" step="1"
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
                              <textarea id="description" name="description" cols="100" rows="10"
                                        placeholder="<fmt:message key="cleaning.description"/>"
                                        required minlength="0" maxlength="500">${fn:escapeXml(map.description)}
                                </textarea>
                </div>
            </div>
            <strong><p class="text-danger"> ${incorrectDescription} </p></strong>
            <br>
            <input type="submit" class="btn btn-dark" value="<fmt:message key="cleaning.submit"/> ">

            <strong><p class="text-danger"> ${errorAddCleaning} </p></strong>
            <strong><p class="text-danger"> ${errorValidateCleaning} </p></strong>

        </fieldset>
    </form>
    <br>
    <div>
        <a href="${pageContext.request.contextPath}/controller?command=go_to_cabinet" class="btn btn-dark"> <fmt:message
                key="back.toCabinet"/></a>
    </div>
</div>

    <%@ include file="/jsp/static/footer.jsp" %>

</body>
</html>
