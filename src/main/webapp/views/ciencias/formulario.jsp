<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/views/header.jsp" %>

<div class="page-header">
    <h2>
        <c:if test="${ciencia == null}">Agregar Ciencia</c:if>
        <c:if test="${ciencia != null}">Editar Ciencia</c:if>
    </h2>
</div>

<form method="post" action="${pageContext.request.contextPath}/ciencias" class="form">
    <c:if test="${ciencia != null}">
        <input type="hidden" name="action" value="actualizar">
        <input type="hidden" name="id" value="${ciencia.idCiencia}">
    </c:if>
    <c:if test="${ciencia == null}">
        <input type="hidden" name="action" value="insertar">
    </c:if>

    <div class="form-group">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="${ciencia.nombre}" required class="form-control">
    </div>

    <div class="form-group">
        <label for="descripcion">Descripción:</label>
        <textarea id="descripcion" name="descripcion" rows="4" class="form-control">${ciencia.descripcion}</textarea>
    </div>

    <div class="form-group checkbox">
        <label>
            <input type="checkbox" name="activo" ${ciencia == null || ciencia.activo ? 'checked' : ''}>
            Activo
        </label>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Guardar</button>
        <a href="${pageContext.request.contextPath}/ciencias" class="btn btn-secondary">Cancelar</a>
    </div>
</form>

<%@ include file="/views/footer.jsp" %>
