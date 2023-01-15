<%--
  Created by IntelliJ IDEA.
  User: Administrador
  Date: 28/05/2022
  Time: 03:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${mvc.locale}"/>
<fmt:setBundle basename="messages" var="mensajes"/>
<fmt:setBundle basename="validationMesssage" var="mensajesValidacion"/>
<fmt:message bundle="${mensajes}" key="entity.producto" var="entity"/><html>
<head>
    <title>Title</title>
</head>
<body>
<h1><fmt:message key="tittle.socio" bundle="${mensajes}">
    <fmt:param value="${entity}"/>
</fmt:message> </h1>
<table>
    <tr>
        <th><fmt:message key="label.codigo" bundle="${mensajes}"/>: </th>
        <th>${producto.codigo}</th>
    </tr>
    <tr>
        <th><fmt:message key="label.nombre" bundle="${mensajes}"/>: </th>
        <th>${producto.nombre}</th>
    </tr>
    <tr>
        <th><fmt:message key="label.descripcion" bundle="${mensajes}"/>: </th>
        <th>${producto.descripcion}</th>
    </tr>
    <tr>
        <th><fmt:message key="label.precio" bundle="${mensajes}"/>:</th>
        <th>${producto.precio}</th>
    </tr>
    <tr>
        <th><fmt:message key="label.stock" bundle="${mensajes}"/></th>
        <th>${producto.stock}</th>
    </tr>

</table>
<c:set var="uriProductos" value="${mvc.uri('allProductos', {'idioma': mvc.locale.language})}"/>
<a href="${uriProductos}"><fmt:message key="tittle.volver.productos" bundle="${mensajes}"/></a>
</body>
</html>
