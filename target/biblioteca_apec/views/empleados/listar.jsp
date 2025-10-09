<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ include file="../header.jsp" %>

<div class="row">
    <div class="col-12">
        <h2><i class="fas fa-user-tie"></i> Empleados</h2>
        <hr>
    </div>
</div>

<div class="row mb-3">
    <div class="col-12">
        <a href="${pageContext.request.contextPath}/empleados?action=nuevo" class="btn btn-primary">
            <i class="fas fa-plus"></i> Nuevo Empleado
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
                                <th>Nombre</th>
                                <th>Cédula</th>
                                <th>Tanda Laboral</th>
                                <th>Comisión (%)</th>
                                <th>Fecha Ingreso</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="empleado" items="${listaEmpleados}">
                                <tr>
                                    <td>${empleado.id}</td>
                                    <td>${empleado.nombre}</td>
                                    <td>${empleado.cedula}</td>
                                    <td>
                                        <span class="badge bg-primary">${empleado.tandaLabor}</span>
                                    </td>
                                    <td>${empleado.porcientoComision}%</td>
                                    <td>${empleado.fechaIngreso}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${empleado.estado}">
                                                <span class="badge bg-success">Activo</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge bg-danger">Inactivo</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/empleados?action=editar&id=${empleado.id}"
                                           class="btn btn-sm btn-warning">
                                            <i class="fas fa-edit"></i> Editar
                                        </a>
                                        <a href="${pageContext.request.contextPath}/empleados?action=eliminar&id=${empleado.id}"
                                           class="btn btn-sm btn-danger"
                                           onclick="return confirm('¿Está seguro de eliminar este empleado?');">
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
