<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/views/header.jsp" %>

<div class="page-header">
    <h2>Gestión de Autores</h2>
    <a href="${pageContext.request.contextPath}/autores?action=nuevo" class="btn btn-primary">Agregar Nuevo Autor</a>
</div>

<table class="data-table">
    <thead>
        <tr>
            <th>ID</th>
            <th>Nombre Completo</th>
            <th>Nacionalidad</th>
            <th>Fecha de Nacimiento</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="autor" items="${listaAutores}">
            <tr>
                <td>${autor.idAutor}</td>
                <td>${autor.nombreCompleto}</td>
                <td>${autor.nacionalidad}</td>
                <td>${autor.fechaNacimiento}</td>
                <td>
                    <c:if test="${autor.activo}">
                        <span class="badge badge-success">Activo</span>
                    </c:if>
                    <c:if test="${!autor.activo}">
                        <span class="badge badge-danger">Inactivo</span>
                    </c:if>
                </td>
                <td class="actions">
                    <a href="${pageContext.request.contextPath}/autores?action=editar&id=${autor.idAutor}" class="btn btn-sm btn-warning">Editar</a>
                    <a href="${pageContext.request.contextPath}/autores?action=eliminar&id=${autor.idAutor}"
                       class="btn btn-sm btn-danger"
                       onclick="return confirm('¿Está seguro de eliminar este autor?')">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<%@ include file="/views/footer.jsp" %>
