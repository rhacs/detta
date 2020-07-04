package cl.rhacs.detta.repositorios.interfaces;

import java.util.List;

import cl.rhacs.detta.modelos.Profesional;

public interface IProfesionalesRepository {

    /**
     * Agrega un nuevo registro al repositorio
     * 
     * @param profesional objeto {@link Profesional} con la información a agregar
     * @return {@code true} si el registro fue agregado, {@code false} en cualquier
     *         otro caso
     */
    public boolean agregarRegistro(Profesional profesional);

    /**
     * Busca todos los registros almacenados en el repositorio de
     * {@link Profesional}es
     * 
     * @return un objeto {@link List} con los resultados obtenidos, {@code null} en
     *         cualquier otro caso
     */
    public List<Profesional> buscarTodos();

    /**
     * Busca un registro en el repositorio
     * 
     * @param id identificador a buscar
     * @return un objeto {@link Profesional} con el resultado, {@code null} en
     *         cualquier otro caso
     */
    public Profesional buscarPorId(int id);

    /**
     * Busca un registro en el repositorio
     * 
     * @param email correo electrónico a buscar
     * @return un objeto {@link Profesional} con el resultado, {@code null} en
     *         cualquier otro caso
     */
    public Profesional buscarPorEmail(String email);

    /**
     * Actualiza la información de un registro en el repositorio
     * 
     * @param profesional objeto {@link Profesional} con la información a actualizar
     * @return {@code true} si el registro fue actualizado, {@code false} en
     *         cualquier otro caso
     */
    public boolean actualizarRegistro(Profesional profesional);

    /**
     * Elimina un registro del repositorio
     * 
     * @param id identificador del registro
     * @return {@code true} si el registro fue eliminado, {@code false} en cualquier
     *         otro caso
     */
    public boolean eliminarRegistro(int id);

}
