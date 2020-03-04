<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 10.02.2020
  Time: 0:04
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
    <title><fmt:message key="mail.title"/></title>
</head>

<body>
<c:set var="pagePath" scope="session" value="/jsp/admin/sendEmail.jsp"/>
<%@ include file="/jsp/static/header.jsp" %>

<div class="container">

<form action="controller" method="POST">
    <input type="hidden" name="command" value="send_email"/>
    <br/>
    <table>
        <tr>
            <td><fmt:message key="mail.to"/></td>
            <td><input type="text" name="mail"/><br/></td>
        </tr>
        <tr>
           <td><fmt:message key="mail.subject"/></td>
            <td><input type="text" name="subject"><br/></td>
        </tr>
        <tr>

            <td><textarea rows="12" cols="80" name="message">${fn:escapeXml(message)}</textarea><br/></td>
        </tr>
        <tr>
            <td><input type="submit" class="btn btn-dark" value="<fmt:message key="mail.send"/>"></td>
            <td><input type="reset"  class="btn btn-dark" value="<fmt:message key="mail.reset"/>"></td>
        </tr>
    </table>
</form>
</div>
<br/>
<%@ include file="/jsp/static/footer.jsp"%>
</body>

</html>
