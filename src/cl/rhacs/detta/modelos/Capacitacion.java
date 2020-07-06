package cl.rhacs.detta.modelos;

import java.time.LocalDate;

public class Capacitacion {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Identificador numérico de la {@link Capacitacion} en base de datos */
    private int id;

    /** Fecha en la que se realizó o realizará la {@link Capacitacion} */
    private LocalDate fecha;

    /** Dirección en donde se ralizó/realizará la {@link Capacitacion} */
    private String direccion;

    /** Tema de la {@link Capacitacion} */
    private String tema;

    /**
     * Cantidad de personas que participaron/participarán de la {@link Capacitacion}
     */
    private int participantes;

    /** Se realizó la {@link Capacitacion} ? */
    private boolean realizada;

    /**
     * Identificador numérico del {@link Profesional} a cargo de la
     * {@link Capacitacion}
     */
    private int profesionalId;

    /**
     * Identificador numérico de la {@link Empresa} que recibió/recibirá la
     * {@link Capacitacion}
     */
    private int empresaId;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link Capacitacion}
     */
    public Capacitacion() {

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
     * @return la fecha
     */
    public final LocalDate getFecha() {
        return fecha;
    }

    /**
     * @return la direccion
     */
    public final String getDireccion() {
        return direccion;
    }

    /**
     * @return el tema
     */
    public final String getTema() {
        return tema;
    }

    /**
     * @return la cantidad de participantes
     */
    public final int getParticipantes() {
        return participantes;
    }

    /**
     * @return {@code true} si se realizó, de lo contrario {@code false}
     */
    public final boolean isRealizada() {
        return realizada;
    }

    /**
     * @return el identificador numérico del {@link Profesional}
     */
    public final int getProfesionalId() {
        return profesionalId;
    }

    /**
     * @return el identificador numérico de la {@link Empresa}
     */
    public final int getEmpresaId() {
        return empresaId;
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
     * @param direccion la dirección a establecer
     */
    public final void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @param tema el tema a establecer
     */
    public final void setTema(String tema) {
        this.tema = tema;
    }

    /**
     * @param participantes la cantidad de participantes a establecer
     */
    public final void setParticipantes(int participantes) {
        this.participantes = participantes;
    }

    /**
     * @param realizada el estado de la {@link Capacitacion} a establecer
     */
    public final void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    /**
     * @param profesionalId el identificador numérico del {@link Profesional} a
     *                      establecer
     */
    public final void setProfesionalId(int profesionalId) {
        this.profesionalId = profesionalId;
    }

    /**
     * @param empresaId el identificador numérico de la {@link Empresa} a establecer
     */
    public final void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
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

        Capacitacion other = (Capacitacion) obj;

        if (id != other.id)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Capacitacion [id=" + id + ", fecha=" + fecha + ", direccion=" + direccion + ", tema=" + tema
                + ", participantes=" + participantes + ", realizada=" + realizada + ", profesionalId=" + profesionalId
                + ", empresaId=" + empresaId + "]";
    }

}
