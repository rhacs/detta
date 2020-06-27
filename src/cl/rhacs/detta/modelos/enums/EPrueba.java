package cl.rhacs.detta.modelos.enums;

public enum EPrueba {

    // Constantes
    // -----------------------------------------------------------------------------------------

    PARTE("Parte de Carabineros"), DECLARACION("Declaración"), TESTIGOS("Testigos"), OTRO("Otro");

    // Atributos
    // -----------------------------------------------------------------------------------------

    private String prueba;

    // Constructores
    // -----------------------------------------------------------------------------------------

    private EPrueba(String prueba) {
        this.prueba = prueba;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return el tipo de medio de prueba del accidente
     */
    public String getPrueba() {
        return prueba;
    }

}
