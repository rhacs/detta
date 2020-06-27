package cl.rhacs.detta.modelos.enums;

public enum ETipoEmpresa {

    // Constantes
    // -----------------------------------------------------------------------------------------

    PRINCIPAL("Principal"), CONTRATISTA("Contratista"), SUBCONTRATISTA("Subcontratista"),
    TRANSITORIO("De servicios transitorios");

    // Attributos
    // -----------------------------------------------------------------------------------------

    private String tipoEmpresa;

    // Constructores
    // -----------------------------------------------------------------------------------------

    private ETipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return el tipo de empresa
     */
    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

}
