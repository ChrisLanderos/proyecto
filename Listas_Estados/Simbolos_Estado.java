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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Simbolos_Estado)) return false;
        Simbolos_Estado other = (Simbolos_Estado) o;
        return this.getm1().equals(other.getm1()) && this.getm2().equals(other.getm2());
    }

    @Override
    public int hashCode() {
        return 31 * getm1().hashCode() + getm2().hashCode();
    }
}
