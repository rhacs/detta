<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<core:if test="${ ver != null }">
    <core:set var="disable" value="disabled" />
</core:if>

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
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">Pefil</div>
                                <div class="card-body">
                                    <form method="post">
                                        <div class="form-row">
                                            <div class="form-group col-lg-6 col-md-12">
                                                <label for="nombre">Nombre</label>
                                                <input type="text" class="form-control" ${ disable } value="${ profesional != null ? profesional.getNombre() : '' }" name="nombre" id="nombre" autocomplete="name" required>
                                            </div>

                                            <div class="form-group col-lg-6 col-md-12">
                                                <label for="email">Correo electrónico</label>
                                                <input type="email" class="form-control" ${ disable } value="${ profesional != null ? profesional.getEmail() : '' }" name="email" id="email" autocomplete="email" required>
                                            </div>
                                        </div>

                                        <div class="form-row">
                                            <div class="form-group col-lg-6 col-md-12">
                                                <label for="telefono">Teléfono de Contacto</label>
                                                <input type="text" class="form-control" ${ disable } value="${ profesional != null ? profesional.getTelefono() : '' }" name="telefono" id="telefono" autocomplete="tel" required>
                                            </div>

                                            <div class="form-group col-lg-6 col-md-12">
                                                <label for="estadoContrato">Estado del Contrato</label>
                                                <select class="form-control" ${ disable } name="estadoContrato" id="estadoContrato">
                                                    <option value="Vigente" ${ profesional != null ? (profesional.getEstadoContrato() == 'Vigente' ? 'selected' : '') : '' }>Vigente</option>
                                                    <option value="Terminado" ${ profesional != null ? (profesional.getEstadoContrato() == 'Terminado' ? 'selected' : '') : ''}>Terminado</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-row justify-content-end mt-4">
                                            <button type="button" class="btn btn-primary" data-accion="editar" data-id="${ profesional != null ? profesional.getId() : '' }">Editar</button>
                                            <button type="button" class="btn btn-danger ml-3" data-accion="borrar" data-id="${ profesional != null ? profesional.getId() : '' }">Eliminar</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row mt-4">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">Clientes</div>
                                <div class="card-body">
                                    <div class="table-responsive-md">
                                        <table class="table table-hover table-striped">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th scope="col" class="text-nowrap">#</th>
                                                    <th scope="col" class="text-nowrap">Nombre</th>
                                                    <th scope="col" class="text-nowrap">R.U.T.</th>
                                                    <th scope="col" class="text-nowrap">Dirección</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <core:choose>
                                                <core:when test="${ empresas == null || empresas.size() == 0 }">
                                                    <tr>
                                                        <th scope="row" class="text-center" colspan="4">No hay registros</th>
                                                    </tr>
                                                </core:when>
                                                <core:otherwise>
                                                    <core:forEach items="${ empresas }" var="empresa">
                                                        <tr data-empresaid="${ empresa.getId() }" role="button">
                                                            <th scope="row">${ empresa.getId() }</th>
                                                            <td>${ empresa.getNombre() }</td>
                                                            <td>${ empresa.getRut() }</td>
                                                            <td>${ empresa.getDireccion() }</td>
                                                        </tr>
                                                    </core:forEach>
                                                </core:otherwise>
                                            </core:choose>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row mt-4">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">Capacitaciones</div>
                                <div class="card-body">
                                    <div class="table-responsive-md">
                                        <table class="table table-hover table-striped">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th scope="col" class="text-nowrap">#</th>
                                                    <th scope="col" class="text-nowrap">Fecha</th>
                                                    <th scope="col" class="text-nowrap">Hora</th>
                                                    <th scope="col" class="text-nowrap">Empresa</th>
                                                </tr>
                                            </thead>

                                            <tbody>
                                            <core:choose>
                                                <core:when test="${ capacitaciones == null || capacitaciones.size() == 0 }">
                                                    <tr>
                                                        <th scope="row" class="text-center" colspan="4">No hay registros</th>
                                                    </tr>
                                                </core:when>
                                                <core:otherwise>
                                                    <core:forEach items="${ capacitaciones }" var="capacitacion">
                                                        <tr data-capacitacionid="${ capacitacion.getId() }" role="button">
                                                            <th scope="row">${ capacitacion.getId() }</th>
                                                            <td>${ capacitacion.getFecha() }</td>
                                                            <td>${ capacitacion.getHora() }</td>
                                                            <td>${ capacitacion.getEmpresa() }</td>
                                                        </tr>
                                                    </core:forEach>
                                                </core:otherwise>
                                            </core:choose>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row mt-4">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">Asesorías</div>
                                <div class="card-body">
                                    <div class="table-responsive-md">
                                        <table class="table table-hover table-striped">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th scope="col" class="text-nowrap">#</th>
                                                    <th scope="col" class="text-nowrap">Fecha</th>
                                                    <th scope="col" class="text-nowrap">Hora</th>
                                                    <th scope="col" class="text-nowrap">Empresa</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <core:choose>
                                                <core:when test="${ asesorias == null || asesorias.size() == 0 }">
                                                    <tr>
                                                        <th scope="row" class="text-center" colspan="4">No hay registros</th>
                                                    </tr>
                                                </core:when>
                                                <core:otherwise>
                                                    <core:forEach items="${ asesorias }" var="asesoria">
                                                        <tr data-capacitacionid="${ asesoria.getId() }" role="button">
                                                            <th scope="row">${ asesoria.getId() }</th>
                                                            <td>${ asesoria.getFecha() }</td>
                                                            <td>${ asesoria.getHora() }</td>
                                                            <td>${ asesoria.getEmpresa() }</td>
                                                        </tr>
                                                    </core:forEach>
                                                </core:otherwise>
                                            </core:choose>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row my-4">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">Visitas</div>
                                <div class="card-body">
                                    <div class="table-responsive-md">
                                        <table class="table table-hover table-striped">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th scope="col" class="text-nowrap">#</th>
                                                    <th scope="col" class="text-nowrap">Fecha</th>
                                                    <th scope="col" class="text-nowrap">Hora</th>
                                                    <th scope="col" class="text-nowrap">Empresa</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <core:choose>
                                                <core:when test="${ visitas == null || visitas.size() == 0 }">
                                                    <tr>
                                                        <th scope="row" class="text-center" colspan="4">No hay registros</th>
                                                    </tr>
                                                </core:when>
                                                <core:otherwise>
                                                    <core:forEach items="${ visitas }" var="visita">
                                                        <tr data-visitaid="${ visita.getId() }" role="button">
                                                            <th scope="row">${ visita.getId() }</th>
                                                            <td>${ visita.getFecha() }</td>
                                                            <td>${ visita.getHora() }</td>
                                                            <td>${ visita.getEmpresa() }</td>
                                                        </tr>
                                                    </core:forEach>
                                                </core:otherwise>
                                            </core:choose>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
        <!-- /Contenido -->

        <jsp:include page="../parciales/dependencias.jsp" />
        <script type="text/javascript" src="<core:url value="/resources/js/panel.js" />"></script>
        <script type="text/javascript" src="<core:url value="/resources/js/profesionales.js" />"></script>
    </body>
</html>