package cl.rhacs.detta.repositorios.interfaces;

import java.util.List;

import cl.rhacs.detta.modelos.Accidente;

public interface IAccidentesRepository extends IRepository {

    /**
     * Busca en el repositorio todos los registros cuya relación con la
     * {@link Empresa} es igual al identificador proporcionado
     * 
     * @param empresaId identificador numérico de la empresa
     * @return un objeto {@link List} con los resultados, {@code null} en cualquier
     *         otro caso
     */
    public List<Accidente> buscarPorEmpresaId(int empresaId);

}
