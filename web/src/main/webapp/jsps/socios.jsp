<%--
  Created by IntelliJ IDEA.
  User: Administrador
  Date: 27/05/2022
  Time: 02:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${mvc.locale}"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages" var="mensajes"/>
<fmt:message bundle="${mensajes}" key="entity.socio" var="entity"/>

<html>
<head>
    <title>Title</title>
</head>
<body>

<h1><fmt:message key="tittle.socios" bundle="${mensajes}"/></h1>

<table>
    <thead>
        <tr>
            <th><fmt:message key="label.nombre" bundle="${mensajes}"/> </th>
            <th><fmt:message key="label.email" bundle="${mensajes}"/> </th>
            <th><fmt:message key="label.saldo" bundle="${mensajes}"/> </th>
            <th><fmt:message key="label.operaciones" bundle="${mensajes}"/> </th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="socio" items="${socios}">
        <c:set var="uriSocio" value="${mvc.uri('oneSocio', {'codigo': socio.codigo, 'idioma': mvc.locale.language})}"/>
        <c:set var="uriUpdateSocio" value="${mvc.uri('formularioUpdateSocio', {'codigo': socio.codigo, 'idioma': mvc.locale.language})}"/>
        <c:set var="uriDeleteSocio" value="${mvc.uri('formularioDeleteSocio', {'codigo': socio.codigo, 'idioma': mvc.locale.language})}"/>

        <tr>
            <td> <a href="${uriSocio}"> ${socio.nombre} </a></td>
            <td>${socio.email}</td>
            <td>${socio.saldo}</td>
            <td>
                <a href="${uriUpdateSocio}"><fmt:message key="tittle.update" bundle="${mensajes}">
                        <fmt:param value="${entity}"/>
                    </fmt:message>
                </a>
                <a href="${uriDeleteSocio}"><fmt:message key="tittle.delete" bundle="${mensajes}">
                    <fmt:param value="${entity}"/>
                </fmt:message>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br/>
<c:set var="uriFormularioSocio" value="${mvc.uri('formularioInsertSocio', {'idioma': mvc.locale.language})}"/>
<a href="${uriFormularioSocio}"> <fmt:message key="operacion.agregar" bundle="${mensajes}">
    <fmt:param value="${entity}"/>
</fmt:message> </a>


</body>
</html>
