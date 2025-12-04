public class Principal {
    public static void main(String[] args) {

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

        // Imprimir la tabulación
        for (Transicion t : a.transiciones) {
            System.out.println(t);
        }
    }
}