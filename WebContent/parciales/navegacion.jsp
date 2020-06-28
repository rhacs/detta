
        <!-- Navegación -->
        <nav class="navbar navbar-expand-md navbar-dark bg-info shadow">
            <div class="container">
                <!-- Logo -->
                <a class="navbar-brand" href="${pageContext.request.contextPath}/accidentes">
                    <i class="fas fa-crutch mr-1"></i>
                    Detta
                </a>
                
                <!-- Botón menú para dispositivos móviles -->
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#menu" aria-controls="menu" aria-expanded="false" aria-label="Menú">
                    <span class="navbar-toggler-icon"></span>
                </button>
                
                <!-- Menú -->
                <div class="collapse navbar-collapse" id="menu">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item ${activo == 'accidentes' ? 'active' : ''}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/accidentes">Listado de Accidentes</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- /Navegación -->