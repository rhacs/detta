package cl.rhacs.detta.controladores;

import java.io.IOException;
import java.util.List;

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
     * Muestra todos los registros contenidos en el repositorio
     * 
     * @param request  objeto {@link HttpServletRequest} con la información de la
     *                 solicitud del cliente al {@link HttpServlet}
     * @param response objeto {@link HttpServletResponse} con la respuesta que le
     *                 envía el {@link HttpServlet} al cliente
     */
    private void mostrarTodos(HttpServletRequest request, HttpServletResponse response) {
        // Buscar todos los registros
        List<Object> accidentes = repositorio.buscarTodos();
        // Agregar el listado a los atributos de la solicitud
        request.setAttribute("accidentes", accidentes);

        request.setAttribute("activo", "accidentes");
        request.setAttribute("titulo", "Listado de Accidentes");

        // Mostrar contenido correspondiente
        try {
            request.getRequestDispatcher("accidentes.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            // Recuperar el mensaje de error
            String mensaje = e.getLocalizedMessage() == null ? e.getMessage() : e.getLocalizedMessage();

            // Mostrar error
            System.err.println("ERROR AccidentesController#mostrarTodos()");
            System.err.println(" [!] " + mensaje);
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
     *                          {@link HttpServlet} maneja la solicitud GET
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Verificar que la solicitud contenga la acción a realizar
        if (request.getParameterMap().containsKey("do")) {
            // Recuperar acción
            String accion = request.getParameter("do");

            if (accion.equals("borrar")) {
                // Eliminar un elemento
                try {
                    // Recuperar identificador del Accidente a eliminar
                    int id = Integer.parseInt(request.getParameter("id"));

                    // Eliminar un registro de la base de datos
                    repositorio.eliminarRegistro(id);
                } catch (NumberFormatException e) {
                    // Ocurrió un error al intentar convertir el id a un número
                    System.err.println("ERROR AccidentesController#doGet()");
                    System.err.println(" [!] " + e.getMessage());
                }

                // Redirigir al usuario al listado de accidentes
                response.sendRedirect(request.getContextPath() + "/accidentes");
            }
        } else {
            mostrarTodos(request, response);
        }
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
