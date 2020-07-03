package cl.rhacs.detta.controladores.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Obtener objeto de sesión
        HttpSession sesion = se.getSession();

        // Verificar si el usuario sigue conectado
        if (sesion.getAttribute("loggedIn") != null) {
            // Se trató de un timeout
            sesion.setAttribute("loginError", "Su sesión ha expirado");
        }
    }

}
