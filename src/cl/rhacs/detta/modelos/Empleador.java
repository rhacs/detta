package cl.rhacs.detta.modelos;

import cl.rhacs.detta.modelos.enums.EPropiedad;
import cl.rhacs.detta.modelos.enums.ETipoEmpresa;

public class Empleador {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Identificador del {@link Empleador} */
    private int id;

    /** Nombre o razón social del {@link Empleador} */
    private String nombre;

    /** Rol Único Tributario del {@link Empleador} */
    private String rut;

    /** {@link Direccion} del {@link Empleador} */
    private String direccion;

    /** Teléfono de contacto del {@link Empleador} */
    private String telefono;

    /** Actividad Económica del {@link Empleador} */
    private String actividadEcononica;

    /** Cantidad trabajadores del {@link Empleador} */
    private int trabajadores;

    /** Si la empresa es pública o privada */
    private EPropiedad propiedad;

    /** Tipo de empresa del {@link Empleador} */
    private ETipoEmpresa tipoEmpresa;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link Empleador}
     */
    public Empleador() {

    }

    /**
     * Crea una nueva instancia del objeto {@link Empleador} dado los valores
     * 
     * @param id                 identificador
     * @param nombre             nombre o razón social
     * @param rut                rol único tributario
     * @param direccion          dirección
     * @param telefono           teléfono de contacto
     * @param actividadEcononica actividad económica
     * @param trabajadores       número total de trabajadores
     * @param propiedad          si es pública o privada
     * @param tipoEmpresa        tipo de empresa
     */
    public Empleador(int id, String nombre, String rut, String direccion, String telefono, String actividadEcononica,
            int trabajadores, EPropiedad propiedad, ETipoEmpresa tipoEmpresa) {
        this.id = id;
        this.nombre = nombre;
        this.rut = rut;
        this.direccion = direccion;
        this.telefono = telefono;
        this.actividadEcononica = actividadEcononica;
        this.trabajadores = trabajadores;
        this.propiedad = propiedad;
        this.tipoEmpresa = tipoEmpresa;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return el identificador numérico del {@link Empleador}
     */
    public int getId() {
        return id;
    }

    /**
     * @return el nombre o razón social del {@link Empleador}
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return el rol único tributario del {@link Empleador}
     */
    public String getRut() {
        return rut;
    }

    /**
     * @return la dirección del {@link Empleador}
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @return el teléfono de contacto del {@link Empleador}
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @return la actividad económica que realiza el {@link Empleador}
     */
    public String getActividadEcononica() {
        return actividadEcononica;
    }

    /**
     * @return la cantidad total de trabajadores del {@link Empleador}
     */
    public int getTrabajadores() {
        return trabajadores;
    }

    /**
     * @return si el {@link Empleador} es público o privado
     */
    public String getPropiedad() {
        return propiedad.getPropiedad();
    }

    /**
     * @return el tipo de empresa del {@link Empleador}
     */
    public String getTipoEmpresa() {
        return tipoEmpresa.getTipoEmpresa();
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
     * @param nombre el nombre o razón social a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param rut el rol único tributario a establecer
     */
    public void setRut(String rut) {
        this.rut = rut;
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
     * @param actividadEcononica el tipo de actividad económica a establecer
     */
    public void setActividadEcononica(String actividadEcononica) {
        this.actividadEcononica = actividadEcononica;
    }

    /**
     * @param trabajadores la cantidad total de trabajadores a establecer
     */
    public void setTrabajadores(int trabajadores) {
        this.trabajadores = trabajadores;
    }

    /**
     * @param propiedad el tipo de propiedad a establecer
     */
    public void setPropiedad(EPropiedad propiedad) {
        this.propiedad = propiedad;
    }

    /**
     * @param tipoEmpresa el tipo de empresa a establecer
     */
    public void setTipoEmpresa(ETipoEmpresa tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
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
        Empleador other = (Empleador) obj;

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
        return "Empleador [id=" + id + ", nombre=" + nombre + ", rut=" + rut + ", direccion=" + direccion
                + ", telefono=" + telefono + ", actividadEcononica=" + actividadEcononica + ", trabajadores="
                + trabajadores + ", propiedad=" + propiedad + ", tipoEmpresa=" + tipoEmpresa + "]";
    }

}
