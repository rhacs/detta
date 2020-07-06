package cl.rhacs.detta.controladores;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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

import cl.rhacs.detta.Utilidades;
import cl.rhacs.detta.modelos.Accidente;
import cl.rhacs.detta.modelos.Empresa;
import cl.rhacs.detta.modelos.Profesional;
import cl.rhacs.detta.modelos.database.Conexion;
import cl.rhacs.detta.repositorios.AccidentesRepository;
import cl.rhacs.detta.repositorios.EmpresasRepository;
import cl.rhacs.detta.repositorios.ProfesionalesRepository;

/**
 * Servlet implementation class ClientesAccionesController
 */
@WebServlet(name = "ClientesAccionesController", urlPatterns = "/panel/clientes/*")
public class ClientesAccionesController extends HttpServlet {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Número de serie de la clase */
    private static final long serialVersionUID = 7154599303975729188L;

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link EmpresasRepository} con los métodos CRUD para {@link Empresa}s
     */
    private EmpresasRepository empresasRepository;

    /**
     * Objeto {@link AccidentesRepository} con los métodos CRUD para
     * {@link Accidente}s
     */
    private AccidentesRepository accidentesRepository;

    /**
     * Objeto {@link ProfesionalesRepository} con los métodos CRUD para
     * {@link Profesional}es
     */
    private ProfesionalesRepository profesionalesRepository;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una instancia vacía del objeto {@link ClientesAccionesController}
     */
    public ClientesAccionesController() {
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

        // Obtener instancia de conexión
        Conexion conexion = Conexion.getInstance(url, user, pass);

        // Inicializar repositorios
        empresasRepository = new EmpresasRepository(conexion);
        accidentesRepository = new AccidentesRepository(conexion);
        profesionalesRepository = new ProfesionalesRepository(conexion);
    }

    /**
     * Maneja las solicitudes de mostrar detalles de una {@link Empresa} al
     * {@link HttpServlet}
     * 
     * @param request  un objeto {@link HttpServletRequest} que contiene la
     *                 solicitud realizada por el cliente al {@link HttpServlet}
     * @param response un objeto {@link HttpServletResponse} que contiene la
     *                 respuesta que le envía el {@link HttpServlet} al cliente
     * @param sesion   un objeto {@link HttpSession} con la información de la sesión
     * 
     * @throws ServletException si una excepción interrumpe el funcionamiento normal
     *                          del {@link HttpServlet}
     * @throws IOException      si un error de entrada/salida es detectado cuando el
     *                          {@link HttpServlet} maneja la solicitud
     */
    private void doMostrarDetalles(HttpServletRequest request, HttpServletResponse response, HttpSession sesion)
            throws IOException, ServletException {
        // Obtener uri
        String uri = request.getRequestURI();

        // Crear matcher
        Matcher matcher = Pattern.compile("([0-9]+)").matcher(uri);

        // Verificar coincidencias
        if (matcher.find()) {
            // Convertir coincidencia
            int id = Integer.parseInt(matcher.group());

            // Buscar empresa
            Empresa empresa = (Empresa) empresasRepository.buscarPorId(id);

            // Verificar existencia
            if (empresa != null) {
                // Obtener rol del usuario
                String rol = (String) sesion.getAttribute("rol");

                // Verificar rol
                if (rol.equals("admin")) {
                    // Buscar todos los profesionales
                    List<Object> aux = profesionalesRepository.buscarTodos();

                    // Verificar si hay resultados
                    if (aux != null) {
                        // Convertir objetos
                        List<Profesional> profesionales = new ArrayList<>();

                        for (Object o : aux) {
                            profesionales.add((Profesional) o);
                        }

                        // Agregar listado a la solicitud
                        request.setAttribute("profesionales", profesionales);
                    }
                }

                // Buscar accidentes
                List<Accidente> accidentes = accidentesRepository.buscarPorEmpresaId(id);

                if (accidentes != null) {
                    // Agregar a la solicitud
                    request.setAttribute("accidentes", accidentes);
                }

                // Agregar empresa al request
                request.setAttribute("empresa", empresa);
                request.setAttribute("ver", true);

                // Mostrar contenido
                request.getRequestDispatcher("/WEB-INF/paneles/clientes.detalles.jsp").forward(request, response);
            } else {
                // Agregar error
                sesion.setAttribute("error",
                        "El Cliente con el identificador '" + id + "' no existe en nuestros registros");

                // Redireccionar
                response.sendRedirect(request.getContextPath() + "/panel/clientes");
            }
        } else {
            // Agregar error a la sesión
            sesion.setAttribute("error", "Debe seleccionar un Cliente del listado para poder ver sus detalles");

            // Redireccionar
            response.sendRedirect(request.getContextPath() + "/panel/clientes");
        }
    }

    /**
     * Maneja las solicitudes de agregar o editar los detalles de una
     * {@link Empresa} al {@link HttpServlet}
     * 
     * @param request  un objeto {@link HttpServletRequest} que contiene la
     *                 solicitud realizada por el cliente al {@link HttpServlet}
     * @param response un objeto {@link HttpServletResponse} que contiene la
     *                 respuesta que le envía el {@link HttpServlet} al cliente
     * @param sesion   un objeto {@link HttpSession} con la información de la sesión
     * 
     * @throws ServletException si una excepción interrumpe el funcionamiento normal
     *                          del {@link HttpServlet}
     * @throws IOException      si un error de entrada/salida es detectado cuando el
     *                          {@link HttpServlet} maneja la solicitud
     */
    private void doAgregarEditar(HttpServletRequest request, HttpServletResponse response, HttpSession sesion)
            throws ServletException, IOException {
        // Obtener uri
        String uri = request.getRequestURI();

        // Crear matcher
        Matcher matcher = Pattern.compile("([0-9]+)").matcher(uri);

        // Agregar atributos a la solicitud
        request.setAttribute("accion", "Agregar");

        // Verificar ocurrencias
        if (matcher.find()) {
            // Convertir ocurrencia
            int id = Integer.parseInt(matcher.group());

            // Buscar empresa
            Empresa empresa = (Empresa) empresasRepository.buscarPorId(id);

            // Verificar existencia
            if (empresa != null) {
                // Agregar a la solicitud
                request.setAttribute("empresa", empresa);

                // Agregar atributos a la solicitud
                request.setAttribute("accion", "Editar");
            }
        }

        // Obtener rol del usuario
        String rol = (String) sesion.getAttribute("rol");

        // Verificar rol
        if (rol.equals("admin")) {
            // Buscar todos los profesionales
            List<Object> aux = profesionalesRepository.buscarTodos();

            // Verificar si hay resultados
            if (aux != null) {
                // Convertir objetos
                List<Profesional> profesionales = new ArrayList<>();

                for (Object o : aux) {
                    profesionales.add((Profesional) o);
                }

                // Agregar listado a la solicitud
                request.setAttribute("profesionales", profesionales);
            }
        }

        // Mostrar contenido
        request.getRequestDispatcher("/WEB-INF/paneles/clientes.detalles.jsp").forward(request, response);
    }

    /**
     * Maneja las solicitudes de eliminar una {@link Empresa} al {@link HttpServlet}
     * 
     * @param request  un objeto {@link HttpServletRequest} que contiene la
     *                 solicitud realizada por el cliente al {@link HttpServlet}
     * @param response un objeto {@link HttpServletResponse} que contiene la
     *                 respuesta que le envía el {@link HttpServlet} al cliente
     * @param sesion   un objeto {@link HttpSession} con la información de la sesión
     * 
     * @throws IOException si un error de entrada/salida es detectado cuando el
     *                     {@link HttpServlet} maneja la solicitud
     */
    private void doEliminar(HttpServletRequest request, HttpServletResponse response, HttpSession sesion)
            throws IOException {
        // Obtener uri
        String uri = request.getRequestURI();

        // Crear matcher
        Matcher matcher = Pattern.compile("([0-9]+)").matcher(uri);

        // Verificar ocurrencias
        if (matcher.find()) {
            // Convertir ocurrencia
            int id = Integer.parseInt(matcher.group());

            // Buscar registro
            Empresa empresa = (Empresa) empresasRepository.buscarPorId(id);

            // Verificar existencia
            if (empresa != null) {
                // Eliminar
                boolean eliminado = empresasRepository.eliminarRegistro(empresa.getId());

                // Verificar eliminación
                if (eliminado) {
                    // Agregar mensaje
                    sesion.setAttribute("exito", "El registro ha sido eliminado satisfactoriamente");
                } else {
                    // Agregar mensaje
                    sesion.setAttribute("error",
                            "El registro no ha podido ser eliminado del repositorio debido a un error interno. Por favor, inténtelo de nuevo más tarde");
                }

                // Redireccionar
                response.sendRedirect(request.getContextPath() + "/panel/clientes");
            }
        } else {
            // Agregar error
            sesion.setAttribute("error", "Debe seleccionar un registro de la tabla para poder efectuar esa acción");

            // Redireccionar
            response.sendRedirect(request.getContextPath() + "/panel/clientes");
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

        // Agregar atributos a la solicitud
        request.setAttribute("titulo", "Clientes");
        request.setAttribute("activo", "empr");

        // Verificar inicio de sesión
        if (sesion.getAttribute("loggedIn") != null && ((boolean) sesion.getAttribute("loggedIn"))) {
            // Obtener uri
            String uri = request.getRequestURI();

            // Verificar acción
            if (uri.contains("ver")) {
                doMostrarDetalles(request, response, sesion);
            } else if (uri.contains("agregar") || uri.contains("editar")) {
                doAgregarEditar(request, response, sesion);
            } else if (uri.contains("eliminar")) {
                doEliminar(request, response, sesion);
            } else {
                // Vueeeeeeeelta
                response.sendRedirect(request.getContextPath() + "/panel/clientes");
            }
        } else {
            // Pa la casa
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

        // Verificar inicio de sesión
        if (sesion.getAttribute("loggedIn") != null && ((boolean) sesion.getAttribute("loggedIn"))) {
            // Obtener información enviada por el usuario
            String identificador = request.getParameter("id");
            String nombre = request.getParameter("nombre");
            String rut = request.getParameter("rut");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            String email = request.getParameter("email");
            String giro = request.getParameter("giro");
            String trabajadores = request.getParameter("trabajadores");
            String tipo = request.getParameter("tipo");
            String profesionalId = request.getParameter("profesional");

            // Crear empresa
            Empresa empresa = new Empresa();

            // Poblar objeto
            empresa.setNombre(nombre);
            empresa.setRut(rut);
            empresa.setDireccion(direccion);
            empresa.setTelefono(telefono);
            empresa.setEmail(email);
            empresa.setGiro(giro);
            empresa.setTrabajadores(Integer.parseInt(trabajadores));
            empresa.setTipo(tipo);

            if (identificador != null && !identificador.isBlank()) {
                // Convertir identificador
                int id = Integer.parseInt(identificador);

                // Buscar registro existente
                Empresa aux = (Empresa) empresasRepository.buscarPorId(id);

                // Verificar existencia
                if (aux != null) {
                    // Reemplazar campos
                    empresa.setId(aux.getId());
                    empresa.setPassword(aux.getPassword());
                    empresa.setProfesionalId(aux.getProfesionalId());

                    // Actualizar registro
                    boolean actualizado = empresasRepository.actualizarRegistro(empresa);

                    // Verificar actualización
                    if (actualizado) {
                        // Redireccionar
                        response.sendRedirect(request.getContextPath() + "/panel/clientes/ver/" + aux.getId());
                    } else {
                        // Agregar mensaje
                        sesion.setAttribute("error",
                                "El registro no ha podido ser actualizado por un problema en la base de datos. Por favor, inténtelo nuevamente");

                        // Redireccionar
                        response.sendRedirect(request.getContextPath() + "/panel/clientes");
                    }
                }
            } else {
                try {
                    // Establecer contraseña
                    empresa.setPassword(Utilidades.codificarContrasena(email));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                // Convertir identificador
                int id = Integer.parseInt(profesionalId);

                // Establecer relación con el profesional
                empresa.setProfesionalId(id);

                // Agregar registro
                boolean agregado = empresasRepository.agregarRegistro(empresa);

                // Verificar agregación
                if (agregado) {
                    // Buscar registro
                    Empresa aux = empresasRepository.buscarPorEmail(email);

                    // Redireccionar
                    response.sendRedirect(request.getContextPath() + "/panel/clientes/ver/" + aux.getId());
                } else {
                    // Agregar mensaje
                    sesion.setAttribute("error",
                            "El registro no pudo ser agregado debido a un error en la base de datos. Por favor, inténtelo nuevamente");

                    // Redireccionar
                    response.sendRedirect(request.getContextPath() + "/panel/clientes");
                }
            }
        } else {
            // Pa la casa
            response.sendRedirect(request.getContextPath());
        }
    }

}
