public class Estados_Compatibles{
    private String m1;
    private String m2;
    private boolean compatibilidad;
        public Estados_Compatibles(String m1, String m2, boolean compatibilidad) {
        this.m1 = m1;
        this.m2 = m2;          
        this.compatibilidad = compatibilidad;
    }
    public String getm1() {
        return m1;
    }
    public String getm2() {
        return m2;
    }
    public boolean isCompatibilidad() {
        return compatibilidad;
    }
    public String toString() {
        return "Estados_Compatibles [m1=" + m1 + ", m2=" + m2 + ", compatibilidad=" + compatibilidad + "]";
    }
}