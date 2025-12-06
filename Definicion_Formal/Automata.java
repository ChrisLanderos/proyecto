package Definicion_Formal;
import java.util.ArrayList;
public class Automata {
    ArrayList<Estado> estados;
    ArrayList<Character> alfabeto;
    ArrayList<Transicion> transiciones;
    Estado inicial;
    ArrayList<Estado> finales;

    public Automata() {
        estados = new ArrayList<>();
        alfabeto = new ArrayList<>();
        transiciones = new ArrayList<>();
        finales = new ArrayList<>();
    }

    public void agregarEstado(Estado e) {
        estados.add(e);
    }

    public void agregarSimbolo(char c) {
        alfabeto.add(c);
    }

    public void agregarInicial(Estado e) {
        inicial = e;
    }

    public Estado getInicial() { 
        return inicial; 
    }
    
    public void setInicial(Estado e) { 
        this.inicial = e; 
    }

    public void agregarFinal(Estado e) {
        finales.add(e);
    }

    public void agregarTransicion(Estado desde, char simbolo, Estado hacia) {
        transiciones.add(new Transicion(desde, simbolo, hacia));
    }

    public void setTransiciones(ArrayList<Transicion> nuevasTransiciones) {
        this.transiciones = nuevasTransiciones;
    }

    public ArrayList<Transicion> getTransiciones() {
        return transiciones;
    }

    public ArrayList<Estado> getEstados() {
        return estados;
    }

    public ArrayList<Estado> getFinales() {
        return finales;
    }

    public ArrayList<Character> getAlfabeto() {
        return alfabeto;
    }
}