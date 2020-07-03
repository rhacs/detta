package cl.rhacs.detta.controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.rhacs.detta.modelos.database.Conexion;
import cl.rhacs.detta.repositorios.AccidentesRepository;
import cl.rhacs.detta.repositorios.EmpresasRepository;
import cl.rhacs.detta.repositorios.ProfesionalesRepository;

/**
 * Servlet implementation class PanelController
 */
@WebServlet(name = "PanelController", urlPatterns = {"/panel"})
public class PanelController extends HttpServlet {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Número de serie de la clase */
    private static final long serialVersionUID = 542451978681710022L;

    // Atributos
    // -----------------------------------------------------------------------------------------

    private ProfesionalesRepository profesionalesRepository;

    private EmpresasRepository empresasRepository;

    private AccidentesRepository accidentesRepository;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link PanelController}
     */
    public PanelController() {
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
        
        // Inicializar objeto de conexión
        Conexion conexion = Conexion.getInstance(url, user, pass);
        
        // Inicializar repositorios
        profesionalesRepository = new ProfesionalesRepository(conexion);
        empresasRepository = new EmpresasRepository(conexion);
        accidentesRepository = new AccidentesRepository(conexion);
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
     *                          {@link HttpServlet} maneja la solicitud GET
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener objeto de sesión
        HttpSession sesion = request.getSession();
        
        // Verificar inicio de sesión
        if(sesion.getAttribute("loggedIn") != null && ((boolean) sesion.getAttribute("loggedIn"))) {
            
        } else {
            // Insertar error en la sesión
            sesion.setAttribute("loginError", "Debe iniciar sesión para poder acceder al panel");

            // Pa la casa
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

}
