package cl.rhacs.detta.controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.rhacs.detta.modelos.Profesional;
import cl.rhacs.detta.modelos.database.Conexion;
import cl.rhacs.detta.repositorios.ProfesionalesRepository;

@WebServlet(name = "ProfesionalesController", urlPatterns = { "/panel/profesionales" })
public class ProfesionalesController extends HttpServlet {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Número de serie de la clase */
    private static final long serialVersionUID = -8933025749888489934L;

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link ProfesionalesRepository} con los métodos CRUD a la base de
     * datos
     */
    private ProfesionalesRepository profesionalesRepository;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link ProfesionalesController}
     */
    public ProfesionalesController() {
        super();
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    @Override
    public void init() throws ServletException {
        // Obtener contexto
        ServletContext contexto = this.getServletContext();

        // Obtener parámetros de configuración
        String url = contexto.getInitParameter("jdbcURL");
        String user = contexto.getInitParameter("jdbcUsername");
        String pass = contexto.getInitParameter("jdbcPassword");

        // Inicializar repositorios
        profesionalesRepository = new ProfesionalesRepository(Conexion.getInstance(url, user, pass));
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
        System.out.println("doGet");

        // Obtener objeto de sesión
        HttpSession sesion = request.getSession();

        // Verificar que el usuario haya iniciado sesión
        if (sesion.getAttribute("loggedIn") != null && ((boolean) sesion.getAttribute("loggedIn"))) {
            // Obtener la información del usuario almacenada en sesión
            String rol = (String) sesion.getAttribute("rol");

            // Verificar que el usuario tenga los "poderes" necesarios para ver esta sección
            if (rol != null && rol.equals("admin")) {
                // Agregar atributos a la solicitud
                request.setAttribute("titulo", "Profesionales");
                request.setAttribute("activo", "prof");

                List<Object> aux = profesionalesRepository.buscarTodos();

                // Verificar si hay resultados
                if (aux != null) {
                    // Crear nueva lista
                    List<Profesional> profesionales = new ArrayList<>();

                    for (Object ob : aux) {
                        Profesional profesional = (Profesional) ob;
                        profesionales.add(profesional);
                    }

                    // Agregar listado a la solicitud
                    request.setAttribute("profesionales", profesionales);
                }

                // Verificar si hay error
                if (sesion.getAttribute("error") != null) {
                    // Obtener mensaje
                    String mensaje = (String) sesion.getAttribute("error");

                    // Borrar error de la sesión
                    sesion.removeAttribute("error");

                    // Agregar error a la solicitud
                    request.setAttribute("error", mensaje);
                }

                // Mostrar contenido
                request.getRequestDispatcher("/WEB-INF/profesionales.jsp").forward(request, response);
            } else {
                // Almacenar error en la sesión
                sesion.setAttribute("youShallNotPass",
                        "No tienes los poderes suficientes para poder acceder a esa sección");

                // Redireccionar al usuario
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doPost");
    }

}
