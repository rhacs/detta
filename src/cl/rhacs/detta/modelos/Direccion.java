package cl.rhacs.detta.modelos;

public class Direccion {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Identificador numérico de la {@link Dirección} */
    private int id;

    /** Nombre de la calle */
    private String calle;

    /** Número de la locación */
    private int numero;

    /** Número del departamento, si aplica */
    private int departamento;

    /** Ciudad */
    private String ciudad;

    /** Comuna */
    private String comuna;

    /** Región */
    private String region;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link Direccion}
     */
    public Direccion() {

    }

    /**
     * Crea una nueva instancia del objeto {@link Direccion} dados los valores
     * 
     * @param id     identificador numérico de la {@link Direccion}
     * @param calle  nombre de la calle
     * @param numero número
     * @param comuna comuna
     * @param region región
     */
    public Direccion(int id, String calle, int numero, String comuna, String region) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.comuna = comuna;
        this.region = region;
    }

    /**
     * Crea una nueva instancia del objeto {@link Direccion} dados los valores
     * 
     * @param id           identificador numérico de la {@link Direccion}
     * @param calle        nombre de la calle
     * @param numero       número
     * @param departamento número de departamento
     * @param ciudad       ciudad
     * @param comuna       comuna
     * @param region       región
     */
    public Direccion(int id, String calle, int numero, int departamento, String ciudad, String comuna, String region) {
        this(id, calle, numero, comuna, region);
        this.departamento = departamento;
        this.ciudad = ciudad;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return el identificador de la {@link Direccion}
     */
    public int getId() {
        return id;
    }

    /**
     * @return la calle de la {@link Direccion}
     */
    public String getCalle() {
        return calle;
    }

    /**
     * @return el número de la {@link Direccion}
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @return el número del departamento de la {@link Direccion}
     */
    public int getDepartamento() {
        return departamento;
    }

    /**
     * @return la ciudad de la {@link Direccion}
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @return la comuna de la {@link Direccion}
     */
    public String getComuna() {
        return comuna;
    }

    /**
     * @return la región de la {@link Direccion}
     */
    public String getRegion() {
        return region;
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
     * @param calle la calle a establecer
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * @param numero el número a establecer
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @param departamento el número del departamento a establecer
     */
    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    /**
     * @param ciudad la ciudad a establecer
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @param comuna la comuna a establecer
     */
    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    /**
     * @param region la región a establecer
     */
    public void setRegion(String region) {
        this.region = region;
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
        Direccion other = (Direccion) obj;

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
        return "Direccion [id=" + id + ", calle=" + calle + ", numero=" + numero + ", departamento=" + departamento
                + ", ciudad=" + ciudad + ", comuna=" + comuna + ", region=" + region + "]";
    }

}
