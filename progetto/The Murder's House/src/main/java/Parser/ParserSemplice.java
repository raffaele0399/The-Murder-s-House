package Parser;

import Adventure.Giocatore;
import Adventure.Stanza;
import StrutturaDati.Mappa;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import Utility.Utils;

/**
 * Classe che rappresenta il funzionamento di un parser semplificato che effettua l'analisi sintattica e semantica
 * di una stringa composta da una sola parola, ovvero: predicato oppure una direzione.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class ParserSemplice implements AnalisiTestuale<Boolean>{
    private final Map<String, ElementoFrase.Posizione> posizioni;
    private final Map<String, ElementoFrase.Verbo> intransitivi;
    private final Map<String, ElementoFrase.Verbo> transitivi;
    public static final int POSIZIONE = 1;
    public static final int INTRANSITIVI = 2;
    public static final int TRANSITIVI = 3;
    
    private JLabel label;
    private JTextArea textArea;

    /**
     * Costruttore che prende in input una JTextArea e una JTextField e associa 
     * gli attributi della classe alle rispettive tabelle di elementi di una frase. 
     * @param label
     * @param textArea
     * @throws FileNotFoundException 
     */
    public ParserSemplice(JLabel label, JTextArea textArea) throws FileNotFoundException {
        this.posizioni = Tabella.getPosizioni();
        this.intransitivi = Tabella.getVerbiIntransitivi();
        this.transitivi = Tabella.getVerbiTransitivi();
        this.label = label;
        this.textArea = textArea;
    }  

    /**
     * Metodo che restituisce un valore booleano a seconda se la chiave della parola
     * è contenuta all'interno di una delle tabelle verificate, effettuando così l'analisi sintattica.
     * @param str
     * @return 
     */
    @Override
    public Boolean analisiSintattica(String[] str) {
        return(posizioni.containsKey(str[0]) 
            || intransitivi.get(str[0])==ElementoFrase.Verbo.SALI
            || intransitivi.get(str[0])==ElementoFrase.Verbo.SCENDI
            || transitivi.get(str[0])==ElementoFrase.Verbo.OSSERVA);
    }

    /**
     * Metodo che switcha i casi in cui la parola passata in input corrisponde ad una posizione oppure ad un'azione.
     * @param str
     * @param giocatore 
     */
    @Override
    public void analisiSemantica(String[] str, Giocatore giocatore) {
        if(checkCase(str[0])!=0){
            switch(checkCase(str[0])){
                case POSIZIONE:
                    processaPosizione(str[0], giocatore);
                break;
                case INTRANSITIVI:
                    processaIntransitivi(str[0], giocatore);
                break;
                case TRANSITIVI:
                    processaTransitivi(giocatore);
                break;
                default:
                break;
            }
        }else{
            return;
        }
    }

    /**
     * Metodo che restituisce un intero a seconda se la stringa passata in input corrisponde
     * ad una chiave contenuta un posizione, oppure in intransitivi.
     * @param str
     * @return 
     */
    private int checkCase(String str){
        if(posizioni.containsKey(str))
            return POSIZIONE;
        if(intransitivi.get(str) == ElementoFrase.Verbo.SALI || intransitivi.get(str) == ElementoFrase.Verbo.SCENDI)
            return INTRANSITIVI;
        if(transitivi.get(str)== ElementoFrase.Verbo.OSSERVA)
            return TRANSITIVI;
        return 0;
    }

    /**
     * Metodo che verifica se la posizione ricade in uno dei casi dello switch.
     * @param str
     * @param giocatore 
     */
    private void processaPosizione(String str, Giocatore giocatore){
        if(posizioni.containsKey(str)){
            if(str.length()==1){
                switch(str.charAt(0)){
                    case 'n':
                        str = "nord";
                    break;
                    case 's':
                        str = "sud";
                    break;
                    case 'e':
                        str = "est";
                    break;
                    case 'o':
                        str = "ovest";
                    break;
                    case 'w':
                        str = "ovest";
                    break;
                    default:
                    break;
                }
            }
            System.out.println("sto per cambiare stanza");
            switch(posizioni.get(str)){
                case SU:
                    this.cambiaPosizione("su", giocatore);
                break;    
                case GIU:
                    this.cambiaPosizione("giu", giocatore);
                break;
                default:
                    this.cambiaPosizione(str, giocatore);
                break;  
            }
        }
    }

    /**
     * Metodo che verifica se la stringa in input ricade in uno dei casi dello switch.
     * @param str
     * @param giocatore 
     */
    private void processaIntransitivi(String str, Giocatore giocatore){
        System.out.println("Sono dentro il case Intransitivi");
        switch(intransitivi.get(str)){
                case SALI:
                    this.cambiaPosizione("su", giocatore);
                break;
                case SCENDI:
                    this.cambiaPosizione("giu", giocatore);
                break;
                default:
                break;
            }
    }

    /**
     * Metodo che effettua lo spostamento logico del giocatore, impostando la sua posizione
     * in un'altra stanza.
     * @param str
     * @param giocatore 
     */
    private void cambiaPosizione(String str, Giocatore giocatore){
        Stanza s = giocatore.getStanzaCorrente();
        Mappa m = giocatore.getMappaGiocatore();
        
        List<Stanza> adiacenti = m.stanzeAdiacenti(s);
        for(Stanza st : adiacenti){
            if(m.leggiCollegamento(s, st).equals(str) && st.isApribile() == true){
                System.out.println("\nSei nella stanza: " + st.getNome());
                giocatore.setStanzaCorrente(st);
                
                if(st.getNome().equals("Trappola")) {
                    s.setIsApribile(false);
                }
                if(s.getNome().equals("Fossato") && s.isApribile() == false) {
                    s.setIsApribile(true);
                }
                
                Utils.stampa(st.getDescrizione(), this.textArea);
                
                label.setIcon(st.getIcon());    
                return;
            }
        }
        Utils.stampa("> Non puoi andare in questa direzione", this.textArea);
    }

    /**
     * Metodo che verifica se la stringa in input ricade in uno dei casi dello switch.
     * @param giocatore 
     */
    private void processaTransitivi(Giocatore giocatore) {
        Utils.stampa(giocatore.getStanzaCorrente().getDescrizione(), textArea);
    }

}
