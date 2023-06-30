package Parser;

import Adventure.Giocatore;

/**
 * Interfaccia con i metodi per la gestione dell'analisi sintattica e semantica.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 * @param <T>
 */
public interface AnalisiTestuale <T> {
    
    T analisiSintattica(String[] str);
    void analisiSemantica(String[] str, Giocatore giocatore);
}
