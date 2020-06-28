    <%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- Head -->
    <head>
        <!-- Codificaci�n de Caracteres -->
        <meta charset="UTF-8">
        
        <!-- Controlar tama�o y escala iniciales de la p�gina -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!-- T�tulo de la p�gina -->
        <title>Detta <core:if test="${not empty titulo}"> - ${titulo}</core:if></title>
        
        <!-- Hojas de Estilo -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/estilo.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/responsividad.css">
    </head>
    <!-- /Head -->
