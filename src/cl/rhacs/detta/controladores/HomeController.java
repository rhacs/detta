package cl.rhacs.detta.controladores;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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

@WebServlet(name = "HomeController", urlPatterns = { "" })
public class HomeController extends HttpServlet {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Número de serie de la clase */
    private static final long serialVersionUID = -4418477024354810199L;

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Repositorio de {@link Profesional}es */
    private ProfesionalesRepository profesionalesRepository;

    /** Repositorio de {@link Empresa}s */
    private EmpresasRepository empresasRepository;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia del objeto {@link HomeController}
     */
    public HomeController() {
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

        // Crear objeto de conexión
        Conexion conexion = Conexion.getInstance(url, user, pass);

        // Inicializar repositorios
        profesionalesRepository = new ProfesionalesRepository(conexion);
        empresasRepository = new EmpresasRepository(conexion);
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

        // Verificar si la sesión está iniciada
        if (sesion.getAttribute("loggedIn") != null && ((boolean) sesion.getAttribute("loggedIn"))) {
            response.sendRedirect(request.getContextPath() + "/panel");
        } else {
            // Verificar si hay error
            if (sesion.getAttribute("loginError") != null) {
                // Obtener mensaje
                String error = (String) sesion.getAttribute("loginError");

                // Borrar mensaje del objeto de sesión
                sesion.removeAttribute("loginError");

                // Agregar error al request
                request.setAttribute("error", error);
            }

            // Mostrar contenido correspondiente
            request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
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

        // Obtener información enviada por el usuario
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Codificar contraseña
        try {
            password = Utilidades.codificarContrasena(password);
        } catch (NoSuchAlgorithmException e) {
            Utilidades.extraerError("HomeController", "doPost", e);
        }

        // Buscar el usuario en los registros
        Profesional profesional = profesionalesRepository.buscarPorEmail(email);
        Empresa empresa = empresasRepository.buscarPorEmail(email);

        // Contraseña a comparar
        String comparame = null;

        // Rol del usuario
        String rol = null;

        // Identificador del usuario
        int id = -1;

        // Verificar si hay resultado y, si lo hay, que no tenga el contrato terminado
        if (profesional != null && profesional.getEstadoContrato().equals("Vigente")) {
            comparame = profesional.getPassword();
            rol = "profesional";
            id = profesional.getId();
        } else if (empresa != null) {
            comparame = empresa.getPassword();
            rol = "empresa";
            id = empresa.getId();
        } else if (email.equals("admin@detta.cl")) {
            comparame = "GWskgZaAUVBVVB7ptdgDV3/lAbp4FJ4vpx/ffMoeDgg=";
            rol = "admin";
        }

        // Verificar que haya contraseña y que sea igual a la ingresada
        if (comparame != null && comparame.equals(password)) {
            // Inicio de sesión correcto
            sesion.setAttribute("loggedIn", true);
            sesion.setAttribute("rol", rol);
            sesion.setAttribute("uid", id);

            // Redireccionar al usuario al panel
            response.sendRedirect(request.getContextPath() + "/panel");
        } else {
            // Crear mensaje de error
            String error = "Las credenciales proporcionadas no coinciden con los almacenados en nuestros registros.";

            // Agregar error al request
            request.setAttribute("error", error);
            request.setAttribute("email", email);

            // Mostrar contenido
            request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
        }
    }

}
