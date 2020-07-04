package cl.rhacs.detta.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AuthController", urlPatterns = { "/auth/logout" })
public class AuthController extends HttpServlet {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Número de serie de la clase */
    private static final long serialVersionUID = 521528903711113532L;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link AuthController}
     */
    public AuthController() {
        super();
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

        // Verificar si el usuario ha iniciado sesión
        if (sesion.getAttribute("loggedIn") != null) {
            // Eliminar sesión
            sesion.removeAttribute("loggedIn");

            // Agregar mensaje
            sesion.setAttribute("loginError", "Sesión cerrada correctamente, hasta chao :D");
        }

        // Redireccionar usuario al login
        response.sendRedirect(request.getContextPath() + "/");
    }

}
