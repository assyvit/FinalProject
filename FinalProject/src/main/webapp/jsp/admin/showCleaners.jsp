<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 09.02.2020
  Time: 0:18
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
    <%--    <link rel="stylesheet" href="css/login.css">--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <title><fmt:message key="cleanerList.title"/></title>
</head>
<body>
<c:set var="pagePath" scope="session" value="/controller?command=show_cleaners"/>
<%@ include file="/jsp/static/header.jsp" %>
<c:set var="cleaner" scope="session" value="${cleanerList}"/>
<c:set var="totalCount" scope="session" value="${cleanerList.size()}"/>
<c:out value="${totalcount}"> </c:out>
<c:set var="perPage" scope="session" value="${5}"/>
<c:set var="pageStart" value="${param.start}"/>
<c:if test="${empty pageStart or pageStart < 0}">
    <c:set var="pageStart" value="0"/>
</c:if>
<c:if test="${totalCount < pageStart}">
    <c:set var="pageStart" value="${pageStart - perPage}"/>
</c:if>

<h5><fmt:message key="title.cleaners"/></h5>
<c:choose>
    <c:when test="${not empty cleanerList}">
        <table class="table table-striped">
            <thead>
            <tr>
                <td><b>Id</b></td>
                <td><b><fmt:message key="registration.firstName"/></b></td>
                <td><b><fmt:message key="registration.lastName"/></b></td>
                <td><b><fmt:message key="registration.address"/></b></td>
                <td><b><fmt:message key="registration.telephoneNumber"/></b></td>
                <td><b><fmt:message key="title.status"/></b></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="cleaner" items="${cleanerList}"
                       begin="${pageStart}" end="${pageStart + perPage - 1}">
                <c:set var="classSuccess" value=""/>
                <tr class="${classSuccess}">
                    <td>${fn:escapeXml(cleaner.cleanerId)}</td>
                    <td>${fn:escapeXml(cleaner.firstName)}</td>
                    <td>${fn:escapeXml(cleaner.lastName)}</td>
                    <td>${fn:escapeXml(cleaner.address)}</td>
                    <td>${fn:escapeXml(cleaner.telephoneNumber)}</td>
                    <td><c:choose>
                        <c:when test="${cleaner.isActive == true}">
                            <fmt:message key="block.statusNo"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="block.statusYes"/>
                        </c:otherwise>
                    </c:choose>
                    </td>
                    <td align="center">
                        <c:choose>
                            <c:when test="${cleaner.isActive ==  'true'}">
                                <form method="POST" action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="command" value="change_user_status"/>
                                    <input type="hidden" name="id" value="${cleaner.cleanerId}"/>
                                    <input type="hidden" name="isActive" value="false"/>
                                    <input type="hidden" name="start" value="${param.start}"/>
                                    <input type="submit" class="btn btn-dark"
                                           value="<fmt:message key="title.blockUser"/>"/>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form method="POST" action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="command" value="change_user_status"/>
                                    <input type="hidden" name="id" value="${cleaner.cleanerId}"/>
                                    <input type="hidden" name="isActive" value="false"/>
                                    <input type="hidden" name="start" value="${param.start}"/>
                                    <input type="submit" disabled class="btn btn-dark"
                                           value="<fmt:message key="title.blockUser"/>"/>
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
            No people found matching your search criteria
        </div>
    </c:otherwise>
</c:choose>

<%--<a href="controller?command=show_cleaners&start=${pageStart - perPage}"--%>
<%--   class="btn btn-dark"><fmt:message key="title.previous"/></a>--%>
<%--<a href="controller?command=show_cleaners&start=${pageStart + perPage}"--%>
<%--   class="btn btn-dark"><fmt:message key="title.next"/></a>--%>

<a href="controller?command=show_blocked_cleaner&start=${pageStart - perPage}"><fmt:message key="title.previous"/></a>
${pageStart + 1} - ${pageStart + perPage}
<a href="controller?command=show_cleaners&start=${pageStart + perPage}"><fmt:message key="title.next"/></a>

<strong><p class="text-danger"> ${blockUserError} </p></strong>
<strong><p class="text-success"> ${blockUser} </p></strong>
<br>
<div>
    <a href="${pageContext.request.contextPath}/controller?command=go_to_cabinet" class="btn btn-dark"> <fmt:message
            key="back.toCabinet"/></a>
</div>
<%@ include file="/jsp/static/footer.jsp" %>
</body>
</html>
