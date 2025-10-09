<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ include file="../header.jsp" %>

<div class="row">
    <div class="col-12">
        <h2>
            <i class="fas fa-edit"></i>
            <c:choose>
                <c:when test="${prestamo != null}">Editar Préstamo</c:when>
                <c:otherwise>Nuevo Préstamo</c:otherwise>
            </c:choose>
        </h2>
        <hr>
    </div>
</div>

<div class="row">
    <div class="col-md-10">
        <div class="card">
            <div class="card-body">
                <form method="post" action="${pageContext.request.contextPath}/prestamos">
                    <c:if test="${prestamo != null}">
                        <input type="hidden" name="id" value="${prestamo.id}">
                        <input type="hidden" name="action" value="actualizar">
                    </c:if>
                    <c:if test="${prestamo == null}">
                        <input type="hidden" name="action" value="insertar">
                    </c:if>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="empleadoId" class="form-label">Empleado:</label>
                            <select class="form-select" id="empleadoId" name="empleadoId" required>
                                <option value="">Seleccione...</option>
                                <c:forEach var="empleado" items="${listaEmpleados}">
                                    <option value="${empleado.id}" ${prestamo != null && prestamo.empleadoId == empleado.id ? 'selected' : ''}>
                                        ${empleado.nombre}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="usuarioId" class="form-label">Usuario:</label>
                            <select class="form-select" id="usuarioId" name="usuarioId" required>
                                <option value="">Seleccione...</option>
                                <c:forEach var="usuario" items="${listaUsuarios}">
                                    <option value="${usuario.id}" ${prestamo != null && prestamo.usuarioId == usuario.id ? 'selected' : ''}>
                                        ${usuario.nombre}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 mb-3">
                            <label for="libroId" class="form-label">Libro:</label>
                            <select class="form-select" id="libroId" name="libroId" required>
                                <option value="">Seleccione...</option>
                                <c:forEach var="libro" items="${listaLibros}">
                                    <option value="${libro.id}" ${prestamo != null && prestamo.libroId == libro.id ? 'selected' : ''}>
                                        ${libro.descripcion} - ISBN: ${libro.isbn}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="fechaPrestamo" class="form-label">Fecha Préstamo:</label>
                            <input type="date" class="form-control" id="fechaPrestamo" name="fechaPrestamo"
                                   value="${prestamo != null ? prestamo.fechaPrestamo : ''}" required>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="fechaDevolucion" class="form-label">Fecha Devolución:</label>
                            <input type="date" class="form-control" id="fechaDevolucion" name="fechaDevolucion"
                                   value="${prestamo != null && prestamo.fechaDevolucion != null ? prestamo.fechaDevolucion : ''}">
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="montoDia" class="form-label">Monto por Día:</label>
                            <input type="number" step="0.01" class="form-control" id="montoDia" name="montoDia"
                                   value="${prestamo != null ? prestamo.montoDia : '0.00'}" required>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="diasExcedidos" class="form-label">Días Excedidos:</label>
                            <input type="number" class="form-control" id="diasExcedidos" name="diasExcedidos"
                                   value="${prestamo != null ? prestamo.diasExcedidos : '0'}" required>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 mb-3">
                            <label for="comentario" class="form-label">Comentario:</label>
                            <textarea class="form-control" id="comentario" name="comentario" rows="3">${prestamo != null ? prestamo.comentario : ''}</textarea>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="estado" class="form-label">Estado:</label>
                            <select class="form-select" id="estado" name="estado">
                                <option value="true" ${prestamo == null || prestamo.estado ? 'selected' : ''}>Activo</option>
                                <option value="false" ${prestamo != null && !prestamo.estado ? 'selected' : ''}>Inactivo</option>
                            </select>
                        </div>
                    </div>

                    <div class="d-flex gap-2">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save"></i> Guardar
                        </button>
                        <a href="${pageContext.request.contextPath}/prestamos" class="btn btn-secondary">
                            <i class="fas fa-arrow-left"></i> Cancelar
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
