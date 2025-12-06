import Definicion_Formal.Automata;
import Definicion_Formal.Estado;
import Definicion_Formal.Transicion;
public class Run {
    public static void main(String[] args) {

        Automata a = new Automata();

        // Estados
        Estado ea = new Estado("A");  
        Estado eb = new Estado("B");  
        Estado ec = new Estado("C");  
        Estado ed = new Estado("D");  
        Estado ee = new Estado("E");  
        Estado ef = new Estado("F");  
        Estado eg = new Estado("G");  
        Estado eh = new Estado("H");  
        Estado ei = new Estado("I");  

        a.agregarEstado(ea);
        a.agregarEstado(eb);
        a.agregarEstado(ec);
        a.agregarEstado(ed);
        a.agregarEstado(ee);
        a.agregarEstado(ef);
        a.agregarEstado(eg);
        a.agregarEstado(eh);
        a.agregarEstado(ei);

        // Alfabeto 
        a.agregarSimbolo('0'); 
        a.agregarSimbolo('1'); 

        // Inicial 
        a.agregarInicial(ea);

        //Finales
        a.agregarFinal(ec);  
        a.agregarFinal(ef);  
        a.agregarFinal(ei);  

        // Transiciones ajustadas a nombres nuevos y símbolos 0/1
        // (A) transiciones
        a.agregarTransicion(ea, '0', eb);  // A --0--> B
        a.agregarTransicion(ea, '1', ee);  // A --1--> E
        
        // (B) transiciones
        a.agregarTransicion(eb, '0', ec);  // B --0--> C
        a.agregarTransicion(eb, '1', ef);  // B --1--> F
        
        // (C) transiciones
        a.agregarTransicion(ec, '0', ed);  // C --0--> D
        a.agregarTransicion(ec, '1', eh);  // C --1--> H
        
        // (D) transiciones
        a.agregarTransicion(ed, '0', ee);  // D --0--> E
        a.agregarTransicion(ed, '1', eh);  // D --1--> H
        
        // (E) transiciones
        a.agregarTransicion(ee, '0', ef);  // E --0--> F
        a.agregarTransicion(ee, '1', ei);  // E --1--> I
        
        // (F) transiciones
        a.agregarTransicion(ef, '0', eg);  // F --0--> G
        a.agregarTransicion(ef, '1', eb);  // F --1--> B
        
        // (G) transiciones
        a.agregarTransicion(eg, '0', eh);  // G --0--> H
        a.agregarTransicion(eg, '1', eb);  // G --1--> B
        
        // (H) transiciones
        a.agregarTransicion(eh, '0', ei);  // H --0--> I
        a.agregarTransicion(eh, '1', ec);  // H --1--> C
        
        // (I) transiciones
        a.agregarTransicion(ei, '0', ea);  // I --0--> A
        a.agregarTransicion(ei, '1', ee);  // I --1--> E

        // Imprimir la tabulacion
        System.out.println("Definicion Formal del Automata:");
        System.out.println("Estados: " + a.getEstados());
        System.out.println("Alfabeto: " + a.getAlfabeto());
        System.out.println("Estado Inicial: " + a.getInicial());
        System.out.println("Estados Finales: " + a.getFinales());
        System.out.println("Transiciones:");

        for (Transicion t : a.getTransiciones()) {
            System.out.println(t);
        }

        Minimizacion minimizador = new Minimizacion(a);  //Le pasamos el automata al minimizador
        minimizador.CompatibilidadEstados();

        System.out.println("\nDefinicion Formal del Automata Minimizado:");
        System.out.println("Estados: " + a.getEstados());
        System.out.println("Alfabeto: " + a.getAlfabeto());
        System.out.println("Estado Inicial: " + a.getInicial());
        System.out.println("Estados Finales: " + a.getFinales());
        System.out.println("Transiciones:");
        for (Transicion t : a.getTransiciones()) {
            System.out.println(t);
        }
    }
}