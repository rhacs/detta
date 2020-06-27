package cl.rhacs.detta.modelos.enums;

public enum EGenero {

    // Constantes
    // -----------------------------------------------------------------------------------------

    FEMENINO("Femenino"), MASCULINO("Masculino");

    // Attributos
    // -----------------------------------------------------------------------------------------

    /** Género */
    private String genero;

    // Constructores
    // -----------------------------------------------------------------------------------------

    private EGenero(String genero) {
        this.genero = genero;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return el género
     */
    public String getGenero() {
        return genero;
    }

}
