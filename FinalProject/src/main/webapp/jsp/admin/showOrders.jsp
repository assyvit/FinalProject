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
    <title><fmt:message key="clientList.title"/></title>
</head>
<body>
<c:set var="pagePath" scope="session" value="/jsp/admin/showOrders.jsp"/>
<%@ include file="/jsp/static/header.jsp" %>

<c:set var="currentPage" scope="session" value="/jsp/admin/showCleaners.jsp"/>
<c:set var="order" scope="session" value="${orderList}"/>
<c:set var="totalCount" scope="session" value="${orderList.size()}"/>
<c:out value="${totalcount}"> </c:out>
<c:set var="perPage" scope="session" value="${5}"/>
<c:set var="pageStart" value="${param.start}"/>
<c:if test="${empty pageStart or pageStart < 0}">
    <c:set var="pageStart" value="0"/>
</c:if>
<c:if test="${totalCount < pageStart}">
    <c:set var="pageStart" value="${pageStart - perPage}"/>
</c:if>
<h5><fmt:message key="order.title"/></h5>
<c:choose>
    <c:when test="${not empty orderList}">
        <table class="table table-striped">
            <thead>
            <tr>
                <td>Id</td>
                <td><fmt:message key="order.cleaning"/></td>
                <td><fmt:message key="orderSum"/></td>
                <td><fmt:message key="order.incomingDate"/></td>
                <td><fmt:message key="order.executeDate"/></td>
                <td><fmt:message key="orderStatus"/></td>
                <td><fmt:message key="order.paymentType"/></td>
                <td><fmt:message key="order.paymentFulfilled"/></td>
                <td><fmt:message key="order.comment"/></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orderList}"
                       begin="${pageStart}" end="${pageStart + perPage - 1}">
                <c:set var="classSuccess" value=""/>
                <tr class="${classSuccess}">
                    <td>${order.id}</td>
                    <td>
                        <c:forEach var="item" items="${order.cleaningList}">
                            ${item.cleaning.name}  ${item.quantity}
                        </c:forEach>
                    </td>
                    <td>${fn:escapeXml(order.orderSum)}</td>
                    <td>${ctg:formatLocalDateTime(order.incomingDate,"yyyy-MM-dd hh:mm:ss" )}</td>
                    <td>${ctg:formatLocalDateTime(order.executeDate,"yyyy-MM-dd hh:mm:ss" )}</td>
                    <td>${fn:escapeXml(order.orderStatus)}</td>
                    <td>${fn:escapeXml(order.paymentType)}</td>
                    <td>${fn:escapeXml(order.paymentFulfilled)}</td>
                    <td>${fn:escapeXml(order.comment)}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <br>
        <div class="alert alert-info">
            No people found matching your search criteria
        </div>
    </c:otherwise>
</c:choose>

<a href="${pageContext.request.contextPath}controller?command=show_orders&start=${pageStart - perPage}"><fmt:message key="title.previous"/></a>
${pageStart + 1} - ${pageStart + perPage}
<a href="${pageContext.request.contextPath}controller?command=show_orders&start=${pageStart + perPage}"><fmt:message key="title.next"/></a>
<br>
<br>
<div>
    <a href="${pageContext.request.contextPath}/controller?command=go_to_cabinet" class="btn btn-dark"> <fmt:message
            key="back.toCabinet"/></a>
</div>
<%@ include file="/jsp/static/footer.jsp" %>
</body>
</html>
