package cl.rhacs.detta.modelos.enums;

public enum ETipoContrato {

    // Constantes
    // -----------------------------------------------------------------------------------------
    
    INDEFINIDO("Indefinido"), FIJO("Plazo Fijo"), OBRA("Por Obra o Faena"), TEMPORADA("Temporada");
    
    // Atributos
    // -----------------------------------------------------------------------------------------
    
    private String tipo;
    
    // Constructores
    // -----------------------------------------------------------------------------------------
    
    private ETipoContrato(String tipo) {
        this.tipo = tipo;
    }
    
    // Getters
    // -----------------------------------------------------------------------------------------
    
    /**
     * @return tipo de contrato
     */
    public String getTipo() {
        return tipo;
    }
    
}
