public class Estados_Compatibles{
    private String M1;
    private String M2;
    private boolean compatibilidad;
        public Estados_Compatibles(String M1, String M2, boolean compatibilidad) {
        this.M1 = M1;
        this.M2 = M2;          
        this.compatibilidad = compatibilidad;
    }
    public String getM1() {
        return M1;
    }
    public String getM2() {
        return M2;
    }
    public boolean isCompatibilidad() {
        return compatibilidad;
    }
    public String toString() {
        return "Estados_Compatibles [M1=" + M1 + ", M2=" + M2 + ", compatibilidad=" + compatibilidad + "]";
    }
}