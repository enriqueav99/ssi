<%--
  Created by IntelliJ IDEA.
  User: Administrador
  Date: 28/05/2022
  Time: 02:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${mvc.locale}"/>
<fmt:setBundle basename="messages" var="mensajes"/>
<fmt:setBundle basename="validationMesssage" var="mensajesValidacion"/>
<fmt:message bundle="${mensajes}" key="entity.producto" var="entity"/>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1><fmt:message key="tittle.productos" bundle="${mensajes}"/></h1>

<table>
    <thead>
        <tr>
            <th><fmt:message key="label.nombre" bundle="${mensajes}"/></th>
            <th><fmt:message key="label.descripcion" bundle="${mensajes}"/></th>
            <th><fmt:message key="label.precio" bundle="${mensajes}"/></th>
            <th><fmt:message key="label.stock" bundle="${mensajes}"/></th>
            <th><fmt:message key="label.operaciones" bundle="${mensajes}"/></th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="producto" items="${productos}">
        <c:set var="uriUpdateProducto" value="${mvc.uri('formularioUpdateProducto',{'codigo' : producto.codigo, 'idioma': mvc.locale.language})}"/>
        <c:set var="uriProducto" value="${mvc.uri('oneProducto', {'codigo' : producto.codigo, 'idioma': mvc.locale.language})}"/>
        <c:set var="uriDeleteProducto" value="${mvc.uri('formularioDeleteProducto', {'codigo' : producto.codigo, 'idioma': mvc.locale.language})}"/>
        <tr>
            <th><a href="${uriProducto}">${producto.nombre}</a></th>
            <th>${producto.descripcion}</th>
            <th>${producto.precio}</th>
            <th>${producto.stock}</th>
            <th><a href="${uriUpdateProducto}"><fmt:message key="operacion.editar" bundle="${mensajes}"/></a></th>
            <th><a href="${uriDeleteProducto}"><fmt:message key="operacion.eliminar" bundle="${mensajes}"/></a></th>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:set var="uriFormlarioInsertProducto" value="${mvc.uri('formularioInsertProducto', {'idioma': mvc.locale.language})}"/>
<h1><a href="${uriFormlarioInsertProducto}"><fmt:message key="operacion.agregar" bundle="${mensajes}">
    <fmt:param value="${entity}"/>
    </fmt:message>
</a></h1>

</body>
</html>
