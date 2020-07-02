package cl.rhacs.detta.repositorios.interfaces;

import java.util.List;

public interface IRepository {

    /**
     * Agrega un registro {@link Object} al repositorio
     * 
     * @param objeto un objeto {@link Object} con la información a agregar
     * @return {@code true} si el registro fue agregado, {@code false} en cualquier
     *         otro caso
     */
    public boolean agregarRegistro(Object objeto, int padreId);

    /**
     * Recupera todos los registros almacenados en el repositorio
     * 
     * @return un objeto {@link List} con todos los registros, {@code null} en
     *         cualquier otro caso
     */
    public List<Object> buscarTodos();

    /**
     * Recupera un registro del repositorio en base al identificador proporcionado
     * 
     * @param id identificador del registro
     * @return un objeto {@link Object} con el registro obtenido, {@code null} en
     *         cualquier otro caso
     */
    public Object buscarPorId(int id);

    /**
     * Actualiza la información de un registro dada la información proporcionada
     * 
     * @param objeto objeto {@link Object} que contiene la información a actualizar
     * @return {@code true} si el registro fue actualizado, {@code false} en
     *         cualquier otro caso
     */
    public boolean actualizarRegistro(Object objeto);

    /**
     * Elimina un registro del repositorio dado el identificador proporcionado
     * 
     * @param id identificador del registro en base de datos
     * @return {@code true} si el registro fue eliminado, {@code false} en cualquier
     *         otro caso
     */
    public boolean eliminarRegistro(int id);

}
