<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ include file="header.jsp" %>

<div class="row">
    <div class="col-12 text-center">
        <div class="alert alert-danger" role="alert">
            <h2><i class="fas fa-exclamation-triangle"></i> Error</h2>
            <p>Ha ocurrido un error en la aplicación.</p>
            <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">
                <i class="fas fa-home"></i> Volver al Inicio
            </a>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
