package cl.rhacs.detta.modelos;

public class Profesional {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Identificador numérico del {@link Profesional} */
    private int id;

    /** Nombre del {@link Profesional} */
    private String nombre;

    /** Dirección de correo electrónico del {@link Profesional} */
    private String email;

    /** Dirección completa del {@link Profesional} */
    private String direccion;

    /** Teléfono de contacto del {@link Profesional} */
    private String telefono;

    /** Estado del contrato del {@link Profesional} (activo, terminado) */
    private String estadoContrato;

    /** Contraseña de acceso del {@link Profesional} */
    private String password;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link Profesional}
     */
    public Profesional() {

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
     * @return el nombre completo
     */
    public final String getNombre() {
        return nombre;
    }

    /**
     * @return la dirección de correo electrónico
     */
    public final String getEmail() {
        return email;
    }

    /**
     * @return la dirección
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
     * @return el estado del contrato
     * @see Profesional#estadoContrato
     */
    public final String getEstadoContrato() {
        return estadoContrato;
    }

    /**
     * @return la contraseña de acceso al sistema
     */
    public final String getPassword() {
        return password;
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
     * @param nombre el nombre a establecer
     */
    public final void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param email la dirección de correo electrónico a establecer
     */
    public final void setEmail(String email) {
        this.email = email;
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
     * @param estadoContrato el estado del contrato a establecer
     * @see Profesional#estadoContrato
     */
    public final void setEstadoContrato(String estadoContrato) {
        this.estadoContrato = estadoContrato;
    }

    /**
     * @param password la contraseña de acceso al sistema a establecer
     */
    public final void setPassword(String password) {
        this.password = password;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + id;

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Profesional other = (Profesional) obj;

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;

        if (id != other.id)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Profesional [id=" + id + ", nombre=" + nombre + ", email=" + email + ", direccion=" + direccion
                + ", telefono=" + telefono + ", estadoContrato=" + estadoContrato + "]";
    }

}
