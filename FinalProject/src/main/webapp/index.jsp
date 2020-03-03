<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="language" scope="session"/>
<html>
<head><title>EL for pageContext</title></head>
<body>
<jsp:forward page="/jsp/login.jsp"/>

${nullPage}
<%--Путь к контексту : ${ pageContext.request.contextPath } <br/>--%>
<%--Имя хоста : ${ header["host"] }<br/>--%>
<%--Тип и кодировка контента : ${pageContext.response.contentType}<br/>--%>
<%--Кодировка ответа : ${pageContext.response.characterEncoding}<br/>--%>
<%--ID сессии : ${pageContext.request.session.id}<br/>--%>
<%--Время создания сессии в мсек : ${pageContext.request.session.creationTime}<br/>--%>
<%--Время последнего доступа к сессии : ${pageContext.request.session.lastAccessedTime}<br/>--%>
<%--Имя сервлета : ${pageContext.servletConfig.servletName}--%>
<%--<ctg:info-time/>--%>
</body></html>