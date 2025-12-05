public class PilaGenerica <T extends Object>{
    private NodoP<T> inicio, fin;
    private int tamaño;
    
    /**
     * Agrega un elemento de la cima de la pila.
     * @param valor Valor a insertar en la cima.
     */
    public void push(T valor) {
        push(new NodoP<T>(valor));
    }

    public void push(NodoP<T> nodo) {
        if (inicio == null) {
            inicio = fin = nodo;
            tamaño = 1;
        } else {
            NodoP<T> nuevo = nodo;
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
    public T pop() {
        if (inicio != null) {
            NodoP<T> borrado = null;
            if (size() == 1) {
                borrado = fin;
                fin = inicio = null;
                tamaño = 0;
            } else {
                borrado = fin;
                fin = fin.izq;
                fin.der = borrado.izq = null;
                tamaño--;
            }
            return (borrado != null) ? borrado.dato : null;
        }
        return null;
    }

    /**
     * Elimina un nodo de la cima de la pila.
     * @return Nodo eliminado.
     */
    public NodoP<T> popNodo() {
        if (inicio != null) {
            NodoP<T> borrado = null;
            if (size() == 1) {
                borrado = fin;
                fin = inicio = null;
                tamaño = 0;
            } else {
                borrado = fin;
                fin = fin.izq;
                fin.der = borrado.izq = null;
                tamaño--;
            }
            return borrado;
        }
        return null;
    }

    /**
     * Verifica si la pila está vacía.
     * @return True si está vacía, false si no.
     */
    public boolean isempty() {
        return (size() == 0);
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
        if (!isempty()) {
            T dato = pop();      
            push(dato);          
            return dato;
        }
        return null;
    }

    @Override
    public String toString() {
        String cadena = "Pila(" + size() + "): {";
        NodoP<T> cursor = inicio;
        while (cursor != null) {
            cadena += cursor;
            cursor = cursor.der;
        }
        return cadena + "\b}";
    }
    
    public class NodoP <T> {
        public T dato;
        public NodoP<T> izq, der;

        public NodoP(T dato) {
            this.dato = dato;
        }

        @Override
        public String toString() {
            return "" + dato + ' ';
        }
    }
}