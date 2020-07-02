package cl.rhacs.detta.controladores;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HomeController", urlPatterns = { "" })
public class HomeController extends HttpServlet {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Número de serie de la clase */
    private static final long serialVersionUID = -4418477024354810199L;

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
        // Agregar atributos a la solicitud
        request.setAttribute("activo", "home");

        // Mostrar contenido correspondiente
        request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
    }

}
