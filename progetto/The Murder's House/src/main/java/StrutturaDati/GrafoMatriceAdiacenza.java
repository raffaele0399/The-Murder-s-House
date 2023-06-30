package StrutturaDati;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe astratta che rappresenta il funzionamento della struttura dati grafo matrice di adiacenza esteso.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 * @param <N>
 * @param <A>
 */
public abstract class GrafoMatriceAdiacenza<N, A> implements OperatoriGrafo<GrafoMatriceAdiacenza.Nodo<N>, A, N>, Serializable{
    private List<GrafoMatriceAdiacenza.Nodo<N>> nodi;
    private A matrice[][];
    private int dimensioneMassima;
    private int dimensioneAttuale;

    /**
     * Inner class che rappresenta l'elemento nodo di un grafo.
     * @param <N> 
     */
    protected static class Nodo<N> implements Serializable{
        private int pos;
        private N val;
        private boolean cancellato;

        /**
         * Costruttore di un oggetto di tipo nodo.
         * @param val 
         */
        Nodo(N val){
            this.val = val;
            cancellato = false;
        }

        /**
         * Setter dell'attributo val.
         * @param val 
         */
        private void setVal(N val){
            this.val = val;
        }

        /**
         * Getter dell'attributo val.
         * @return val
         */
        protected N getVal(){
            return this.val;
        }

        /**
         * Setter dell'attributo pos.
         * @param pos 
         */
        private void setPos(int pos){
            this.pos = pos;
        }

        /**
         * Getter dell'attributo pos.
         * @return pos
         */
        private int getPos(){
            return pos;
        }

        /**
         * Setter dell'attributo cancellato.
         * @param cancellato 
         */
        private void setCancellato(boolean cancellato){
            this.cancellato = cancellato;
        }

        /** 
         * Getter dell'attributo cancellato.
         * @return cancellato
         */
        protected boolean getCancellato(){
            return cancellato;
        }

    }


    /**
     * Costruttore che prende in input un parametro che viene associato ad un attributo della classe.
     * @param dim 
     */
    @SuppressWarnings("unchecked") //per evitare warning di compilazione sul casting di  un Object in una matrice
    public GrafoMatriceAdiacenza(int dim){
        dimensioneMassima = dim;
        dimensioneAttuale = 0;
        nodi = new ArrayList<>();
        matrice = (A[][]) new Object[dimensioneMassima][dimensioneMassima];
    }

    /**
     * Setter dell'attributo dimensioneAttuale.
     * @param dim 
     */
    private void setDimensioneAttuale(int dim){
        dimensioneAttuale = dim;
    }

    /**
     * Getter dell'attributo dimensioneAttuale.
     * @return dimensioneAttuale
     */
    protected int getDimensioneAttuale(){
        return dimensioneAttuale;
    }

    /**
     * Getter dell'attributo dimensioneMassima.
     * @return dimensioneMassima
     */
    private int getDimensioneMassima(){
        return dimensioneMassima;
    }

    /**
     * Getter della lista di nodi di un grafo.
     * @return lista di nodi
     */
    public List<GrafoMatriceAdiacenza.Nodo<N>> getNodi(){
        return this.nodi;
    }

    /**
     * Getter di una matrice.
     * @return una matrice
     */
    @SuppressWarnings("unchecked") //per evitare warning di compilazione sul casting di un Object in una matrice
    protected A[][] getMatrice(){
        A[][] nMatrice = (A[][]) new Object[this.matrice.length][this.matrice[0].length];
        for(int i = 0; i < this.matrice.length; i++){
            for(int j = 0; j < this.matrice[0].length; j++){
                nMatrice[i][j] = this.matrice[i][j];
            }
        }
        return nMatrice;
    }

    /**
     * Metodo che verifica se la lista dei nodi presenti in un grafo è vuota.
     * @return valore booleano di conferma
     */
    @Override
    public boolean vuoto() {
        return(nodi.isEmpty()); //controllo solo la lista dei nodi
    }

    /**
     * Metodo che inserisce un nodo all'interno del grafo.
     * @param nodo 
     */
    @Override
    public void insNodo(Nodo<N> nodo){
        try{
            if(esisteNodo(nodo)) throw new GrafoException(GrafoException.getNODO_ESISTENTE());
            if(getDimensioneAttuale() < getDimensioneMassima()){
                nodi.add(nodo);
                nodo.setPos(nodi.indexOf(nodo));
                setDimensioneAttuale(nodi.size());
            }else{
                for(GrafoMatriceAdiacenza.Nodo<N> node : nodi){
                    if(node.getCancellato() && nodi.indexOf(node) < getDimensioneMassima()){
                        nodi.set(nodi.indexOf(node), nodo);
                    }
                }
            }
        }catch(GrafoException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Metodo che legge il peso di un nodo.
     * @param nodo
     * @return un nodo
     */
    @Override
    public N leggiNodo(Nodo<N> nodo) {
        try {
            if (esisteNodo(nodo)) {
                return (N) nodi.get(nodi.indexOf(nodo)).getVal();
            } else {
                throw new GrafoException(GrafoException.getNODO_INESISTENTE());
            }
        } catch (GrafoException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Metodo che avvalora il peso di un nodo.
     * @param a
     * @param val 
     */
    @Override
    public void scriviNodo(Nodo<N> a, N val) {
        try {
            if (esisteNodo(a) && val == a.getVal()) {
                throw new GrafoException(GrafoException.getERRORE_SCRITTURA_NODO());
            } else {
                if(!esisteNodo(a)) insNodo(a);
                a.setVal(val); 
            }
        } catch (GrafoException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Metodo che cancella un nodo da un grafo.
     * @param nodo 
     */
    @Override
    public void cancellaNodo(Nodo<N> nodo) {
        try {
            if(!esisteNodo(nodo)) throw new GrafoException(GrafoException.getNODO_INESISTENTE());
                List<GrafoMatriceAdiacenza.Nodo<N>> adiacenti = new ArrayList<>();
                adiacenti = adiacenti(nodo);
                for (GrafoMatriceAdiacenza.Nodo<N> n : adiacenti) cancArco(nodo, n);
                nodo.setCancellato(true);
        } catch (GrafoException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Metodo che crea un arco dati due nodi in input.
     * @param a
     * @param b
     * @param peso 
     */
    @Override
    public void insArco(Nodo<N> a, Nodo<N> b, A peso) {
        try{
            if(!(esisteNodo(a) && esisteNodo(b))) throw new GrafoException(GrafoException.getNODO_INESISTENTE());
            if(!esisteArco(a, b)) {
                matrice[a.getPos()][b.getPos()] = peso;
            }else{
                throw new GrafoException(GrafoException.getARCO_ESISTENTE());
            }
        }catch (GrafoException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Metodo che legge il peso di un arco dati due nodi.
     * @param a
     * @param b
     * @return il valore letto su un arco
     */
    @Override
    public A leggiPeso(Nodo<N> a, Nodo<N> b) {
        try {
            if(!esisteNodo(a) && !esisteNodo(b)) throw new GrafoException(GrafoException.getNODO_INESISTENTE());
            
            if (!esisteArco(a, b)) throw new GrafoException(GrafoException.getARCO_INESISTENTE());
            
            return(matrice[a.getPos()][b.getPos()]);
        } catch (GrafoException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Metodo che avvalora il peso di un arco.
     * @param a
     * @param b
     * @param peso 
     */
    @Override
    public void scriviArco(Nodo<N> a, Nodo<N> b, A peso) {
        try{
            if(!esisteNodo(a) && !esisteNodo(b)) throw new GrafoException(GrafoException.getNODO_INESISTENTE());
            else if(esisteArco(a, b)) matrice[a.getPos()][b.getPos()] = peso;
            else insArco(a, b, peso);
        }catch(GrafoException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Metodo che cancella l'arco sotteso su due nodi.
     * @param a
     * @param b 
     */
    @Override
    public void cancArco(Nodo<N> a, Nodo<N> b) {
        try {
            if(!esisteNodo(a) && !esisteNodo(b)) throw new GrafoException(GrafoException.getNODO_INESISTENTE());
            if (!esisteArco(a, b)) throw new GrafoException(GrafoException.getARCO_INESISTENTE());
            
            matrice[a.getPos()][b.getPos()] = null;
        } catch (GrafoException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Metodo che mostra i nodi adiacenti rispetto ad un nodo dato in input.
     * @param n
     * @return lista dei nodi adiacenti
     */
    @Override
    public List<GrafoMatriceAdiacenza.Nodo<N>> adiacenti(Nodo<N> n) {
        try {
            if (!esisteNodo(n)) throw new GrafoException(GrafoException.getNODO_INESISTENTE());
            int indiceNodo = nodi.indexOf(n);
            List<GrafoMatriceAdiacenza.Nodo<N>> listaAdiacenti = new ArrayList<>();
            for(int i = 0; i < matrice.length; ++i){
                if(matrice[indiceNodo][i]!=null) {
                    listaAdiacenti.add(nodi.get(i));
                }
            }
            return listaAdiacenti;
        } catch (GrafoException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Metodo che verifica l'esistenza di un nodo dato in input.
     * @param n
     * @return booleano di conferma
     */
    @Override
    public boolean esisteNodo(Nodo<N> n) {
        return(nodi.contains(n));
    }

    /**
     * Metodo che verifica l'esistenza di un arco dati due nodi in input.
     * @param a
     * @param b
     * @return booleano di conferma
     */
    @Override
    public boolean esisteArco(Nodo<N> a, Nodo<N> b) {
        return(matrice[a.getPos()][b.getPos()]!=null);
    }

    /**
     * Metodo che stampa una grafo sottoforma di matrice di adiacenza.
     */
    public void stampa(){
        System.out.print("  ");
        for(GrafoMatriceAdiacenza.Nodo<N> node : nodi){
            if(!node.getCancellato())
                System.out.print(node.val + "    "); //ci andava il get_val
        }
        System.out.println();
        
        for(int i = 0; i < getDimensioneAttuale(); ++i){   //la dimensione della matrice la vincolo con la dimensione della lista di nodi
            if(!nodi.get(i).getCancellato()){
                System.out.print(nodi.get(i).val + " ");  //non sarà necessario stampare il resto della matrice se non ho inserito nodi
            for(int j = 0; j < getDimensioneAttuale(); ++j){
                if(!nodi.get(j).getCancellato())
                    System.out.print(matrice[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

}
