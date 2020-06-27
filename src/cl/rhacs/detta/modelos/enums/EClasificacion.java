package cl.rhacs.detta.modelos.enums;

public enum EClasificacion {

    // Constantes
    // -----------------------------------------------------------------------------------------

    LEVE("Leve"), GRAVE("Grave"), FATAL("Fatal"), OTRO("Otro");

    // Atributos
    // -----------------------------------------------------------------------------------------

    private String clasificacion;

    // Constructores
    // -----------------------------------------------------------------------------------------

    private EClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return la clasificación del accidente
     */
    public String getClasificacion() {
        return clasificacion;
    }

}
