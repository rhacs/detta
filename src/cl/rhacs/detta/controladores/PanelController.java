package cl.rhacs.detta.controladores;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.rhacs.detta.modelos.Accidente;
import cl.rhacs.detta.modelos.database.Conexion;
import cl.rhacs.detta.repositorios.AccidentesRepository;
import cl.rhacs.detta.repositorios.EmpresasRepository;
import cl.rhacs.detta.repositorios.ProfesionalesRepository;

@WebServlet(name = "PanelController", urlPatterns = { "/panel" })
public class PanelController extends HttpServlet {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Número de serie de la clase */
    private static final long serialVersionUID = 542451978681710022L;

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Repositorio de {@link Profesional}es */
    private ProfesionalesRepository profesionalesRepository;

    /** Repositorio de {@link Empresa}s */
    private EmpresasRepository empresasRepository;

    /** Repositorio de {@link Accidente}s */
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
     *                          {@link HttpServlet} maneja la solicitud
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener objeto de sesión
        HttpSession sesion = request.getSession();

        // Verificar inicio de sesión
        if (sesion.getAttribute("loggedIn") != null && ((boolean) sesion.getAttribute("loggedIn"))) {
            // Obtener todos los accidentes
            List<Object> accidentes = accidentesRepository.buscarTodos();

            // Verificar que hayan registros
            if (accidentes != null) {
                // Generar listado de fechas con la cantidad de accidentes
                Map<LocalDate, Integer> fechas = new HashMap<>();

                // Rellenar el listado
                accidentes.forEach(accidente -> {
                    // Obtener la fecha del accidente
                    LocalDate fecha = ((Accidente) accidente).getFecha();

                    // Verificar si la fecha existe
                    if (fechas.containsKey(fecha)) {
                        // Sumar un accidente a la fecha
                        fechas.computeIfPresent(fecha, (key, value) -> value + 1);
                    } else {
                        // Agregar fecha del accidente
                        fechas.put(fecha, 1);
                    }
                });

                // Agregar listado al request
                request.setAttribute("accidentes", fechas);
            }

            // Agregar título
            request.setAttribute("titulo", "Dashboard");

            // Agregar página a incluir
            request.setAttribute("pagina", "./panel/reportes.jsp");

            // Agregar página activa
            request.setAttribute("activo", "home");

            // Mostrar contenido correspondiente
            request.getRequestDispatcher("WEB-INF/panel.jsp").forward(request, response);
        } else {
            // Insertar error en la sesión
            sesion.setAttribute("loginError", "Debe iniciar sesión para poder acceder al panel");

            // Pa la casa
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

}
