<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ include file="../header.jsp" %>

<div class="row">
    <div class="col-12">
        <h2><i class="fas fa-users"></i> Usuarios</h2>
        <hr>
    </div>
</div>

<div class="row mb-3">
    <div class="col-12">
        <a href="${pageContext.request.contextPath}/usuarios?action=nuevo" class="btn btn-primary">
            <i class="fas fa-plus"></i> Nuevo Usuario
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
                                <th>No. Carnet</th>
                                <th>Tipo Persona</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="usuario" items="${listaUsuarios}">
                                <tr>
                                    <td>${usuario.id}</td>
                                    <td>${usuario.nombre}</td>
                                    <td>${usuario.cedula}</td>
                                    <td>${usuario.noCarnet}</td>
                                    <td>
                                        <span class="badge bg-info text-dark">${usuario.tipoPersona}</span>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${usuario.estado}">
                                                <span class="badge bg-success">Activo</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge bg-danger">Inactivo</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/usuarios?action=editar&id=${usuario.id}"
                                           class="btn btn-sm btn-warning">
                                            <i class="fas fa-edit"></i> Editar
                                        </a>
                                        <a href="${pageContext.request.contextPath}/usuarios?action=eliminar&id=${usuario.id}"
                                           class="btn btn-sm btn-danger"
                                           onclick="return confirm('¿Está seguro de eliminar este usuario?');">
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
