import java.util.ArrayList;
import Definicion_Formal.Automata;
import Definicion_Formal.Estado;
import Definicion_Formal.Transicion;
import Listas_Estados.Estados_Compatibles;
import Listas_Estados.Simbolos_Estado;

public class Minimizacion {
    private ArrayList<Estados_Compatibles> estados_compatibles;
    private Automata a; 
    private ArrayList<Simbolos_Estado> origen;

    public Minimizacion(Automata automata) {
        estados_compatibles = new ArrayList<>();
        this.a = automata;
        this.origen = new ArrayList<>();
    }

    public void CompatibilidadEstados() {
        ArrayList<Estado> estados = a.getEstados();
        ArrayList<Estado> finales = a.getFinales();

        // recorrer todos los pares de estados
        for (int i = 0; i < estados.size(); i++) {
            Estado estado1 = estados.get(i);
            boolean esFinal1 = finales.contains(estado1);

            for (int j = i + 1; j < estados.size(); j++) {
                Estado estado2 = estados.get(j);
                boolean esFinal2 = finales.contains(estado2);

                // solo comparar pares que sean ambos finales o ambos no finales
                if (esFinal1 == esFinal2) {
                    estados_compatibles.add(new Estados_Compatibles(estado1, estado2, true));
                }
            }
        }

        // evaluar cada par
        for (Estados_Compatibles par : new ArrayList<>(estados_compatibles)) {
            if (!a.getEstados().contains(par.getm1()) || !a.getEstados().contains(par.getm2())) {
                continue;
            }

            if (par.isCompatibilidad()) {
                boolean compatibles = imprimirBloqueEnumerado(par);
                if (compatibles) {
                    eliminarEstado(par.getm2(), par.getm1());
                }
            }
        }
    }

    private boolean imprimirBloqueEnumerado(Estados_Compatibles par) {
        Estado m1 = par.getm1();
        Estado m2 = par.getm2();
        Simbolos_Estado parOrigen = new Simbolos_Estado(m1, m2);

        ArrayList<String> m1m2 = new ArrayList<>();
        ArrayList<String> trans0 = new ArrayList<>();
        ArrayList<String> trans1 = new ArrayList<>();
        ArrayList<Simbolos_Estado> pendientes = new ArrayList<>();

        m1m2.add(m1 + " - " + m2);
        pendientes.add(parOrigen);
        if (!origen.contains(parOrigen)) {
            origen.add(parOrigen);
        }

        boolean compatibleFinal = true;
        String detalleIncompatibilidad = "";

        // recorrer pares pendientes en cascada
        while (!pendientes.isEmpty()) {
            Simbolos_Estado actual = pendientes.remove(0);
            Estado e1 = actual.getm1();
            Estado e2 = actual.getm2();

            for (char simbolo : a.getAlfabeto()) {
                Estado destino1 = null;
                Estado destino2 = null;

                for (Transicion t : a.getTransiciones()) {
                    if (t.desde == e1 && t.simbolo == simbolo) destino1 = t.hacia;
                    if (t.desde == e2 && t.simbolo == simbolo) destino2 = t.hacia;
                }

                if (destino1 != null && destino2 != null) {
                    boolean d1Final = a.getFinales().contains(destino1);
                    boolean d2Final = a.getFinales().contains(destino2);

                    String formato = (destino1 == destino2) 
                        ? destino1 + " = " + destino2 
                        : destino1 + " - " + destino2;

                    if (d1Final == d2Final) {
                        Simbolos_Estado nuevoPar = new Simbolos_Estado(destino1, destino2);
                        if (!origen.contains(nuevoPar) && destino1 != destino2) {
                            origen.add(nuevoPar);
                            pendientes.add(nuevoPar);
                            m1m2.add(destino1 + " - " + destino2);
                        }
                        if (simbolo == '0') trans0.add(formato);
                        if (simbolo == '1') trans1.add(formato);
                    } else {
                        compatibleFinal = false;
                        if (d1Final && !d2Final) {
                            detalleIncompatibilidad = "El estado " + destino1 + " es final y " + destino2 + " no lo es";
                        } else if (!d1Final && d2Final) {
                            detalleIncompatibilidad = "El estado " + destino2 + " es final y " + destino1 + " no lo es";
                        }
                        if (simbolo == '0') trans0.add(formato);
                        if (simbolo == '1') trans1.add(formato);
                        // no rompemos el ciclo, seguimos expandiendo para ver toda la traza
                    }
                }
            }
        }

        // impresión de la tabla
        System.out.println("\nComparando par: " + m1 + " - " + m2);

        System.out.println("\n   M1 - M2");
        System.out.println("   --------");
        for (int i = 0; i < m1m2.size(); i++) {
            System.out.println("   " + (i+1) + ". | " + m1m2.get(i));
        }

        System.out.println("\n   0");
        System.out.println("   --------");
        for (int i = 0; i < trans0.size(); i++) {
            System.out.println("   " + (i+1) + ". | " + trans0.get(i));
        }

        System.out.println("\n   1");
        System.out.println("   --------");
        for (int i = 0; i < trans1.size(); i++) {
            System.out.println("   " + (i+1) + ". | " + trans1.get(i));
        }

        System.out.println("\n--------------------------------------------");
        if (compatibleFinal) {
            System.out.println("Resultado final: Compatible");
        } else {
            System.out.println("Resultado final: Incompatible -> " + detalleIncompatibilidad);
        }
        System.out.println("============================================");

        return compatibleFinal;
    }

    private void eliminarEstado(Estado eliminar, Estado compatible) {
        ArrayList<Transicion> nuevasTransiciones = new ArrayList<>();

        for (Transicion t : a.getTransiciones()) {
            if (t.hacia == eliminar) {
                nuevasTransiciones.add(new Transicion(t.desde, t.simbolo, compatible));
            } else if (t.desde != eliminar) {
                nuevasTransiciones.add(t);
            }
        }

        a.setTransiciones(nuevasTransiciones);
        a.getEstados().remove(eliminar);
        a.getFinales().remove(eliminar);

        estados_compatibles.removeIf(p -> p.getm1() == eliminar || p.getm2() == eliminar);

        System.out.println("Estado " + eliminar + " eliminado. Redirigido hacia " + compatible);
    }
}
