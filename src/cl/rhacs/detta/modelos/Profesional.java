package cl.rhacs.detta.modelos;

public class Profesional {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Identificador numérico del {@link Profesional} */
    private int id;

    /** Nombre completo del {@link Profesional} */
    private String nombre;

    /** Correo electrónico del {@link Profesional} */
    private String email;

    /** Teléfono de contacto del {@link Profesional} */
    private String telefono;

    /** Estado del contrato del {@link Profesional} (Vigente, Terminado) */
    private String estadoContrato;

    /** Contraseña de acceso al sistema del {@link Profesional} */
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
     * @return el correo electrónico
     */
    public final String getEmail() {
        return email;
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
     * @param nombre el nombre completo a establecer
     */
    public final void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param email el correo electrónico a establecer
     */
    public final void setEmail(String email) {
        this.email = email;
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

    /**
     * @return un código hash para este objeto
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + id;

        return result;
    }

    /**
     * Indica si otro objeto {@code obj} es "igual a" éste
     * 
     * @param obj objeto con el cual hacer la comparación
     * @return {@code true} si los dos objetos son iguales, {@code false} en
     *         cualquier otro caso
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Profesional other = (Profesional) obj;

        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;

        if (id != other.id)
            return false;

        return true;
    }

    /**
     * @return una representación textual del objeto
     */
    @Override
    public String toString() {
        return "Profesional [id=" + id + ", nombre=" + nombre + ", email=" + email + ", telefono=" + telefono
                + ", estadoContrato=" + estadoContrato + "]";
    }

}
