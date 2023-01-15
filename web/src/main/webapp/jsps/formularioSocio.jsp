<%--
  Created by IntelliJ IDEA.
  User: Administrador
  Date: 27/05/2022
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${mvc.locale}"/>
<fmt:setBundle basename="messages" var="mensajes"/>
<fmt:setBundle basename="validationMesssage" var="mensajesValidacion"/>

<fmt:message bundle="${mensajes}" key="entity.socio" var="entity"/>
<html>
<head>

</head>
<body>


<c:choose>
    <c:when test="${operacion eq 'INSERT'}">
        <c:set var="uriSocio" value="${mvc.uri('insertSocio', {'idioma' : mvc.locale.language})}"/>
        <c:set var="labelSubmit" value="Crear socio"/>
        <c:set var="method" value="POST"/>
    </c:when>

    <c:when test="${operacion eq 'UPDATE'}">
        <c:set var="uriSocio" value="${mvc.uri('updateSocio', {'codigo' : socio.codigo, 'idioma': mvc.locale.language})}"/>
        <c:set var="labelSubmit" value="Actualizar socio"/>
        <c:set var="method" value="PUT"/>

    </c:when>
    <c:when test="${operacion eq 'DELETE'}">
        <c:set var="uriSocio" value="${mvc.uri('deleteSocio', {'codigo' : socio.codigo, 'idioma': mvc.locale.language})}"/>
        <c:set var="labelSubmit" value="Borrar socio"/>
        <c:set var="method" value="DELETE"/>
    </c:when>
</c:choose>



<h1><fmt:message key="tittle.formulario.socios" bundle="${mensajes}"/></h1>
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

<form id="formularioSocio" method="post" action="${uriSocio}" enctype="application/x-www-form-urlencoded">

    <input type="hidden" name="_method" value="${method}"/>

    <fmt:message key="label.nombre" bundle="${mensajes}"/> : <input name="nombre" type="text" maxlength="100" value="${socio.nombre}" <c:if test="${operacion eq 'DELETE'}"> disabled </c:if>>
    <br/>
    <fmt:message key="label.email" bundle="${mensajes}"/>: <input name="email" type="text" maxlength="100" value="${socio.email}" <c:if test="${operacion eq 'DELETE'}"> disabled </c:if>>
    <br/>
    <fmt:message key="label.saldo" bundle="${mensajes}"/>: <input name="saldo" type="number" value="${socio.saldo}" <c:if test="${operacion eq 'DELETE'}"> disabled </c:if> <c:if test="${operacion eq 'INSERT'}"> disabled </c:if> />
    <br/>

    <input type="submit" value="${labelSubmit}">

</form>



</body>
</html>
