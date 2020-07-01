package cl.rhacs.detta.repositorios.interfaces;

import java.util.List;

import cl.rhacs.detta.modelos.Profesional;

public interface IProfesionalesRepository extends IRepository {

    /**
     * Busca en el repositorio el o los {@link Profesional}es con el nombre
     * proporcionado
     * 
     * @param nombre el nombre a buscar
     * @return un objeto {@link List} con los resultados obtenidos, {@code null} en
     *         cualquier otro caso
     */
    public List<Profesional> buscarPorNombre(String nombre);

    /**
     * Busca en el repositorio todos aquellos registros que tienen el estado del
     * contrato especificado
     * 
     * @param estadoContrato el estado del contrato a buscar
     * @return un objeto {@link List} con los resultados obtenidos, {@code null} en
     *         cualquier otro caso
     * @see Profesional#getEstadoContrato()
     */
    public List<Profesional> buscarPorEstadoContrato(String estadoContrato);

}
