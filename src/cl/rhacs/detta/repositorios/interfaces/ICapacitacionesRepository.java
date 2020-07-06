package cl.rhacs.detta.repositorios.interfaces;

import java.util.List;

import cl.rhacs.detta.modelos.Capacitacion;

public interface ICapacitacionesRepository extends IRepository {

    /**
     * Busca en el repositorio los registros que coincidan con el identificador
     * numérico del {@link Profesional} proporcionado
     * 
     * @param profesionalId el identificador numérico del {@link Profesional} a
     *                      buscar
     * @return un objeto {@link List} con los resultados, {@code null} en cualquier
     *         otro caso
     */
    public List<Capacitacion> buscarPorProfesionalId(int profesionalId);

    /**
     * Busca en el repositorio los registros que coincidan con el identificador
     * numérico de la {@link Empresa} proporcionada
     * 
     * @param empresaId identificador numérico de la {@link Empresa}
     * @return un objeto {@link List} con los resultados, {@code null} en cualquier
     *         otro caso
     */
    public List<Capacitacion> buscarPorEmpresaId(int empresaId);

}
