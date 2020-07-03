<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- Head -->
    <head>
        <!-- Codificación de Caracteres -->
        <meta charset="UTF-8">

        <!-- Configuración del ancho y escala iniciales -->
        <meta name="viewport" content="widt=device-width, initial-scale=1.0">

        <!-- Título -->
        <title>detta <core:if test="${ not empty titulo }">- <core:out value="${ titulo }" /></core:if></title>

        <!-- Hojas de Estilo -->
        <link rel="stylesheet" href="<core:url value="/resources/css/bootstrap.min.css" />">
    </head>
    <!-- /Head -->
