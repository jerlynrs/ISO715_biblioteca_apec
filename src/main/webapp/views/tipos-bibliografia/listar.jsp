<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/views/header.jsp" %>

<div class="page-header">
    <h2>Gestión de Tipos de Bibliografía</h2>
    <a href="${pageContext.request.contextPath}/tipos-bibliografia?action=nuevo" class="btn btn-primary">Agregar Nuevo Tipo</a>
</div>

<table class="data-table">
    <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="tipo" items="${listaTipos}">
            <tr>
                <td>${tipo.idTipoBibliografia}</td>
                <td>${tipo.nombre}</td>
                <td>${tipo.descripcion}</td>
                <td>
                    <c:if test="${tipo.activo}">
                        <span class="badge badge-success">Activo</span>
                    </c:if>
                    <c:if test="${!tipo.activo}">
                        <span class="badge badge-danger">Inactivo</span>
                    </c:if>
                </td>
                <td class="actions">
                    <a href="${pageContext.request.contextPath}/tipos-bibliografia?action=editar&id=${tipo.idTipoBibliografia}" class="btn btn-sm btn-warning">Editar</a>
                    <a href="${pageContext.request.contextPath}/tipos-bibliografia?action=eliminar&id=${tipo.idTipoBibliografia}"
                       class="btn btn-sm btn-danger"
                       onclick="return confirm('¿Está seguro de eliminar este tipo de bibliografía?')">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<%@ include file="/views/footer.jsp" %>
