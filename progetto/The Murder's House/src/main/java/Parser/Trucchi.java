package Parser;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import Adventure.Giocatore;
import Adventure.Stanza;

/**
 * Classe che rappresenta la possibilità di utilizzare trucchi all'interno di una partita.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class Trucchi {
    private Giocatore giocatore;
    private String comando;
    private JTextArea textArea;
    private static final int PUNTEGGIO_MASSIMO = 1000;

    public Trucchi(Giocatore giocatore, String comando, JTextArea textArea) {
        this.giocatore = giocatore;
        this.comando = comando;
        this.textArea = textArea;
    }

    public void checkComando(){
        switch(this.comando){
            case "$apri stanza":
                apriStanza();
            break;
            case "$punteggio massimo":
                setPunteggioMassimo();
            break;
            default:
                textArea.append("Comando non valido\n");
            break;
        }
    }

    private void apriStanza() {
        try {
            Field mappa = giocatore.getClass().getDeclaredField("mappaGiocatore");
            mappa.setAccessible(true);
            Object mappaGiocatore = mappa.get(giocatore);
        
            Field stanzaCorrente = giocatore.getClass().getDeclaredField("stanzaCorrente");
            stanzaCorrente.setAccessible(true);
            Object stanzaCorrenteObj = stanzaCorrente.get(giocatore);
        
            Class<?> classeMappa = mappaGiocatore.getClass();
            Class<?> classeStanza = stanzaCorrente.getType();
        
            Method ottieniStanzeAdiacenti = classeMappa.getDeclaredMethod("stanzeAdiacenti", classeStanza);
            ottieniStanzeAdiacenti.setAccessible(true);
        
            List<Stanza> adiacenti = (List<Stanza>) ottieniStanzeAdiacenti.invoke(mappaGiocatore, stanzaCorrenteObj);
            for (Stanza s : adiacenti) {
                if (!s.isApribile()) {
                    Field apribile = s.getClass().getDeclaredField("apribile");
                    apribile.setAccessible(true);
                    apribile.setBoolean(s, true);
                    textArea.append("Stanza aperta\n");
                }
            }
        } catch (NoSuchFieldException | SecurityException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            System.err.println("Eccezione nella classe Trucchi e nel metodo apriStanza: " + e.getMessage());
        }
    }


    private void setPunteggioMassimo(){
        Class<?> classeGiocatore = giocatore.getClass();
        Field punteggio;
        try {
            punteggio = classeGiocatore.getDeclaredField("punteggio");
            punteggio.setAccessible(true);
            punteggio.set(giocatore, PUNTEGGIO_MASSIMO);
            textArea.append("Punteggio massimo impostato: 1000\n");
            System.out.println(this.giocatore.getPunteggio());   //verifico nella stampa poi lo tolgo
        } catch (NoSuchFieldException | SecurityException  | IllegalArgumentException | IllegalAccessException e) {
            System.err.println("Eccezione nella classe Trucchi e nel metodo setPunteggioMassimo: " + e.getMessage());
        }
    }
    
}
