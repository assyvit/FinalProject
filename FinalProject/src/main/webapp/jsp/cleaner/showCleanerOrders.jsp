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
<c:set var="pagePath" scope="session" value="/jsp/cleaner/showCleanerOrders.jsp"/>
<%@ include file="/jsp/static/header.jsp" %>

${showOrdersError}
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
<c:choose>
    <c:when test="${not empty orderList}">
        <table class="table table-striped">
            <thead>
            <tr>
                <td><b>Id</b></td>
                <td><b><fmt:message key="order.cleaning"/></b></td>
                <td><b><fmt:message key="orderSum"/></b></td>
                <td><b><fmt:message key="order.incomingDate"/></b></td>
                <td><b><fmt:message key="order.executeDate"/></b></td>
                <td><b><fmt:message key="orderStatus"/></b></td>
                <td><b><fmt:message key="order.paymentType"/></b></td>
                <td><b><fmt:message key="order.paymentFulfilled"/></b></td>
                <td><b><fmt:message key="order.comment"/></b></td>
                <td><b><fmt:message key="title.clientsInfo"/></b></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orderList}"
                       begin="${pageStart}" end="${pageStart + perPage - 1}">
                <c:set var="classSuccess" value=""/>
                <tr class="${classSuccess}">
                    <td>${fn:escapeXml(order.id)}</td>
                    <td>
                        <c:forEach var="item" items="${order.cleaningList}">
                            ${fn:escapeXml(item.cleaning.name)}  ${fn:escapeXml(item.quantity)}
                        </c:forEach>
                    </td>
                    <td>${fn:escapeXml(order.orderSum)}</td>
                    <td>${fn:escapeXml(ctg:formatLocalDateTime(order.incomingDate,"yyyy-MM-dd hh:mm:ss" ))}</td>
                    <td>${fn:escapeXml(ctg:formatLocalDateTime(order.executeDate,"yyyy-MM-dd hh:mm" ))}</td>
                    <td>${fn:escapeXml(order.orderStatus)}</td>
                    <td>${fn:escapeXml(order.paymentType)}</td>
                    <td>${fn:escapeXml(order.paymentFulfilled)}
                        <c:if test="${order.orderStatus.toString() == 'PROCESSED'}">
                            <form method="POST" action="${pageContext.request.contextPath}/controller">
                                <input type="hidden" name="command" value="confirm_payment"/>
                                <input type="hidden" name="start" value="${param.start}"/>
                                <input type="hidden" name="orderId" value="${order.id}"/>
                                <input type="submit" class="btn btn-dark"
                                       value="<fmt:message key="order.confirmPayment"/>"/>
                            </form>
                        </c:if>
                    </td>
                    <td>${fn:escapeXml(order.comment)}</td>
                    <td>
                            ${fn:escapeXml(order.client.firstName)}
                            ${fn:escapeXml(order.client.lastName)}
                            ${fn:escapeXml(order.client.address)}
                            ${fn:escapeXml(order.client.telephoneNumber)}
                    </td>
                    <td><c:choose>
                        <c:when test="${order.orderStatus == 'NEW'}">
                            <form method="POST" action="${pageContext.request.contextPath}/controller">
                                <input type="hidden" name="command" value="change_order_status"/>
                                <input type="hidden" name="orderId" value="${order.id}"/>
                                <input type="hidden" name="start" value="${param.start}"/>
                                <input type="hidden" name="orderStatus" value="cancelled"/>
                                <input type="submit" class="btn btn-dark" value="<fmt:message key="order.cancel"/>"/>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form method="POST" action="${pageContext.request.contextPath}/controller">
                                <input type="hidden" name="command" value="change_order_status"/>
                                <input type="hidden" name="start" value="${param.start}"/>
                                <input type="hidden" name="orderId" value="${order.id}"/>
                                <input type="submit" class="btn btn-dark" value="<fmt:message key="order.cancel"/>"
                                       disabled/>
                            </form>
                        </c:otherwise>
                    </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${order.orderStatus ==  'NEW'}">
                                <form method="POST" action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="command" value="change_order_status"/>
                                    <input type="hidden" name="orderId" value="${order.id}"/>
                                    <input type="hidden" name="start" value="${param.start}"/>
                                    <input type="hidden" name="orderStatus" value="processed"/>
                                    <input type="submit" class="btn btn-dark"
                                           value="<fmt:message key="order.confirm"/>"/>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form method="POST" action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="command" value="change_order_status"/>
                                    <input type="hidden" name="start" value="${param.start}"/>
                                    <input type="hidden" name="orderId" value="${order.id}"/>
                                    <input type="submit" class="btn btn-dark" value="<fmt:message key="order.confirm"/>"
                                           disabled/>
                                </form>
                            </c:otherwise>
                        </c:choose></td>

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

<%--<a href="controller?command=show_cleaner_orders&start=${pageStart - perPage}"--%>
<%--   class="btn btn-dark">Previous</a>--%>
<%--<a href="controller?command=show_cleaner_orders&start=${pageStart + perPage}"--%>
<%--   class="btn btn-dark">Next</a>--%>


<a href="controller?command=show_cleaner_orders&start=${pageStart - perPage}"><fmt:message key="title.previous"/></a>
${pageStart + 1} - ${pageStart + perPage}
<a href="controller?command=show_cleaner_orders&start=${pageStart + perPage}"><fmt:message key="title.next"/></a>
</form>
<br>
<div>
    <a href="${pageContext.request.contextPath}/controller?command=go_to_cabinet" class="btn btn-dark"> <fmt:message
            key="back.toCabinet"/></a>
</div>

<br>
<%@ include file="/jsp/static/footer.jsp" %>

</body>
</html>
