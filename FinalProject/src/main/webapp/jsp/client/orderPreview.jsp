<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 10.02.2020
  Time: 22:57
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
    <title><fmt:message key="cleaningList.tittle"/></title>
</head>
<body>
<c:set var="pagePath" scope="session" value="/jsp/client/orderPreview.jsp"/>
<%@ include file="/jsp/static/header.jsp" %>


<h1><fmt:message key="cleaningList.tittle"/></h1>
<c:choose>
    <c:when test="${not empty cleaningItemList}">
        <table class="table table-striped">
            <thead>
            <tr>
                <td><b>Id</b></td>
                <td><b><fmt:message key="cleaning.name"/></b></td>
                <td><b><fmt:message key="cleaning.price"/></b></td>
                <td><b><fmt:message key="cleaning.type"/></b></td>
                <td><b><fmt:message key="cleaning.description"/></b></td>
                <td><b>Cleaner ID</b></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${cleaningItemList}">
                <c:set var="classSuccess" value=""/>
                <tr class="${classSuccess}">
                    <td>${fn:escapeXml(item.cleaning.id)}</td>
                    <td>${fn:escapeXml(item.cleaning.name)}</td>
                    <td>${fn:escapeXml(item.cleaning.price)}</td>
                    <td>${fn:escapeXml(item.cleaning.cleaningType)}</td>
                    <td>${fn:escapeXml(item.cleaning.description)}</td>
                    <td>${fn:escapeXml(item.cleaning.cleanerId)}</td>
                    <td>
                        <form method="POST" action="controller">
                            <input type="hidden" name="command" value="add_to_order_list"/>
                            <input type="hidden" name="cleaningId" value="${item.cleaning.id}"/>
                            <input type="number" name="quantity" value="${item.quantity}" min="1" max="100"/>
                            <input type="submit" class="btn btn-dark" value="<fmt:message key="button.add"/>"/>
                        </form>
                    </td>

                    <td align="center">
                        <form method="POST" action="controller">
                            <input type="hidden" name="command" value="remove_from_order_list"/>
                            <input type="hidden" name="cleaningId" value="${item.cleaning.id}"/>
                            <input type="submit" class="btn btn-dark" value="<fmt:message key="button.delete"/>"/>
                        </form>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </c:when>
    <c:otherwise>
        <br>
        <div class="alert alert-info">
                ${message.showCleaningError}
        </div>
    </c:otherwise>
</c:choose>
<div class="order_total">
    <div class="order_total_content text-md-right">
        <div class="order_total_title"><b><fmt:message key="orderSum"/> : "${totalSum}"</b></div>
        <div class="order_total_amount"><b><fmt:message key="total.cleanings"/> : "${cleaningItemList.size()}"</b></div>
    </div>
</div>
<%--<c:out value="${totalSum}"></c:out>--%>
<%--<c:out value="${clientCleaningList.size()}"></c:out>--%>
<c:choose>
    <c:when test="${totalSum > 0}">
        <a href="${pageContext.request.contextPath}/controller?command=clear_order_list" class="btn btn-dark"> <fmt:message key="title.clear"/> </a>
        <a href="${pageContext.request.contextPath}/controller?command=go_to_order" class="btn btn-dark"> <fmt:message key="title.checkout"/> </a>
        <a href="${pageContext.request.contextPath}/controller?command=show_catalog" class="btn btn-dark"> <fmt:message key="cleaning.catalogBack"/> </a>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/controller?command=show_catalog" class="btn btn-dark"> <fmt:message key="cleaning.catalogBack"/> </a>
    </c:otherwise>
</c:choose>
<br>
<br>
<br>
<br>
<br>

<%@ include file="/jsp/static/footer.jsp" %>

</body>
</html>
