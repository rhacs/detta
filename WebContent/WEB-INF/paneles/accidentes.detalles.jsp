<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <jsp:include page="../parciales/header.jsp" />
    <body>
        <jsp:include page="../parciales/navegacion.jsp" />

        <core:if test="${ not empty ver }">
            <core:set var="disable" value="disabled" />
        </core:if>

        <!-- Contenido -->
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="../parciales/sidebar.jsp" />

                <main class="col-md-8 ml-sm-auto col-lg-9 col-xl-10 px-md-4" role="main">

                    <h3 class="border-bottom pt-3 pb-2 mb-3">Información del Accidente</h3>
                    <div class="row mb-4">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                    <form method="post" action="${ pageContext.request.contextPath }/panel/accidentes/">
                                        <input type="hidden" name="id" id="id" value="${ accidente != null ? accidente.getId() : '' }">

                                        <div class="form-row">
                                            <div class="form-group col-lg-2 col-md-6 col-sm-12">
                                                <label for="fecha">Fecha</label>
                                                <input type="date" class="form-control" name="fecha" id="fecha" value="${ accidente != null ? accidente.getFecha() : '' }" ${ disable } required>
                                            </div>

                                            <div class="form-group col-lg-2 col-md-6 col-sm-12">
                                                <label for="hora">Hora</label>
                                                <input type="time" class="form-control" name="hora" id="hora" value="${ accidente != null ? accidente.getHora() : '' }" ${ disable } required>
                                            </div>

                                            <div class="form-group col-lg-8 col-md-12">
                                                <label for="direccion">Direccion</label>
                                                <input type="text" class="form-control" name="direccion" id="direccion" value="${ accidente != null ? accidente.getDireccion() : '' }" ${ disable } required>
                                            </div>
                                        </div>

                                        <div class="form-row">
                                            <div class="form-group col-lg-6 col-md-12">
                                                <label for="lugar">Lugar</label>
                                                <input type="text" class="form-control" name="lugar" id="lugar" value="${ accidente != null ? accidente.getLugar() : '' }" ${ disable } requred>
                                            </div>

                                            <div class="form-group col-lg-6 col-md-12">
                                                <label for="circunstancia">Circunstancia</label>
                                                <input type="text" class="form-control" name="circunstancia" id="circunstancia" value="${ accidente != null ? accidente.getCircunstancia() : '' }" ${ disable } required>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="detalles">Detalles</label>
                                            <textarea class="form-control" name="detalles" id="detalles" rows="4" ${ disable } required>${ accidente != null ? accidente.getDetalles() : '' }</textarea>
                                        </div>

                                        <div class="form-row">
                                            <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                                <label for="clasificacion">Clasificación</label>
                                                <select class="form-control" name="clasificacion" id="clasificacion" ${ disable } required>
                                                    <option value="Leve" ${ accidente != null ? (accidente.getClasificacion() == 'Leve' ? 'selected' : '') : '' }>Leve</option>
                                                    <option value="Grave" ${ accidente != null ? (accidente.getClasificacion() == 'Grave' ? 'selected' : '') : '' }>Grave</option>
                                                    <option value="Fatal" ${ accidente != null ? (accidente.getClasificacion() == 'Fatal' ? 'selected' : '') : '' }>Fatal</option>
                                                    <option value="Otro" ${ accidente != null ? (accidente.getClasificacion() == 'Otro' ? 'selected' : '') : '' }>Otro</option>
                                                </select>
                                            </div>

                                            <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                                <label for="tipo">Tipo de Accidente</label>
                                                <select class="form-control" name="tipo" id="tipo" ${ disable } required>
                                                    <option value="Trabajo" ${ accidente != null ? (accidente.getTipo() == 'Trabajo' ? 'selected' : '') : '' }>Trabajo</option>
                                                    <option value="Trayecto" ${ accidente != null ? (accidente.getTipo() == 'Trayecto' ? 'selected' : '') : '' }>Trayecto</option>
                                                </select>
                                            </div>

                                            <div class="form-group col-lg-4 col-md-12">
                                                <label for="medioPrueba">Medio de Prueba</label>
                                                <select class="form-control" name="medioPrueba" id="medioPrueba" ${ disable } required>
                                                    <option value="Parte de Carabineros" ${ accidente != null ? (accidente.getMedioPrueba() == 'Parte de Carabineros' ? 'selected' : '') : '' }>Parte de Carabineros</option>
                                                    <option value="Declaración" ${ accidente != null ? (accidente.getMedioPrueba() == 'Declaración' ? 'selected' : '') : '' }>Declaración</option>
                                                    <option value="Testigos" ${ accidente != null ? (accidente.getMedioPrueba() == 'Testigos' ? 'selected' : '') : '' }>Testigos</option>
                                                    <option value="Otro" ${ accidente != null ? (accidente.getMedioPrueba() == 'Otro' ? 'selected' : '') : '' }>Otro</option>
                                                </select>
                                            </div>
                                        </div>

                                        <core:if test="${ empresas != null && empresas.size() > 0 }">
                                        <div class="form-group">
                                            <label for="empresa">Empresa</label>
                                            <select class="form-control" name="empresa" id="empresa" ${ disable } required>
                                                <core:forEach items="${ empresas }" var="empresa">
                                                <option value="${ empresa.getId() }" ${ accidente.getEmpresaId() == empresa.getId() ? 'selected' : '' }>${ empresa.getNombre() }</option>
                                                </core:forEach>
                                            </select>
                                        </div>
                                        </core:if>

                                        <div class="form-group text-right mt-4">
                                            <core:choose>
                                                <core:when test="${ not empty ver }">
                                                    <button type="button" class="btn btn-primary" data-accion="editar" data-id="${ accidente != null ? accidente.getId() : '' }">Editar</button>
                                                    <button type="button" class="btn btn-danger ml-2" data-accion="eliminar" data-id="${ accidente != null ? accidente.getId() : '' }">Eliminar</button>
                                                </core:when>
                                                <core:otherwise>
                                                    <button type="button" class="btn btn-warning mr-2" data-accion="cancelar">Cancelar</button>
                                                    <button type="submit" class="btn btn-primary">${ accion }</button>
                                                </core:otherwise>
                                            </core:choose>
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
        <script type="text/javascript" src="<core:url value="/resources/js/panel.js" />"></script>
        <script type="text/javascript" src="<core:url value="/resources/js/accidentes.js" />"></script>
    </body>
</html>