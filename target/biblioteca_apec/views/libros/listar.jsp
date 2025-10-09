<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ include file="../header.jsp" %>

<div class="row">
    <div class="col-12">
        <h2><i class="fas fa-book"></i> Gestión de Libros</h2>
        <hr>
    </div>
</div>

<div class="row mb-3">
    <div class="col-12">
        <a href="${pageContext.request.contextPath}/libros?action=nuevo" class="btn btn-primary">
            <i class="fas fa-plus"></i> Nuevo Libro
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
                                <th>Descripción</th>
                                <th>ISBN</th>
                                <th>Año</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="libro" items="${listaLibros}">
                                <tr>
                                    <td>${libro.id}</td>
                                    <td>${libro.descripcion}</td>
                                    <td>${libro.isbn}</td>
                                    <td>${libro.anioPublicacion}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${libro.estado}">
                                                <span class="badge bg-success">Disponible</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge bg-danger">No Disponible</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/libros?action=editar&id=${libro.id}"
                                           class="btn btn-sm btn-warning">
                                            <i class="fas fa-edit"></i> Editar
                                        </a>
                                        <a href="${pageContext.request.contextPath}/libros?action=eliminar&id=${libro.id}"
                                           class="btn btn-sm btn-danger"
                                           onclick="return confirm('¿Está seguro de eliminar este libro?');">
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
