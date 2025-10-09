<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ include file="../header.jsp" %>

<div class="row">
    <div class="col-12">
        <h2><i class="fas fa-flask"></i> Ciencias</h2>
        <hr>
    </div>
</div>

<div class="row mb-3">
    <div class="col-12">
        <a href="${pageContext.request.contextPath}/ciencias?action=nuevo" class="btn btn-primary">
            <i class="fas fa-plus"></i> Nueva Ciencia
        </a>
    </div>
</div>

<div class="row">
    <div class="col-12">
        <div class="card">
            <div class="card-body">
                <table class="table table-striped table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Descripción</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="ciencia" items="${listaCiencias}">
                            <tr>
                                <td>${ciencia.id}</td>
                                <td>${ciencia.descripcion}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${ciencia.estado}">
                                            <span class="badge bg-success">Activo</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge bg-danger">Inactivo</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/ciencias?action=editar&id=${ciencia.id}"
                                       class="btn btn-sm btn-warning">
                                        <i class="fas fa-edit"></i> Editar
                                    </a>
                                    <a href="${pageContext.request.contextPath}/ciencias?action=eliminar&id=${ciencia.id}"
                                       class="btn btn-sm btn-danger"
                                       onclick="return confirm('¿Está seguro de eliminar esta ciencia?');">
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

<%@ include file="../footer.jsp" %>
