<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/views/header.jsp" %>

<div class="page-header">
    <h2>
        <c:if test="${autor == null}">Agregar Autor</c:if>
        <c:if test="${autor != null}">Editar Autor</c:if>
    </h2>
</div>

<form method="post" action="${pageContext.request.contextPath}/autores" class="form">
    <c:if test="${autor != null}">
        <input type="hidden" name="action" value="actualizar">
        <input type="hidden" name="id" value="${autor.idAutor}">
    </c:if>
    <c:if test="${autor == null}">
        <input type="hidden" name="action" value="insertar">
    </c:if>

    <div class="form-group">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="${autor.nombre}" required class="form-control">
    </div>

    <div class="form-group">
        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" value="${autor.apellido}" required class="form-control">
    </div>

    <div class="form-group">
        <label for="nacionalidad">Nacionalidad:</label>
        <input type="text" id="nacionalidad" name="nacionalidad" value="${autor.nacionalidad}" class="form-control">
    </div>

    <div class="form-group">
        <label for="fechaNacimiento">Fecha de Nacimiento:</label>
        <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="${autor.fechaNacimiento}" class="form-control">
    </div>

    <div class="form-group">
        <label for="biografia">Biografía:</label>
        <textarea id="biografia" name="biografia" rows="6" class="form-control">${autor.biografia}</textarea>
    </div>

    <div class="form-group checkbox">
        <label>
            <input type="checkbox" name="activo" ${autor == null || autor.activo ? 'checked' : ''}>
            Activo
        </label>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Guardar</button>
        <a href="${pageContext.request.contextPath}/autores" class="btn btn-secondary">Cancelar</a>
    </div>
</form>

<%@ include file="/views/footer.jsp" %>
