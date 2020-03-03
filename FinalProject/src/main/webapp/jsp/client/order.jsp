<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 09.02.2020
  Time: 1:05
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
    <%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>--%>
    <%--    <script src="https://cdn.jsdelivr.net/momentjs/2.14.1/moment.min.js"></script>--%>
    <%--    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>--%>

    <%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>--%>
    <%--    <link rel="stylesheet"--%>
    <%--          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css">--%>
    <%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">--%>
    <%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>--%>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.13.0/moment.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css"
          rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
    <title><fmt:message key="order.checkout"/></title>
</head>

<body>
<c:set var="pagePath" value="/jsp/client/order.jsp" scope="session"/>
<%--<%@ include file="/jsp/static/header.jsp"%>--%>
<nav class="navbar navbar-inverse">

</nav>
<div class="container">
    <h5><fmt:message key="order.confirm"/></h5>
    <br>
    <form method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="confirm_order"/>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group col-md-4">
                    <label for="idAddress"><fmt:message key="registration.address"/></label>
                    <input type="text" class="form-control" id="idAddress" name="address"
                           value="${fn:escapeXml(userProfile.address)}"
                           maxlength="45"
                           minlength="1"
                           readonly>
                </div>
                <div class="form-group col-md-4">
                    <label for="idTelephone"><fmt:message key="registration.telephoneNumber"/></label>
                    <input type="text" class="form-control" id="idTelephone" name="telephoneNumber"
                           value="${fn:escapeXml(userProfile.telephoneNumber)}"
                           maxlength="45"
                           minlength="1"
                           readonly>
                </div>
                <div class="form-group col-md-4">
                    <label for="idTelephone"><fmt:message key="orderSum"/></label>
                    <input type="text" class="form-control" id="totalSum" name="totalSum"
                           value="${fn:escapeXml(totalSum)}"
                           readonly
                           maxlength="45"
                           minlength="1"
                           required>
                </div>
            </div>
        </div>
        <h5><fmt:message key="order.paymentType"/></h5>
        <div>
            <div class="radio-inline">
                <label class="radio-inline" for="inlineRadio1">
                    <input class="radio-inline" type="radio" name="paymentType" id="inlineRadio1" value="card">
                    <fmt:message key="payment.card"/></label>
            </div>
            <div class="radio-inline">
                <label class="radio-inline" for="inlineRadio2">
                    <input class="radio-inline" type="radio" name="paymentType" id="inlineRadio2"
                           value="cash" required>
                    <fmt:message key="payment.cash"/></label>
            </div>
            <div class="radio-inline">
                <label class="radio-inline" for="inlineRadio3">
                    <input class="radio-inline" type="radio" name="paymentType" id="inlineRadio3"
                           value="bank_transfer">
                    <fmt:message key="payment.bank"/></label>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group col-md-4">
                    <label class="control-label"> <fmt:message key="order.comment"/></label> <br>
                    <textarea name="comment" rows="10" cols="70" maxlength="500"
                              placeholder="<fmt:message key="order.commentPlaceholder"/>">
                        ${fn:escapeXml(comment)}
                    </textarea>
                </div>
            </div>
            <div class='col-md-4'>
                <div class="form-group">
                    <label class="control-label"><fmt:message key="order.dayTime"/></label>
                    <div class='input-group date' id='datetimepicker5'>
                        <input type='text' name="executing_date" class="form-control"/>
                        <span class="input-group-addon">
                     <span class="glyphicon glyphicon-calendar"></span>
                     </span>
                    </div>
                </div>
            </div>
        </div>
        <input type="submit" class="btn" value="<fmt:message key="order.send"/>">
    </form>

    <div>
        <div>
            <a href="${pageContext.request.contextPath}/controller?command=go_to_cabinet" class="btn btn-dark"> <fmt:message
                    key="back.toCabinet"/></a>
        </div>

    </div>
    <strong><p class="text-danger"> ${map.paymentTypeError} </p></strong>
    <strong><p class="text-danger"> ${map.dateError} </p></strong>
    <strong><p class="text-danger"> ${map.textError} </p></strong>
    <strong><p class="text-danger"> ${orderError} </p></strong>


    <script>

        $(function () {

            $('#datetimepicker5').datetimepicker(
                {
                    format: 'YYYY-MM-DD HH:mm',
                    minDate: 'now',
                    disabledHours: [0, 1, 2, 3, 4, 5, 6, 22, 23, 24],
                    enabledHours: [7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 18, 19, 20, 21]
                }
            );

        });
    </script>
    <br>
    <br>


</div>
<footer class="navbar navbar-inverse navbar-fixed-bottom">
    <div class="footer-copyright text-center py-3">
        <a class="text-muted">© 2020 Copyright:Cleaning.com</a>
    </div>
</footer>

</body>
</html>
