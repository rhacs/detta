<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="./parciales/header.jsp" />
<jsp:include page="./parciales/navegacion.jsp" />

        <!-- Contenido -->
        <div class="container my-5">
            <div class="row justify-content-center">
                <div class="col-lg-8 col-md-10 col-12">
                    <div class="card">
                        <div class="card-header">Profesionales</div>
                        <div class="card-body">
                            <div class="table-responsive-md">
                                <table class="table table-hover table-striped">
                                    <caption class="text-right mr-2">Total registros: ${ profesionales != null ? profesionales.size() : '0' }</caption>
                                    <thead>
                                        <tr class="table-info">
                                            <th scope="col">#</th>
                                            <th scope="col">Nombre</th>
                                            <th scope="col">Teléfono</th>
                                            <th scope="col">Correo Electrónico</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                    <core:choose>
                                        <core:when test="${ not empty profesionales }">
                                            <core:forEach items="${ profesionales }" begin="0" end="5" var="profesional">
                                            <tr>
                                                <th scope="row">${ profesional.getId() }</th>
                                                <td>${ profesional.getNombre() }</td>
                                                <td>${ profesional.getTelefono() }</td>
                                                <td>${ profesional.getEmail() }</td>
                                            </tr>
                                            </core:forEach>
                                        </core:when>

                                        <core:otherwise>
                                            <tr>
                                                <td class="text-center" colspan="4">No hay registros</td>
                                            </tr>
                                        </core:otherwise>
                                    </core:choose>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
<jsp:include page="./parciales/footer.jsp" />