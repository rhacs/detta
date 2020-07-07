package cl.rhacs.detta.controladores;

import java.io.IOException;
import java.time.LocalDate;
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

import cl.rhacs.detta.modelos.Capacitacion;
import cl.rhacs.detta.modelos.Empresa;
import cl.rhacs.detta.modelos.Profesional;
import cl.rhacs.detta.modelos.database.Conexion;
import cl.rhacs.detta.repositorios.CapacitacionesRepository;
import cl.rhacs.detta.repositorios.EmpresasRepository;
import cl.rhacs.detta.repositorios.ProfesionalesRepository;

@WebServlet(name = "CapacitacionesController", urlPatterns = { "/panel/capacitaciones", "/panel/capacitaciones/*" })
public class CapacitacionesController extends HttpServlet {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Número de serie de la clase */
    private static final long serialVersionUID = -1072022717584686035L;

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link CapacitacionesRepository} con los métodos CRUD para
     * {@link Capacitacion}es
     */
    private CapacitacionesRepository capacitacionesRepository;

    /**
     * Objeto {@link ProfesionalesRepository} con los métodos CRUD para
     * {@link Profesional}es
     */
    private ProfesionalesRepository profesionalesRepository;

    /**
     * Objeto {@link EmpresasRepository} con los métodos CRUD para {@link Empresa}s
     */
    private EmpresasRepository empresasRepository;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link CapacitacionesController}
     */
    public CapacitacionesController() {
        super();
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    @Override
    public void init() throws ServletException {
        // Obtener contexto
        ServletContext contexto = this.getServletContext();

        // Obtener información de configuración
        String url = contexto.getInitParameter("jdbcURL");
        String user = contexto.getInitParameter("jdbcUsername");
        String pass = contexto.getInitParameter("jdbcPassword");

        // Obtener instancia de conexión
        Conexion conexion = Conexion.getInstance(url, user, pass);

        // Inicializar repositorios
        capacitacionesRepository = new CapacitacionesRepository(conexion);
        profesionalesRepository = new ProfesionalesRepository(conexion);
        empresasRepository = new EmpresasRepository(conexion);
    }

    /**
     * Maneja la operación de mostrar el contenido de una {@link Capacitacion}
     * 
     * @param request  objeto {@link HttpServletRequest} con la información de la
     *                 solicitud que le hace el cliente al {@link HttpServlet}
     * @param response objeto {@link HttpServletResponse} con la respuesta que le
     *                 envía el {@link HttpServlet} al cliente
     * @param sesion   objeto {@link HttpSession} con la información de la sesión
     * @throws IOException      cuando ocurre un error de entrada/salida mientras el
     *                          {@link HttpServlet} intenta procesar la
     *                          solicitud/respuesta
     * @throws ServletException si un error interrumpe el normal funcionamiento del
     *                          {@link HttpServlet}
     */
    private void doVer(HttpServletRequest request, HttpServletResponse response, HttpSession sesion)
            throws IOException, ServletException {
        // Obtener uri
        String uri = request.getRequestURI();

        // Crear matcher
        Matcher matcher = Pattern.compile("([0-9]+)").matcher(uri);

        // Verificar ocurrencia
        if (matcher.find()) {
            // Convertir ocurrencia
            int id = Integer.parseInt(matcher.group());

            // Buscar capacitación
            Capacitacion capacitacion = (Capacitacion) capacitacionesRepository.buscarPorId(id);

            // Verificar existencia
            if (capacitacion != null) {
                // Obtener rol
                String rol = (String) sesion.getAttribute("rol");

                // Verificar rol
                if (rol.equals("admin")) {
                    // Buscar profesionales
                    List<Object> aux = profesionalesRepository.buscarTodos();

                    // Verificar resultados
                    if (aux != null) {
                        // Crear listado
                        List<Profesional> profesionales = new ArrayList<>();

                        // Convertir objetos
                        aux.forEach(pro -> profesionales.add((Profesional) pro));

                        // Agregar al request
                        request.setAttribute("profesionales", profesionales);
                    }

                    // Buscar empresas
                    aux = empresasRepository.buscarTodos();

                    // Verificar resultados
                    if (aux != null) {
                        // Crear listado
                        List<Empresa> empresas = new ArrayList<>();

                        // Convertir objetos
                        aux.forEach(empresa -> empresas.add((Empresa) empresa));

                        // Agregar al request
                        request.setAttribute("empresas", empresas);
                    }
                } else if (rol.equals("profesional")) {
                    // Buscar empresas
                    List<Empresa> aux = empresasRepository.buscarPorProfesionalId((int) sesion.getAttribute("uid"));

                    // Verificar resultados
                    if (aux != null) {
                        // Agregar al request
                        request.setAttribute("empresas", aux);
                    }
                }

                // Buscar profesional
                Profesional profesional = profesionalesRepository.buscarPorId(capacitacion.getProfesionalId());

                // Buscar empresa
                Empresa empresa = (Empresa) empresasRepository.buscarPorId(capacitacion.getEmpresaId());

                // Agregar elementos a la solicitud
                request.setAttribute("capacitacion", capacitacion);
                request.setAttribute("profesional", profesional);
                request.setAttribute("empresa", empresa);
                request.setAttribute("ver", true);

                // Mostrar contenido
                request.getRequestDispatcher("/WEB-INF/paneles/capacitaciones.detalles.jsp").forward(request, response);
            } else {
                // Agregar error
                sesion.setAttribute("error",
                        "El registro de la Capacitación con el identificador '" + id + "' no existe en el repositorio");

                // Redireccionar
                response.sendRedirect(request.getContextPath() + "/panel/capacitaciones");
            }
        } else {
            // Agregar error
            sesion.setAttribute("error", "Debe seleccionar un registro de la tabla para poder ver sus detalles");

            // Redireccionar
            response.sendRedirect(request.getContextPath() + "/panel/capacitaciones");
        }
    }

    /**
     * Maneja la operación de mostrar el contenido de una {@link Capacitacion}
     * 
     * @param request  objeto {@link HttpServletRequest} con la información de la
     *                 solicitud que le hace el cliente al {@link HttpServlet}
     * @param response objeto {@link HttpServletResponse} con la respuesta que le
     *                 envía el {@link HttpServlet} al cliente
     * @param sesion   objeto {@link HttpSession} con la información de la sesión
     * @throws IOException cuando ocurre un error de entrada/salida mientras el
     *                     {@link HttpServlet} intenta procesar la
     *                     solicitud/respuesta
     */
    private void doEliminar(HttpServletRequest request, HttpServletResponse response, HttpSession sesion)
            throws IOException {
        // Obtener rol
        String rol = (String) sesion.getAttribute("rol");

        // Verificar rol
        if (!rol.equals("empresa")) {
            // Obtener uri
            String uri = request.getRequestURI();

            // Crear matcher
            Matcher matcher = Pattern.compile("([0-9]+)").matcher(uri);

            // Verificar ocurrencias
            if (matcher.find()) {
                // Convertir ocurrencia
                int id = Integer.parseInt(matcher.group());

                // Buscar capacitacion
                Capacitacion capacitacion = (Capacitacion) capacitacionesRepository.buscarPorId(id);

                // Verificar existencia
                if (capacitacion != null) {
                    // Eliminar registro
                    boolean eliminado = capacitacionesRepository.eliminarRegistro(id);

                    // Verificar eliminación
                    if (eliminado) {
                        // Agregar mensaje de éxito
                        sesion.setAttribute("exito", "El registro fue eliminado correctamente");
                    } else {
                        // Agregar error
                        sesion.setAttribute("error",
                                "Ha ocurrido un error mientras se intentaba eliminar el registro. Por favor, inténtelo nuevamente");
                    }
                } else {
                    // Agregar error
                    sesion.setAttribute("error", "El registro de la Capacitación con el identificador '" + id
                            + "' no existe en el repositorio");
                }
            } else {
                // Agregar error
                sesion.setAttribute("error", "Debe seleccionar un registro de la tabla para poder ver sus detalles");
            }
        } else {
            // Agregar error
            sesion.setAttribute("error", "No posee las credenciales necesarias para poder eliminar un registro");
        }

        // Redireccionar
        response.sendRedirect(request.getContextPath() + "/panel/capacitaciones");
    }

    /**
     * Maneja la operación de mostrar el contenido de una {@link Capacitacion}
     * 
     * @param request  objeto {@link HttpServletRequest} con la información de la
     *                 solicitud que le hace el cliente al {@link HttpServlet}
     * @param response objeto {@link HttpServletResponse} con la respuesta que le
     *                 envía el {@link HttpServlet} al cliente
     * @param sesion   objeto {@link HttpSession} con la información de la sesión
     * @throws IOException      cuando ocurre un error de entrada/salida mientras el
     *                          {@link HttpServlet} intenta procesar la
     *                          solicitud/respuesta
     * @throws ServletException si un error interrumpe el normal funcionamiento del
     *                          {@link HttpServlet}
     */
    private void doAgregarEditar(HttpServletRequest request, HttpServletResponse response, HttpSession sesion)
            throws IOException, ServletException {
        // Obtener rol
        String rol = (String) sesion.getAttribute("rol");

        // Verificar rol
        if (!rol.equals("empresa")) {
            // Obtener uri
            String uri = request.getRequestURI();

            // Crear matcher
            Matcher matcher = Pattern.compile("([0-9]+)").matcher(uri);

            // Verificar ocurrencias
            if (matcher.find()) {
                // Convertir ocurrencia
                int id = Integer.parseInt(matcher.group());

                // Buscar capacitación
                Capacitacion capacitacion = (Capacitacion) capacitacionesRepository.buscarPorId(id);

                // Verificar existencia
                if (capacitacion != null) {
                    // Verificar rol
                    if (rol.equals("admin")) {
                        // Buscar todos los profesionales
                        List<Object> aux = profesionalesRepository.buscarTodos();

                        // Verificar resultados
                        if (aux != null) {
                            // Crear lista
                            List<Profesional> profesionales = new ArrayList<>();

                            // Convertir objetos
                            aux.forEach(o -> profesionales.add((Profesional) o));

                            // Agregar listado a la solicitud
                            request.setAttribute("profesionales", profesionales);
                        }

                        // Buscar todas las empresas
                        aux = empresasRepository.buscarTodos();

                        // Verificar resultados
                        if (aux != null) {
                            // Crear lista
                            List<Empresa> empresas = new ArrayList<>();

                            // Convertir objetos
                            aux.forEach(o -> empresas.add((Empresa) o));

                            // Agregar a la solicitud
                            request.setAttribute("empresas", empresas);
                        }
                    } else if (rol.equals("profesional")) {
                        // Obtener identificador
                        int profid = (int) sesion.getAttribute("uid");

                        // Buscar empresas del profesional
                        List<Empresa> empresas = empresasRepository.buscarPorProfesionalId(profid);

                        // Verificar resultados
                        if (empresas != null) {
                            // Agregar listado al request
                            request.setAttribute("empresas", empresas);
                        }
                    }

                    // Agregar capacitacion al request
                    request.setAttribute("capacitacion", capacitacion);

                    request.setAttribute("accion", "Editar");

                    // Mostrar contenido
                    request.getRequestDispatcher("/WEB-INF/paneles/capacitaciones.detalles.jsp").forward(request,
                            response);
                } else {
                    // Agregar error
                    sesion.setAttribute("error", "El registro de la Capacitación con el identificador '" + id
                            + "' no existe en el repositorio");

                    // Redireccionar
                    response.sendRedirect(request.getContextPath() + "/panel/capacitaciones");
                }
            } else {
                // Buscar profesionales
                List<Object> aux = profesionalesRepository.buscarTodos();

                // Verificar resultados
                if (aux != null) {
                    // Crear lista
                    List<Profesional> profesionales = new ArrayList<>();

                    // Convertir objetos
                    aux.forEach(pro -> profesionales.add((Profesional) pro));

                    // Agregar al request
                    request.setAttribute("profesionales", profesionales);
                }

                // Buscar empresas
                if (rol.equals("admin")) {
                    // Buscar todas las empresas
                    aux = empresasRepository.buscarTodos();

                    // Verificar resultados
                    if (aux != null) {
                        // Crear lista
                        List<Empresa> empresas = new ArrayList<>();

                        // Convertir objetos
                        aux.forEach(emp -> empresas.add((Empresa) emp));

                        // Agregar al request
                        request.setAttribute("empresas", empresas);
                    }
                } else if (rol.equals("profesional")) {
                    // Buscar empresas a cargo del profesional
                    List<Empresa> empresas = empresasRepository
                            .buscarPorProfesionalId((int) sesion.getAttribute("uid"));

                    // Verificar si hay resultados
                    if (empresas != null) {
                        // Agregar al request
                        request.setAttribute("empresas", empresas);
                    }
                }

                // Agregar atributos al request
                request.setAttribute("accion", "Agregar");

                // Mostrar contenido
                request.getRequestDispatcher("/WEB-INF/paneles/capacitaciones.detalles.jsp").forward(request, response);
            }
        } else {
            // Agregar error
            sesion.setAttribute("error", "No posee las credenciales suficientes para poder efectuar esa acción");

            // Redireccionar
            response.sendRedirect(request.getContextPath() + "/panel/capacitaciones");
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

        // Verificar inicio de sesión
        if (sesion.getAttribute("loggedIn") != null && ((boolean) sesion.getAttribute("loggedIn"))) {
            // Obtener uri
            String uri = request.getRequestURI();

            // Agregar atributos a la solicitud
            request.setAttribute("titulo", "Capacitaciones");
            request.setAttribute("activo", "capa");

            // Verificar acción
            if (uri.contains("agregar") || uri.contains("editar")) {
                doAgregarEditar(request, response, sesion);
            } else if (uri.contains("eliminar")) {
                doEliminar(request, response, sesion);
            } else if (uri.contains("ver")) {
                doVer(request, response, sesion);
            } else {
                // Obtener el rol del usuario
                String rol = (String) sesion.getAttribute("rol");

                // Crear listado
                List<Capacitacion> capacitaciones = new ArrayList<>();

                // Verificar el rol del usuario
                if (rol.equals("admin")) {
                    // Buscar todas las capacitaciones
                    List<Object> aux = capacitacionesRepository.buscarTodos();

                    // Verificar si hay resultados
                    if (aux != null) {
                        // Convertir objetos
                        for (Object o : aux) {
                            capacitaciones.add((Capacitacion) o);
                        }
                    }
                } else if (rol.equals("profesional")) {
                    // Buscar capacitaciones
                    List<Capacitacion> aux = capacitacionesRepository
                            .buscarPorProfesionalId((int) sesion.getAttribute("uid"));

                    if (aux != null) {
                        capacitaciones = aux;
                    }
                } else if (rol.equals("empresa")) {
                    // Buscar capacitaciones
                    List<Capacitacion> aux = capacitacionesRepository
                            .buscarPorEmpresaId((int) sesion.getAttribute("uid"));

                    if (aux != null) {
                        capacitaciones = aux;
                    }
                }

                // Crear listados
                List<Profesional> profesionales = new ArrayList<>();
                List<Empresa> empresas = new ArrayList<>();

                // Buscar información
                capacitaciones.forEach(capacitacion -> {
                    // Buscar profesional
                    Profesional profesional = profesionalesRepository.buscarPorId(capacitacion.getProfesionalId());

                    // Verificar si hay resultados
                    if (profesional != null) {
                        // Agregar al listado
                        profesionales.add(profesional);
                    }

                    // Buscar empresa
                    Empresa empresa = (Empresa) empresasRepository.buscarPorId(capacitacion.getEmpresaId());

                    // Verificar resultado
                    if (empresa != null) {
                        // Agregar al listado
                        empresas.add(empresa);
                    }
                });

                // Verificar si hay error
                if (sesion.getAttribute("error") != null) {
                    // Agregar mensaje al request
                    request.setAttribute("error", sesion.getAttribute("error"));

                    // Borrar el error de la sesión
                    sesion.removeAttribute("error");
                }

                // Verificar si hay mensaje de éxito
                if (sesion.getAttribute("exito") != null) {
                    // Agregar mensaje a la solicitud
                    request.setAttribute("exito", sesion.getAttribute("exito"));

                    // Quitar mensaje de la sesión
                    sesion.removeAttribute("exito");
                }

                // Agregar listados a la solicitud
                request.setAttribute("profesionales", profesionales);
                request.setAttribute("empresas", empresas);
                request.setAttribute("capacitaciones", capacitaciones);

                // Mostrar contenido
                request.getRequestDispatcher("/WEB-INF/capacitaciones.jsp").forward(request, response);
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
        // Obtener objeto de sesión
        HttpSession sesion = request.getSession();

        // Verificar inicio de sesión
        if (sesion.getAttribute("loggedIn") != null && ((boolean) sesion.getAttribute("loggedIn"))) {
            // Obtener rol
            String rol = (String) sesion.getAttribute("rol");

            // Verificar rol
            if (!rol.equals("empresa")) {
                // Obtener información enviada por el usuario
                String auxId = request.getParameter("id");
                String auxFecha = request.getParameter("fecha");
                String direccion = request.getParameter("direccion");
                String tema = request.getParameter("tema");
                String auxParticipantes = request.getParameter("participantes");
                String auxRealizada = request.getParameter("realizada");
                String auxProfesional = request.getParameter("profesionalId");
                String auxEmpresa = request.getParameter("empresaId");

                // Crear objeto
                Capacitacion capacitacion = new Capacitacion();

                // Poblar objeto
                capacitacion.setFecha(LocalDate.parse(auxFecha));
                capacitacion.setDireccion(direccion);
                capacitacion.setTema(tema);
                capacitacion.setParticipantes(Integer.parseInt(auxParticipantes));
                capacitacion.setRealizada(Boolean.parseBoolean(auxRealizada));
                capacitacion.setProfesionalId(Integer.parseInt(auxProfesional));
                capacitacion.setEmpresaId(Integer.parseInt(auxEmpresa));

                // Verificar si el identificador está presente
                if (auxId != null && !auxId.isBlank()) {
                    // Convertir identificador
                    int id = Integer.parseInt(auxId);

                    // Buscar capacitación
                    Capacitacion aux = (Capacitacion) capacitacionesRepository.buscarPorId(id);

                    // Verificar existencia
                    if (aux != null) {
                        // Insertar identificador
                        capacitacion.setId(id);

                        // Actualizar registro
                        boolean actualizado = capacitacionesRepository.actualizarRegistro(capacitacion);

                        // Verificar
                        if (actualizado) {
                            // Redirigir a la capacitación
                            response.sendRedirect(request.getContextPath() + "/panel/capacitaciones/ver/" + id);
                        } else {
                            // Agregar error
                            sesion.setAttribute("error",
                                    "Ocurrió un error al intentar actualizar la información del registro. Por favor, inténtelo nuevamente");

                            // Redirigir
                            response.sendRedirect(request.getContextPath() + "/panel/capacitaciones");
                        }
                    }
                } else {
                    // Agregar nuevo registro
                    boolean agregado = capacitacionesRepository.agregarRegistro(capacitacion);

                    // Verificar
                    if (agregado) {
                        // Agregar mensaje
                        sesion.setAttribute("exito", "El registro fue agregado correctamente");
                    } else {
                        // Agregar mensaje
                        sesion.setAttribute("error",
                                "El registro no pudo ser agregado debido a un error en la base de datos. Por favor, inténtelo nuevamente");
                    }

                    // Redireccionar
                    response.sendRedirect(request.getContextPath() + "/panel/capacitaciones");
                }
            } else {
                // Agregar error
                sesion.setAttribute("error", "No posee las credenciales necesarias para poder efectuar la acción");

                // Redireccionar
                response.sendRedirect(request.getContextPath() + "/panel/capacitaciones");
            }
        } else {
            // Pa' la casa
            response.sendRedirect(request.getContextPath());
        }
    }

}
