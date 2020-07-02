<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
        <!-- Navegación -->
        <nav class="navbar navbar-expand-md navbar-dark bg-primary">
            <div class="container">
                <a class="navbar-brand" href="${ pageContext.request.contextPath }">
                    <i class="fas fa-crutch mr-1"></i>
                    detta
                </a>

                <!-- Botón de navegación para dispositivos móviles -->
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#menu" aria-controls="menu" aria-expanded="false" aria-label="Menú de Navegación">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <!-- Menú -->
                <div class="collapse navbar-collapse" id="menu">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item ${ activo != null ? (activo == 'home' ? 'active' : '') : '' }">
                            <a class="nav-link" href="${ pageContext.request.contextPath }">Home</a>
                        </li>

                        <li class="nav-item ${ activo != null ? (activo == 'profesionales' ? 'active' : '') : '' }">
                            <a class="nav-link" href="${ pageContext.request.contextPath }/profesionales">Profesionales</a>
                        </li>

                        <li class="nav-item ${ activo != null ? (activo == 'clientes' ? 'active' : '') : '' }">
                            <a class="nav-link" href="${ pageContext.request.contextPath }/clientes">Clientes</a>
                        </li>

                        <li class="nav-item mr-2 ${ activo != null ? (activo == 'accidentes' ? 'active' : '') : '' }">
                            <a class="nav-link" href="${ pageContext.request.contextPath }/accidentes">Accidentes</a>
                        </li>

                        <li class="nav-item">
                            <div class="btn-group">
                                <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Cuenta</button>

                                <div class="dropdown-menu">
                                    <core:choose>
                                        <core:when test="${ not empty loggedIn }">
                                        <a class="dropdown-item" href="${ pageContext.request.contextPath }/auth/perfil">Ver perfil</a>
                                        <a class="dropdown-item" href="${ pageContext.request.contextPath }/auth/logout">Cerrar sesión</a>
                                        </core:when>

                                        <core:otherwise>
                                        <a class="dropdown-item" href="${ pageContext.request.contextPath }/auth/login">Iniciar sesión</a>
                                        </core:otherwise>
                                    </core:choose>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- /Navegación -->
