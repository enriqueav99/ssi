<%--
  Created by IntelliJ IDEA.
  User: Administrador
  Date: 28/05/2022
  Time: 03:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${mvc.locale}"/>
<fmt:setBundle basename="messages" var="mensajes"/>
<fmt:setBundle basename="validationMesssage" var="mensajesValidacion"/>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:choose>
    <c:when test="${operacion eq 'INSERT'}">
        <c:set var="method" value="POST"/>
        <c:set var="uriProducto" value="${mvc.uri('insertProducto', {'idioma': mvc.locale.language})}"/>
        <c:set var="labelSubmit" value="Introduce la nueva asignatura"/>
    </c:when>
    <c:when test="${operacion eq 'UPDATE'}">
            <c:set var="method" value="PUT"/>
            <c:set var="uriProducto" value="${mvc.uri('updateProducto', {'idioma': mvc.locale.language, 'codigo': producto.codigo})}"/>
            <c:set var="labelSubmit" value="Actualiza la asignatura"/>
    </c:when>
    <c:when test="${operacion eq 'DELETE'}">
                <c:set var="method" value="DELETE"/>
                <c:set var="uriProducto" value="${mvc.uri('deleteProducto', {'idioma': mvc.locale.language, 'codigo': producto.codigo})}"/>
                <c:set var="labelSubmit" value="Borra la asignatura"/>
        </c:when>
</c:choose>

<h1><fmt:message key="tittle.formulario.productos" bundle="${mensajes}"/></h1>
<c:if test="${not empty errores}">
    <h2><fmt:message key="tittle.error" bundle="${mensajes}"/></h2>
    <ul>
        <c:forEach var="menssajeError" items = "${errores}">
            <li>
                    ${menssajeError}
            </li>
        </c:forEach>
    </ul>
    <br/>
    <br/>
</c:if>
<form id="formularioProducto" method="post" action="${uriProducto}" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="_method" value="${method}"/>
    <fmt:message key="label.nombre" bundle="${mensajes}"/>:
    <input name="nombre" type="text" maxlength="100" value="${producto.nombre}"<c:if test="${operacion eq 'DELETE'}">disabled </c:if>/>
    <br/>
    <fmt:message key="label.descripcion" bundle="${mensajes}"/>: <input name="descripcion" type="text" maxlength="200" value="${produto.descripcion}" <c:if test="${operacion eq 'DELETE'}">disabled </c:if>/>
    <br/>
    <fmt:message key="label.precio" bundle="${mensajes}"/>: <input  name="precio" type="number" min="0.1" step="0.5" value="${producto.precio}" <c:if test="${operacion eq 'DELETE'}">disabled </c:if>/>
    <br/>
    <fmt:message key="label.stock" bundle="${mensajes}"/>: <input name="stock" type="number" step="1" value="${producto.stock}" <c:if test="${operacion eq 'DELETE'}"> disabled </c:if>/>
    <br/>
    <input type="submit" value="${labelSubmit}"/>
</form>


</body>
</html>
