<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ include file="../header.jsp" %>

<div class="row">
    <div class="col-12">
        <h2>
            <i class="fas fa-edit"></i>
            <c:choose>
                <c:when test="${empleado != null}">Editar Empleado</c:when>
                <c:otherwise>Nuevo Empleado</c:otherwise>
            </c:choose>
        </h2>
        <hr>
    </div>
</div>

<div class="row">
    <div class="col-md-10">
        <div class="card">
            <div class="card-body">
                <form method="post" action="${pageContext.request.contextPath}/empleados">
                    <c:if test="${empleado != null}">
                        <input type="hidden" name="id" value="${empleado.id}">
                        <input type="hidden" name="action" value="actualizar">
                    </c:if>
                    <c:if test="${empleado == null}">
                        <input type="hidden" name="action" value="insertar">
                    </c:if>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="nombre" class="form-label">Nombre:</label>
                            <input type="text" class="form-control" id="nombre" name="nombre"
                                   value="${empleado != null ? empleado.nombre : ''}" required>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="cedula" class="form-label">Cédula:</label>
                            <input type="text" class="form-control" id="cedula" name="cedula"
                                   value="${empleado != null ? empleado.cedula : ''}" placeholder="001-1234567-8">
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="tandaLabor" class="form-label">Tanda Laboral:</label>
                            <select class="form-select" id="tandaLabor" name="tandaLabor" required>
                                <option value="">Seleccione...</option>
                                <option value="MATUTINA" ${empleado != null && empleado.tandaLabor == 'MATUTINA' ? 'selected' : ''}>Matutina</option>
                                <option value="VESPERTINA" ${empleado != null && empleado.tandaLabor == 'VESPERTINA' ? 'selected' : ''}>Vespertina</option>
                                <option value="NOCTURNA" ${empleado != null && empleado.tandaLabor == 'NOCTURNA' ? 'selected' : ''}>Nocturna</option>
                            </select>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="porcientoComision" class="form-label">Porcentaje de Comisión (%):</label>
                            <input type="number" step="0.01" class="form-control" id="porcientoComision" name="porcientoComision"
                                   value="${empleado != null ? empleado.porcientoComision : '0.00'}" required>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="fechaIngreso" class="form-label">Fecha de Ingreso:</label>
                            <input type="date" class="form-control" id="fechaIngreso" name="fechaIngreso"
                                   value="${empleado != null ? empleado.fechaIngreso : ''}" required>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="estado" class="form-label">Estado:</label>
                            <select class="form-select" id="estado" name="estado">
                                <option value="true" ${empleado == null || empleado.estado ? 'selected' : ''}>Activo</option>
                                <option value="false" ${empleado != null && !empleado.estado ? 'selected' : ''}>Inactivo</option>
                            </select>
                        </div>
                    </div>

                    <div class="d-flex gap-2">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save"></i> Guardar
                        </button>
                        <a href="${pageContext.request.contextPath}/empleados" class="btn btn-secondary">
                            <i class="fas fa-arrow-left"></i> Cancelar
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
