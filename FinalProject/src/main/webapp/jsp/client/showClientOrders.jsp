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
    <title><fmt:message key="ordersList.title"/></title>
</head>
<body>
<%--<c:set var="pagePath" value="/jsp/client/showClientOrders.jsp" scope="session"/>--%>
<c:set var="pagePath" value="/controller?command=show_client_orders" scope="session"/>
<%@ include file="/jsp/static/header.jsp" %>

${orderCancelError}
${showOrdersError}
<c:set var="order" scope="session" value="${clientOrderList}"/>
<c:set var="totalCount" scope="session" value="${clientOrderList.size()}"/>
<c:out value="${totalcount}"> </c:out>
<c:set var="perPage" scope="session" value="${5}"/>
<c:set var="pageStart" value="${param.start}"/>
<c:if test="${empty pageStart or pageStart < 0}">
    <c:set var="pageStart" value="0"/>
</c:if>
<c:if test="${totalCount < pageStart}">
    <c:set var="pageStart" value="${pageStart - perPage}"/>
</c:if>
<c:choose>
    <c:when test="${not empty clientOrderList}">
        <table class="table table-striped">
            <thead>
            <tr>
                <td><b>№</b></td>
                <td><b><fmt:message key="order.cleaning"/></b></td>
                <td><b><fmt:message key="orderSum"/></b></td>
                <td><b><fmt:message key="order.incomingDate"/></b></td>
                <td><b><fmt:message key="order.executeDate"/></b></td>
                <td><b><fmt:message key="orderStatus"/></b></td>
                <td><b><fmt:message key="order.paymentType"/></b></td>
                <td><b><fmt:message key="order.paymentFulfilled"/></b></td>
                <td><b><fmt:message key="order.comment"/></b></td>
                <td><b><fmt:message key="title.cleanersInfo"/></b></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${clientOrderList}"
                       begin="${pageStart}" end="${pageStart + perPage - 1}">
                <c:set var="classSuccess" value=""/>
                <tr class="${classSuccess}">
                    <td>${order.id}</td>
                    <td>
                        <c:forEach var="item" items="${order.cleaningList}">
                            ${item.cleaning.name}  ${item.quantity}
                        </c:forEach>
                    </td>
                    <td>${order.orderSum}</td>
                    <td>${ctg:formatLocalDateTime(order.incomingDate,"yyyy-MM-dd hh:mm:ss" )}</td>
                    <td>${ctg:formatLocalDateTime(order.executeDate,"yyyy-MM-dd hh:mm:ss" )}</td>
                    <td>${order.orderStatus}</td>
                    <td>${order.paymentType}</td>
                    <td>${order.paymentFulfilled}</td>
                    <td>${order.comment}</td>
                    <td>
                            ${fn:escapeXml(order.cleaner.firstName)}
                            ${fn:escapeXml(order.cleaner.lastName)}
                            ${fn:escapeXml(order.cleaner.address)}
                            ${fn:escapeXml(order.cleaner.telephoneNumber)}
                    </td>
                    <td>
                        <c:choose>

                            <c:when test="${order.orderStatus ==  'NEW'}">
                                <form method="POST" action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="command" value="cancel_order"/>
                                    <input type="hidden" name="orderId" value="${order.id}"/>
                                    <input type="hidden" name="start" value="${param.start}"/>
                                    <input type="submit" class="btn btn-dark" value="<fmt:message key="order.cancel"/>"/>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form method="POST" action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="command" value="cancel_order"/>
                                    <input type="hidden" name="start" value="${param.start}"/>
                                    <input type="hidden" name="orderId" value="${order.id}"/>
                                    <input type="submit" class="btn btn-dark" value="<fmt:message key="order.cancel"/>" disabled/>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <br>
        <div class="alert alert-info">
            No orders found
        </div>
    </c:otherwise>
</c:choose>


<a href="controller?command=show_client_orders&start=${pageStart - perPage}"><fmt:message key="title.previous"/></a>
${pageStart + 1} - ${pageStart + perPage}
<a href="controller?command=show_client_orders&start=${pageStart + perPage}"><fmt:message key="title.next"/></a>
</form>
<br>

<div>
    <a href="${pageContext.request.contextPath}/controller?command=go_to_cabinet" class="btn btn-dark"> <fmt:message
            key="back.toCabinet"/></a>
</div>


    <%@ include file="/jsp/static/footer.jsp" %>

</body>
</html>
