<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/views/header.jsp" %>

<div class="page-header">
    <h2>
        <c:if test="${tipo == null}">Agregar Tipo de Bibliografía</c:if>
        <c:if test="${tipo != null}">Editar Tipo de Bibliografía</c:if>
    </h2>
</div>

<form method="post" action="${pageContext.request.contextPath}/tipos-bibliografia" class="form">
    <c:if test="${tipo != null}">
        <input type="hidden" name="action" value="actualizar">
        <input type="hidden" name="id" value="${tipo.idTipoBibliografia}">
    </c:if>
    <c:if test="${tipo == null}">
        <input type="hidden" name="action" value="insertar">
    </c:if>

    <div class="form-group">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="${tipo.nombre}" required class="form-control">
    </div>

    <div class="form-group">
        <label for="descripcion">Descripción:</label>
        <textarea id="descripcion" name="descripcion" rows="4" class="form-control">${tipo.descripcion}</textarea>
    </div>

    <div class="form-group checkbox">
        <label>
            <input type="checkbox" name="activo" ${tipo == null || tipo.activo ? 'checked' : ''}>
            Activo
        </label>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Guardar</button>
        <a href="${pageContext.request.contextPath}/tipos-bibliografia" class="btn btn-secondary">Cancelar</a>
    </div>
</form>

<%@ include file="/views/footer.jsp" %>
