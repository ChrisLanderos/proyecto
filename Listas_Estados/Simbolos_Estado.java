package Listas_Estados;
import Definicion_Formal.Estado;
public class Simbolos_Estado {
    private Estado m1;
    private Estado m2;
    public Simbolos_Estado(Estado m1, Estado m2) {
        this.m1 = m1;
        this.m2 = m2;          
    }
    
    public Estado getm1() {
        return m1;
    }

    public Estado getm2() {
        return m2;
    }
    public String toString() {
        return "Simbolos_Estado [m1=" + m1 + ", m2=" + m2 + "]";
    }
}
