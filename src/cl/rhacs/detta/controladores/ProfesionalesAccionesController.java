package cl.rhacs.detta.controladores;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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

import cl.rhacs.detta.Utilidades;
import cl.rhacs.detta.modelos.Empresa;
import cl.rhacs.detta.modelos.Profesional;
import cl.rhacs.detta.modelos.database.Conexion;
import cl.rhacs.detta.repositorios.EmpresasRepository;
import cl.rhacs.detta.repositorios.ProfesionalesRepository;

/**
 * Servlet implementation class ProfesionalesAccionesController
 */
@WebServlet(name = "ProfesionalesAccionesController", urlPatterns = "/panel/profesionales/*")
public class ProfesionalesAccionesController extends HttpServlet {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Número de serie de la clase */
    private static final long serialVersionUID = 4769926641022361885L;

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Objeto {@link ProfesionalesRepository} con los métodos CRUD */
    private ProfesionalesRepository profesionalesRepository;

    /** Objeto {@link EmpresasRepository} con los métodos CRUD */
    private EmpresasRepository empresasRepository;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto
     * {@link ProfesionalesAccionesController}
     */
    public ProfesionalesAccionesController() {
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

        // Obtener instancia de conexion
        Conexion conexion = Conexion.getInstance(url, user, pass);

        // Inicializar repositorios
        profesionalesRepository = new ProfesionalesRepository(conexion);
        empresasRepository = new EmpresasRepository(conexion);
    }

    /**
     * Maneja la solicitud de mostrar detalles sobre un {@link Profesional}
     * 
     * @param request  objeto {@link HttpServletRequest} con la solicitud enviada
     *                 por el cliente al {@link HttpServlet}
     * @param response objeto {@link HttpServletResponse} con la respuesta que le
     *                 envía el {@link HttpServlet} al cliente
     * @throws IOException      si ocurre un error de entrada/salida cuando el
     *                          {@link HttpServlet} intenta procesar la solicitud
     * @throws ServletException si ocurre un error que interrumpe el normal
     *                          funcionamiento del {@link HttpServlet}
     */
    private void doDetalles(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // Obtener sesión
        HttpSession sesion = request.getSession();

        // Obtener uri
        String uri = request.getRequestURI();

        // Crear objeto matcher
        Matcher matcher = Pattern.compile("([0-9]+)").matcher(uri);

        // Verificar ocurrencia
        if (matcher.find()) {
            // Convertir ocurrencia
            int id = Integer.parseInt(matcher.group());

            // Buscar información
            Profesional profesional = profesionalesRepository.buscarPorId(id);

            // Verificar resultado
            if (profesional != null) {
                // Buscar empresas a cargo del profesional
                List<Empresa> empresas = empresasRepository.buscarPorProfesionalId(profesional.getId());

                // Agregar información a la solicitud
                request.setAttribute("profesional", profesional);
                request.setAttribute("empresas", empresas);
                request.setAttribute("ver", true);

                // Mostrar contenido
                request.getRequestDispatcher("/WEB-INF/paneles/profesionales.detalles.jsp").forward(request, response);
            } else {
                // Agregar error
                sesion.setAttribute("error",
                        "El Profesional con el identificador '" + id + "' no se encuentra en nuestros registros");

                // Redireccionar
                response.sendRedirect(request.getContextPath() + "/panel/profesionales");
            }
        } else {
            // Agregar error
            sesion.setAttribute("error", "Debe seleccionar un Profesional del listado para poder ver sus detalles");

            // Redireccionar
            response.sendRedirect(request.getContextPath() + "/panel/profesionales");
        }
    }

    /**
     * Maneja la solicitud de agregar o editar un {@link Profesional}
     * 
     * @param request  objeto {@link HttpServletRequest} con la solicitud enviada
     *                 por el cliente al {@link HttpServlet}
     * @param response objeto {@link HttpServletResponse} con la respuesta que le
     *                 envía el {@link HttpServlet} al cliente
     * @throws IOException      si ocurre un error de entrada/salida cuando el
     *                          {@link HttpServlet} intenta procesar la solicitud
     * @throws ServletException si ocurre un error que interrumpe el normal
     *                          funcionamiento del {@link HttpServlet}
     */
    private void doAgregarEditar(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // Obtener sesión
        HttpSession sesion = request.getSession();

        // Obtener uri
        String uri = request.getRequestURI();

        // Crear matcher
        Matcher matcher = Pattern.compile("([0-9]+)").matcher(uri);

        // Verificar acción
        if (uri.contains("agregar")) {
            // Agregar atributos a la solicitud
            request.setAttribute("accion", "Agregar");

            // Mostrar contenido
            request.getRequestDispatcher("/WEB-INF/paneles/profesionales.agregar.jsp").forward(request, response);
        } else if (uri.contains("editar")) {
            // Verificar ocurrencia
            if (matcher.find()) {
                // Convertir ocurrencia
                int id = Integer.parseInt(matcher.group());

                // Buscar profesional
                Profesional profesional = profesionalesRepository.buscarPorId(id);

                // Verificar existencia
                if (profesional != null) {
                    // Agregar información a la solicitud
                    request.setAttribute("profesional", profesional);
                    request.setAttribute("accion", "Editar");

                    // Mostrar contenido
                    request.getRequestDispatcher("/WEB-INF/paneles/profesionales.agregar.jsp").forward(request,
                            response);
                } else {
                    // Agregar error
                    sesion.setAttribute("error",
                            "El Profesional con el identificador '" + id + "' no se encuentra en nuestros registros");

                    // Redireccionar
                    response.sendRedirect(request.getContextPath() + "/panel/profesionales");
                }
            } else {
                // Agregar error
                sesion.setAttribute("error", "Debe seleccionar un Profesional del listado para poder ver sus detalles");

                // Redireccionar
                response.sendRedirect(request.getContextPath() + "/panel/profesionales");
            }
        }
    }

    /**
     * Maneja la solicitud de eliminar un {@link Profesional}
     * 
     * @param request  objeto {@link HttpServletRequest} con la solicitud enviada
     *                 por el cliente al {@link HttpServlet}
     * @param response objeto {@link HttpServletResponse} con la respuesta que le
     *                 envía el {@link HttpServlet} al cliente
     * @throws IOException si ocurre un error de entrada/salida cuando el
     *                     {@link HttpServlet} intenta procesar la solicitud
     */
    private void doEliminar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Obtener sesion
        HttpSession sesion = request.getSession();

        // Obtener uri
        String uri = request.getRequestURI();

        // Crear matcher
        Matcher matcher = Pattern.compile("([0-9]+)").matcher(uri);

        // Verificar existencia de número
        if (matcher.find()) {
            // Convertir ocurrencia
            int id = Integer.parseInt(matcher.group());

            // Buscar profesional
            Profesional profesional = profesionalesRepository.buscarPorId(id);

            // Verificar existencia
            if (profesional != null) {
                // Borrar profesional
                boolean borrado = profesionalesRepository.eliminarRegistro(id);

                // Verificar si se eliminó
                if (borrado) {
                    // Agregar mensaje
                    sesion.setAttribute("exito", "El registro fue eliminado satisfactoriamente");

                    // Redireccionar
                    response.sendRedirect(request.getContextPath() + "/panel/profesionales");
                } else {
                    // Agregar error
                    sesion.setAttribute("error",
                            "El registro no pudo ser eliminado por problemas internos, por favor inténtelo de nuevo más tarde");

                    // Redireccionar
                    response.sendRedirect(request.getContextPath() + "/panel/profesionales");
                }
            } else {
                // Agregar error
                sesion.setAttribute("error",
                        "El Profesional con el identificador '" + id + "' no se encuentra en nuestros registros");

                // Redireccionar
                response.sendRedirect(request.getContextPath() + "/panel/profesionales");
            }
        } else {
            // Agregar error
            sesion.setAttribute("error", "Debe seleccionar un Profesional del listado para poder eliminarlo");

            // Redireccionar
            response.sendRedirect(request.getContextPath() + "/panel/profesionales");
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
        // Obtener sesión
        HttpSession sesion = request.getSession();

        // Verificar inicio de sesión
        if (sesion.getAttribute("loggedIn") != null && ((boolean) sesion.getAttribute("loggedIn"))) {
            // Obtener rol del usuario
            String rol = (String) sesion.getAttribute("rol");

            // Verificar "poderes"
            if (rol != null && rol.equals("admin")) {
                // Obtener URI
                String uri = request.getRequestURI();

                // Agregar atributos a la solicitud
                request.setAttribute("titulo", "Profesionales");
                request.setAttribute("activo", "prof");

                // Verificar acción
                if (uri.contains("ver")) {
                    doDetalles(request, response);
                } else if (uri.contains("agregar") || uri.contains("editar")) {
                    doAgregarEditar(request, response);
                } else if (uri.contains("eliminar")) {
                    doEliminar(request, response);
                } else {
                    // Mostrar contenido
                    request.getRequestDispatcher("/WEB-INF/paneles/profesionales.detalles.jsp").forward(request,
                            response);
                }
            } else {
                // Agregar error
                sesion.setAttribute("youShallNotPass", "No posee el nivel necesario para poder acceder a esta sección");

                // Redireccionar
                response.sendRedirect(request.getContextPath() + "/panel");
            }
        } else {
            // Crear mensaje de error
            String mensaje = "Debe iniciar sesión para acceder al panel";

            // Insertar error en la sesión
            sesion.setAttribute("loginError", mensaje);

            // Redireccionar al usuario
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
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener sesión
        HttpSession sesion = req.getSession();

        // Verificar inicio de sesión
        if (sesion.getAttribute("loggedIn") != null && ((boolean) sesion.getAttribute("loggedIn"))) {
            // Obtener parámetros enviados por el usuario
            String identificador = req.getParameter("id");
            String nombre = req.getParameter("nombre");
            String email = req.getParameter("email");
            String telefono = req.getParameter("telefono");
            String estadoContrato = req.getParameter("estadoContrato");

            // Crear profesional
            Profesional profesional = new Profesional();

            // Poblar datos
            profesional.setNombre(nombre);
            profesional.setEmail(email);
            profesional.setTelefono(telefono);
            profesional.setEstadoContrato(estadoContrato);

            try {
                profesional.setPassword(Utilidades.codificarContrasena(email));
            } catch (NoSuchAlgorithmException e) {
                profesional.setPassword(email);
            }

            // Verificar existencia de identificador
            if (identificador != null && !identificador.isBlank()) {
                // Convertir identificador
                int id = Integer.parseInt(identificador);

                // Buscar profesional
                profesional = profesionalesRepository.buscarPorId(id);

                // Reemplazar datos
                profesional.setNombre(nombre);
                profesional.setEmail(email);
                profesional.setTelefono(telefono);
                profesional.setEstadoContrato(estadoContrato);

                // Actualizar información
                profesionalesRepository.actualizarRegistro(profesional);

                // Redireccionar
                resp.sendRedirect(req.getContextPath() + "/panel/profesionales/ver/" + profesional.getId());
            } else {
                // Agregar nuevo profesional
                boolean agregado = profesionalesRepository.agregarRegistro(profesional);

                // Verificar si se agregó
                if (agregado) {
                    // Buscar identificador generado
                    profesional = profesionalesRepository.buscarPorEmail(email);

                    // Redireccionar
                    resp.sendRedirect(req.getContextPath() + "/panel/profesionales/ver/" + profesional.getId());
                } else {
                    // Agregar mensaje de error
                    sesion.setAttribute("error",
                            "Ocurrió un error al intentar agregar el nuevo registro al sistema. Por favor, inténtelo de nuevo más tarde");

                    // Redireccionar
                    resp.sendRedirect(req.getContextPath() + "/panel/profesionales");
                }
            }
        } else {
            // Pa la casa
            resp.sendRedirect(req.getContextPath());
        }
    }

}
