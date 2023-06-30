package Adventure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import Parser.ElementoFrase;
import StrutturaDati.Mappa;

/**
 * Classe che rappresenta un giocatore.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class Giocatore implements Serializable {
    private int punteggio;
    private boolean isRagazzaSalvata = false;

    private List<Giocatore> giocatori = new ArrayList<>();

    private Inventario inventarioGiocatore = new Inventario();
    private Mappa mappaGiocatore = new Mappa(10);
    private Stanza stanzaCorrente;

    /**
     * Costruttore che inizializza alcuni attributi del giocatore di tipo Giocatore
     * in modo da posizionare un giocatore in uno stato iniziale del gioco.
     * @param stanzaCorrente
     * @param mappaGiocatore
     */
    public Giocatore(Stanza stanzaCorrente, Mappa mappaGiocatore){
        this.punteggio = 0;
        this.stanzaCorrente = stanzaCorrente;
        this.mappaGiocatore = mappaGiocatore;
    }

    /**
     * Getter dell'attributo punteggio del giocatore.
     * @return il punteggio del giocatore
     */
    public int getPunteggio(){
        return punteggio;
    }

    /**
     * Metodo che incrementa il punteggio del giocatore.
     * @param punteggio
     */
    public void incrementaPunteggio(int punteggio){
        this.punteggio += punteggio;
    }

    /**
     * Setter dell'attributo isRagazzaSalvata.
     * @param isRagazzaSalvata 
     */
    public void setIsRagazzaSalvata(boolean isRagazzaSalvata) {
        this.isRagazzaSalvata = isRagazzaSalvata;
    }
    
    /**
     * Getter dell'attributo isRagazzaSalvata.
     * @return true se è stata salvata, false altrimenti
     */
    public boolean isIsRagazzaSalvata() {
        return isRagazzaSalvata;
    }

    /**
     * Setter dell'attributo stanzaCorrente in cui si trova un giocatore.
     * @param stanzaCorrente
     */
    public void setStanzaCorrente(Stanza stanzaCorrente){
        this.stanzaCorrente = stanzaCorrente;
    }

    /**
     * Getter dell'attributo stanzaCorrente in cui si trova un giocatore.
     * @return la stanzaCorrente
     */
    public Stanza getStanzaCorrente(){
        return stanzaCorrente;
    }

    /**
     * Setter di una lista di giocatori.
     * @param giocatori
     */
    public void setGiocatori(List<Giocatore> giocatori){
        this.giocatori = giocatori;
    }

    /**
     * Metodo che aggiunge un giocatore all'interno della lista dei giocatori.
     * @param g
     */
    public void aggiungiGiocatore(Giocatore g){
        giocatori.add(g);
    }

    /**
     * Setter dell'attributo inventarioGiocatore.
     * @param inventarioGiocatore
     */
    public void setInventarioGiocatore(Inventario inventarioGiocatore){
        this.inventarioGiocatore = inventarioGiocatore;
    }

    /**
     * Getter dell'inventarioGiocatore.
     * @return l'inventario di un giocatore
     */
    public Inventario getInventarioGiocatore(){
        return inventarioGiocatore;
    }

    /**
     * Setter che imposta la mappa corrente relativa ad una partita, del giocatore.
     * @param mappaGiocatore
     */
    public void setMappaGiocatore(Mappa mappaGiocatore){
        this.mappaGiocatore = mappaGiocatore;
    }

    /**
     * Getter della mappa corrente del giocatore.
     * @return la mappa corrente del giocatore
     */
    public Mappa getMappaGiocatore(){
        return mappaGiocatore;
    }

    /**
     * Metodo che permette ad un giocatore di prendere un oggetto all'interno di una stanza.
     * @param oggetto
     * @return un booleano che rappresenta se un determinato oggetto è stato preso o meno
     */
    public boolean prendiOggetto(ElementoFrase.Oggetto oggetto){
        List<Oggetto> oggetti = stanzaCorrente.getOggettiStanza();

        for(Oggetto o: oggetti){
            if(o.getId() == oggetto && !o.isPreso()){  //controllo se l'oggetto è presente nella stanza e se non è già stato preso
                 System.out.println("L'ho preso " + o.getNome());
                 o.setPreso(true);  //setto l'oggetto come preso
                 o.setGettatto(false); 
                 oggetti.remove(o);
                 this.getInventarioGiocatore().addOggetto(o);
                 return true;
            }
        }

        return false;
    }

    /**
     * Metodo che permette ad un giocatore di lasciare un oggetto all'interno del suo inventario,
     * nella stanza corrente in cui si trova.
     * @param oggetto
     * @return un booleano che rappresenta se un determinato oggetto è stato lasciato o meno
     */
    public boolean lasciaOggetto(ElementoFrase.Oggetto oggetto){
        List<Oggetto> oggetti = inventarioGiocatore.getInventario();

        for(Oggetto o: oggetti){
            if(o.getId() == oggetto && o.isPreso() && !o.isGettato() && o.isGettabile()){
                o.setPreso(false);
                o.setGettatto(true);
                inventarioGiocatore.getInventario().remove(o);
                stanzaCorrente.getOggettiStanza().add(o);

                return true; 
            }
        }

        return false;
    }

}
