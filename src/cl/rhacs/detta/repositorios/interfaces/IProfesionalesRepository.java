package cl.rhacs.detta.repositorios.interfaces;

import java.util.List;

import cl.rhacs.detta.modelos.Profesional;

public interface IProfesionalesRepository extends IRepository {

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
