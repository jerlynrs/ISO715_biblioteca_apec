<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ include file="../header.jsp" %>

<div class="row">
    <div class="col-12">
        <h2><i class="fas fa-exchange-alt"></i> Gestión de Préstamos</h2>
        <hr>
    </div>
</div>

<div class="row mb-3">
    <div class="col-12">
        <a href="${pageContext.request.contextPath}/prestamos?action=nuevo" class="btn btn-primary">
            <i class="fas fa-plus"></i> Nuevo Préstamo
        </a>
    </div>
</div>

<div class="row">
    <div class="col-12">
        <div class="card">
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-striped table-hover">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Empleado ID</th>
                                <th>Libro ID</th>
                                <th>Usuario ID</th>
                                <th>Fecha Préstamo</th>
                                <th>Fecha Devolución</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="prestamo" items="${listaPrestamos}">
                                <tr>
                                    <td>${prestamo.id}</td>
                                    <td>${prestamo.empleadoId}</td>
                                    <td>${prestamo.libroId}</td>
                                    <td>${prestamo.usuarioId}</td>
                                    <td>${prestamo.fechaPrestamo}</td>
                                    <td>${prestamo.fechaDevolucion != null ? prestamo.fechaDevolucion : 'Pendiente'}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${prestamo.estado && prestamo.fechaDevolucion == null}">
                                                <span class="badge bg-warning">En Préstamo</span>
                                            </c:when>
                                            <c:when test="${prestamo.fechaDevolucion != null}">
                                                <span class="badge bg-success">Devuelto</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge bg-danger">Inactivo</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/prestamos?action=editar&id=${prestamo.id}"
                                           class="btn btn-sm btn-warning">
                                            <i class="fas fa-edit"></i> Editar
                                        </a>
                                        <a href="${pageContext.request.contextPath}/prestamos?action=eliminar&id=${prestamo.id}"
                                           class="btn btn-sm btn-danger"
                                           onclick="return confirm('¿Está seguro de eliminar este préstamo?');">
                                            <i class="fas fa-trash"></i> Eliminar
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
