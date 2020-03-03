<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 08.02.2020
  Time: 18:04
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
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <title><fmt:message key="catalog.title"/></title>
</head>
<body>
<c:set var="pagePath" value="/jsp/showCleaningCatalog.jsp" scope="session"/>
<%@ include file="/jsp/static/header.jsp" %>

<c:set var="currentPage" scope="session" value="/jsp/client/showCleaningCatalog.jsp"/>
<c:set var="cleaner" scope="session" value="${cleaningList}"/>
<c:set var="totalCount" scope="session" value="${cleaningList.size()}"/>
<c:out value="${totalcount}"> </c:out>
<c:set var="perPage" scope="session" value="${5}"/>
<c:set var="pageStart" value="${param.start}"/>
<c:if test="${empty pageStart or pageStart < 0}">
    <c:set var="pageStart" value="0"/>
</c:if>
<c:if test="${totalCount < pageStart}">
    <c:set var="pageStart" value="${pageStart - perPage}"/>
</c:if>
<strong><p class="text-danger"> ${emptyCartError} </p></strong>

<h5><fmt:message key="catalog.title"/></h5>
</form>
<c:out value="${totalSum}"></c:out>
<c:choose>
    <c:when test="${not empty cleaningList}">
        <table class="table table-striped">
            <thead>
            <tr>
                <td><b><fmt:message key="cleaning.name"/></b></td>
                <td><b><fmt:message key="cleaning.price"/></b></td>

                <td><b><fmt:message key="cleaning.type"/></b></td>
                <td><b><fmt:message key="cleaning.description"/></b></td>
                <td><b><fmt:message key="cleaning.quantity"/></b></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="cleaning" items="${cleaningList}"
                       begin="${pageStart}" end="${pageStart + perPage - 1}">
                <c:set var="classSuccess" value=""/>
                <tr class="${classSuccess}">
                    <td>${cleaning.name}</td>
                    <td>${cleaning.price}</td>
                    <td>${cleaning.cleaningType}</td>
                    <td>${cleaning.description}</td>
                    <td>${cleaning.quantity}</td>
                    <td align="center">
                        <c:if test="${user.userRole =='CLIENT'}">
                            <c:choose>
                                <c:when test="${cleaning.isAvailable ==  'true'}">
                                    <form method="POST" action="${pageContext.request.contextPath}/controller">
                                        <input type="hidden" name="command" value="add_to_order_list"/>
                                        <input type="hidden" name="cleaningId" value="${cleaning.id}"/>
                                        <input type="hidden" name="start" value="${param.start}"/>
                                        <input type="hidden" name="quantity" value="1"/>
                                        <input type="submit" class="btn btn-dark"
                                               value="<fmt:message key="button.add"/>"/>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="cleaning.unavailable"/>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <br>
        <div class="alert alert-info">
            <fmt:message key="catalog.unavailable"/>
        </div>
    </c:otherwise>
</c:choose>
<a href="controller?command=show_catalog&start=${pageStart - perPage}"> <fmt:message key="title.previous"/></a>
${pageStart + 1} - ${pageStart + perPage}
<a href="controller?command=show_catalog&start=${pageStart + perPage}"> <fmt:message key="title.next"/></a>

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<%--<footer class=" fixed-bottom ">--%>
<%--    <%@ include file="/jsp/static/footer.jsp" %>--%>
<%--</footer>--%>
</body>
<%@ include file="/jsp/static/footer.jsp" %>
</html>
