package cl.rhacs.detta.repositorios.interfaces;

import java.util.List;

import cl.rhacs.detta.modelos.Profesional;

public interface IProfesionalesRepository {

    /**
     * Agrega un registro {@link Profesional} al repositorio
     * 
     * @param profesional un objeto {@link Profesional} con la información a agregar
     * @return {@code true} si el registro fue agregado, {@code false} en cualquier
     *         otro caso
     */
    public boolean agregarRegistro(Profesional profesional);

    /**
     * Recupera todos los registros almacenados en el repositorio
     * 
     * @return un objeto {@link List} con todos los registros, {@code null} en
     *         cualquier otro caso
     */
    public List<Profesional> buscarTodos();

    /**
     * Recupera un registro del repositorio en base al identificador proporcionado
     * 
     * @param id identificador del registro
     * @return un objeto {@link Profesional} con el registro obtenido, {@code null}
     *         en cualquier otro caso
     */
    public Profesional buscarPorId(int id);

    /**
     * Actualiza la información de un registro dada la información proporcionada
     * 
     * @param profesional objeto {@link Profesional} que contiene la información a
     *                    actualizar
     * @return {@code true} si el registro fue actualizado, {@code false} en
     *         cualquier otro caso
     */
    public boolean actualizarRegistro(Profesional profesional);

    /**
     * Elimina un registro del repositorio dado el identificador proporcionado
     * 
     * @param id identificador del registro en base de datos
     * @return {@code true} si el registro fue eliminado, {@code false} en cualquier
     *         otro caso
     */
    public boolean eliminarRegistro(int id);

    /**
     * Busca en el repositorio todos los registros que en su {@code campo} tengan un
     * valor igual o parecido a {@code valor}
     * 
     * @param campo campo sobre el cual realizar la búsqueda
     * @param valor valor a buscar
     * @return un objeto {@link List} con los resultados, {@code null} en cualquier
     *         otro caso
     */
    public List<Profesional> buscarPor(String campo, String valor);

}
