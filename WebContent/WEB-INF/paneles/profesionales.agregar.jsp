<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <jsp:include page="../parciales/header.jsp" />
    <body>
        <jsp:include page="../parciales/navegacion.jsp" />

        <!-- Contenido -->
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="../parciales/sidebar.jsp" />

                <main class="col-md-8 ml-sm-auto col-lg-9 col-xl-10 px-md-4" role="main">

                    <h3 class="border-bottom pt-3 pb-2 mb-3">Información del Profesional</h3>
                    <div class="row mb-4">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">Pefil</div>
                                <div class="card-body">
                                    <form method="post" action="${ pageContext.request.contextPath }/panel/profesionales/">
                                        <input type="hidden" value="${ profesional != null ? profesional.getId() : '' }">

                                        <div class="form-row">
                                            <div class="form-group col-lg-6 col-md-12">
                                                <label for="nombre">Nombre</label>
                                                <input type="text" class="form-control" value="${ profesional != null ? profesional.getNombre() : '' }" name="nombre" id="nombre" autocomplete="name" required>
                                            </div>

                                            <div class="form-group col-lg-6 col-md-12">
                                                <label for="email">Correo electrónico</label>
                                                <input type="email" class="form-control" value="${ profesional != null ? profesional.getEmail() : '' }" name="email" id="email" autocomplete="email" required>
                                            </div>
                                        </div>

                                        <div class="form-row">
                                            <div class="form-group col-lg-6 col-md-12">
                                                <label for="telefono">Teléfono de Contacto</label>
                                                <input type="text" class="form-control" value="${ profesional != null ? profesional.getTelefono() : '' }" name="telefono" id="telefono" autocomplete="tel" required>
                                            </div>

                                            <div class="form-group col-lg-6 col-md-12">
                                                <label for="estadoContrato">Estado del Contrato</label>
                                                <select class="form-control" name="estadoContrato" id="estadoContrato">
                                                    <option value="Vigente" ${ profesional != null ? (profesional.getEstadoContrato() == 'Vigente' ? 'selected' : '') : '' }>Vigente</option>
                                                    <option value="Terminado" ${ profesional != null ? (profesional.getEstadoContrato() == 'Terminado' ? 'selected' : '') : ''}>Terminado</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-row justify-content-end mt-4">
                                            <button type="button" class="btn btn-warning mr-2" data-cancelar>Cancelar</button>
                                            <button type="submit" class="btn btn-primary">${ accion }</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                </main>
            </div>
        </div>
        <!-- /Contenido -->

        <jsp:include page="../parciales/dependencias.jsp" />
        <script type="text/javascript" src="<core:url value="/resources/js/profesionales.js" />"></script>
    </body>
</html>