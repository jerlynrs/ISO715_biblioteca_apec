<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ include file="../header.jsp" %>

<div class="row">
    <div class="col-12">
        <h2>
            <i class="fas fa-edit"></i>
            <c:choose>
                <c:when test="${editora != null}">Editar Editora</c:when>
                <c:otherwise>Nueva Editora</c:otherwise>
            </c:choose>
        </h2>
        <hr>
    </div>
</div>

<div class="row">
    <div class="col-md-8">
        <div class="card">
            <div class="card-body">
                <form method="post" action="${pageContext.request.contextPath}/editoras">
                    <c:if test="${editora != null}">
                        <input type="hidden" name="id" value="${editora.id}">
                        <input type="hidden" name="action" value="actualizar">
                    </c:if>
                    <c:if test="${editora == null}">
                        <input type="hidden" name="action" value="insertar">
                    </c:if>

                    <div class="mb-3">
                        <label for="descripcion" class="form-label">Descripción:</label>
                        <input type="text" class="form-control" id="descripcion" name="descripcion"
                               value="${editora != null ? editora.descripcion : ''}" required>
                    </div>

                    <div class="mb-3">
                        <label for="estado" class="form-label">Estado:</label>
                        <select class="form-select" id="estado" name="estado">
                            <option value="true" ${editora == null || editora.estado ? 'selected' : ''}>Activo</option>
                            <option value="false" ${editora != null && !editora.estado ? 'selected' : ''}>Inactivo</option>
                        </select>
                    </div>

                    <div class="d-flex gap-2">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save"></i> Guardar
                        </button>
                        <a href="${pageContext.request.contextPath}/editoras" class="btn btn-secondary">
                            <i class="fas fa-arrow-left"></i> Cancelar
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
