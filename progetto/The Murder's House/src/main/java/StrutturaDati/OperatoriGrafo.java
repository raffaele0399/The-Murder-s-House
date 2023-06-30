package StrutturaDati;

import java.util.List;

/**
 * Interfaccia che definisce gli operatori che possono essere applicati ad un
 * grafo.
 * 
 * @param <N> tipo del nodo
 * @param <A> tipo del peso dell'arco
 * @param <V> tipo del valore contenuto nel nodo
 */
public interface OperatoriGrafo<N, A, V> {
    public boolean vuoto();
    public void insNodo(N nodo);
    public V leggiNodo(N nodo);
    public void scriviNodo(N a, V val);
    public void cancellaNodo(N nodo);
    public void insArco(N a, N b, A peso);
    public A leggiPeso(N a, N b);
    public void scriviArco(N a, N b, A peso);
    public void cancArco(N a, N b);
    public List<N> adiacenti(N n);
    public boolean esisteNodo(N n);
    public boolean esisteArco(N a, N b);

}
