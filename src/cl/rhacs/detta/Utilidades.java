package cl.rhacs.detta;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Base64;

public class Utilidades {

    /**
     * Extrae la información de la {@link Exception} y la imprime por consola
     * 
     * @param clase   Nombre de la clase donde ocurrió el error
     * @param funcion Nombre del método que se ejecutó
     * @param error   objeto {@link Exception} con la información del error
     */
    public static void extraerError(String clase, String funcion, Exception error) {
        // Mostrar cabecera
        System.err.println(String.format("%s#%s()", clase, funcion));

        // Verificar si la Excepción es una instancia de SQLException
        if (error instanceof SQLException) {
            // Obtener código de estado
            String codigo = ((SQLException) error).getSQLState();

            // Mostrar código de error
            System.err.println(String.format(" [!] Código de estado SQL: %s", codigo));
        }

        // Capturar mensaje
        String mensaje = error.getLocalizedMessage() == null ? error.getMessage() : error.getLocalizedMessage();

        // Mostrar mensaje
        System.err.println(String.format(" [!] %s", mensaje));
    }

    /**
     * Codifica una contraseña utilizando el algoritmo SHA-256
     * 
     * @param password contraseña a codificar
     * @return la contraseña codificada
     * @throws NoSuchAlgorithmException cuando el algoritmo especificado no existe o
     *                                  no está disponible en el entorno
     */
    public static String codificarContrasena(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(hash);
    }

}
