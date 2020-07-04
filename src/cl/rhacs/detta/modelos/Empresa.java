package cl.rhacs.detta.modelos;

import java.time.LocalDateTime;

public class Empresa {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Identificador numérico de la {@link Empresa} */
    private int id;

    /** Nombre o Razón Social de la {@link Empresa} */
    private String nombre;

    /** Rol Único Tributario de la {@link Empresa} */
    private String rut;

    /** Dirección completa de la {@link Empresa} */
    private String direccion;

    /** Teléfono de contacto de la {@link Empresa} */
    private String telefono;

    /** Correo electrónico de la {@link Empresa} */
    private String email;

    /** Giro o Actividad Económica de la {@link Empresa} */
    private String giro;

    /** Número total de trabajadores de la {@link Empresa} */
    private int trabajadores;

    /**
     * Tipo de {@link Empresa} (Principal, Contratista, Subcontratista, De Servicios
     * Transitorios)
     */
    private String tipo;

    /** Contraseña de Acceso de la {@link Empresa} */
    private String password;

    /**
     * Identificador numérico del {@link Profesional} que está a cargo de la
     * {@link Empresa}
     */
    private int profesionalId;

    /** Fecha en la que la {@link Empresa} se registró en el sistema */
    private LocalDateTime fechaRegistro;

    /**
     * Última fecha en la que la información de la {@link Empresa} fue actualizada
     */
    private LocalDateTime fechaActualizacion;

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
     * @return el nombre o razón social
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
     * @return el correo electrónico
     */
    public final String getEmail() {
        return email;
    }

    /**
     * @return el giro o actividad económica
     */
    public final String getGiro() {
        return giro;
    }

    /**
     * @return la cantidad total de trabajadores
     */
    public final int getTrabajadores() {
        return trabajadores;
    }

    /**
     * @return el tipo de la {@link Empresa}
     * @see Empresa#tipo
     */
    public final String getTipo() {
        return tipo;
    }

    /**
     * @return la contraseña de acceso
     */
    public final String getPassword() {
        return password;
    }

    /**
     * @return el identificador numérico del {@link Profesional} a cargo
     */
    public final int getProfesionalId() {
        return profesionalId;
    }

    /**
     * @return la fecha en que se registró
     */
    public final LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @return la última fecha en la que la información se actualizó
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
     * @param nombre el nombre o razón social a establecer
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
     * @param direccion la dirección a establecer
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
     * @param email el correo electrónico a establecer
     */
    public final void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param giro el giro o actividad económica a establecer
     */
    public final void setGiro(String giro) {
        this.giro = giro;
    }

    /**
     * @param trabajadores la cantidad total de trabajadores a establecer
     */
    public final void setTrabajadores(int trabajadores) {
        this.trabajadores = trabajadores;
    }

    /**
     * @param tipo el tipo a establecer
     * @see Empresa#tipo
     */
    public final void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @param password la contraseña de acceso a establecer
     */
    public final void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param profesionalId el identificador numérico del {@link Profesional} a
     *                      establecer
     */
    public final void setProfesionalId(int profesionalId) {
        this.profesionalId = profesionalId;
    }

    /**
     * @param fechaRegistro la fecha de registro a establecer
     */
    public final void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @param fechaActualizacion la última fecha de edición a establecer
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

        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + id;
        result = prime * result + ((rut == null) ? 0 : rut.hashCode());

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

        Empresa other = (Empresa) obj;

        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
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
                + telefono + ", email=" + email + ", giro=" + giro + ", trabajadores=" + trabajadores + ", tipo=" + tipo
                + ", profesionalId=" + profesionalId + ", fechaRegistro=" + fechaRegistro + ", fechaActualizacion="
                + fechaActualizacion + "]";
    }

}
