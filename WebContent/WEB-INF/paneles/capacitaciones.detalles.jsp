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
                    <h3 class="border-bottom pt-3 pb-2 mb-3">Capacitaciones</h3>

                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">Detalles</div>
                                <div class="card-body">
                                    <form action="${ pageContext.request.contextPath }/panel/capacitaciones/" method="post">
                                        <div class="form-row">
                                            <div class="form-group col-lg-2 col-md-4 col-sm-12">
                                                <label for="fecha">Fecha</label>
                                                <input type="date" class="form-control" name="fecha" id="fecha" value="${ capacitacion != null ? capacitacion.getFecha() : '' }" ${ disabled } required>
                                            </div>
                                            <div class="form-group col-lg-10 col-md-8 col-sm-12">
                                                <label for="direccion">Dirección</label>
                                                <input type="text" class="form-control" name="direccion" id="direccion" value="${ capacitacion != null ? capacitacion.getDireccion() : '' }" ${ disabled } required>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="tema">Tema</label>
                                            <input type="text" class="form-control" name="tema" id="tema" value="${ capacitacion != null ? capacitacion.getTema() : '' }" ${ disabled } required>
                                        </div>

                                        <div class="form-row">
                                            <div class="form-group ${ rol != 'empresa' ? 'col-md-6 col-sm-12' : 'col-12' }">
                                                <label for="participantes">Participantes</label>
                                                <input type="number" class="form-control" name="participantes" id="participantes" value="${ capacitacion != null ? capacitacion.getParticipantes() : '' }" ${ disabled } required>
                                            </div>

                                            <core:if test="${ rol != 'empresa' }">
                                            <div class="form-group col-md-6 col-sm-12">
                                                <label for="realizada">Estado</label>
                                                <select class="form-control" name="realizada" id="realizada" ${ disabled } required>
                                                    <option value="true" ${ capacitacion != null ? (capacitacion.isRealizada() ? 'selected' : '') : '' }>Realizada</option>
                                                    <option value="false" ${ capacitacion != null ? (!capacitacion.isRealizada() ? 'selected' : '') : ''}>Pendiente</option>
                                                </select>
                                            </div>
                                            </core:if>
                                        </div>
                                        
                                        <core:choose>
                                            <core:when test="${ rol == 'admin' }">
                                                <div class="form-group">
                                                    <label for="profesionalId">Profesional</label>
                                                    <select class="form-control" name="profesionalId" id="profesionalId" ${ disabled } required>
                                                        <core:forEach items="${ profesionales }" var="prof">
                                                            <option value="${ prof.getId() }" ${ prof.getId() == capacitacion.getProfesionalId() ? 'selected' : '' }>${ prof.getNombre() }</option>
                                                        </core:forEach>
                                                    </select>
                                                </div>
                                            </core:when>
                                            <core:when test="${ rol == 'empresa' }">
                                                <div class="form-group">
                                                    <label for="profesional">Profesional</label>
                                                    <input class="form-control" type="text" name="profesional" id="profesional" value="${ profesional.getNombre() }" disabled required>
                                                    <input type="hidden" name="profesionalId" id="profesionalId" value="${ profesional.getId() }">
                                                </div>
                                            </core:when>
                                            <core:otherwise>
                                                <input type="hidden" name="profesionalId" id="profesionalId" value="${ uid }">
                                            </core:otherwise>
                                        </core:choose>
                                        
                                        <core:choose>
                                            <core:when test="${ rol != 'empresa' }">
                                                <div class="form-group">
                                                    <label for="empresaId">Empresa</label>
                                                    <select class="form-control" name="empresaId" id="empresaId" ${ disabled } required>
                                                        <core:forEach items="${ empresas }" var="empresa">
                                                            <option value="${ empresa.getId() }" ${ capacitacion.getEmpresaId() == empresa.getId() ? 'selected' : '' }>${ empresa.getNombre() }</option>
                                                        </core:forEach>
                                                    </select>
                                                </div>
                                            </core:when>
                                            <core:otherwise>
                                                <input type="hidden" name="empresaId" id="empresaId" value="${ capacitacion.getEmpresaId() }">
                                            </core:otherwise>
                                        </core:choose>

                                        <div class="form-group text-right">
                                        <core:choose>
                                            <core:when test="${ not empty ver }">
                                                <button type="button" class="btn btn-primary" data-accion="editar" data-id="${ capacitacion.getId() }">Editar</button>
                                                <core:if test="${ rol != 'empresa' }">
                                                <button type="button" class="btn btn-danger ml-2" data-accion="eliminar" data-id="${ capacitacion.getId() }">Eliminar</button>
                                                </core:if>
                                            </core:when>
                                            <core:otherwise>
                                                <button type="button" class="btn btn-warning" data-accion="cancelar">Cancelar</button>
                                                <core:if test="${ rol != 'empresa' }">
                                                <button type="submit" class="btn btn-primary">${ accion }</button>
                                                </core:if>
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
        <script type="text/javascript" src="<core:url value="/resources/js/capacitaciones.js" />"></script>
    </body>
</html>
