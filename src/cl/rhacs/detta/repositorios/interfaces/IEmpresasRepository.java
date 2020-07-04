package cl.rhacs.detta.repositorios.interfaces;

import java.util.List;

import cl.rhacs.detta.modelos.Empresa;

public interface IEmpresasRepository extends IRepository {

    /**
     * Busca en el repositorio por el registro con el rut especificado
     * 
     * @param rut rut a buscar
     * @return un objeto {@link Empresa} con el resultado, {@code null} en cualquier
     *         otro caso
     */
    public Empresa buscarPorRut(String rut);

    /**
     * Busca un registro en el repositorio dado un correo electrónico
     * 
     * @param email correo electrónico a buscar
     * @return un objeto {@link Empresa} con los resultados, {@code null} en
     *         cualquier otro caso
     */
    public Empresa buscarPorEmail(String email);

    /**
     * Busca en el repositorio los registros que se encuentran bajo la supervisión
     * del {@link Profesional} indicado
     * 
     * @param id identificador del {@link Profesional}
     * @return un objeto {@link List} con los resultados obtenidos, {@code null} en
     *         cualquier otro caso
     */
    public List<Empresa> buscarPorProfesionalId(int profesionalId);

}
