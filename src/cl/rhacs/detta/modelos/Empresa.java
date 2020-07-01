package cl.rhacs.detta.modelos;

import java.time.LocalDateTime;

public class Empresa {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Identificador numérico de la {@link Empresa} en la base de datos */
    private int id;

    /** Nombre o razón social de la {@link Empresa} */
    private String nombre;

    /** Rol Único Tributario de la {@link Empresa} */
    private String rut;

    /** Dirección completa de la {@link Empresa} */
    private String direccion;

    /** Teléfono de contacto de la {@link Empresa} */
    private String telefono;

    /** Actividad económica principal de la {@link Empresa} */
    private String giro;

    /** Cantidad de trabajadores de la {@link Empresa} */
    private int trabajadores;

    /**
     * Tipo de {@link Empresa} (Principal, Contratista, Subcontratista, De Servicios
     * Transitorios)
     */
    private String tipo;

    /** Fecha en la cual la {@link Empresa} fue registrada en el sistema */
    private LocalDateTime registro;

    /**
     * Fecha en la cual la información de la {@link Empresa} fue actualizada por
     * última vez
     */
    private LocalDateTime actualizacion;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link Empresa}
     */
    public Empresa() {

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
     * @return la razón social
     */
    public final String getNombre() {
        return nombre;
    }

    /**
     * @return el rol único tributario
     */
    public final String getRut() {
        return rut;
    }

    /**
     * @return la dirección completa
     */
    public final String getDireccion() {
        return direccion;
    }

    /**
     * @return el teléfono de contacto
     */
    public final String getTelefono() {
        return telefono;
    }

    /**
     * @return la actividad económica
     */
    public final String getGiro() {
        return giro;
    }

    /**
     * @return la cantidad de trabajadores
     */
    public final int getTrabajadores() {
        return trabajadores;
    }

    /**
     * @return el tipo de empresa
     */
    public final String getTipo() {
        return tipo;
    }

    /**
     * @return la fecha en la cual la {@link Empresa} fue registrada
     */
    public final LocalDateTime getRegistro() {
        return registro;
    }

    /**
     * @return la fecha en la cual la información de la {@link Empresa} fue
     *         modificada por última vez
     */
    public final LocalDateTime getActualizacion() {
        return actualizacion;
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
     * @param nombre la razón social a establecer
     */
    public final void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param rut el rol único tributario a establecer
     */
    public final void setRut(String rut) {
        this.rut = rut;
    }

    /**
     * @param direccion la dirección completa a establecer
     */
    public final void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @param telefono el teléfono de contacto a establecer
     */
    public final void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @param giro la actividad económica a establecer
     */
    public final void setGiro(String giro) {
        this.giro = giro;
    }

    /**
     * @param trabajadores la cantidad de trabajadores a establecer
     */
    public final void setTrabajadores(int trabajadores) {
        this.trabajadores = trabajadores;
    }

    /**
     * @param tipo el tipo de {@link Empresa} a establecer
     * @see Empresa#tipo
     */
    public final void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @param registro la fecha en la que la {@link Empresa} fue agregada a la base
     *                 de datos
     */
    public final void setRegistro(LocalDateTime registro) {
        this.registro = registro;
    }

    /**
     * @param actualizacion la fecha en la que la información de la {@link Empresa}
     *                      fue modificada por última vez
     */
    public final void setActualizacion(LocalDateTime actualizacion) {
        this.actualizacion = actualizacion;
    }

    // Herencias (Objeto)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + id;
        result = prime * result + ((rut == null) ? 0 : rut.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Empresa other = (Empresa) obj;

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        if (id != other.id)
            return false;

        if (rut == null) {
            if (other.rut != null)
                return false;
        } else if (!rut.equals(other.rut))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Empresa [id=" + id + ", nombre=" + nombre + ", rut=" + rut + ", direccion=" + direccion + ", telefono="
                + telefono + ", giro=" + giro + ", trabajadores=" + trabajadores + ", tipo=" + tipo + ", registro="
                + registro + ", actualizacion=" + actualizacion + "]";
    }

}
