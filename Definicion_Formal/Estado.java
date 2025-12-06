package Definicion_Formal;
public class Estado {
    String nombre;

    public Estado(String nombre) {
        this.nombre = nombre;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estado)) return false;
        Estado other = (Estado) o;
        return this.nombre != null && this.nombre.equals(other.nombre);
    }

    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

