<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- Head -->
    <head>
        <!-- Codificaci�n de Caracteres -->
        <meta charset="UTF-8">

        <!-- Configuraci�n del ancho y escala iniciales -->
        <meta name="viewport" content="widt=device-width, initial-scale=1.0">

        <!-- T�tulo -->
        <title>detta <core:if test="${ not empty titulo }">- <core:out value="${ titulo }" /></core:if></title>

        <!-- Hojas de Estilo -->
        <link rel="stylesheet" href="<core:url value="/resources/css/bootstrap.min.css" />">
    </head>
    <!-- /Head -->
