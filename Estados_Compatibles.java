import Definicion_Formal.Estado;
public class Estados_Compatibles{
    private Estado m1;
    private Estado m2;
    private boolean compatibilidad;
        public Estados_Compatibles(Estado m1, Estado m2, boolean compatibilidad) {
        this.m1 = m1;
        this.m2 = m2;          
        this.compatibilidad = compatibilidad;
    }
    public Estado getm1() {
        return m1;
    }
    public Estado getm2() {
        return m2;
    }
    public boolean isCompatibilidad() {
        return compatibilidad;
    }
    public String toString() {
        return "Estados_Compatibles [m1=" + m1 + ", m2=" + m2 + ", compatibilidad=" + compatibilidad + "]";
    }
}