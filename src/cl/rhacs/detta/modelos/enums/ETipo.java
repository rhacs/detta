package cl.rhacs.detta.modelos.enums;

public enum ETipo {

    // Constantes
    // -----------------------------------------------------------------------------------------

    TRABAJO("Trabajo"), TRAYECTO("Trayecto");

    // Atributos
    // -----------------------------------------------------------------------------------------

    private String tipo;

    // Constructores
    // -----------------------------------------------------------------------------------------

    private ETipo(String tipo) {
        this.tipo = tipo;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return la categorización de accidente
     */
    public String getTipo() {
        return tipo;
    }

}
