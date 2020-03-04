<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 09.02.2020
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>

<%--<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />--%>
<%--<fmt:setLocale value="${language}" scope="session"/>--%>
<%--<fmt:setLocale value="${locale}" scope="session"/>--%>
<fmt:setBundle basename="messages"/>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
      <title>Header</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <h5 class="navbar-brand">My Precious</h5>
<%--        <a c href="#"></a>--%>
<%--        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--            <span class="navbar-toggler-icon"></span>--%>
<%--        </button>--%>
    <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2" id="navbarNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=go_to_main">
                    <fmt:message key="title.main"/> </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=show_catalog">
                    <fmt:message key="catalog.title"/></a>
            </li>
            <c:if test="${ not empty user}">
                <div>
                    <a href="${pageContext.request.contextPath}/controller?command=go_to_cabinet" class="btn btn-dark"> <fmt:message
                            key="account.title"/></a>
                </div>

                <%--                <c:choose>--%>
<%--                    <c:when test="${user.userRole == 'ADMIN'}">--%>
<%--                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/admin/adminCabinet.jsp">--%>
<%--                            <fmt:message key="account.title"/></a>--%>
<%--                    </c:when>--%>
<%--                    <c:when test="${user.userRole == 'CLIENT'}">--%>
<%--                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/client/clientCabinet.jsp">--%>
<%--                            <fmt:message key="account.title"/></a>--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/cleaner/cleanerCabinet.jsp">--%>
<%--                            <fmt:message key="account.title"/></a>--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>
            </c:if>
        </ul>
    </div>

    <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
        <ul class="navbar-nav ml-auto">
    <c:if test="${empty user}">
        <div aria-flowto="right">
            <span class="badge badge-pill badge-danger"><ctg:role role="${ user.userRole }"/></span>
        </div>
    </c:if>
    <c:if test="${ user.userRole == 'ADMIN' }">
        <div aria-flowto="right">
            <span class="badge badge-pill badge-warning"><ctg:role role="${ user.userRole }"/></span>
        </div>

    </c:if>
    <c:if test="${ user.userRole == 'CLIENT' }">
        <div aria-flowto="right">
            <span class="badge badge-pill badge-success"><ctg:role role="${ user.userRole }"/></span>
        </div>
    </c:if>
    <c:if test="${ user.userRole == 'CLEANER' }">
        <div aria-flowto="right">
            <span class="badge badge-pill badge-primary"><ctg:role role="${ user.userRole }"/></span>
        </div>
    </c:if>

    <li class="nav-item">
                             <a class="nav-link"
                   href="${pageContext.session.servletContext.contextPath}/controller?command=change_language&newLanguage=en_En">
                    <i>
                        <span class="badge badge-pill badge-secondary">EN</span>
                    </i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link"
                   href="${pageContext.session.servletContext.contextPath}/controller?command=change_language&newLanguage=ru_RU">
                    <i>
                        <span class="badge badge-pill badge-secondary">RU</span>
                    </i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link"
                   href="${pageContext.session.servletContext.contextPath}/controller?command=change_language&newLanguage=de_DE">
                    <i>
                        <span class="badge badge-pill badge-secondary">DE</span>
                    </i>
                </a>
            </li>

            <%--                        <li class="nav-item"><a   title="English"><span>EN</span></a></li>--%>
            <%--            <li class="nav-item"><a--%>

            <%--                    title="France"><span>RU</span></a></li>--%>
            <%--            <li class="nav-item"><a--%>

            <%--                    title="Nederlands"><span>DE</span></a></li>--%>


            <%--                            <li class="nav-item active">--%>
            <%--                                <form method="POST" action="${pageContext.request.contextPath}/controller">--%>
            <%--                                    <input type="hidden" name="command" value="change_language"/>--%>
            <%--                                    <select class="selectpicker" class="btn btn-dark" name="newLanguage" onchange="submit() ">--%>
            <%--                                        <option> Choose language</option>--%>
            <%--                                        <option value="en_EN">English</option>--%>
            <%--                                        <option value="ru_RU">Russian</option>--%>
            <%--                                        <option value="de_DE">Deutch</option>--%>
            <%--                                    </select>--%>
            <%--                                </form>--%>
            <%--                            </li>--%>

            <%--            <li>--%>
            <%--                <div>--%>
            <%--                    <span class="badge badge-danger"><ctg:role role="${ user.userRole }"/></span>--%>

            <%--                </div>--%>
            <%--            </li>--%>

            <c:if test="${ user.userRole == 'CLIENT'}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=go_to_order_preview">
                        <i class="fa fa-cleaningList-arrow-down">
                            <span class="badge badge-danger">${cleaningItemList.size()}</span>
                        </i>
                        <fmt:message key="order.title"/></a>
                    </a>
                </li>
            </c:if>
            <c:if test="${ not empty user}">
                <c:choose>
                    <c:when test="${  user.avatarPath != null}">
                        <ul class="navbar-nav ml-auto nav-flex-icons">
                            <li class="nav-item avatar">
                                <img src="data:image/jpg; base64, <c:out value='${user.avatarPath}'/> "
                                     class="rounded-circle z-depth-0"
                                     alt="avatar image" height="40">

                            </li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <ul class="navbar-nav ml-auto nav-flex-icons">
                            <li class="nav-item avatar">
                                <a class="nav-link mx-3" href="#">
                                    <img src="${pageContext.session.servletContext.contextPath}/uploads/testmonial-default.png"
                                         class="rounded-circle z-depth-0"
                                         alt="avatar image" height="35">
                                </a>
                            </li>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </c:if>

            <c:choose>
                <c:when test="${empty user}">
                    <li class="nav-item">
                        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/jsp/login.jsp"><i
                                class="fa fa-sign-in fa-lg fa-fw"></i><fmt:message
                                key="login.submit"/></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="btn btn-secondary"
                           href="${pageContext.request.contextPath}/controller?command=logout"><i
                                class="fa fa-sign-out fa-lg fa-fw"></i><fmt:message
                                key="title.signOut"/></a>
                    </li>
                </c:otherwise>
            </c:choose>
            <c:if test="${empty user}">
                <li class="nav-item">
                    <a class="btn btn-secondary"
                       href="${pageContext.request.contextPath}/jsp/registration.jsp"><i
                            class="fa fa-user-plus fa-lg fa-fw"></i><fmt:message
                            key="button.signUp"/></a>
                </li>
            </c:if>

        </ul>
    </div>

</nav>

</body>
<%--<script type="text/javascript">--%>
<%--    $(function () {--%>
<%--        $("#language").change(function (){--%>
<%--            var selected = $('#language').find(":selected").val();--%>
<%--            $.get('${pageContext.request.contextPath}/setLang?lang=selected,--%>
<%--            function(response) {--%>
<%--                console.log(response);--%>
<%--            });--%>
<%--            location.reload();--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>
</html>
