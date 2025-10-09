<%@ include file="/views/header.jsp" %>

<div class="welcome-section">
    <h2>Bienvenido al Sistema de Gestión de Biblioteca APEC</h2>
    <p>Seleccione una opción del menú para comenzar</p>

    <div class="modules-grid">
        <div class="module-card">
            <h3>Tipos de Bibliografía</h3>
            <p>Gestionar catálogo de tipos de publicaciones</p>
            <a href="${pageContext.request.contextPath}/tipos-bibliografia" class="btn">Acceder</a>
        </div>

        <div class="module-card">
            <h3>Ciencias</h3>
            <p>Gestionar áreas del conocimiento</p>
            <a href="${pageContext.request.contextPath}/ciencias" class="btn">Acceder</a>
        </div>

        <div class="module-card">
            <h3>Autores</h3>
            <p>Gestionar catálogo de autores</p>
            <a href="${pageContext.request.contextPath}/autores" class="btn">Acceder</a>
        </div>
    </div>
</div>

<%@ include file="/views/footer.jsp" %>
