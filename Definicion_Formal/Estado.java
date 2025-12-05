package Definicion_Formal;
public class Estado {
    String nombre;

    public Estado(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

