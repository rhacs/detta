package cl.rhacs.detta.controladores;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.rhacs.detta.modelos.Accidente;
import cl.rhacs.detta.modelos.Empresa;
import cl.rhacs.detta.modelos.database.Conexion;
import cl.rhacs.detta.repositorios.AccidentesRepository;
import cl.rhacs.detta.repositorios.EmpresasRepository;

/**
 * Servlet implementation class AccidentesController
 */
@WebServlet(name = "AccidentesController", urlPatterns = { "/panel/accidentes", "/panel/accidentes/*" })
public class AccidentesController extends HttpServlet {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Número de serie de la clase */
    private static final long serialVersionUID = 5628064848989505579L;

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@AccidentesRepository} con los métodos CRUD para los
     * {@link Accidente}s
     */
    private AccidentesRepository accidentesRepository;

    /**
     * Objeto {@link EmpresasRepository} con los métodos CRUD para las
     * {@link Empresa}s
     */
    private EmpresasRepository empresasRepository;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva intancia vacía del objeto {@link AccidentesController}
     */
    public AccidentesController() {
        super();
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    @Override
    public void init() throws ServletException {
        // Obtener contexto
        ServletContext contexto = this.getServletContext();

        // Obtener configuración
        String url = contexto.getInitParameter("jdbcURL");
        String user = contexto.getInitParameter("jdbcUsername");
        String pass = contexto.getInitParameter("jdbcPassword");

        // Obtener instancia de Conexión
        Conexion conexion = Conexion.getInstance(url, user, pass);

        // Inicializar repositorios
        accidentesRepository = new AccidentesRepository(conexion);
        empresasRepository = new EmpresasRepository(conexion);
    }

    private void doVer(HttpServletRequest request, HttpServletResponse response, HttpSession sesion)
            throws IOException, ServletException {
        // Obtener uri
        String uri = request.getRequestURI();

        // Crear matcher
        Matcher matcher = Pattern.compile("([0-9]+)").matcher(uri);

        // Verificar ocurrencias
        if (matcher.find()) {
            // Convertir ocurrencia
            int id = Integer.parseInt(matcher.group());

            // Buscar accidente
            Accidente accidente = (Accidente) accidentesRepository.buscarPorId(id);

            // Verificar existencia
            if (accidente != null) {
                // Agregar accidente a la solicitud
                request.setAttribute("accidente", accidente);

                // Agregar atributos a la solicitud
                request.setAttribute("ver", true);

                // Mostrar contenido
                request.getRequestDispatcher("/WEB-INF/paneles/accidentes.detalles.jsp").forward(request, response);
            } else {
                // Agregar error
                sesion.setAttribute("error", "El registro del Accidente con el identificador '" + id
                        + "' no se encuentra presente en nuestros registros");

                // Redireccionar
                response.sendRedirect(request.getContextPath() + "/panel/accidentes");
            }
        } else {
            // Agregar error
            sesion.setAttribute("error", "Debe seleccionar un registro del listado para poder revisar los detalles");

            // Redireccionar
            response.sendRedirect(request.getContextPath() + "/panel/accidentes");
        }
    }

    private void doEliminar(HttpServletRequest request, HttpServletResponse response, HttpSession sesion)
            throws IOException {
        // Obtener uri
        String uri = request.getRequestURI();

        // Crear matcher
        Matcher matcher = Pattern.compile("([0-9]+)").matcher(uri);

        // Verificar coincidencias
        if (matcher.find()) {
            // Convertir identificador
            int id = Integer.parseInt(matcher.group());

            // Buscar registro
            Accidente accidente = (Accidente) accidentesRepository.buscarPorId(id);

            // Verificar existencia
            if (accidente != null) {
                // Eliminar registro
                boolean eliminado = accidentesRepository.eliminarRegistro(id);

                // Verificar eliminación
                if (eliminado) {
                    // Agregar mensaje de éxito
                    sesion.setAttribute("exito", "El registro fue eliminado correctamente");
                } else {
                    // Agregar error
                    sesion.setAttribute("error",
                            "El registro no pudo ser eliminado por un problema con la base de datos. Por favor, inténtelo nuevamente");
                }
            } else {
                // Agregar error
                sesion.setAttribute("error",
                        "El registro del Accidente con el identificador '" + id + "' no existe en el repositorio");
            }
        } else {
            // Agregar error
            sesion.setAttribute("error", "Debe seleccionar un registro del listado para poder efectuar la acción");
        }

        // Redireccionar
        response.sendRedirect(request.getContextPath() + "/panel/accidentes");
    }

    private void doAgregarEditar(HttpServletRequest request, HttpServletResponse response, HttpSession sesion)
            throws IOException, ServletException {
        // Obtener uri
        String uri = request.getRequestURI();

        // Verificar acción
        if (uri.contains("agregar")) {
            // Agregar atributos a la solicitud
            request.setAttribute("accion", "Agregar");

            // Mostrar contenido
            request.getRequestDispatcher("/WEB-INF/paneles/accidentes.detalles.jsp").forward(request, response);
        } else if (uri.contains("editar")) {
            // Crear matcher
            Matcher matcher = Pattern.compile("([0-9]+)").matcher(uri);

            // Verificar coincidencias
            if (matcher.find()) {
                // Convertir coincidencia
                int id = Integer.parseInt(matcher.group());

                // Buscar registro
                Accidente accidente = (Accidente) accidentesRepository.buscarPorId(id);

                // Verificar existencia
                if (accidente != null) {
                    // Agregar accidente a la solicitud
                    request.setAttribute("accidente", accidente);

                    // Agregar atributos a la solicitud
                    request.setAttribute("accion", "Editar");

                    // Mostrar contenido
                    request.getRequestDispatcher("/WEB-INF/paneles/accidentes.detalles.jsp").forward(request, response);
                } else {
                    // Agregar error
                    sesion.setAttribute("error",
                            "El registro del Accidente con el idenficiador '" + id + "' no existe en el repositorio");

                    // Redireccionar
                    response.sendRedirect(request.getContextPath() + "/panel/accidentes");
                }
            } else {
                // Agregar error
                sesion.setAttribute("error",
                        "Debe seleccionar un registro del listado para poder efectuar la operación");

                // Redireccionar
                response.sendRedirect(request.getContextPath() + "/panel/accidentes");
            }
        } else {
            // Agregar error
            sesion.setAttribute("error",
                    "No se pudo identificar la acción a realizar. Por favor, inténtelo nuevamente");

            // Redireccionar
            response.sendRedirect(request.getContextPath() + "/panel/accidentes");
        }
    }

    // Solicitudes
    // -----------------------------------------------------------------------------------------

    /**
     * Maneja las solicitudes GET al {@link HttpServlet}
     * 
     * @param request  un objeto {@link HttpServletRequest} que contiene la
     *                 solicitud realizada por el cliente al {@link HttpServlet}
     * @param response un objeto {@link HttpServletResponse} que contiene la
     *                 respuesta que le envía el {@link HttpServlet} al cliente
     * 
     * @throws ServletException si una excepción interrumpe el funcionamiento normal
     *                          del {@link HttpServlet}
     * @throws IOException      si un error de entrada/salida es detectado cuando el
     *                          {@link HttpServlet} maneja la solicitud
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener objeto de sesión
        HttpSession sesion = request.getSession();

        // Verificar sesión
        if (sesion.getAttribute("loggedIn") != null && ((boolean) sesion.getAttribute("loggedIn"))) {
            // Obtener uri
            String uri = request.getRequestURI();

            // Agregar atributos a la solicitud
            request.setAttribute("titulo", "Accidentes");
            request.setAttribute("activo", "acci");

            // Verificar acción
            if (uri.contains("agregar") || uri.contains("editar")) {
                doAgregarEditar(request, response, sesion);
            } else if (uri.contains("eliminar")) {
                doEliminar(request, response, sesion);
            } else if (uri.contains("ver")) {
                doVer(request, response, sesion);
            } else {
                // Obtener atributos de la sesión
                String rol = (String) sesion.getAttribute("rol");
                int uid = (int) sesion.getAttribute("uid");

                // Crear listado de accidentes
                List<Accidente> accidentes = new ArrayList<>();

                // Verificar rol
                if (rol.equals("admin")) {
                    // Buscar todos los accidentes
                    List<Object> aux = accidentesRepository.buscarTodos();

                    // Verificar que hayan registros
                    if (aux != null) {
                        // Convertir objetos
                        for (Object o : aux) {
                            accidentes.add((Accidente) o);
                        }
                    }
                } else if (rol.equals("profesional")) {
                    // Buscar todas las empresas del profesional
                    List<Empresa> empresas = empresasRepository.buscarPorProfesionalId(uid);

                    // Verificar que hayan resultados
                    if (empresas != null) {
                        // Inicializar
                        // Buscar todos los accidentes de las empresas en el listado
                        for (Empresa e : empresas) {
                            List<Accidente> aux = accidentesRepository.buscarPorEmpresaId(e.getId());

                            // Verificar resultados
                            if (aux != null) {
                                // Agregar al listado
                                accidentes.addAll(aux);
                            }
                        }
                    }
                } else if (rol.equals("empresa")) {
                    // Buscar todos los accidentes de la empresa
                    accidentes = accidentesRepository.buscarPorEmpresaId(uid);
                }

                // Agregar listado a la solicitud
                request.setAttribute("accidentes", accidentes);

                // Verificar si hay mensajes de éxito
                if (sesion.getAttribute("exito") != null) {
                    // Agregar mensaje a la solicitud
                    request.setAttribute("exito", sesion.getAttribute("exito"));

                    // Eliminar mensaje de la sesión
                    sesion.removeAttribute("exito");
                }

                // Verificar si hay mensajes de error
                if (sesion.getAttribute("error") != null) {
                    // Agregar mensaje a la solicitud
                    request.setAttribute("error", sesion.getAttribute("error"));

                    // Eliminar mensaje de la sesión
                    sesion.removeAttribute("error");
                }

                // Mostrar contenido
                request.getRequestDispatcher("/WEB-INF/accidentes.jsp").forward(request, response);
            }
        } else {
            // Pa' la casa
            response.sendRedirect(request.getContextPath());
        }
    }

    /**
     * Maneja las solicitudes POST al {@link HttpServlet}
     * 
     * @param request  un objeto {@link HttpServletRequest} que contiene la
     *                 solicitud realizada por el cliente al {@link HttpServlet}
     * @param response un objeto {@link HttpServletResponse} que contiene la
     *                 respuesta que le envía el {@link HttpServlet} al cliente
     * 
     * @throws ServletException si una excepción interrumpe el funcionamiento normal
     *                          del {@link HttpServlet}
     * @throws IOException      si un error de entrada/salida es detectado cuando el
     *                          {@link HttpServlet} maneja la solicitud
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener sesión
        HttpSession sesion = request.getSession();

        // Obtener información enviada por el usuario
        String identificador = request.getParameter("id");
        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora");
        String direccion = request.getParameter("direccion");
        String lugar = request.getParameter("lugar");
        String circunstancia = request.getParameter("circunstancia");
        String detalles = request.getParameter("detalles");
        String clasificacion = request.getParameter("clasificacion");
        String tipo = request.getParameter("tipo");
        String medioPrueba = request.getParameter("medioPrueba");

        // Crear Accidente
        Accidente accidente = new Accidente();

        // Poblar accidente
        accidente.setFecha(LocalDate.parse(fecha));
        accidente.setHora(LocalTime.parse(hora));
        accidente.setDireccion(direccion);
        accidente.setLugar(lugar);
        accidente.setCircunstancia(circunstancia);
        accidente.setDetalles(detalles);
        accidente.setClasificacion(clasificacion);
        accidente.setTipo(tipo);
        accidente.setMedioPrueba(medioPrueba);

        // Verificar identificador
        if (identificador != null && !identificador.isBlank()) {
            // Convertir identificador
            int id = Integer.parseInt(identificador);

            // Buscar accidente
            Accidente aux = (Accidente) accidentesRepository.buscarPorId(id);

            // Verificar existencia
            if (aux != null) {
                // Faltantes
                accidente.setId(aux.getId());
                accidente.setEmpresaId(aux.getEmpresaId());

                // Actualizar registro
                boolean actualizado = accidentesRepository.actualizarRegistro(accidente);

                // Verificar actualización
                if (actualizado) {
                    // Redireccionar
                    response.sendRedirect(request.getContextPath() + "/panel/accidentes/ver/" + id);
                } else {
                    // Agregar error
                    sesion.setAttribute("error",
                            "Ocurrió un error mientras se intentaba actualizar la información del registro. Por favor, inténtelo nuevamente");

                    // Redireccionar
                    response.sendRedirect(request.getContextPath() + "/panel/accidentes");
                }
            }
        } else {
            // Recuperar id del usuario
            int uid = (int) sesion.getAttribute("uid");

            // Insertar identificador
            accidente.setEmpresaId(uid);

            // Agregar registro
            boolean agregado = accidentesRepository.agregarRegistro(accidente);

            // Verificar
            if (agregado) {
                // Agregar mensaje
                sesion.setAttribute("exito", "El registro fue agregado correctamente");
            } else {
                // Agregar mensaje
                sesion.setAttribute("error", "El registro no pudo ser agregado debido a un error en la base de datos");
            }

            // Redireccionar
            response.sendRedirect(request.getContextPath() + "/panel/accidentes");
        }
    }

}
