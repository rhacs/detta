package cl.rhacs.detta.modelos;

import java.time.LocalDate;
import java.time.LocalTime;

import cl.rhacs.detta.modelos.enums.EClasificacion;
import cl.rhacs.detta.modelos.enums.EPrueba;
import cl.rhacs.detta.modelos.enums.ETipo;

public class Accidente {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Identificador del {@link Accidente} */
    private int id;

    /** Fecha en la cual ocurrió el {@link Accidente} */
    private LocalDate fecha;

    /** Hora en la cual ocurrió el {@link Accidente} */
    private LocalTime hora;

    /** Dirección en la que ocurrió el {@link Accidente} */
    private String direccion;

    /** Comuna en donde ocurrió el {@link Accidente} */
    private String comuna;

    /**
     * Qué estaba haciendo el trabajador al momento o justo antes del
     * {@link Accidente}
     */
    private String circunstancia;

    /** En qué parte de la localidad ocurrió el {@link Accidente} */
    private String lugar;

    /** Qué pasó o cómo ocurrió el {@link Accidente} */
    private String detalles;

    /** Clasificación del {@link Accidente} */
    private EClasificacion clasificacion;

    /** Tipo de {@link Accidente} */
    private ETipo tipo;

    /** Medio de Prueba para el {@link Accidente} */
    private EPrueba medioPrueba;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link Accidente}
     */
    public Accidente() {

    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return identificador numérico del {@link Accidente}
     */
    public int getId() {
        return id;
    }

    /**
     * @return fecha en la cual ocurrió el {@link Accidente}
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * @return hora en la cual ocurrió el {@link Accidente}
     */
    public LocalTime getHora() {
        return hora;
    }

    /**
     * @return dirección donde ocurrió el {@link Accidente}
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @return comuna en donde ocurrió el {@link Accidente}
     */
    public String getComuna() {
        return comuna;
    }

    /**
     * @return qué hacía el {@link Trabajador} en el momento que ocurrió el
     *         {@link Accidente}
     */
    public String getCircunstancia() {
        return circunstancia;
    }

    /**
     * @return lugar físico en donde ocurrió el {@link Accidente}
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * @return los detalles del {@link Accidente}
     */
    public String getDetalles() {
        return detalles;
    }

    /**
     * @return la clasificación del {@link Accidente}
     */
    public String getClasificacion() {
        return clasificacion.getClasificacion();
    }

    /**
     * @return el tipo de {@link Accidente}
     */
    public String getTipo() {
        return tipo.getTipo();
    }

    /**
     * @return el medio de prueba que corrobora el {@link Accidente}
     */
    public String getMedioPrueba() {
        return medioPrueba.getPrueba();
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param id el identificador numérico a establecer
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param fecha la fecha a establecer
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * @param hora la hora a establecer
     */
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    /**
     * @param direccion dirección a establecer
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @param comuna comuna a establecer
     */
    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    /**
     * @param circunstancia la circunstancia a establecer
     */
    public void setCircunstancia(String circunstancia) {
        this.circunstancia = circunstancia;
    }

    /**
     * @param lugar el lugar a establecer
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * @param detalles los detalles a establecer
     */
    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    /**
     * @param clasificacion la clasificación a establecer
     */
    public void setClasificacion(EClasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    /**
     * @param tipo el tipo a establecer
     */
    public void setTipo(ETipo tipo) {
        this.tipo = tipo;
    }

    /**
     * @param medioPrueba el medio de prueba a establecer
     */
    public void setMedioPrueba(EPrueba medioPrueba) {
        this.medioPrueba = medioPrueba;
    }

    // Herencias (Objeto)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + id;

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Accidente other = (Accidente) obj;

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        if (id != other.id)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Accidente [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", direccion=" + direccion + ", comuna="
                + comuna + ", circunstancia=" + circunstancia + ", lugar=" + lugar + ", detalles=" + detalles
                + ", clasificacion=" + clasificacion + ", tipo=" + tipo + ", medioPrueba=" + medioPrueba + "]";
    }

}
