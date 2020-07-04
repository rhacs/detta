package cl.rhacs.detta.repositorios.interfaces;

import cl.rhacs.detta.modelos.Profesional;

public interface IProfesionalesRepository extends IRepository {

    /**
     * Busca un registro en el repositorio
     * 
     * @param email correo electrónico a buscar
     * @return un objeto {@link Profesional} con el resultado, {@code null} en
     *         cualquier otro caso
     */
    public Profesional buscarPorEmail(String email);

}
