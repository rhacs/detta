package cl.rhacs.detta.modelos;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private Direccion direccion;

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

    /** Información del {@link Empleador} */
    private Empleador empleador;

    /** Información del {@link Trabajador} */
    private Trabajador trabajador;

    /** Fecha en la cual el {@link Accidente} fue agregado a la base de datos */
    private LocalDateTime creado;

    /** Última fecha en la cual el {@link Accidente} fue actualizado */
    private LocalDateTime actualizado;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia del objeto {@link Accidente} con los valores de
     * fecha de creación y fecha de actualización inicializados
     */
    public Accidente() {
        creado = LocalDateTime.now();
        actualizado = creado;
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
     * @return información de la {@link Direccion} donde ocurrió el
     *         {@link Accidente}
     */
    public Direccion getDireccion() {
        return direccion;
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

    /**
     * @return información sobre el {@link Empleador}
     */
    public Empleador getEmpleador() {
        return empleador;
    }

    /**
     * @return información sobre el {@link Trabajador} al que le ocurrió el
     *         {@link Accidente}
     */
    public Trabajador getTrabajador() {
        return trabajador;
    }

    /**
     * @return tiempo exacto en el que el reporte del {@link Accidente} fue creado
     */
    public LocalDateTime getCreado() {
        return creado;
    }

    /**
     * @return tiempo exacto en el que el reporte del {@link Accidente} fue
     *         modificado por última vez
     */
    public LocalDateTime getActualizado() {
        return actualizado;
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
     * @param direccion la {@link Direccion} a establecer
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
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

    /**
     * @param empleador el {@link Empleador} a establecer
     */
    public void setEmpleador(Empleador empleador) {
        this.empleador = empleador;
    }

    /**
     * @param trabajador el {@link Trabajador} a establecer
     */
    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
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
        return "Accidente [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", direccion=" + direccion
                + ", circunstancia=" + circunstancia + ", lugar=" + lugar + ", detalles=" + detalles
                + ", clasificacion=" + clasificacion + ", tipo=" + tipo + ", medioPrueba=" + medioPrueba
                + ", empleador=" + empleador + ", trabajador=" + trabajador + ", creado=" + creado + ", actualizado="
                + actualizado + "]";
    }

}
