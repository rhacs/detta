<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <jsp:include page="../parciales/header.jsp" />
    <body>
        <jsp:include page="../parciales/navegacion.jsp" />

        <core:if test="${ not empty ver }">
            <core:set var="disabled" value="disabled" />
        </core:if>

        <!-- Contenido -->
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="../parciales/sidebar.jsp" />

                <main class="col-md-8 ml-sm-auto col-lg-9 col-xl-10 px-md-4" role="main">
                    <h3 class="border-bottom pt-3 pb-2 mb-3">Detalles del Cliente</h3>

                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">Perfil de la Empresa</div>
                                <div class="card-body">
                                    <form action="${ pageContext.request.contextPath }/panel/clientes/" method="post">
                                        <input type="hidden" name="id" id="id" value="${ empresa != null ? empresa.getId() : '' }">

                                        <div class="form-row">
                                            <div class="form-group col-lg-6 col-md-12">
                                                <label for="nombre">Nombre o Razón Social</label>
                                                <input type="text" class="form-control" name="nombre" id="nombre" value="${ empresa != null ? empresa.getNombre() : '' }" ${ disabled } required>
                                            </div>
                                            <div class="form-group col-lg-6 col-md-12">
                                                <label for="rut">Rol Único Tributario</label>
                                                <input type="text" class="form-control" name="rut" id="rut" value="${ empresa != null ? empresa.getRut() : '' }" ${ disabled } required>
                                            </div>
                                        </div>

                                        <div class="form-row">
                                            <div class="form-group col-lg-8 col-md-12">
                                                <label for="direccion">Dirección</label>
                                                <input type="text" class="form-control" name="direccion" id="direccion" value="${ empresa != null ? empresa.getDireccion() : '' }" ${ disabled } required>
                                            </div>
                                            <div class="form-group col-lg-4 col-md-12">
                                                <label for="telefono">Teléfono</label>
                                                <input type="tel" class="form-control" name="telefono" id="telefono" value="${ empresa != null ? empresa.getTelefono() : '' }" ${ disabled } required>
                                            </div>
                                        </div>

                                        <div class="form-row">
                                            <div class="form-group col-lg-4 col-md-12">
                                                <label for="email">Correo electrónico</label>
                                                <input type="email" class="form-control" name="email" id="email" value="${ empresa != null ? empresa.getEmail() : '' }" ${ disabled } required>
                                            </div>

                                            <div class="form-group col-lg-4 col-md-12">
                                                <label for="giro">Actividad Económica</label>
                                                <input type="text" class="form-control" name="giro" id="giro" value="${ empresa != null ? empresa.getGiro() : '' }" ${ disabled } required>
                                            </div>

                                            <div class="form-group col-lg-4 col-md-12">
                                                <label for="trabajadores">Cantidad de Trabajadores</label>
                                                <input type="number" class="form-control" name="trabajadores" id="trabajadores" value="${ empresa != null ? empresa.getTrabajadores() : '' }" ${ disabled } required>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="tipo">Tipo de Empresa</label>
                                            <select name="tipo" id="tipo" class="form-control" ${ disabled }>
                                                <option value="Principal" ${ empresa != null ? (empresa.getTipo() == 'Principal' ? 'selected' : '') : '' }>Principal</option>
                                                <option value="Contratista" ${ empresa != null ? (empresa.getTipo() == 'Contratista' ? 'selected' : '') : '' }>Contratista</option>
                                                <option value="Subcontratista" ${ empresa != null ? (empresa.getTipo() == 'Subcontratista' ? 'selected' : '') : '' }>Subcontratista</option>
                                                <option value="De Servicios Transitorios" ${ empresa != null ? (empresa.getTipo() == 'De Servicios Transitorios' ? 'selected' : '') : '' }>De Servicios Transitorios</option>
                                            </select>
                                        </div>

                                        <div class="form-group text-right">
                                            <core:choose>
                                                <core:when test="${ not empty ver }">
                                                    <button type="button" data-accion="editar" data-id="${ empresa != null ? empresa.getId() : '' }" class="btn btn-primary">Editar</button>
                                                    <button type="button" data-accion="eliminar" data-id="${ empresa != null ? empresa.getId() : '' }" class="btn btn-danger">Eliminar</button>
                                                </core:when>
                                                <core:otherwise>
                                                    <button type="button" data-accion="cancelar" class="btn btn-warning">Cancelar</button>
                                                    <button type="submit" class="btn btn-primary">${ accion }</button>
                                                </core:otherwise>
                                            </core:choose>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <core:if test="${ not empty ver }">
                        <div class="col-12 mt-4">
                            <div class="card">
                                <div class="card-header">Accidentes</div>
                                <div class="card-body">
                                    <div class="table-responsive-md">
                                        <table class="table table-hover table-striped">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th scope="col" class="text-nowrap">#</th>
                                                    <th scope="col" class="text-nowrap">Fecha</th>
                                                    <th scope="col" class="text-nowrap">Hora</th>
                                                    <th scope="col" class="text-nowrap">Clasificacion</th>
                                                    <th scope="col" class="text-nowrap">Tipo</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <core:choose>
                                                    <core:when test="${ accidentes == null || accidentes.size() == 0 }">
                                                        <tr>
                                                            <th scope="row" colspan="5" class="text-center">No hay registros</th>
                                                        </tr>
                                                    </core:when>
                                                    <core:otherwise>
                                                        <core:forEach items="${ accidentes }" var="accidente">
                                                            <tr role="button" data-accidenteid="${ accidente.getId() }">
                                                                <th scope="row">${ accidente.getId() }</th>
                                                                <td class="text-nowrap">${ accidente.getFecha() }</td>
                                                                <td class="text-nowrap">${ accidente.getHora() }</td>
                                                                <td class="text-nowrap">${ accidente.getClasificacion() }</td>
                                                                <td class="text-nowrap">${ accidente.getTipo() }</td>
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
                        </core:if>
                    </div>
                </main>
            </div>
        </div>
        <!-- /Contenido -->

        <jsp:include page="../parciales/dependencias.jsp" />
        <script type="text/javascript" src="<core:url value="/resources/js/panel.js" />"></script>
        <script type="text/javascript" src="<core:url value="/resources/js/clientes.js" />"></script>
    </body>
</html>
