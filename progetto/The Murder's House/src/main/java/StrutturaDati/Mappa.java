
package StrutturaDati;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import Adventure.Stanza;

/**
 * Classe che rappresenta una mappa relativa ad una partita.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class Mappa extends Grafo<Stanza, String> {
    //private static final int N_STANZE = 11;

    /**
     * Costruttore della classe mappa che eredita dalla classe Grafo.
     * @param dim 
     */
    public Mappa(int dim){
        super(dim);  //richiamo il costruttore di grafo
    }

    /**
     * Metodo che ricerca un nodo di tipo Stanza all'interno della mappa rappresentata come un grafo.
     * @param a
     * @return un nodo
     */
    private Grafo.Nodo<Stanza> trovaNodoStanza(Stanza a){
        List<GrafoMatriceAdiacenza.Nodo<Stanza>> listaAttuale = super.getNodi();
        for(Grafo.Nodo<Stanza> s : listaAttuale){
            if(s.getVal().equals(a)){
                return s;
            }
        }
        return null;
    }

    /**
     * Metodo che aggiunge una stanza all'interno della mappa.
     * @param a 
     */
    public void aggiungiStanza(Stanza a){
        super.insNodo(new Grafo.Nodo<>(a));
    }

    /**
     * Metodo che verifica se un oggetto di tipo stanza a uno stato avvalorato o meno,
     * nel caso positivo viene invocato il metodo che legge il suo stato.
     * @param a
     * @return lo stato della stanza
     */
    public Stanza leggiStanza(Stanza a){
        GrafoMatriceAdiacenza.Nodo<Stanza> s = this.trovaNodoStanza(a);
        if(s != null)
            return (Stanza) super.leggiNodo(s);
        return null;
    }

    /**
     * Metodo che cancella una stanza all'interno della mappa.
     * @param a 
     */
    public void cancellaStanza(Stanza a){
        GrafoMatriceAdiacenza.Nodo<Stanza> s = this.trovaNodoStanza(a);
        if(s != null) super.cancellaNodo(s);
    }

    /**
     * Metodo che collega due stanze con un arco.
     * @param a
     * @param b
     * @param direzione 
     */
    public void collegaStanze(Stanza a, Stanza b, String direzione){
        GrafoMatriceAdiacenza.Nodo<Stanza> nodoa = this.trovaNodoStanza(a);
        GrafoMatriceAdiacenza.Nodo<Stanza> nodob = this.trovaNodoStanza(b);
        if(nodoa != null && nodob != null)
            super.insArco(nodoa, nodob, direzione);
    }

    /**
     * Metodo che legge il valore contenuto nel collegmento sotteso tra due stanze.
     * @param a
     * @param b
     * @return il valore letto
     */
    public String leggiCollegamento(Stanza a, Stanza b){
        GrafoMatriceAdiacenza.Nodo<Stanza> nodoa = this.trovaNodoStanza(a);
        GrafoMatriceAdiacenza.Nodo<Stanza> nodob = this.trovaNodoStanza(b);
        if(nodoa != null && nodob != null)
            return (String) super.leggiPeso(nodoa, nodob);
        return null;                
    }

    /**
     * Metodo che cancella il collegamento tra due stanze.
     * @param a
     * @param b 
     */
    public void cancellaCollegamento(Stanza a, Stanza b){
        GrafoMatriceAdiacenza.Nodo<Stanza> nodoa = this.trovaNodoStanza(a);
        GrafoMatriceAdiacenza.Nodo<Stanza> nodob = this.trovaNodoStanza(b);
        if(nodoa != null && nodob != null)
            super.cancArco(nodoa, nodob);
    }

    /**
     * Metodo che verifica la presenza di stanze adiacenti a quella passata in input.
     * @param a
     * @return la lista delle stanze adiacenti
     */
    public List<Stanza> stanzeAdiacenti(Stanza a){
        GrafoMatriceAdiacenza.Nodo<Stanza> nodoa = this.trovaNodoStanza(a);
        List<Stanza> stanzeAdc = new ArrayList<>();
        for (Iterator<GrafoMatriceAdiacenza.Nodo<Stanza>> it = super.adiacenti(nodoa).iterator(); it.hasNext();) {
            GrafoMatriceAdiacenza.Nodo<Stanza> n = (Grafo.Nodo<Stanza>) it.next();
            stanzeAdc.add(n.getVal());
        }
        return stanzeAdc;
    }
   
    
    @Override
    public void stampa(){
        List<GrafoMatriceAdiacenza.Nodo<Stanza>> listaNodi = super.getNodi();
        Object[][] matrice = super.getMatrice();
        System.out.print("  ");
        for(GrafoMatriceAdiacenza.Nodo<Stanza> node : listaNodi){
            if(!node.getCancellato())
                System.out.print(node.getVal().getCodice() + "    ");
        }
        System.out.println();
        for(int i = 0; i < super.getDimensioneAttuale(); ++i){
            if(!listaNodi.get(i).getCancellato()){
                System.out.print(listaNodi.get(i).getVal().getCodice() + " ");
                
                for(int j = 0; j < super.getDimensioneAttuale(); ++j){
                    if(!listaNodi.get(j).getCancellato())
                        System.out.print(matrice[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

}
