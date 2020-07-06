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

import cl.rhacs.detta.modelos.Empresa;
import cl.rhacs.detta.modelos.database.Conexion;
import cl.rhacs.detta.repositorios.EmpresasRepository;

@WebServlet(name = "ClientesController", urlPatterns = "/panel/clientes")
public class ClientesController extends HttpServlet {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Número de serie de la clase */
    private static final long serialVersionUID = 2172950795908708817L;

    // Atributos
    // -----------------------------------------------------------------------------------------

    private EmpresasRepository empresasRepository;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link ClientesController}
     */
    public ClientesController() {
        super();
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    @Override
    public void init() throws ServletException {
        // Obtener contexto
        ServletContext contexto = this.getServletContext();

        // Información de configuración
        String url = contexto.getInitParameter("jdbcURL");
        String user = contexto.getInitParameter("jdbcUsername");
        String pass = contexto.getInitParameter("jdbcPassword");

        // Inicializar repositorios
        empresasRepository = new EmpresasRepository(Conexion.getInstance(url, user, pass));
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
            // Obtener parámetros de sesión
            String rol = (String) sesion.getAttribute("rol");
            int id = (int) sesion.getAttribute("uid");

            // Verificar roles
            if (rol.equals("empresa")) {
                // Agregar error
                sesion.setAttribute("youShallNotPass",
                        "No posee las credenciales necesarias para acceder a esta sección");

                // Redireccionar
                response.sendRedirect(request.getContextPath() + "/panel");
            } else {
                // Admin o Profesional

                // Crear listado
                List<Empresa> empresas = null;

                if (id != -1) {
                    // Profesional

                    // Recuperar las empresas a cargo del Profesional
                    empresas = empresasRepository.buscarPorProfesionalId(id);
                } else {
                    // Administrador

                    // Recuperar todas las empresas
                    List<Object> aux = empresasRepository.buscarTodos();

                    // Verificar resultados
                    if (aux != null) {
                        // Inicializar listado
                        empresas = new ArrayList<Empresa>();

                        // Convertir objetos
                        for (Object o : aux) {
                            empresas.add((Empresa) o);
                        }
                    }
                }

                // Agregar atributos a la solicitud
                request.setAttribute("empresas", empresas);
                request.setAttribute("titulo", "Clientes");
                request.setAttribute("activo", "empr");

                // Verificar si hay errores
                if (sesion.getAttribute("error") != null) {
                    // Agregar error al request
                    request.setAttribute("error", sesion.getAttribute("error"));

                    // Eliminar error de la sesión
                    sesion.removeAttribute("error");
                }

                // Verificar si hay mensajes de éxito
                if (sesion.getAttribute("exito") != null) {
                    // Agregar mensaje a la solicitud
                    request.setAttribute("exito", sesion.getAttribute("exito"));

                    // Borrar mensaje de la sesión
                    sesion.removeAttribute("exito");
                }

                // Mostrar contenido
                request.getRequestDispatcher("/WEB-INF/clientes.jsp").forward(request, response);
            }
        } else {
            // Pa la casa
            response.sendRedirect(request.getContextPath());
        }
    }

}
