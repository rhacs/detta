<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <!-- Codificación de Caracteres -->
        <meta charset="UTF-8">
        
        <!-- Configuración inicial del ancho y escalas -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!-- Título -->
        <title>Detta</title>
        
        <!-- Hojas de Estilo -->
        <link rel="stylesheet" href="<core:url value="/resources/css/home.css" />" type="text/css">
    </head>
    <body>

        <div class="contenedor">
            <h1>detta</h1>
            
            <form method="post">
                <core:if test="${ not empty error }">
                <div class="alerta">${ error }</div>
                </core:if>
                <div class="otro-contenedor">
                    <input class="top" type="email" id="email" name="email" placeholder="Correo electrónico" autocomplete="email" value="<core:if test="${ not empty email }">${ email }</core:if>" autofocus>
                    <input class="bottom" type="password" id="password" name="password" placeholder="Contraseña">
                </div>

                <button type="submit">Iniciar Sesión</button>
            </form>
        </div>

        <!-- JavaScripts -->
        <script type="text/javascript" src="<core:url value="/resources/js/libs/jquery-3.5.1.min.js" />"></script>
        <script type="text/javascript" src="<core:url value="/resources/js/libs/jquery.validate-1.19.2.min.js" />"></script>
        <script type="text/javascript" src="<core:url value="/resources/js/libs/jquery.loading-overlay-2.1.7.min.js" />"></script>

        <script type="text/javascript" src="<core:url value="/resources/js/home.js" />"></script>
    </body>
</html>