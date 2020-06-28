package cl.rhacs.detta.controladores;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.rhacs.detta.repositorios.AccidentesRepository;

@WebServlet(name = "AccidentesController", urlPatterns = { "/accidentes" })
public class AccidentesController extends HttpServlet {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Número de serie de la clase */
    private static final long serialVersionUID = 1375735210342017948L;

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Repositorio con la información almacenada en la base de datos */
    private AccidentesRepository repositorio;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link AccidentesController}
     */
    public AccidentesController() {
        super();
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * Método que se ejecuta al inicializar el servlet
     * 
     * @throws ServletException si una excepción interrumpe el funcionamiento normal
     *                          del {@link HttpServlet}
     */
    @Override
    public void init() throws ServletException {
        // Obtener el contexto
        ServletContext contexto = this.getServletContext();

        // Obtener la información de conexión con la base de datos
        String url = contexto.getInitParameter("jdbcURL");
        String user = contexto.getInitParameter("jdbcUsername");
        String pass = contexto.getInitParameter("jdbcPassword");

        // Inicializar el repositorio
        repositorio = new AccidentesRepository(url, user, pass);
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
     *                          {@link HttpServlet} maneja la solicitud GET
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Maneja las solicitudes POST al {@link HttpServlet}
     * 
     * @param request  un objeto {@link HttpServletRequest} que contiene la
     *                 solicitud que el cliente le realizó al {@link HttpServlet}
     * @param response un objeto {@link HttpServletResponse} que contiene la
     *                 respuesta que el {@link HttpServlet} le envía al cliente
     * 
     * @throws ServletException si la solicitud POST hecha al {@link HttpServlet} no
     *                          pudo ser manejada
     * @throws IOException      si un error de entrada/salida es detectado cuando el
     *                          {@link HttpServlet} maneja la solicitud POST
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
