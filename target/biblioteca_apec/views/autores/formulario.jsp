<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ include file="../header.jsp" %>

<div class="row">
    <div class="col-12">
        <h2>
            <i class="fas fa-edit"></i>
            <c:choose>
                <c:when test="${autor != null}">Editar Autor</c:when>
                <c:otherwise>Nuevo Autor</c:otherwise>
            </c:choose>
        </h2>
        <hr>
    </div>
</div>

<div class="row">
    <div class="col-md-8">
        <div class="card">
            <div class="card-body">
                <form method="post" action="${pageContext.request.contextPath}/autores">
                    <c:if test="${autor != null}">
                        <input type="hidden" name="id" value="${autor.id}">
                        <input type="hidden" name="action" value="actualizar">
                    </c:if>
                    <c:if test="${autor == null}">
                        <input type="hidden" name="action" value="insertar">
                    </c:if>

                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre:</label>
                        <input type="text" class="form-control" id="nombre" name="nombre"
                               value="${autor != null ? autor.nombre : ''}" required>
                    </div>

                    <div class="mb-3">
                        <label for="paisOrigen" class="form-label">País de Origen:</label>
                        <input type="text" class="form-control" id="paisOrigen" name="paisOrigen"
                               value="${autor != null ? autor.paisOrigen : ''}">
                    </div>

                    <div class="mb-3">
                        <label for="idiomaNativo" class="form-label">Idioma Nativo:</label>
                        <select class="form-select" id="idiomaNativo" name="idiomaNativo" required>
                            <option value="">Seleccione...</option>
                            <c:forEach var="idioma" items="${listaIdiomas}">
                                <option value="${idioma.id}" ${autor != null && autor.idiomaNativo == idioma.id ? 'selected' : ''}>
                                    ${idioma.descripcion}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="estado" class="form-label">Estado:</label>
                        <select class="form-select" id="estado" name="estado">
                            <option value="true" ${autor == null || autor.estado ? 'selected' : ''}>Activo</option>
                            <option value="false" ${autor != null && !autor.estado ? 'selected' : ''}>Inactivo</option>
                        </select>
                    </div>

                    <div class="d-flex gap-2">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save"></i> Guardar
                        </button>
                        <a href="${pageContext.request.contextPath}/autores" class="btn btn-secondary">
                            <i class="fas fa-arrow-left"></i> Cancelar
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
