package Parser;

import Adventure.Giocatore;
import Adventure.Oggetto;
import Adventure.Stanza;
import Grafica.InterfacciaGrafica;
import Utility.Utils;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * Classe che rappresenta il funzionamento di un parser che effettua l'analisi sintattica e semantica
 * di una stringa media, ovvero: predicato e complemento.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class ParserMedio implements AnalisiTestuale<Boolean>{
    private static final String DESC_BAGNO = "> Questo dev'essere il bagno. E' uno spettacolo raccapricciante, c'è sporcizia ovunque.";
    private static final String DESC_GROTTA = "> Non so come sia possibile che l'assassino abbia scavato questa grotta, probabilmente dev'essersi formata negli anni in modo naturale.";
    private static final String DESC_RIPOSTIGLIO = "> Questo sembra essere un semplice ripostiglio.";
    private static final String RISPOSTA_TRAPPOLA_FALLITA = "HAI PERSO MISERRIMAMENTE!  RITENTA QUESTA AVVENTURA IN UNA NUOVA PARTITA";
    private static final String RISPOSTA_TRAPPOLA_SUPERATA = "HAI SUPERATO LA PROVA DELL'ASSASSINO!\nORA SARA' MEGLIO USCIRE DA QUESTA STANZA E NON METTERE PIU' PIEDE AL SUO INTERNO!!!";
    private static final String INDOVINELLO = "Viene rotto appena si pronuncia.\n\nCos'è?";
    private static final String RISPOSTA_POSITIVA_CHIAVE_CELLA = "CONGRATULAZIONI HAI APERTO LA SCATOLA!\n\nPUOI PRENDERE LA CHIAVE ALL'INTERNO.";
    private static final String PRENDI_CHIAVE = "Detective, guarda...il signor Esekiel ha fatto cadere una chiave, probabilmente è quella del portone di questo rudere.";
    private static final String RISPOSTA_NON_HAI_CHIAVE = "Probabilmente ci vuole una chiave per aprire questa cella...sarà nascosta da qualche parte.";
    private static final String HAI_VINTO = "CONGRATULAZIONI DETECTIVE, HAI VINTO!\n\nOra digita 'si' se vuoi continuare altrimenti digita 'no'";
    private static final String HAI_PERSO = "HAI PERSO!\nUna delle vittime del Signor Ezekiel Nostrom\nera imprigionata all'interno di una cella nascosta.\nPer colpa della tua incompetenza ora è morta...";
    private static final String HAI_PERSO_SENZA_CHIAVE = "HAI PERSO!\nNonostante abbia salvato la ragazza imprigionata,\nti sei scordato la chiave d'uscita...RITENTA";
    
    private static final int PRENDI_CHIAVE_STUDIO = 5146;
    private static final int PRENDI_COLLANA_BAGNO = 598;
    private static final int PRENDI_PISTOLA_GROTTA = 5614;
    private static final int PRENDI_CORDA_GROTTA = 54414;
    private static final int PRENDI_FOTO_RIPOSTIGLIO = 51015;
    private static final int PRENDI_CHIAVE_FINALE = 51417;
    private static final int APRI_FINESTRA = 281;
    private static final int ACCENDI_LUCE_SOGGIORNO = 6114; 
    private static final int SOLLEVA_PIEDE = 11325;
    private static final int PRENDI_SCELTA = 4195;
    private static final int ACCENDI_LUCE_TRAPPOLA = 6115;
    private static final int OSSERVA_SCRIGNO = 4376;
    private static final int APRI_BOTOLA = 217;
    private static final int SPOSTA_CASSA = 15579;
    private static final int APRI_SPECCHIO = 23310;
    private static final int APRI_CELLA = 2411;
    private static final int USA_CHIAVE_PRIGIONE = 71411;
    private static final int OSSERVA_TAPPETO = 44112;
    private static final int USA_CORDA = 74413;
    private static final int UCCIDI_ASSASSINO = 134717;
    private static final int RISPARMIA_ASSASSINO = 144717;
    private static final int USA_CHIAVE_USCITA = 7143;
    private static final int APRI_USCITA = 2613;
    
    private static final ImageIcon IMG_SALOTTO_ACCESO = new ImageIcon("ImmaginiStanze/SaloneSpettraleAcceso.png");
    private static final ImageIcon IMG_TRAPPOLA_ACCESA = new ImageIcon("ImmaginiStanze/TrappolaSpettraleSecondaFase.png");
    private static final ImageIcon IMG_TRAPPOLA_RISOLTA = new ImageIcon("ImmaginiStanze/TrappolaSpettraleTerzaFase.png");
    private static final ImageIcon IMG_ASSASSINO_UCCISO = new ImageIcon("ImmaginiStanze/AssassinoUcciso.png");
    private static final ImageIcon IMG_ASSASSINO_RISPARMIATO = new ImageIcon("ImmaginiStanze/AssassinoRisparmiato.png");
    private static final ImageIcon IMG_PROF = new ImageIcon("ImmaginiStanze/Prof.png");
    
    private final Map<String, ElementoFrase.Verbo> transitivi;
    private final Map<String, ElementoFrase.Oggetto> oggetti;
    private Map<Integer, String> output;
    
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
    public ParserMedio(JLabel label, JTextArea textArea, JFrame interfacciaCorrente) throws FileNotFoundException {
        this.transitivi = Tabella.getVerbiTransitivi();
        this.oggetti = Tabella.getOggetti();
        this.output = Tabella.getTabellaOut();
        this.label = label;
        this.textArea = textArea;
        this.interfacciaCorrente = interfacciaCorrente;
    }

    /**
     * Metodo che verifica se un predicato e un complemento sono contenuti nella stringa passata in input.
     * @param str
     * @return boolean in risposta alla verifica
     */
    @Override
    public Boolean analisiSintattica(String[] str) {
        return(transitivi.containsKey(str[0]) && oggetti.containsKey(str[1]));
    }

    /**
     * Metodo che effettua l'analisi semantica e switcha a seconda dei casi, codici Hash precisi di casi 
     * particolari che evitano l'avverarsi di incongruenze dei comandi.
     * Inoltre switcha i comandi legati all'azione di prendere e gettare un oggetto.
     * @param str
     * @param giocatore 
     */
    @Override
    public void analisiSemantica(String[] str, Giocatore giocatore) {
        int hash = Utils.calcolaChiave(str, giocatore.getStanzaCorrente().getCodice(), (String[] s, Integer stanza) -> {
            Integer verbo = transitivi.get(str[0]).ordinal() + 1;
            Integer oggetto = oggetti.get(str[1]).ordinal() + 1;
            ++stanza;
            return Integer.parseInt(new String(verbo.toString() + oggetto.toString() + stanza.toString()));
        });
        if(output.containsKey(hash)){
            try {
                effettuaInterazione(hash, giocatore);
            } catch (FileNotFoundException ex) {
                System.err.println("Eccezione nella classe ParserMedio e nel metodo analisiSemantica" + ex.getMessage());
            }
        } else {
            switch(transitivi.get(str[0])){
                case PRENDI:
                    if(giocatore.prendiOggetto(oggetti.get(str[1]))){
                        Utils.stampa("Hai preso l'oggetto", this.textArea);
                    }else
                        Utils.stampa("Non puoi prendere l'oggetto", this.textArea);
                break;
                case GETTA:
                    if(giocatore.lasciaOggetto(oggetti.get(str[1]))){
                        Utils.stampa("Hai lasciato l'oggetto", this.textArea);
                    }else
                        Utils.stampa("Non puoi lasciare l'oggetto", this.textArea);
                break;
                case OSSERVA:
                    if(oggetti.get(str[1]) == ElementoFrase.Oggetto.STANZA){
                        String[] s = {str[0]};
                        try {
                            new ParserSemplice(label, textArea).analisiSemantica(s, giocatore);
                        } catch (FileNotFoundException ex) {
                            System.err.println("Eccezione nella classe ParserMedio e nel metodo analisiSemantica" + ex.getMessage());
                        }
                    }
                break;
                default:
                    Utils.stampa("Non puoi farlo", this.textArea);
                break;
            }
        }
    }


    public void effettuaInterazione(int hash, Giocatore giocatore) throws FileNotFoundException{
        String s = output.get(hash);
            switch(hash){
                case PRENDI_CHIAVE_STUDIO: //prendi chiave
                    prendiChiaveStudio(giocatore, s);
                break;
                case PRENDI_COLLANA_BAGNO: //prendi collana
                    raccogliOggetto(giocatore, 100, ElementoFrase.Oggetto.COLLANA, DESC_BAGNO, "Hai preso la collana", "Hai già preso la collana da qui");
                break;
                case PRENDI_PISTOLA_GROTTA:
                    raccogliOggetto(giocatore, 200, ElementoFrase.Oggetto.PISTOLA, DESC_GROTTA, "Hai preso la pistola", "Hai già preso la pistola da qui");
                break;
                case PRENDI_CORDA_GROTTA:
                    raccogliOggetto(giocatore, 200, ElementoFrase.Oggetto.CORDA, DESC_GROTTA, "Hai preso la corda", "Hai già preso la corda da qui");
                break;
                case PRENDI_FOTO_RIPOSTIGLIO:
                    raccogliOggetto(giocatore, 100, ElementoFrase.Oggetto.FOTO, DESC_RIPOSTIGLIO, "Hai preso la foto", "Hai già preso la foto da qui");
                break;
                case PRENDI_CHIAVE_FINALE:
                    prendiChiaveFinale(giocatore, s);
                break;
                case APRI_FINESTRA:
                    aperturaStanze(giocatore, s);
                break;
                case ACCENDI_LUCE_SOGGIORNO:
                    accendiLuce(giocatore, s, IMG_SALOTTO_ACCESO);
                break;
                case SOLLEVA_PIEDE:
                    sollevaPiedeTrappola(giocatore, s);
                break;
                case PRENDI_SCELTA:
                    prendiSceltaTrappola(giocatore, s);
                break;
                case ACCENDI_LUCE_TRAPPOLA:
                    accendiLuce(giocatore, s, IMG_TRAPPOLA_ACCESA);
                break;
                case OSSERVA_SCRIGNO:
                    osservaScrignoStudio(giocatore, s);
                break;
                case APRI_BOTOLA:
                    aperturaStanze(giocatore, s);
                break;
                case SPOSTA_CASSA:
                    aperturaStanze(giocatore, s);
                break;
                case APRI_SPECCHIO:
                    aperturaStanze(giocatore, s);
                break;
                case APRI_CELLA:
                case USA_CHIAVE_PRIGIONE:
                    apriCellaPrigione(giocatore, s);
                break;
                case OSSERVA_TAPPETO:
                    osservaTappeto(giocatore, s);
                break;
                case USA_CORDA:
                    usaCordaFossato(giocatore, s);
                break;
                case UCCIDI_ASSASSINO:
                    uccidiAssassino(giocatore, s);
                break;
                case RISPARMIA_ASSASSINO:
                    risparmiaAssassino(giocatore, s);
                break;
                case USA_CHIAVE_USCITA:
                case APRI_USCITA:
                    apriUscitaIngresso(giocatore, s);
                break;
                default:
                    Utils.stampa(s, this.textArea);
                break;
            }
    }

    /**
     * Metodo per generalizzare la raccolta degli oggetti
     * @param giocatore
     * @param punteggio
     * @param oggetto
     * @param descrizione
     * @param preso
     * @param nonPreso
     */
    private void raccogliOggetto(Giocatore giocatore, int punteggio, ElementoFrase.Oggetto oggetto, String descrizione, String preso, String nonPreso){
        giocatore.incrementaPunteggio(punteggio);
        if(giocatore.prendiOggetto(oggetto)) {
            for(Oggetto oggettoInInventario : giocatore.getInventarioGiocatore().getInventario()) {
                if(oggettoInInventario.getNome().equals("Chiave dell'ingresso")) {
                    return;
                }
            }
            
            Utils.stampa(preso, this.textArea);
        } else
            Utils.stampa(nonPreso, this.textArea);
        if(descrizione != null)
            giocatore.getStanzaCorrente().setDescrizione(descrizione);
    }
    
    /**
     * Metodo che apre una porta, finestra, cella.
     * @param giocatore
     * @param s 
     */
    private void aperturaStanze(Giocatore giocatore, String s) {
        for(Stanza stanzaAdiacente : giocatore.getMappaGiocatore().stanzeAdiacenti(giocatore.getStanzaCorrente())) {
            if(!stanzaAdiacente.getNome().equals(giocatore.getStanzaCorrente().getNome())) {
                if (stanzaAdiacente.isApribile() == false) {
                    stanzaAdiacente.setIsApribile(true);
                    System.out.println("stanza aperta");
                    Utils.stampa(s, this.textArea);
                }
            }
        }
    }    


    /**
     * Metodo che permette di accendere la luce di una stanza cambiando l'immagine della stanza e della label
     * @param giocatore
     * @param s
     * @param img
     */
    private void accendiLuce(Giocatore giocatore, String s, ImageIcon img) {
        giocatore.getStanzaCorrente().setIcon(img);
        this.label.setIcon(img);
        Utils.stampa(s, this.textArea);
    }


    /**
     * Metodo che permette di raccogliere la chiave della cella nello studio
     * @param giocatore
     * @param s
     */
    private void prendiChiaveStudio(Giocatore giocatore, String s) {
        for(Oggetto oggetto : giocatore.getStanzaCorrente().getOggettiStanza()) {
            if(oggetto.isPrendibile() == true && oggetto.getNome().equals("Chiave della cella") && oggetto.isPreso() == false) {
                raccogliOggetto(giocatore, 200, ElementoFrase.Oggetto.CHIAVE, null, "\nHai preso la chiave", "Non puoi prendere la chiave");
                oggetto.setPrendibile(false);
            }
        }
    }

    /**
     * Metodo che permette di raccogliere la chave che permette di aprire la porta di ingresso
     * per poter uscire dal gioco.
     * @param giocatore
     * @param s
     */
    private void prendiChiaveFinale(Giocatore giocatore, String s) {
        raccogliOggetto(giocatore, 200, ElementoFrase.Oggetto.CHIAVE, null, "Hai preso la chiave che apre la porta di ingresso nell'atrio", "Hai già preso la chiave da qui");
        for(Stanza stanzaAdiacente : giocatore.getMappaGiocatore().stanzeAdiacenti(giocatore.getStanzaCorrente())) {
            if(stanzaAdiacente.getNome().equals("Mattatoio")) {
                Utils.stampa(s, this.textArea);
                giocatore.setStanzaCorrente(stanzaAdiacente);
                label.setIcon(stanzaAdiacente.getIcon());
            }
        }
    }

    /**
     * Metodo che fa uscire il giocatore dal gioco una volta eseguita l'azione nella stanza trappola.
     * @param giocatore
     * @param s
     */
    private void sollevaPiedeTrappola(Giocatore giocatore, String s) {
        Utils.stampa(s, this.textArea);
        JOptionPane.showMessageDialog(this.interfacciaCorrente, "HAI PERSO MISERRIMAMENTE!\n\nRITENTA QUESTA AVVENTURA IN UNA NUOVA PARTITA");
        this.interfacciaCorrente.dispose();
        try {
            new InterfacciaGrafica().setVisible(true);
        } catch (FileNotFoundException e) {
            System.err.println("Eccezione nella classe ParserMedio e nel metodo sollevaPiedeTrappola: " + e.getMessage());
        }
    }

    /**
     * Metodo che permette di effettuare una scelta secondo la quale sarà possibile o uscire
     * dalla trappola oppure perdere.
     * @param giocatore
     * @param s
     */
    private void prendiSceltaTrappola(Giocatore giocatore, String s){
        giocatore.getStanzaCorrente().setIsApribile(false);
        Utils.stampa(s, this.textArea);
        String risposta = JOptionPane.showInputDialog(this.interfacciaCorrente, "Inserisci PRIMA oppure SECONDA, in base alla tua scelta: ");
        risposta = risposta.toLowerCase();
        if(risposta.equals("seconda")) {
            JOptionPane.showMessageDialog(this.interfacciaCorrente, RISPOSTA_TRAPPOLA_FALLITA);
            this.interfacciaCorrente.dispose();
            try {
                new InterfacciaGrafica().setVisible(true);
            } catch (FileNotFoundException ex) {
                System.err.println("Eccezione nella classe ParserMedio e nel metodo prendiSceltaTrappola: " + ex.getMessage());
                Logger.getLogger(ParserMedio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            giocatore.getStanzaCorrente().setIcon(IMG_TRAPPOLA_RISOLTA);
            this.label.setIcon(IMG_TRAPPOLA_RISOLTA);
            JOptionPane.showMessageDialog(this.interfacciaCorrente, RISPOSTA_TRAPPOLA_SUPERATA);
            for(Stanza stanzaAdiacente : giocatore.getMappaGiocatore().stanzeAdiacenti(giocatore.getStanzaCorrente())) {    
                if(!stanzaAdiacente.getNome().equals(giocatore.getStanzaCorrente().getNome())) {
                    if (stanzaAdiacente.isApribile() == false) {
                        stanzaAdiacente.setIsApribile(true);
                        giocatore.getStanzaCorrente().setIsApribile(false);
                        giocatore.setStanzaCorrente(stanzaAdiacente);
                        Utils.stampa(stanzaAdiacente.getDescrizione(), this.textArea);
                        label.setIcon(stanzaAdiacente.getIcon());
                    }
                }
            }
        }
    }
    
    /**
     * Metodo che permette di osservare lo scrigno/la scatola nello studio.
     * Lo scrigno contiene la chiave della cella. 
     * Per poterla prendere, il giocatore dovrà rispondere ad un indovinello.
     * @param giocatore
     * @param s
     */
    private void osservaScrignoStudio(Giocatore giocatore, String s) {
        for(Oggetto oggettoInInventario : giocatore.getInventarioGiocatore().getInventario()) {
            if(oggettoInInventario.getNome().equals("Chiave della cella")) {
                Utils.stampa("\nHai già preso la chiave.", this.textArea);
                return;
            }
        }
        this.textArea.append("\n");
        Utils.stampa(s, this.textArea);
        String rispostaIndovinello = JOptionPane.showInputDialog(this.interfacciaCorrente, INDOVINELLO);
        rispostaIndovinello.toLowerCase();
        if(rispostaIndovinello.equals("silenzio") || rispostaIndovinello.equals("il silenzio")) {
            for(Oggetto oggetto : giocatore.getStanzaCorrente().getOggettiStanza()) {
                if(oggetto.getNome().equals("Chiave della cella")) {
                    oggetto.setPrendibile(true);
                    JOptionPane.showMessageDialog(this.interfacciaCorrente, RISPOSTA_POSITIVA_CHIAVE_CELLA);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this.interfacciaCorrente, "RITENTA");
        }
    }
    
    
    /**
     * Metodo che permette di aprire la cella in cui è imprigionata la ragazza da salvare.
     * @param giocatore
     * @param s
     */
    private void apriCellaPrigione(Giocatore giocatore, String s) {
        boolean trovato = false;
        for(Oggetto oggetto : giocatore.getInventarioGiocatore().getInventario()) {
            if(oggetto.getNome().equals("Chiave della cella")) {
                giocatore.setIsRagazzaSalvata(true); 
                trovato = true;
                Utils.stampa(s, this.textArea);
                break;
            }
        }
        if(!trovato) {
            Utils.stampa(RISPOSTA_NON_HAI_CHIAVE, this.textArea);
        }
    }
    
    /**
     * Metodo che permette di far scattare una trappola una volta osservato il tappeto.
     * @param giocatore
     * @param s
     */
    private void osservaTappeto(Giocatore giocatore, String s) {
        giocatore.getStanzaCorrente().setIsApribile(false);
        for(Stanza stanzaAdiacente : giocatore.getMappaGiocatore().stanzeAdiacenti(giocatore.getStanzaCorrente())) {
            if(stanzaAdiacente.getNome().equals("Fossato")) {
                giocatore.setStanzaCorrente(stanzaAdiacente);
                Utils.stampa(s, this.textArea);
                label.setIcon(stanzaAdiacente.getIcon());
            }
        }
    }

    /**
     * Metodo che permette di usare la corda per attraversare il fossato e uscire dalla trappola.
     * @param giocatore
     * @param s
     */
    private void usaCordaFossato(Giocatore giocatore, String s) {
        giocatore.getStanzaCorrente().setIsApribile(false);
        for(Oggetto oggetto : giocatore.getInventarioGiocatore().getInventario()) {
            if(oggetto.getNome().equals("Corda")) {
                Utils.stampa(s, this.textArea);
                for(Stanza stanzaAdiacente : giocatore.getMappaGiocatore().stanzeAdiacenti(giocatore.getStanzaCorrente())) {
                    if(stanzaAdiacente.getNome().equals("Tappeto")) {
                        giocatore.setStanzaCorrente(stanzaAdiacente);
                        giocatore.getStanzaCorrente().setIsApribile(true);
                        label.setIcon(stanzaAdiacente.getIcon());
                    }
                }
            }
        }
    }

    /**
     * Metodo che permette di usare la pistola per uccidere l'assassino.
     * @param giocatore
     * @param s
     */
    private void uccidiAssassino(Giocatore giocatore, String s) {
        for(Oggetto oggetto : giocatore.getInventarioGiocatore().getInventario()) {
            if(oggetto.getNome().equals("Pistola")) {
                Utils.stampa(s, this.textArea);
                giocatore.getStanzaCorrente().setIcon(IMG_ASSASSINO_UCCISO);
                this.label.setIcon(IMG_ASSASSINO_UCCISO);
                giocatore.getStanzaCorrente().setIsApribile(false);
            }
        }
    }

    /**
     * Metodo che permette di risparmiare l'assassino.
     * @param giocatore
     * @param s
     */
    private void risparmiaAssassino(Giocatore giocatore, String s) {
        giocatore.getStanzaCorrente().setIcon(IMG_ASSASSINO_RISPARMIATO);
        Utils.stampa(s, this.textArea);
        Utils.stampa(PRENDI_CHIAVE, this.textArea);
        this.label.setIcon(IMG_ASSASSINO_RISPARMIATO);
        giocatore.getStanzaCorrente().setIsApribile(false);
    }

    /**
     * Metodo che permette di uscire dal gioco, mostrando l'esito della partita.
     * @param giocatore
     * @param s
     */
    private void apriUscitaIngresso(Giocatore giocatore, String s) throws FileNotFoundException {
        for(Oggetto oggetto : giocatore.getInventarioGiocatore().getInventario()) {
            if(oggetto.getNome().equals("Chiave dell'ingresso") && giocatore.isIsRagazzaSalvata() == true) {
                giocatore.getStanzaCorrente().setIcon(IMG_PROF);
                this.label.setIcon(IMG_PROF);
                Integer punteggioNum = giocatore.getPunteggio();
                String punteggio = punteggioNum.toString();
                this.textArea.append("Il tuo punteggio è di: " + punteggio);
                String rispostaFineGioco = JOptionPane.showInputDialog(this.interfacciaCorrente, HAI_VINTO);
                rispostaFineGioco = rispostaFineGioco.toLowerCase();
                
                if(rispostaFineGioco.equals("si")) {
                    this.interfacciaCorrente.dispose();
                    
                    new InterfacciaGrafica().setVisible(true);
                } else {
                    this.interfacciaCorrente.dispose();
                }
            } else if(oggetto.getNome().equals("Chiave dell'ingresso")) {
                JOptionPane.showMessageDialog(this.interfacciaCorrente, HAI_PERSO);
                this.interfacciaCorrente.dispose();
            }
        }
    }
}
