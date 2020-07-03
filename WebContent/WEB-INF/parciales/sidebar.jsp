<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- Sidebar -->
            <nav id="sidebar" class="col-md-4 col-lg-3 col-xl-2 d-md-block bg-light sidebar collapse">
                <div class="sidebar-sticky pt-2">
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mb-1 mt-4 text-muted">Dashboard</h6>
                    <ul class="nav flex-column">
                        <li class="nav-item ml-2">
                            <a class="nav-link ${ activo != null ? (activo == 'home' ? 'active' : '') : '' }" href="<core:url value="/panel" />">
                                <i class="fas fa-clipboard mr-1"></i>
                                Reportes generales
                            </a>
                        </li>

                        <core:if test="${ rol != 'profesional' && rol != 'empresa' }">
                        <li class="nav-item ml-2">
                            <a class="nav-link ${ activo != null ? (activo == 'prof' ? 'active' : '') : '' }" href="<core:url value="/panel/profesionales" />">
                                <i class="fas fa-user-tie mr-1"></i>
                                Profesionales
                            </a>
                        </li>
                        </core:if>

                        <core:if test="${ rol != 'empresa' }">
                        <li class="nav-item ml-2">
                            <a class="nav-link ${ activo != null ? (activo == 'empr' ? 'active' : '') : '' }" href="<core:url value="/panel/empresas" />">
                                <i class="fas fa-address-book mr-1"></i>
                                Clientes
                            </a>
                        </li>
                        </core:if>

                        <li class="nav-item ml-2">
                            <a class="nav-link ${ activo != null ? (activo == 'acci' ? 'active' : '') : '' }" href="<core:url value="/panel/accidentes" />">
                                <i class="fas fa-notes-medical mr-1"></i>
                                Accidentes
                            </a>
                        </li>
                        
                        <li class="nav-item ml-2">
                            <a class="nav-link ${ activo != null ? (activo == 'capa' ? 'active' : '') : '' }" href="<core:url value="/panel/capacitaciones" />">
                                <i class="fas fa-chalkboard-teacher mr-1"></i>
                                Capacitaciones
                            </a>
                        </li>
                        
                        <li class="nav-item ml-2">
                            <a class="nav-link ${ activo != null ? (activo == 'ases' ? 'active' : '') : '' }" href="<core:url value="/panel/asesorias" />">
                                <i class="fas fa-people-arrows mr-1"></i>
                                Asesorías
                            </a>
                        </li>
                        
                        <li class="nav-item ml-2">
                            <a class="nav-link ${ activo != null ? (activo == 'visi' ? 'active' : '') : '' }" href="<core:url value="/panel/visitas" />">
                                <i class="fas fa-calendar-check mr-1"></i>
                                Visitas
                            </a>
                        </li>
                    </ul>

                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">Cuenta</h6>
                    <ul class="nav flex-column mb-2">
                        <core:if test="${ rol ne 'admin' }">
                        <li class="nav-item ml-2">
                            <a class="nav-link ${ activo != null ? (activo == 'perf' ? 'active' : '') : '' }" href="<core:url value="/panel/perfil" />">
                                <i class="far fa-user-circle mr-1"></i>
                                Mi Perfil
                            </a>
                        </li>
                        </core:if>
                        <li class="nav-item ml-2">
                            <a class="nav-link" href="<core:url value="/auth/logout" />">
                                <i class="fas fa-sign-out-alt mr-1"></i>
                                Cerrar Sesión
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
    <!-- /Sidebar -->

