package Definicion_Formal;
public class Transicion {
    public Estado desde;
    public char simbolo;
    public Estado hacia;

    public Transicion(Estado desde, char simbolo, Estado hacia) {
        this.desde = desde;
        this.simbolo = simbolo;
        this.hacia = hacia;
    }

    @Override
    public String toString() {
        return desde + " --" + simbolo + "--> " + hacia;
    }
}
