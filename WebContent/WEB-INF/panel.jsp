<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <jsp:include page="./parciales/header.jsp" />
    <body>
        <jsp:include page="./parciales/navegacion.jsp" />

        <!-- Contenido -->
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="./parciales/sidebar.jsp" />
                <main class="col-md-8 col-lg-9 col-xl-10 px-md-4" role="main">
                    <jsp:include page="${ pagina }" />
                </main>
            </div>
        </div>
        <!-- /Contenido -->

        <jsp:include page="./parciales/dependencias.jsp" />
    </body>
</html>
