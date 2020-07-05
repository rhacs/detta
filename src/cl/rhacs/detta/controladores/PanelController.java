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

import cl.rhacs.detta.modelos.Accidente;
import cl.rhacs.detta.modelos.Empresa;
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
            // Crear listado de accidentes
            List<Accidente> accidentes = null;

            // Obtener el rol del usuario
            String rol = (String) sesion.getAttribute("rol");

            // Proseguir de acuerdo al rol
            if (rol.equals("admin")) {
                // Buscar todos los accidentes
                List<Object> aux = accidentesRepository.buscarTodos();

                // Inicializar listado
                accidentes = new ArrayList<>();

                // Poblar listado
                for (Object accidente : aux) {
                    accidentes.add((Accidente) accidente);
                }
            } else if (rol.equals("profesional")) {
                // Obtener id del usuario
                int id = (int) sesion.getAttribute("uid");

                // Buscar empresas que están bajo la supervisión del profesional
                List<Empresa> empresas = empresasRepository.buscarPorProfesionalId(id);

                // Verificar que el listado no sea nulo
                if (empresas != null) {
                    // Inicializar listado
                    accidentes = new ArrayList<>();

                    // Buscar los accidentes de las empresas
                    for (Empresa empresa : empresas) {
                        List<Accidente> empresaAccidentes = accidentesRepository.buscarPorEmpresaId(empresa.getId());

                        if (empresaAccidentes != null) {
                            for (Accidente accidente : empresaAccidentes) {
                                accidentes.add(accidente);
                            }
                        }
                    }
                }
            } else if (rol.equals("empresa")) {
                // Obtener id del usuario
                int id = (int) sesion.getAttribute("uid");

                // Buscar los accidentes de la empresa
                accidentes = accidentesRepository.buscarPorEmpresaId(id);
            }

            // Verificar que hayan registros
            if (accidentes != null) {
                // Agregar listado al request
                request.setAttribute("accidentes", accidentes);
            }

            // Verificar si hay errores en sesión
            if (sesion.getAttribute("youShallNotPass") != null) {
                // Recuperar error
                String mensaje = (String) sesion.getAttribute("youShallNotPass");

                // Quitar error de la sesión
                sesion.removeAttribute("youShallNotPass");

                // Insertar error en la solicitud
                request.setAttribute("error", mensaje);
            }

            // Agregar título
            request.setAttribute("titulo", "Dashboard");

            // Agregar página activa
            request.setAttribute("activo", "home");

            // Mostrar contenido correspondiente
            request.getRequestDispatcher("/WEB-INF/panel.jsp").forward(request, response);
        } else {
            // Insertar error en la sesión
            sesion.setAttribute("loginError", "Debe iniciar sesión para poder acceder al panel");

            // Pa la casa
            response.sendRedirect(request.getContextPath());
        }
    }

}
