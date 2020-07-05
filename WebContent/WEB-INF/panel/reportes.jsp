<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, cl.rhacs.detta.modelos.*, org.json.*, java.time.format.*, java.text.*" %>

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

                    <script type="text/javascript">
                        var fechas = <%= fechasValores %>;
                        var accidentes = <%= fechasAccidentes %>;

                        var clasificaciones = <%= clasEtiquetas %>;
                        var clasificacionesAccidentes = <%= clasAccidentes %>;
                        
                        var porTipoEtiquetas = <%= porTipoEtiquetas %>;
                        var porTipoAccidentes = <%= porTipoAccidentes %>;

                        window.onload = function() {
                        	var context = document.getElementById('porFecha').getContext('2d');
                        	var chart = new Chart(context, {
                        		type: 'line',
                        		data: {
                        			labels: fechas,
                        			datasets: [{
                        				backgroundColor: 'rgba(23, 162, 184, 0.25)',
                        				borderColor: 'rgba(23, 162, 184, 0.75)',
                        				data: accidentes,
                        				label: '# de Accidentes'
                        			}]
                        		},
                        		options: {
                        			maintainAspectRatio: false,
                        			scales: {
                                        yAxes: [{
                                            ticks: {
                                                beginAtZero: true
                                            }
                                        }]
                                    }
                        		}
                        	});

                        	var porClasificacion = document.getElementById('porClasificacion').getContext('2d');
                        	var chartClas = new Chart(porClasificacion, {
                        		type: 'bar',
                        		data: {
                        			labels: clasificaciones,
                        			datasets: [{
                        				backgroundColor: 'rgba(23, 162, 184, 0.75)',
                        				data: clasificacionesAccidentes,
                        				label: '# de Accidentes'
                        			}]
                        		},
                        		options: {
                        			maintainAspectRatio: false,
                        			scales: {
                        				yAxes: [{
                        					ticks: {
                        						beginAtZero: true
                        					}
                        				}]
                        			}
                        		}
                        	});

                        	var porTipo = document.getElementById('porTipo').getContext('2d');
                        	var chartTipo = new Chart(porTipo, {
                        		type: 'pie',
                        		data: {
                        			labels: porTipoEtiquetas,
                        			datasets: [{
                        				backgroundColor: [
                        					'rgba(23, 162, 184, 0.75)',
                        					'rgba(23, 162, 184, 0.35)'
                        				],
                        				data: porTipoAccidentes
                        			}]
                        		},
                        		options: {
                        			maintainAspectRatio: false
                        		}
                        	});
                        };
                    </script>
