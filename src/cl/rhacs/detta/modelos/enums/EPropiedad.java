package cl.rhacs.detta.modelos.enums;

public enum EPropiedad {

    // Constantes
    // -----------------------------------------------------------------------------------------
    
    PUBLICA("Pública"), PRIVADA("Privada");
    
    // Atributos
    // -----------------------------------------------------------------------------------------
    
    private String propiedad;
    
    // Constructores
    // -----------------------------------------------------------------------------------------
    
    private EPropiedad(String propiedad) {
        this.propiedad = propiedad;
    }
    
    // Getters
    // -----------------------------------------------------------------------------------------
    
    /**
     * @return el nombre de la propiedad
     */
    public String getPropiedad() {
        return propiedad;
    }
    
}
