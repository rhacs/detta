package cl.rhacs.detta;

import java.sql.SQLException;

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

}
