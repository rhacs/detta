package cl.rhacs.detta.modelos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Accidente {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Identificador numérico del {@link Accidente} en base de datos */
    private int id;

    /** Fecha en que ocurrió el {@link Accidente} */
    private LocalDate fecha;

    /** Hora en que ocurrió el {@link Accidente} */
    private LocalTime hora;

    /** Dirección del {@link Accidente} */
    private String direccion;

    /** Lugar del {@link Accidente} (nombre de la sección, edificio, área, etc.) */
    private String lugar;

    /** Qué estaba haciendo el trabajador al momento del {@link Accidente} */
    private String circunstancia;

    /** Qué pasó o cómo ocurrió el {@link Accidente} */
    private String detalles;

    /** Clasificación del {@link Accidente} (Leve, Grave, Fatal, Otro) */
    private String clasificacion;

    /** Tipo de {@link Accidente} (Trabajo, Trayecto) */
    private String tipo;

    /**
     * Medio de prueba que acredita el {@link Accidente} (Parte de Carabineros,
     * Declaración, Testigos, Otro)
     */
    private String medioPrueba;

    /**
     * Identificador numérico de la {@link Empresa} a la que pertenece el trabajador
     * accidentado
     */
    private int empresaId;

    /** Fecha en que se registró el {@link Accidente} en el sistema */
    private LocalDateTime fechaRegistro;

    /** Última fecha en que se modificó la información del {@link Accidente} */
    private LocalDateTime fechaActualizacion;

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
     * @return el identificador numérico
     */
    public final int getId() {
        return id;
    }

    /**
     * @return la fecha en que ocurrió
     */
    public final LocalDate getFecha() {
        return fecha;
    }

    /**
     * @return la hora en que ocurrió
     */
    public final LocalTime getHora() {
        return hora;
    }

    /**
     * @return la dirección donde ocurrió
     */
    public final String getDireccion() {
        return direccion;
    }

    /**
     * @return el lugar donde ocurrió
     * @see Accidente#lugar
     */
    public final String getLugar() {
        return lugar;
    }

    /**
     * @return la circunstancia
     * @see Accidente#circunstancia
     */
    public final String getCircunstancia() {
        return circunstancia;
    }

    /**
     * @return los detalles
     */
    public final String getDetalles() {
        return detalles;
    }

    /**
     * @return la clasificación
     * @see Accidente#clasificacion
     */
    public final String getClasificacion() {
        return clasificacion;
    }

    /**
     * @return el tipo
     * @see Accidente#tipo
     */
    public final String getTipo() {
        return tipo;
    }

    /**
     * @return el medio de prueba
     * @see Accidente#medioPrueba
     */
    public final String getMedioPrueba() {
        return medioPrueba;
    }

    /**
     * @return el identificador de la {@link Empresa}
     */
    public final int getEmpresaId() {
        return empresaId;
    }

    /**
     * @return la fecha de registro en el sistema
     */
    public final LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @return la última fecha de modificación
     */
    public final LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param id el identificador numérico a establecer
     */
    public final void setId(int id) {
        this.id = id;
    }

    /**
     * @param fecha la fecha a establecer
     */
    public final void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * @param hora la hora a establecer
     */
    public final void setHora(LocalTime hora) {
        this.hora = hora;
    }

    /**
     * @param direccion la dirección a establecer
     */
    public final void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @param lugar el lugar a establecer
     * @see Accidente#lugar
     */
    public final void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * @param circunstancia la circunstancia a establecer
     * @see Accidente#circunstancia
     */
    public final void setCircunstancia(String circunstancia) {
        this.circunstancia = circunstancia;
    }

    /**
     * @param detalles los detalles a establecer
     */
    public final void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    /**
     * @param clasificacion la clasificación a establecer
     * @see Accidente#clasificacion
     */
    public final void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    /**
     * @param tipo el tipo a establecer
     * @see Accidente#tipo
     */
    public final void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @param medioPrueba el medio de prueba a establecer
     * @see Accidente#medioPrueba
     */
    public final void setMedioPrueba(String medioPrueba) {
        this.medioPrueba = medioPrueba;
    }

    /**
     * @param empresaId el identificador de la {@link Empresa} a establecer
     */
    public final void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }

    /**
     * @param fechaRegistro la fecha de registro en el sistema a establecer
     */
    public final void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @param fechaActualizacion la última fecha de modificación a establecer
     */
    public final void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    // Herencias (Object)
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
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Accidente other = (Accidente) obj;

        if (id != other.id)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Accidente [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", direccion=" + direccion + ", lugar="
                + lugar + ", circunstancia=" + circunstancia + ", detalles=" + detalles + ", clasificacion="
                + clasificacion + ", tipo=" + tipo + ", medioPrueba=" + medioPrueba + ", fechaRegistro=" + fechaRegistro
                + ", fechaActualizacion=" + fechaActualizacion + "]";
    }

}
