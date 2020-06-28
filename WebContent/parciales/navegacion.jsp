
        <!-- Navegaci�n -->
        <nav class="navbar navbar-expand-md navbar-dark bg-info shadow">
            <div class="container">
                <!-- Logo -->
                <a class="navbar-brand" href="${pageContext.request.contextPath}/accidentes">
                    <i class="fas fa-crutch mr-1"></i>
                    Detta
                </a>
                
                <!-- Bot�n men� para dispositivos m�viles -->
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#menu" aria-controls="menu" aria-expanded="false" aria-label="Men�">
                    <span class="navbar-toggler-icon"></span>
                </button>
                
                <!-- Men� -->
                <div class="collapse navbar-collapse" id="menu">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item ${activo == 'accidentes' ? 'active' : ''}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/accidentes">Listado de Accidentes</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- /Navegaci�n -->