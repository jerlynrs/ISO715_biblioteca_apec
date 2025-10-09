<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/views/header.jsp" %>

<div class="page-header">
    <h2>Gestión de Ciencias</h2>
    <a href="${pageContext.request.contextPath}/ciencias?action=nuevo" class="btn btn-primary">Agregar Nueva Ciencia</a>
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
        <c:forEach var="ciencia" items="${listaCiencias}">
            <tr>
                <td>${ciencia.idCiencia}</td>
                <td>${ciencia.nombre}</td>
                <td>${ciencia.descripcion}</td>
                <td>
                    <c:if test="${ciencia.activo}">
                        <span class="badge badge-success">Activo</span>
                    </c:if>
                    <c:if test="${!ciencia.activo}">
                        <span class="badge badge-danger">Inactivo</span>
                    </c:if>
                </td>
                <td class="actions">
                    <a href="${pageContext.request.contextPath}/ciencias?action=editar&id=${ciencia.idCiencia}" class="btn btn-sm btn-warning">Editar</a>
                    <a href="${pageContext.request.contextPath}/ciencias?action=eliminar&id=${ciencia.idCiencia}"
                       class="btn btn-sm btn-danger"
                       onclick="return confirm('¿Está seguro de eliminar esta ciencia?')">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<%@ include file="/views/footer.jsp" %>
