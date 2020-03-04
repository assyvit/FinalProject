<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 22.02.2020
  Time: 23:41
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
    <title><fmt:message key="cleaner.cleanings"/></title>
</head>
<body>
<%--<c:set var="pagePath" value="/jsp/cleaner/showCleanerCleaning.jsp" scope="session"/>--%>
<c:set var="pagePath" value="/controller?command=show_cleaner_orders" scope="session"/>
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

<strong><p class="text-danger"> ${showCleaningError} </p></strong>
<strong><p class="text-danger"> ${cleaningEditError} </p></strong>

</form>
<c:out value="${totalSum}"></c:out>
<c:choose>
    <c:when test="${not empty cleaningList}">
        <table class="table table-striped">
            <thead>
            <tr>
                    <%--                <td><b>Id</b></td>--%>
                <td><b><fmt:message key="cleaning.name"/></b></td>
                <td><b><fmt:message key="cleaning.price"/></b></td>
                <td><b><fmt:message key="cleaning.type"/></b></td>
                <td><b><fmt:message key="cleaning.description"/></b></td>
                <td><b><fmt:message key="cleaning.quantity"/></b></td>
                <td><b><fmt:message key="cleaning.isAvailable"/></b></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="cleaning" items="${cleaningList}"
                       begin="${pageStart}" end="${pageStart + perPage - 1}">
                <c:set var="classSuccess" value=""/>
                <tr class="${classSuccess}">
                        <%--                    <td>${cleaning.id}</td>--%>
                    <td>${cleaning.name}</td>
                    <td>${cleaning.price}</td>
                    <td>${cleaning.cleaningType}</td>
                    <td>${cleaning.description}</td>
                    <td>${cleaning.quantity}</td>
                    <td><c:choose>
                        <c:when test="${cleaning.isAvailable == true}">
                            <fmt:message key="title.available"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="title.notAvailable"/>
                        </c:otherwise>
                    </c:choose>
                    </td>
                    <td align="center">
                        <c:choose>
                            <c:when test="${cleaning.isAvailable ==  'true'}">
                                <form method="POST" action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="command" value="change_cleaning_status"/>
                                    <input type="hidden" name="cleaningId" value="${cleaning.id}"/>
                                    <input type="hidden" name="availableStatus" value="fasle"/>
                                    <input type="hidden" name="start" value="${param.start}"/>
                                    <input type="submit" class="btn btn-dark"
                                           value="<fmt:message key="cleaning.block"/>"/>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form method="POST" action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="command" value="change_cleaning_status"/>
                                    <input type="hidden" name="cleaningId" value="${cleaning.id}"/>
                                    <input type="hidden" name="availableStatus" value="true"/>
                                    <input type="hidden" name="start" value="${param.start}"/>
                                    <input type="submit" class="btn btn-dark"
                                           value="<fmt:message key="cleaning.unlock"/>"/>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td align="center">
                        <form method="POST" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="go_cleaning_profile"/>
                            <input type="hidden" name="cleaningId" value="${cleaning.id}"/>
                            <input type="hidden" name="cleanerId" value="${user.userId}"/>
                            <input type="hidden" name="start" value="${param.start}"/>
                            <input type="submit" class="btn btn-dark" value="<fmt:message key="cleaning.edit"/>"/>
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
            No cleaning found matching your search criteria
        </div>
    </c:otherwise>
</c:choose>
<a href="controller?command=show_cleaner_cleanings&start=${pageStart - perPage}"><fmt:message key="title.previous"/></a>
${pageStart + 1} - ${pageStart + perPage}
<a href="controller?command=show_cleaner_cleanings&start=${pageStart + perPage}"><fmt:message key="title.next"/></a>


<br>
<div>
    <a href="${pageContext.request.contextPath}/controller?command=go_to_cabinet" class="btn btn-dark"> <fmt:message
            key="back.toCabinet"/></a>
</div>

<%@ include file="/jsp/static/footer.jsp" %>

</body>
</html>
