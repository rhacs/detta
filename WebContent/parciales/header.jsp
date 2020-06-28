    <%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
    <!-- Head -->
    <head>
        <!-- Codificación de Caracteres -->
        <meta charset="UTF-8">
        
        <!-- Controlar tamaño y escala iniciales de la página -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!-- Título de la página -->
        <title>Detta <core:if test="${not empty titulo}"> - ${titulo}</core:if></title>
        
        <!-- Hojas de Estilo -->
        <link rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css"
            integrity="sha256-aAr2Zpq8MZ+YA/D6JtRD3xtrwpEz2IqOS+pWD/7XKIw=" crossorigin="anonymous" />
    </head>
    <!-- /Head -->
