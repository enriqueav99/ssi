<%--
  Created by IntelliJ IDEA.
  User: Administrador
  Date: 27/05/2022
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${mvc.locale}"/>
<fmt:setBundle basename="messages" var="mensajes"/>
<fmt:message bundle="${mensajes}" key="entity.socio" var="entity"/>

<html>
<head>
    <title><fmt:message key="tittle.socio" bundle="${mensajes}">
        <fmt:param value="${entity}"/>
    </fmt:message>
    :</title>
</head>
<body>

<h1>Datos del socio ${socio.nombre}</h1>
<table>
    <tr>
        <th><fmt:message key="label.codigo" bundle="${mensajes}"/>: </th>
        <td>${socio.codigo}</td>
    </tr>
    <tr>
        <th><fmt:message key="label.nombre" bundle="${mensajes}"/>:: </th>
        <td>${socio.nombre}</td>
    </tr>
    <tr>
        <th><fmt:message key="label.email" bundle="${mensajes}"/>:: </th>
        <td>${socio.email}</td>
    </tr>
    <tr>
        <th><fmt:message key="label.saldo" bundle="${mensajes}"/>:: </th>
        <td>${socio.saldo}</td>
    </tr>
</table>
<c:set var="uriSocios" value="${mvc.uri('allSocios', {'idioma': mvc.locale.language})}"/>
<a href="${uriSocios}"> Volver a ver todos los socios</a>
</body>
</html>
