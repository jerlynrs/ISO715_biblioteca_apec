<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Biblioteca APEC</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <style>
        body {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }
        .navbar-brand {
            font-weight: bold;
        }
        .main-content {
            flex: 1;
            padding: 20px 0;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
                <i class="fas fa-book"></i> Biblioteca APEC
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="catalogoDropdown" role="button" data-bs-toggle="dropdown">
                            <i class="fas fa-folder"></i> Catálogos
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/tipos-bibliografia">Tipos de Bibliografía</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/editoras">Editoras</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/ciencias">Ciencias</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/idiomas">Idiomas</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/autores">Autores</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/libros">
                            <i class="fas fa-book"></i> Libros
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/usuarios">
                            <i class="fas fa-users"></i> Usuarios
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/empleados">
                            <i class="fas fa-user-tie"></i> Empleados
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/prestamos">
                            <i class="fas fa-exchange-alt"></i> Préstamos
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="main-content">
        <div class="container">
