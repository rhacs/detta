<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
    <jsp:include page="./parciales/header.jsp" />
    <body>
        <jsp:include page="./parciales/navegacion.jsp" />

        <!-- Contenido -->
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="./parciales/sidebar.jsp" />

                <main class="col-md-8 ml-sm-auto col-lg-9 col-xl-10 px-md-4" role="main">

                    <core:if test="${ not empty exito }">
                    <div class="alert alert-success my-4">${ exito }</div>
                    </core:if>

                    <core:if test="${ not empty error }">
                    <div class="alert alert-danger my-4">${ error }</div>
                    </core:if>

                    <h3 class="border-bottom pt-3 pb-2 mb-3">Listado de Clientes</h3>

                    <div class="row mb-3">
                        <div class="col-12 text-right">
                            <button type="button" data-agregar class="btn btn-primary">Agregar Capacitación</button>
                        </div>
                    </div>

                    <div class="table-responsive-md">
                        <table class="table table-striped table-hover">
                            <caption class="text-right mr-2">Total Registros: ${ capacitaciones != null ? capacitaciones.size() : 0 }</caption>
                            <thead class="thead-light">
                                <tr>
                                    <th scope="col" class="text-nowrap">#</th>
                                    <th scope="col" class="text-nowrap">Fecha</th>
                                    <th scope="col" class="text-nowrap">Participantes</th>
                                    <th scope="col" class="text-nowrap">Tema</th>
                                    <core:if test="${ rol != 'profesional' }"><th scope="col" class="text-nowrap">Profesional</th></core:if>
                                    <core:if test="${ rol != 'empresa' }"><th scope="col" class="text-nowrap">Empresa</th></core:if>
                                </tr>
                            </thead>
                            <tbody>
                            <core:choose>
                                <core:when test="${ empresas == null || empresas.size() == 0 }">
                                <tr>
                                    <th scope="row" colspan="6" class="text-center">No hay registros</th>
                                </tr>
                                </core:when>
                                <core:otherwise>
                                    <core:forEach items="${ capacitaciones }" var="capacitacion">
                                    <tr role="button" data-clienteid="${ capacitacion.getId() }">
                                        <th scope="row">${ capacitacion.getId() }</th>
                                        <td>${ capacitacion.getFecha() }</td>
                                        <td>${ capacitacion.getParticipantes() }</td>
                                        <core:if test="${ rol != 'profesional' }">
                                            <td>
                                                <core:forEach items="${ profesionales }" var="profesional">
                                                    <core:if test="${ profesional.getId() == capacitacion.getProfesionalId() }">${ profesional.getNombre() }</core:if>
                                                </core:forEach>
                                            </td>
                                        </core:if>
                                        <core:if test="${ rol != 'empresa' }">
                                        <td>
                                            <core:forEach items="${ empresas }" var="empresa">
                                                <core:if test="${ empresa.getId() == capacitacion.getEmpresaId() }">${ empresa.getNombre() }</core:if>
                                            </core:forEach>
                                        </td>
                                        </core:if>
                                    </tr>
                                    </core:forEach>
                                </core:otherwise>
                            </core:choose>
                            </tbody>
                        </table>
                    </div>

                </main>
            </div>
        </div>
        <!-- /Contenido -->

        <jsp:include page="./parciales/dependencias.jsp" />
        <script type="text/javascript" src="<core:url value="/resources/js/panel.js" />"></script>
        <script type="text/javascript" src="<core:url value="/resources/js/capacitaciones.js" />"></script>
    </body>
</html>
