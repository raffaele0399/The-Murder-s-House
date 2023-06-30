package Parser;

import Adventure.Giocatore;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.io.FileNotFoundException;
import javax.swing.JFrame;

/**
 * Classe che rappresenta un giocatore.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class Parser {

    private static final short NON_VALIDO = -1;
    private static final short TRANSITIVO = 0;
    private static final short INTRANSITIVO = 1;

    private boolean accettato;
    private short transitivo;
    private Giocatore giocatore;
    private String[] strings;
    private JLabel label;
    private JTextArea textArea;
    private JFrame interfacciaCorrente;

    /**
     * Costruttore che prende in input una serie di parametri i cui valori
     * vengono associati agli attributi della classe.
     * @param giocatore
     * @param label
     * @param textArea 
     */
    public Parser(Giocatore giocatore, JLabel label, JTextArea textArea, JFrame interfacciaCorrente){
        this.accettato = false;
        this.giocatore = giocatore;
        this.transitivo = NON_VALIDO;
        this.label = label;
        this.textArea = textArea;
        this.interfacciaCorrente = interfacciaCorrente;
    }

    /**
     * Setter dell'attributo accettato.
     * @param accettato 
     */
    public void setAccettato(boolean accettato){
        this.accettato = accettato;
    }

    /**
     * Getter dell'attributo accettato.
     * @return accettato
     */
    public boolean isAccettato(){
        return accettato;
    }

    /**
     * Setter dell'attributo transitivo.
     * @param transitivo 
     */
    public void setTransitivo(short transitivo){
        this.transitivo = transitivo;
    }

    /**
     * Getter dell'attributo transitivo.
     * @return transitivo
     */
    public short getTransitivo(){
        return transitivo;
    }

    /**
     * Setter dell'attributo giocaotre.
     * @param giocatore 
     */
    public void setGiocatore(Giocatore giocatore){
        this.giocatore = giocatore;
    }

    /**
     * Getter dell'attributo giocatore.
     * @return giocatore
     */
    public Giocatore getGiocatore(){
        return giocatore;
    }

    /**
     * Metodo che a seconda del caso affida l'analisi sintattica e semantica ad un'istanza di una delle tre classi Parser.
     * @param str
     * @throws FileNotFoundException 
     */
    public void parse(String str) throws FileNotFoundException{
        str = str.toLowerCase();
        
        if(isComandoMod(str) && !str.isEmpty()) {
            new Trucchi(giocatore, str, textArea).checkComando();
        }
        
        str = this.spazioDopoApostrofo(str);
        strings = str.split("\\s+");
        switch(strings.length){
            case 1:
                this.setAccettato(new ParserSemplice(label, textArea).analisiSintattica(strings));
                if(this.isAccettato())
                    new ParserSemplice(label, textArea).analisiSemantica(strings, giocatore);
            break;
            case 2:
                this.setAccettato(new ParserMedio(label, textArea, interfacciaCorrente).analisiSintattica(strings));
                if(this.isAccettato())
                    new ParserMedio(label, textArea, interfacciaCorrente).analisiSemantica(strings, giocatore);
                break;
            case 3: 
                this.setTransitivo(new ParserComplesso(label, textArea, interfacciaCorrente).analisiSintattica(strings));
                switch(this.getTransitivo()){
                    case TRANSITIVO:
                        this.setAccettato(true);
                        new ParserComplesso(label, textArea, interfacciaCorrente).analisiSemantica(strings, giocatore);
                        break;
                    case INTRANSITIVO:
                        String[] s = {strings[2]};
                        new ParserSemplice(label, textArea).analisiSemantica(s, giocatore);
                        break;
                    default:
                        this.setAccettato(false);
                        break;
                }
                break;
            default:
                this.setAccettato(false);
                this.setTransitivo(NON_VALIDO);
                break;
        }
    }

    /**
     * Metodo che sostituisce un apostrofo all'interno di una stringa e lo elimina.
     * @param str
     * @return una stringa senza apostrofo
     */
    private String spazioDopoApostrofo(String str){
        String s = str.replaceAll("\u0027", "\u0027 "); //aggiungo uno spazio dopo l'apostrofo
        return s;
    }

    /**
     * Metodo che verifica se un comando fa parte dei trucchi del gioco.
     * @param str
     * @return true se il primo carattere della stringa è $, false altrimenti
     */
    private boolean isComandoMod(String str) {
        if(str != null && !str.isEmpty()) return(str.charAt(0) == '$');
        else return false;
    }
}
