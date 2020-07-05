<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map, java.util.LinkedHashMap, java.util.List, cl.rhacs.detta.modelos.Accidente, java.time.format.TextStyle, java.util.Locale, org.json.JSONArray" %>
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
                    <%
    // Ordinario, pero sirve :D
    Map<String, Integer> porFecha = new LinkedHashMap<>();
    porFecha.put("enero", 0);
    porFecha.put("febrero", 0);
    porFecha.put("marzo", 0);
    porFecha.put("abril", 0);
    porFecha.put("mayo", 0);
    porFecha.put("junio", 0);
    porFecha.put("julio", 0);
    porFecha.put("agosto", 0);
    porFecha.put("septiembre", 0);
    porFecha.put("octubre", 0);
    porFecha.put("noviembre", 0);
    porFecha.put("diciembre", 0);

    Map<String, Integer> porClasificacion = new LinkedHashMap<>();
    porClasificacion.put("Leve", 0);
    porClasificacion.put("Grave", 0);
    porClasificacion.put("Fatal", 0);
    porClasificacion.put("Otro", 0);

    Map<String, Integer> porTipo = new LinkedHashMap<>();
    porTipo.put("Trabajo", 0);
    porTipo.put("Trayecto", 0);

    if(request.getAttribute("accidentes") != null) {
        // Obtener accidentes
        List<Accidente> accidentes = (List<Accidente>) request.getAttribute("accidentes");

        accidentes.forEach(accidente -> {
            // Obtener mes
            String mes = accidente.getFecha().getMonth().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es"));

            // Sumar una unidad al mes correspondiente
            porFecha.computeIfPresent(mes, (key, value) -> value + 1);

            // Obtener clasificacion
            String clasificacion = accidente.getClasificacion();

            // Sumar una unidad
            porClasificacion.computeIfPresent(clasificacion, (key, value) -> value + 1);

            // Obtener el tipo
            String tipo = accidente.getTipo();
            porTipo.computeIfPresent(tipo, (key, value) -> value + 1);
        });
    }

    JSONArray fechasValores = new JSONArray(porFecha.keySet());
    JSONArray fechasAccidentes = new JSONArray(porFecha.values());

    JSONArray clasEtiquetas = new JSONArray(porClasificacion.keySet());
    JSONArray clasAccidentes = new JSONArray(porClasificacion.values());

    JSONArray porTipoEtiquetas = new JSONArray(porTipo.keySet());
    JSONArray porTipoAccidentes = new JSONArray(porTipo.values());
%>

                    <core:if test="${ not empty error }">
                    <div class="alert alert-danger my-4" role="alert">${ error }</div>
                    </core:if>

                    <!-- Reportes Generales -->
                    <h3 class="border-bottom pt-3 pb-2 mb-3">Reportes Generales</h3>
                    <div class="row">
                        <div class="col-xl-4 col-lg-12 mb-4">
                            <div class="card">
                                <div class="card-header">Accidentes por Fecha</div>
                                <div class="card-body">
                                    <canvas id="porFecha" height="280" width="100%"></canvas>
                                </div>
                            </div>
                        </div>

                        <div class="col-xl-4 col-lg-6 col-md-12 mb-4">
                            <div class="card">
                                <div class="card-header">Accidentes por Clasificación</div>
                                <div class="card-body">
                                    <canvas id="porClasificacion" height="280" width="100%"></canvas>
                                </div>
                            </div>
                        </div>

                        <div class="col-xl-4 col-lg-6 col-md-12">
                            <div class="card">
                                <div class="card-header">Accidentes por Tipo</div>
                                <div class="card-body">
                                    <canvas id="porTipo" height="280" width="100%"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>

                    <h3 class="border-bottom pt-3 pb-2 mb-3">Historial de Acciones</h3>

                </main>
            </div>
        </div>
        <!-- /Contenido -->

        <jsp:include page="./parciales/dependencias.jsp" />

        <script type="text/javascript" src="<core:url value="/resources/js/panel.js" />"></script>

        <script type="text/javascript">
            var fechas = <%= fechasValores %>;
            var accidentes = <%= fechasAccidentes %>;

            var clasificaciones = <%= clasEtiquetas %>;
            var clasificacionesAccidentes = <%= clasAccidentes %>;

            var porTipoEtiquetas = <%= porTipoEtiquetas %>;
            var porTipoAccidentes = <%= porTipoAccidentes %>;
        </script>
    </body>
</html>
