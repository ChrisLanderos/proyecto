import java.util.ArrayList;

import Definicion_Formal.Automata;
import Definicion_Formal.Estado;
import Definicion_Formal.Transicion;

public class pruebas {
    public static void main(String[] args) {
        ArrayList<Estados_Compatibles>estados_compatibles;
        Automata a = new Automata();

        // Estados
        Estado q0 = new Estado("q0");
        Estado q1 = new Estado("q1");
        Estado q2 = new Estado("q2");

        a.agregarEstado(q0);
        a.agregarEstado(q1);
        a.agregarEstado(q2);

        // Alfabeto
        a.agregarSimbolo('a');
        a.agregarSimbolo('b');

        // Inicial
        a.agregarInicial(q0);

        // Finales
        a.agregarFinal(q2);

        // Transiciones
        a.agregarTransicion(q0, 'a', q1);
        a.agregarTransicion(q0, 'b', q0);
        a.agregarTransicion(q1, 'a', q2);
        a.agregarTransicion(q1, 'b', q1);
        a.agregarTransicion(q2, 'a', q2);
        a.agregarTransicion(q2, 'b', q0);

        // Imprimir la tabulacion
        for (Transicion t : a.getTransiciones()) {
            System.out.println(t);
        }
        //Prueba de compatibilidad de estados
        ArrayList<Estado> estados = a.getEstados();
        ArrayList<Estado> finales = a.getFinales();
        estados_compatibles = new ArrayList<>();
        for (int i = 0; i < estados.size(); i++) {
            Estado estado1 = estados.get(i);
            boolean esFinal1 = finales.contains(estado1);
            
            for (int j = i + 1; j < estados.size(); j++) {
                Estado estado2 = estados.get(j);
                boolean esFinal2 = finales.contains(estado2);
                
                if (estado1 != estado2) { // No es el mismo estado
                    if (esFinal1 == esFinal2) {
                        // Ambos son finales o ambos no son finales - son compatibles
                        estados_compatibles.add(new Estados_Compatibles(estado1, estado2, true));
                    } else {
                        // Uno es final y el otro no - no son compatibles
                        estados_compatibles.add(new Estados_Compatibles(estado1, estado2, false));
                    }
                }
            }
        }
        for (Estados_Compatibles estado : estados_compatibles) {
            System.out.println(estado);
        }
    }
}