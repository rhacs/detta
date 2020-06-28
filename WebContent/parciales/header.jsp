    <%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- Head -->
    <head>
        <!-- Codificación de Caracteres -->
        <meta charset="UTF-8">
        
        <!-- Controlar tamaño y escala iniciales de la página -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!-- Título de la página -->
        <title>Detta <core:if test="${not empty titulo}"> - ${titulo}</core:if></title>
        
        <!-- Hojas de Estilo -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/estilo.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/responsividad.css">
    </head>
    <!-- /Head -->
