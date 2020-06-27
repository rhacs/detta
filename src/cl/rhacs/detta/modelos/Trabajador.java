package cl.rhacs.detta.modelos;

import java.time.LocalDate;

import cl.rhacs.detta.modelos.enums.EGenero;
import cl.rhacs.detta.modelos.enums.ETipoContrato;

public class Trabajador {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Identificador del {@link Trabajador} */
    private int id;

    /** Nombres del {@link Trabajador} */
    private String nombres;

    /** Apellido paterno del {@link Trabajador} */
    private String apellidoPaterno;

    /** Apellido materno del {@link Trabajador} */
    private String apellidoMaterno;

    /** Dirección del {@link Trabajador} */
    private String direccion;

    /** Teléfono de contacto del {@link Trabajador} */
    private String telefono;

    /** Rol único nacional del {@link Trabajador} */
    private String run;

    /** Género del {@link Trabajador} */
    private EGenero genero;

    /** Fecha de Nacimiento del {@link Trabajador} */
    private LocalDate fechaNacimiento;

    /** Nacionalidad del {@link Trabajador} */
    private String nacionalidad;

    /** Profesión u oficio del {@link Trabajador} */
    private String profesion;

    /** Tipo de Contrato del {@link Trabajador} */
    private ETipoContrato tipoContrato;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link Trabajador}
     */
    public Trabajador() {

    }

    /**
     * Crea una nueva instancia del objeto {@link Trabajador}
     * 
     * @param id              identificador numérico
     * @param nombres         nombres
     * @param apellidoPaterno apellido paterno
     * @param apellidoMaterno apellido materno
     * @param direccion       dirección
     * @param telefono        teléfono de contacto
     * @param run             rol único nacional
     * @param genero          género
     * @param fechaNacimiento fecha de nacimiento
     * @param nacionalidad    nacionalidad
     * @param profesion       profesión
     * @param tipoContrato    tipo de contrato
     */
    public Trabajador(int id, String nombres, String apellidoPaterno, String apellidoMaterno, String direccion,
            String telefono, String run, EGenero genero, LocalDate fechaNacimiento, String nacionalidad,
            String profesion, ETipoContrato tipoContrato) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.direccion = direccion;
        this.telefono = telefono;
        this.run = run;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.profesion = profesion;
        this.tipoContrato = tipoContrato;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return el identificador numérico del {@link Trabajador}
     */
    public int getId() {
        return id;
    }

    /**
     * @return los nombres del {@link Trabajador}
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @return el apellido paterno del {@link Trabajador}
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * @return el apellido materno del {@link Trabajador}
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * @return la dirección del {@link Trabajador}
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @return el teléfono de contacto del {@link Trabajador}
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @return el rol único nacional del {@link Trabajador}
     */
    public String getRun() {
        return run;
    }

    /**
     * @return el género del {@link Trabajador}
     */
    public String getGenero() {
        return genero.getGenero();
    }

    /**
     * @return la fecha de nacimiento del {@link Trabajador}
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @return la nacionalidad del {@link Trabajador}
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * @return la profesión u oficio del {@link Trabajador}
     */
    public String getProfesion() {
        return profesion;
    }

    /**
     * @return el tipo de contrato del {@link Trabajador}
     */
    public String getTipoContrato() {
        return tipoContrato.getTipo();
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
     * @param nombres los nombres a establecer
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @param apellidoPaterno el apellido paterno a establecer
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * @param apellidoMaterno el apellido materno a establecer
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * @param direccion la dirección a establecer
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @param telefono el teléfono de contacto a establecer
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @param run el rol único nacional a establecer
     */
    public void setRun(String run) {
        this.run = run;
    }

    /**
     * @param genero el género a establecer
     */
    public void setGenero(EGenero genero) {
        this.genero = genero;
    }

    /**
     * @param fechaNacimiento la fecha de nacimiento a establecer
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @param nacionalidad la nacionalidad a establecer
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * @param profesion la profesión u oficio a establecer
     */
    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    /**
     * @param tipoContrato el tipo de contrato a establecer
     */
    public void setTipoContrato(ETipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    // Herencias (Objeto)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + id;
        result = prime * result + ((run == null) ? 0 : run.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Trabajador other = (Trabajador) obj;

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        if (id != other.id)
            return false;

        if (run == null) {
            if (other.run != null)
                return false;
        } else if (!run.equals(other.run))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Trabajador [id=" + id + ", nombres=" + nombres + ", apellidoPaterno=" + apellidoPaterno
                + ", apellidoMaterno=" + apellidoMaterno + ", direccion=" + direccion + ", telefono=" + telefono
                + ", run=" + run + ", genero=" + genero + ", fechaNacimiento=" + fechaNacimiento + ", nacionalidad="
                + nacionalidad + ", profesion=" + profesion + ", tipoContrato=" + tipoContrato + "]";
    }

}
