<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ include file="../header.jsp" %>

<div class="row">
    <div class="col-12">
        <h2>
            <i class="fas fa-edit"></i>
            <c:choose>
                <c:when test="${usuario != null}">Editar Usuario</c:when>
                <c:otherwise>Nuevo Usuario</c:otherwise>
            </c:choose>
        </h2>
        <hr>
    </div>
</div>

<div class="row">
    <div class="col-md-8">
        <div class="card">
            <div class="card-body">
                <form method="post" action="${pageContext.request.contextPath}/usuarios">
                    <c:if test="${usuario != null}">
                        <input type="hidden" name="id" value="${usuario.id}">
                        <input type="hidden" name="action" value="actualizar">
                    </c:if>
                    <c:if test="${usuario == null}">
                        <input type="hidden" name="action" value="insertar">
                    </c:if>

                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre:</label>
                        <input type="text" class="form-control" id="nombre" name="nombre"
                               value="${usuario != null ? usuario.nombre : ''}" required>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="cedula" class="form-label">Cédula:</label>
                            <input type="text" class="form-control" id="cedula" name="cedula"
                                   value="${usuario != null ? usuario.cedula : ''}" placeholder="001-1234567-8">
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="noCarnet" class="form-label">No. Carnet:</label>
                            <input type="text" class="form-control" id="noCarnet" name="noCarnet"
                                   value="${usuario != null ? usuario.noCarnet : ''}" placeholder="EST-2024-001">
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="tipoPersona" class="form-label">Tipo de Persona:</label>
                            <select class="form-select" id="tipoPersona" name="tipoPersona" required>
                                <option value="">Seleccione...</option>
                                <option value="FISICA" ${usuario != null && usuario.tipoPersona == 'FISICA' ? 'selected' : ''}>Física</option>
                                <option value="JURIDICA" ${usuario != null && usuario.tipoPersona == 'JURIDICA' ? 'selected' : ''}>Jurídica</option>
                            </select>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="estado" class="form-label">Estado:</label>
                            <select class="form-select" id="estado" name="estado">
                                <option value="true" ${usuario == null || usuario.estado ? 'selected' : ''}>Activo</option>
                                <option value="false" ${usuario != null && !usuario.estado ? 'selected' : ''}>Inactivo</option>
                            </select>
                        </div>
                    </div>

                    <div class="d-flex gap-2">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save"></i> Guardar
                        </button>
                        <a href="${pageContext.request.contextPath}/usuarios" class="btn btn-secondary">
                            <i class="fas fa-arrow-left"></i> Cancelar
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
