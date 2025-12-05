import java.util.ArrayList;

import Definicion_Formal.Automata;
import Definicion_Formal.Estado;
import Definicion_Formal.Transicion;
public class Minimizacion {
    ArrayList<Estados_Compatibles>estados_compatibles;
    private Automata a; 
    private PilaGenerica<Simbolos_Estado> destinoA;
    private PilaGenerica<Simbolos_Estado> destinoB;
    private ArrayList<Simbolos_Estado> origen;
    public Minimizacion(Automata automata) {
        estados_compatibles = new ArrayList<>();
        this.a = automata;
        this.origen = new ArrayList<>();
        this.destinoA = new PilaGenerica<>();
        this.destinoB = new PilaGenerica<>();
    }

    public void CompatibilidadEstados() {
        ArrayList<Estado> estados = a.getEstados();
        ArrayList<Estado> finales = a.getFinales();
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
        imprimirCompatibilidad();
        Minimizar();
    }
    public void imprimirCompatibilidad() {
        for (Estados_Compatibles estado : estados_compatibles) {
            System.out.println(estado);
        }
    }

    public void verTransicionesDePares() {
        ArrayList<Character> alfabeto = a.getAlfabeto();

        for (Estados_Compatibles par : estados_compatibles) {
            Estado e1 = par.getm1();
            Estado e2 = par.getm2();
            
            System.out.println("\nPar: " + e1 + " - " + e2 + 
                            " (Compatibles: " + par.isCompatibilidad() + ")");
            
            for (char simbolo : alfabeto) {
                // Buscar transiciones en la lista del autómata
                Estado destino1 = null;
                Estado destino2 = null;
                
                for (Transicion t : a.getTransiciones()) {
                    // Transición para e1
                    if (t.desde == e1 && t.simbolo == simbolo) {
                        destino1 = t.hacia;
                    }
                    // Transición para e2
                    if (t.desde == e2 && t.simbolo == simbolo) {
                        destino2 = t.hacia;
                    }
                }
                
                System.out.println("  Con '" + simbolo + "': " + 
                                e1 + " → " + (destino1 != null ? destino1 : "null") + " | " +
                                e2 + " → " + (destino2 != null ? destino2 : "null"));
            }
        }
    }
    public void Minimizar() {
        ArrayList<Character> alfabeto = a.getAlfabeto();
        ArrayList<Estado> finales = a.getFinales();
        
        System.out.println("\n=== ALGORITMO DE MINIMIZACIÓN ===");
        
        for (Estados_Compatibles par : estados_compatibles) {
            // Solo procesamos si son compatibles según la lista inicial
            if (par.isCompatibilidad()) {
                boolean sonCompatibles = true;
                
                // Verificar transiciones para cada símbolo
                for (char simbolo : alfabeto) {
                    Estado destino1 = null;
                    Estado destino2 = null;
                    
                    // Buscar transiciones
                    for (Transicion t : a.getTransiciones()) {
                        if (t.desde == par.getm1() && t.simbolo == simbolo) {
                            destino1 = t.hacia;
                        }
                        if (t.desde == par.getm2() && t.simbolo == simbolo) {
                            destino2 = t.hacia;
                        }
                    }
                    
                    // VERIFICACIÓN: Si encontramos ambos destinos
                    if (destino1 != null && destino2 != null) {
                        // Verificar si uno es final y el otro no
                        boolean destino1EsFinal = finales.contains(destino1);
                        boolean destino2EsFinal = finales.contains(destino2);
                        
                        if (destino1EsFinal != destino2EsFinal) {
                            // ✗ NO SON COMPATIBLES - CORTAMOS AQUÍ
                            System.out.println("✗ " + par.getm1() + " y " + par.getm2() + 
                                            " NO son compatibles (destinos con '" + simbolo + "')");
                            sonCompatibles = false;
                            break; // Salimos del bucle de símbolos
                        }
                    }
                }
                
                // Si pasó todas las verificaciones, SÍ son compatibles
                if (sonCompatibles) {
                    System.out.println("✓ " + par.getm1() + " y " + par.getm2() + " SON compatibles");
                    
                    // Agregar a la lista de origen (estados compatibles)
                    origen.add(new Simbolos_Estado(par.getm1(), par.getm2()));
                    
                    // Solo si son compatibles, procesamos sus transiciones para las pilas
                    for (char simbolo : alfabeto) {
                        Estado destino1 = null;
                        Estado destino2 = null;
                        
                        for (Transicion t : a.getTransiciones()) {
                            if (t.desde == par.getm1() && t.simbolo == simbolo) {
                                destino1 = t.hacia;
                            }
                            if (t.desde == par.getm2() && t.simbolo == simbolo) {
                                destino2 = t.hacia;
                            }
                        }
                        
                        // Agregar a las pilas correspondientes
                        if (destino1 != null && destino2 != null) {
                            Simbolos_Estado parDestino = new Simbolos_Estado(destino1, destino2);
                            
                            if (simbolo == 'a') {
                                destinoA.push(parDestino);
                                System.out.println("  Agregado a destinoA: (" + destino1 + ", " + destino2 + ")");
                            } else if (simbolo == 'b') {
                                destinoB.push(parDestino);
                                System.out.println("  Agregado a destinoB: (" + destino1 + ", " + destino2 + ")");
                            }
                        }
                    }
                }
                // Si no son compatibles, NO los agregamos a origen NI a las pilas
                // Simplemente continuamos con el siguiente par
            } else {
                // Si ya eran incompatibles desde el inicio, los ignoramos
                System.out.println("- " + par.getm1() + " y " + par.getm2() + " ya eran incompatibles (saltado)");
            }
        }
        
        // Mostrar resultados finales
        System.out.println("\n=== RESUMEN FINAL ===");
        System.out.println("Total de pares procesados: " + estados_compatibles.size());
        System.out.println("Pares compatibles encontrados: " + origen.size());
        System.out.println("Elementos en pila destinoA: " + destinoA.size());
        System.out.println("Elementos en pila destinoB: " + destinoB.size());
        
        // Mostrar contenido de las pilas
        mostrarContenidoPilas();
    }

    private void mostrarContenidoPilas() {
        System.out.println("\n=== CONTENIDO DE LAS PILAS ===");
        
        // Mostrar destinoA
        System.out.println("\nPila destinoA:");
        if (destinoA.isempty()) {
            System.out.println("  Vacía");
        } else {
            PilaGenerica<Simbolos_Estado> temp = new PilaGenerica<>();
            int contador = 1;
            
            while (!destinoA.isempty()) {
                Simbolos_Estado par = destinoA.pop();
                temp.push(par);
                System.out.println("  " + contador + ". (" + par.getm1() + ", " + par.getm2() + ")");
                contador++;
            }
            
            // Restaurar la pila
            while (!temp.isempty()) {
                destinoA.push(temp.pop());
            }
        }
        
        // Mostrar destinoB
        System.out.println("\nPila destinoB:");
        if (destinoB.isempty()) {
            System.out.println("  Vacía");
        } else {
            PilaGenerica<Simbolos_Estado> temp = new PilaGenerica<>();
            int contador = 1;
            
            while (!destinoB.isempty()) {
                Simbolos_Estado par = destinoB.pop();
                temp.push(par);
                System.out.println("  " + contador + ". (" + par.getm1() + ", " + par.getm2() + ")");
                contador++;
            }
            
            // Restaurar la pila
            while (!temp.isempty()) {
                destinoB.push(temp.pop());
            }
        }
    }
}
