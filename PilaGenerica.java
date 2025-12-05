public class PilaGenerica <T extends Object>{
    private NodoP inicio, fin;
    private int tamaño;
    
    /**
     * Agrega un elemento de la cima de la pila.
     * @param valor Valor a insertar en la cima.
     */
    public void push(T valor) {
        push(new NodoP(valor));
    }

    public void push(NodoP nodo) {
        if (inicio == null) {
            inicio = fin = nodo;
            tamaño = 1;
        } else {
            NodoP nuevo = nodo;
            fin.der = nuevo;
            nuevo.izq = fin;
            fin = nuevo;
            tamaño++;
        }
    }

    /**
     * Elimina un elemento de la cima de la pila.
     * @return Valor eliminado.
     */
    public NodoP pop() {
        NodoP borrado = null;
        if (inicio != null) {
            if (size() == 1) {
                borrado = fin;
                fin = inicio = null;
                tamaño = 0;
            }else{
                borrado = fin;
                fin = fin.izq;
                fin.der = borrado.izq = null;
                tamaño--;
            }
        }
        return borrado;
    }

    /**
     * Verifica si la pila está vacía.
     * @return True si está vacía, false si no.
     */
    public boolean isempty() {
        return (size() == 0)? true : false;
    }

    /**
     * Devuelve el tamaño o el número de elementos contenidos en la pila.
     * @return Cantidad de elementos
     */
    public int size() {
        return tamaño;
    }

    /**
     * Devuelve el elemento de la cima de la pila sin eliminarlo.
     * @return Dato en la cima
     */
    public T peek() {
        NodoP x;
        if (!isempty()) {
            x = pop();
            push(x);
            return (T) x.dato;
        }
        return null;
    }

    @Override
    public String toString() {
        String cadena = "Pila(" + size() + "): {";
        NodoP cursor = inicio;
        while (cursor != null) {
            cadena += cursor;
            cursor = cursor.der;
        }
        return cadena + "\b}";
    }
    
    public class NodoP <T> {
        private T dato;
        public NodoP izq, der;

        public NodoP(T dato) {
            this.dato = dato;
        }

        @Override
        public String toString() {
            return "" + dato + ' ';
        }
    }
}
