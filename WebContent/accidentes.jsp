<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <jsp:include page="./parciales/header.jsp" />
    <!-- Body -->
    <body>
        <jsp:include page="./parciales/navegacion.jsp" />
        
        <!-- Contenido -->
        <div class="container mt-5 mb-5">
            <div class="row justify-content-center mb-2">
                <div class="col-xl-8 col-lg-10 col-12 text-right">
                    <button type="button" data-do="agregar" class="btn btn-sm btn-primary">Agregar Registro</button>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-xl-8 col-lg-10 col-12">
                    <div class="table-responsive">
                        <table class="table table-stripped table-hover">
                            <caption class="mt-0 text-right">Total registros: ${accidentes.size()}</caption>
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Fecha</th>
                                    <th scope="col">Hora</th>
                                    <th scope="col">Clasificación</th>
                                    <th scope="col">Tipo</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <core:if test="${accidentes.size() < 1}">
                                <tr>
                                    <td class="text-center" colspan="6" scope="row">No hay registros</td>
                                </tr>
                                </core:if>
                                <core:forEach var="accidente" items="${accidentes}">
                                <tr>
                                    <th scope="row">${accidente.getId()}</th>
                                    <td>${accidente.getFecha()}</td>
                                    <td>${accidente.getHora()}</td>
                                    <td>${accidente.getClasificacion()}</td>
                                    <td>${accidente.getTipo()}</td>
                                    <td class="text-right text-nowrap">
                                        <button type="button" class="btn btn-sm btn-outline-warning"  data-do="ver" data-id="${accidente.getId()}" title="Ver Detalles"><i class="fas fa-search"></i></button>
                                        <button type="button" class="btn btn-sm btn-outline-success" data-do="editar" data-id="${accidente.getId()}" title="Editar"><i class="fas fa-edit"></i></button>
                                        <button type="button" class="btn btn-sm btn-outline-danger" data-do="borrar" data-id="${accidente.getId()}" title="Borrar"><i class="fas fa-trash"></i></button>
                                    </td>
                                </tr>
                                </core:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- /Contenido -->
        <jsp:include page="./parciales/dependencias.jsp" />
        
        <!-- Dependencias específicas -->
        <script src="./res/js/accidentes.js"></script>
    </body>
    <!-- /Body -->
</html>