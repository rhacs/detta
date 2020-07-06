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
                            <button type="button" data-agregar class="btn btn-primary">Agregar Cliente</button>
                        </div>
                    </div>

                    <div class="table-responsive-md">
                        <table class="table table-striped table-hover">
                            <caption class="text-right mr-2">Total Registros: ${ empresas != null ? empresas.size() : 0 }</caption>
                            <thead class="thead-light">
                                <tr>
                                    <th scope="col" class="text-nowrap">#</th>
                                    <th scope="col" class="text-nowrap">Nombre</th>
                                    <th scope="col" class="text-nowrap">Teléfono</th>
                                    <th scope="col" class="text-nowrap">Correo Electrónico</th>
                                </tr>
                            </thead>
                            <tbody>
                            <core:choose>
                                <core:when test="${ empresas == null || empresas.size() == 0 }">
                                <tr>
                                    <th scope="row" colspan="5" class="text-center">No hay registros</th>
                                </tr>
                                </core:when>
                                <core:otherwise>
                                    <core:forEach items="${ empresas }" var="empresa">
                                    <tr role="button" data-clienteid="${ empresa.getId() }">
                                        <th scope="row">${ empresa.getId() }</th>
                                        <td>${ empresa.getNombre() }</td>
                                        <td>${ empresa.getTelefono() }</td>
                                        <td>${ empresa.getEmail() }</td>
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
        <script type="text/javascript" src="<core:url value="/resources/js/clientes.js" />"></script>
    </body>
</html>
                    