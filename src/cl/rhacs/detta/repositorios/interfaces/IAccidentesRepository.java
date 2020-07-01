package cl.rhacs.detta.repositorios.interfaces;

import java.time.LocalDate;
import java.util.List;

import cl.rhacs.detta.modelos.Accidente;

public interface IAccidentesRepository extends IRepository {

    /**
     * Busca en el repositorio todos los registros cuya fecha sea igual a la
     * proporcionada
     * 
     * @param fecha fecha a comparar
     * @return un objeto {@link List} con los resultados obtenidos, {@code null} en
     *         cualquier otro caso
     */
    public List<Accidente> buscarPorFecha(LocalDate fecha);

    /**
     * Busca en el repositorio todos los registros cuyo {@code campo} es igual al
     * {@code valor} proporcionado
     * 
     * @param campo campo a comparar
     * @param valor valor a buscar
     * @return un objeto {@link List} con los resultados obtenidos, {@code null} en
     *         cualquier otro caso
     */
    public List<Accidente> buscarPor(String campo, String valor);

}
