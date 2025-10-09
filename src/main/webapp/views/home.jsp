<%@ include file="header.jsp" %>

<div class="row">
    <div class="col-12 text-center mb-4">
        <h1><i class="fas fa-book-open"></i> Sistema de Gestión de Biblioteca Universitaria APEC</h1>
        <p class="lead">Bienvenido al sistema de gestión de biblioteca</p>
    </div>
</div>

<div class="row g-4">
    <div class="col-md-4">
        <div class="card h-100 shadow-sm">
            <div class="card-body text-center">
                <i class="fas fa-folder fa-3x text-primary mb-3"></i>
                <h5 class="card-title">Catálogos</h5>
                <p class="card-text">Gestión de tipos de bibliografía, editoras, ciencias, idiomas y autores.</p>
                <a href="${pageContext.request.contextPath}/tipos-bibliografia" class="btn btn-primary">Acceder</a>
            </div>
        </div>
    </div>

    <div class="col-md-4">
        <div class="card h-100 shadow-sm">
            <div class="card-body text-center">
                <i class="fas fa-book fa-3x text-success mb-3"></i>
                <h5 class="card-title">Libros</h5>
                <p class="card-text">Administración completa del catálogo de libros de la biblioteca.</p>
                <a href="${pageContext.request.contextPath}/libros" class="btn btn-success">Gestionar</a>
            </div>
        </div>
    </div>

    <div class="col-md-4">
        <div class="card h-100 shadow-sm">
            <div class="card-body text-center">
                <i class="fas fa-users fa-3x text-info mb-3"></i>
                <h5 class="card-title">Usuarios</h5>
                <p class="card-text">Registro y gestión de usuarios de la biblioteca universitaria.</p>
                <a href="${pageContext.request.contextPath}/usuarios" class="btn btn-info text-white">Administrar</a>
            </div>
        </div>
    </div>

    <div class="col-md-4">
        <div class="card h-100 shadow-sm">
            <div class="card-body text-center">
                <i class="fas fa-user-tie fa-3x text-warning mb-3"></i>
                <h5 class="card-title">Empleados</h5>
                <p class="card-text">Control de empleados que asisten a los usuarios de la biblioteca.</p>
                <a href="${pageContext.request.contextPath}/empleados" class="btn btn-warning">Ver Empleados</a>
            </div>
        </div>
    </div>

    <div class="col-md-4">
        <div class="card h-100 shadow-sm">
            <div class="card-body text-center">
                <i class="fas fa-exchange-alt fa-3x text-danger mb-3"></i>
                <h5 class="card-title">Préstamos</h5>
                <p class="card-text">Gestión de préstamos y devoluciones de material bibliográfico.</p>
                <a href="${pageContext.request.contextPath}/prestamos" class="btn btn-danger">Gestionar</a>
            </div>
        </div>
    </div>

    <div class="col-md-4">
        <div class="card h-100 shadow-sm">
            <div class="card-body text-center">
                <i class="fas fa-chart-bar fa-3x text-secondary mb-3"></i>
                <h5 class="card-title">Reportes</h5>
                <p class="card-text">Consultas y reportes de rentas por diversos criterios.</p>
                <a href="${pageContext.request.contextPath}/reportes" class="btn btn-secondary">Ver Reportes</a>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
