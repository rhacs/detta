<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- Sidebar -->
    <div class="container-fluid">
        <div class="row">
            <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
                <div class="sidebar-sticky pt-3">
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">DASHBOARD</h6>

                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">CUENTA</h6>
                    <ul class="nav flex-column mb-2">
                        <li class="nav-item">
                            <a class="nav-link" href="<core:url value="/panel/perfil" />">
                                <i class="far fa-user-circle mr-1"></i>
                                Mi Perfil
                            </a>
                            <a class="nav-link" href="<core:url value="/auth/logout" />">
                                <i class="fas fa-sign-out-alt mr-1"></i>
                                Cerrar Sesión
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
    <!-- /Sidebar -->
