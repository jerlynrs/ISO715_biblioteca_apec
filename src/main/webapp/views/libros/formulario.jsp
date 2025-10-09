<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ include file="../header.jsp" %>

<div class="row">
    <div class="col-12">
        <h2>
            <i class="fas fa-edit"></i>
            <c:choose>
                <c:when test="${libro != null}">Editar Libro</c:when>
                <c:otherwise>Nuevo Libro</c:otherwise>
            </c:choose>
        </h2>
        <hr>
    </div>
</div>

<div class="row">
    <div class="col-md-10">
        <div class="card">
            <div class="card-body">
                <form method="post" action="${pageContext.request.contextPath}/libros">
                    <c:if test="${libro != null}">
                        <input type="hidden" name="id" value="${libro.id}">
                        <input type="hidden" name="action" value="actualizar">
                    </c:if>
                    <c:if test="${libro == null}">
                        <input type="hidden" name="action" value="insertar">
                    </c:if>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="descripcion" class="form-label">Descripción:</label>
                            <input type="text" class="form-control" id="descripcion" name="descripcion"
                                   value="${libro != null ? libro.descripcion : ''}" required>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="isbn" class="form-label">ISBN:</label>
                            <input type="text" class="form-control" id="isbn" name="isbn"
                                   value="${libro != null ? libro.isbn : ''}" required>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="signaturaTopografica" class="form-label">Signatura Topográfica:</label>
                            <input type="text" class="form-control" id="signaturaTopografica" name="signaturaTopografica"
                                   value="${libro != null ? libro.signaturaTopografica : ''}">
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="anioPublicacion" class="form-label">Año de Publicación:</label>
                            <input type="number" class="form-control" id="anioPublicacion" name="anioPublicacion"
                                   value="${libro != null ? libro.anioPublicacion : ''}" required>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="tipoBibliografia" class="form-label">Tipo de Bibliografía:</label>
                            <select class="form-select" id="tipoBibliografia" name="tipoBibliografia" required>
                                <option value="">Seleccione...</option>
                                <c:forEach var="tipo" items="${listaTipos}">
                                    <option value="${tipo.id}" ${libro != null && libro.tipoBibliografia == tipo.id ? 'selected' : ''}>
                                        ${tipo.descripcion}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="autor" class="form-label">Autor:</label>
                            <select class="form-select" id="autor" name="autor" required>
                                <option value="">Seleccione...</option>
                                <c:forEach var="autor" items="${listaAutores}">
                                    <option value="${autor.id}" ${libro != null && libro.autor == autor.id ? 'selected' : ''}>
                                        ${autor.nombre}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="editora" class="form-label">Editora:</label>
                            <select class="form-select" id="editora" name="editora" required>
                                <option value="">Seleccione...</option>
                                <c:forEach var="editora" items="${listaEditoras}">
                                    <option value="${editora.id}" ${libro != null && libro.editora == editora.id ? 'selected' : ''}>
                                        ${editora.descripcion}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="ciencia" class="form-label">Ciencia:</label>
                            <select class="form-select" id="ciencia" name="ciencia" required>
                                <option value="">Seleccione...</option>
                                <c:forEach var="ciencia" items="${listaCiencias}">
                                    <option value="${ciencia.id}" ${libro != null && libro.ciencia == ciencia.id ? 'selected' : ''}>
                                        ${ciencia.descripcion}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="idioma" class="form-label">Idioma:</label>
                            <select class="form-select" id="idioma" name="idioma" required>
                                <option value="">Seleccione...</option>
                                <c:forEach var="idioma" items="${listaIdiomas}">
                                    <option value="${idioma.id}" ${libro != null && libro.idioma == idioma.id ? 'selected' : ''}>
                                        ${idioma.descripcion}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="estado" class="form-label">Estado:</label>
                            <select class="form-select" id="estado" name="estado">
                                <option value="true" ${libro == null || libro.estado ? 'selected' : ''}>Disponible</option>
                                <option value="false" ${libro != null && !libro.estado ? 'selected' : ''}>No Disponible</option>
                            </select>
                        </div>
                    </div>

                    <div class="d-flex gap-2">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save"></i> Guardar
                        </button>
                        <a href="${pageContext.request.contextPath}/libros" class="btn btn-secondary">
                            <i class="fas fa-arrow-left"></i> Cancelar
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
