package StrutturaDati;

/**
 * Classe che rappresenta le possibili eccezioni che potrebbero un oggetto di tipo Grafo.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class GrafoException extends Exception{
    private static final String NODO_INESISTENTE = "Nodo inesistente.";
    private static final String NODO_ESISTENTE = "Nodo già esistente.";
    private static final String ARCO_INESISTENTE = "Arco inesistente.";
    private static final String ARCO_ESISTENTE = "Arco già esistente.";
    private static final String ERRORE_SCRITTURA_NODO = "Errore nella scrittura del nodo:\nIl nodo già esiste e ha lo stesso valore.";
    

    /**
     * Costruttore della classe che prende in input una stringa
     * e istanzia un oggetto di questa classe.
     * @param message 
     */
    public GrafoException(String message) {
        super(message);
    }

    /**
     * Getter della costante NODO_INESISTENTE.
     * @return un messaggio di errore
     */
    public static String getNODO_INESISTENTE() {
        return NODO_INESISTENTE;
    }

    /**
     * Getter della costante ARCO_INESISTENTE.
     * @return un messaggio di errore
     */
    public static String getARCO_INESISTENTE() {
        return ARCO_INESISTENTE;
    }

    /**
     * Getter della costante ARCO_ESISTENTE.
     * @return un messaggio di errore
     */
    public static String getARCO_ESISTENTE() {
        return ARCO_ESISTENTE;
    }

    /**
     * Getter della costante ERRORE_SCRITTURA_NODO.
     * @return un messaggio di errore
     */
    public static String getERRORE_SCRITTURA_NODO() {
        return ERRORE_SCRITTURA_NODO;
    }

    /**
     * Getter della costante NODO_ESISTENTE.
     * @return un messaggio di errore
     */
    public static String getNODO_ESISTENTE() {
        return NODO_ESISTENTE;
    }

    /**
     * Getter della stringa passata in input al costruttore.
     * @return 
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
