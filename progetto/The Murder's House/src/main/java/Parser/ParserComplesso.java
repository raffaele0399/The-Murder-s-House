package Parser;

import Adventure.Giocatore;
import Utility.Utils;
import java.io.FileNotFoundException;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * Classe che rappresenta il funzionamento di un parser che effettua l'analisi sintattica e semantica
 * di una stringa complessa, ovvero: predicato, articolo/ preposizione e complemento.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class ParserComplesso implements AnalisiTestuale<Short>{

    private static final short NON_VALIDO = -1;
    private static final short TRANSITIVO = 0;
    private static final short INTRANSITIVO = 1;

    private final Map<String, ElementoFrase.Posizione> posizioni;
    private final Map<String, ElementoFrase.Verbo> transitivi;
    private final Map<String, ElementoFrase.Verbo> intransitivi;
    private final Map<String, ElementoFrase.Articolo> articoli;
    private final Map<String, ElementoFrase.Oggetto> oggetti;
    private final Map<String, ElementoFrase.Preposizione> preposizioni;
    
    private Map<Integer, Integer> outputRef;

    private JLabel label;
    private JTextArea textArea;
    private JFrame interfacciaCorrente;

    /**
     * Costruttore che prende in input una JTextArea e una JTextField e associa 
     * gli attributi della classe alle rispettive tabelle di elementi di una frase.
     * @param label
     * @param textArea
     * @throws FileNotFoundException 
     */
    public ParserComplesso(JLabel label, JTextArea textArea, JFrame interfacciaCorrente) throws FileNotFoundException {
        this.posizioni = Tabella.getPosizioni();
        this.transitivi = Tabella.getVerbiTransitivi();
        this.intransitivi = Tabella.getVerbiIntransitivi();
        this.articoli = Tabella.getArticoli();
        this.oggetti = Tabella.getOggetti();
        this.preposizioni = Tabella.getPreposizioni();
        this.outputRef = Tabella.getTabellaChiaviRiferimento();
        
        this.label = label;
        this.textArea = textArea;
        this.interfacciaCorrente = interfacciaCorrente;
    }


    /**
     * Metodo che effettua l'analisi sintattica di una stringa complessa.
     * @param str
     * @return flag
     */
    @Override
    public Short analisiSintattica(String[] str) {
        short flag = NON_VALIDO;
        if(transitivi.containsKey(str[0]) && articoli.containsKey(str[1]) && oggetti.containsKey(str[2]))
            flag = TRANSITIVO;
        else if((intransitivi.containsKey(str[0]) && preposizioni.get(str[1])==ElementoFrase.Preposizione.A && posizioni.containsKey(str[2]))
                || (intransitivi.containsKey(str[0]) && preposizioni.get(str[1])==ElementoFrase.Preposizione.DALLA && oggetti.containsKey(str[2])))
            flag = INTRANSITIVO; 
        return flag;
    }

    /**
     * Metodo che effettua l'analisi semantica di una stringa del tipo predicato, articolo/preposizione complemento,
     * dove tramite dei riferimenti, l'azione di analisi semantica viene affidata al ParserMedio.
     * @param str
     * @param giocatore 
     */
    @Override
    public void analisiSemantica(String[] str, Giocatore giocatore) {
        int hash = Utils.calcolaChiave(str, giocatore.getStanzaCorrente().getCodice(), (String[] s, Integer stanza) ->{
            Integer verbo = transitivi.get(s[0]).ordinal() + 1;
            Integer articolo = (articoli.get(str[1]).ordinal() + 1) * 111;
            Integer oggetto = oggetti.get(str[2]).ordinal() + 1;
            ++stanza;
            return Integer.parseInt(new String(verbo.toString() + articolo.toString() + oggetto.toString() + stanza.toString()));
        });
        if(outputRef.containsKey(hash)){
            try {
                new ParserMedio(label, textArea, interfacciaCorrente).effettuaInterazione(outputRef.get(hash), giocatore);
            } catch (FileNotFoundException e) {
                System.err.println("Errore riga 106 ParserComplesso: " + e.getMessage());
            }
        }else{
            switch(transitivi.get(str[0])){
                case PRENDI:
                case GETTA:
                    if(isArticoloLA(str[1])){
                        String[] s = {str[0], str[2]};
                        try {
                            new ParserMedio(label, textArea, interfacciaCorrente).analisiSemantica(s, giocatore);
                        } catch (FileNotFoundException ex) {
                            System.err.println("Eccezione nella classe ParserComplesso e nel metodo analisiSemantica" + ex.getMessage());
                        }
                    }
                break;
                case OSSERVA:
                    if(articoli.get(str[1]) == ElementoFrase.Articolo.LA && oggetti.get(str[2]) == ElementoFrase.Oggetto.STANZA){
                        String[] s = {str[0]};
                        try {
                            new ParserSemplice(label, textArea).analisiSemantica(s, giocatore);
                        } catch (FileNotFoundException ex) {
                            System.err.println("Eccezione nella classe ParserComplesso e nel metodo analisiSemantica" + ex.getMessage());
                        }
                    }
                break;
                default:
                    Utils.stampa("Comando non consentito", this.textArea);
                break;
            }
        }
            
    }
    
    /**
    * Metodo che verifica la concordanza dell'articolo con l'oggetto.
    * Essendo tutti gli oggetti al femminile verifico solo l'articolo "la".
    * @param str
    * @return true se l'articolo è "la", false altrimenti.
    */
    private boolean isArticoloLA(String str){
        return(articoli.get(str) == ElementoFrase.Articolo.LA);
    }


}
