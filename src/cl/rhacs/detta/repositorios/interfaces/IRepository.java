package cl.rhacs.detta.repositorios.interfaces;

import java.util.List;

public interface IRepository {

    /**
     * Inserta un nuevo {@link Object} al repositorio
     * 
     * @param object un objeto {@link Object} con la información a insertar
     * 
     * @return {@code true} si el objeto fue insertado, {@code false} en cualquier
     *         otro caso
     */
    public boolean insertarRegistro(Object object);

    /**
     * Recupera todos los objetos del repositorio
     * 
     * @return una {@link List} con los objetos almacenados en el repositorio
     */
    public List<Object> buscarTodos();

    /**
     * Busca en el repositorio el objeto con el identificador numérico proporcionado
     * 
     * @param id el identificador numérico a buscar
     * 
     * @return un objeto {@link Object} con la información, {@code null} si no se
     *         encuentra
     */
    public Object buscarPorID(int id);

    /**
     * Actualiza la información de un {@link Object} en el repositorio
     * 
     * @param object un objeto {@link Object} con la nueva información a actualizar
     * 
     * @return {@code true} si el objeto fue actualizado, {@code false} en cualquier
     *         otro caso
     */
    public boolean actualizarRegistro(Object object);

    /**
     * Elimina un objeto {@link Object} del repositorio
     * 
     * @param id identificador del registro a eliminar
     * 
     * @return {@code true} si el objeto fue eliminado, {@code false} en cualquier
     *         otro caso
     */
    public boolean eliminarRegistro(int id);

}
