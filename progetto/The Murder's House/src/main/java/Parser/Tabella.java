package Parser;

import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Classe che raggruppa le diverse tabelle che compongono
 * il dizionario utilizzabile nel programma.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class Tabella {
    private static final String FILE_ARTICOLI = "fileDiTesto/Articoli.txt";
    private static final String FILE_TRANSITIVI = "fileDiTesto/Transitivi.txt";
    private static final String FILE_INTRANSITIVI = "fileDiTesto/Intransitivi.txt";
    private static final String FILE_OGGETTI = "fileDiTesto/Oggetti.txt";
    private static final String FILE_POSIZIONI = "fileDiTesto/Posizioni.txt";
    private static final String FILE_PREPOSIZIONI = "fileDiTesto/Preposizioni.txt";
    private static final String FILE_OUTPUT = "fileDiTesto/Output.txt";
    private static final String FILE_CHIAVI_RIFERIMENTO = "fileDiTesto/ChiaviRiferimento.txt";

    /**
     * Metodo che restituisce una tabella formata da chiave (rappresentata da un intero) - valore (rappresentato da una stringa)
     * formata dalle diverse posizioni che possono essere utilizzate.
     * @return una tabella
     * @throws FileNotFoundException 
     */
    public static Map<String, ElementoFrase.Posizione> getPosizioni() throws FileNotFoundException {
         return ParseTabella.processaFile(FILE_POSIZIONI, (String chiave) -> chiave, (String valore) -> ElementoFrase.Posizione.valueOf(valore));
    }

    /**
     * Metodo che restituisce una tabella formata da chiave (rappresentata da un intero) - valore (rappresentato da una stringa)
     * formata dai diversi articoli che possono essere utilizzati.
     * @return una tabella
     * @throws FileNotFoundException 
     */
    public static Map<String, ElementoFrase.Articolo> getArticoli() throws FileNotFoundException{
        return ParseTabella.processaFile(FILE_ARTICOLI, (String chiave) -> chiave, (String valore) -> ElementoFrase.Articolo.valueOf(valore));
    }

    /**
     * Metodo che restituisce una tabella formata da chiave (rappresentata da un intero) - valore (rappresentato da una stringa)
     * formata dai diversi verbi intransitivi che possono essere utilizzati.
     * @return una tabella
     * @throws FileNotFoundException 
     */
    public static Map<String, ElementoFrase.Verbo> getVerbiIntransitivi() throws FileNotFoundException{
        return ParseTabella.processaFile(FILE_INTRANSITIVI, (String chiave) -> chiave, (String valore) -> ElementoFrase.Verbo.valueOf(valore));
    }

    /**
     * Metodo che restituisce una tabella formata da chiave (rappresentata da un intero) - valore (rappresentato da una stringa)
     * formata dai diversi verbi transitivi che possono essere utilizzati.
     * @return una tabella
     * @throws FileNotFoundException 
     */
    public static Map<String, ElementoFrase.Verbo> getVerbiTransitivi() throws FileNotFoundException{
        return ParseTabella.processaFile(FILE_TRANSITIVI, (String chiave) -> chiave, (String valore) -> ElementoFrase.Verbo.valueOf(valore));
    }

    /**
     * Metodo che restituisce una tabella formata da chiave (rappresentata da un intero) - valore (rappresentato da una stringa)
     * formata dai diversi complementi che possono essere utilizzati.
     * @return una tabella
     * @throws FileNotFoundException 
     */
    public static Map<String, ElementoFrase.Oggetto> getOggetti() throws FileNotFoundException{
        return ParseTabella.processaFile(FILE_OGGETTI, (String chiave) -> chiave, (String valore) -> ElementoFrase.Oggetto.valueOf(valore));
    }

    /**
     * Metodo che restituisce una tabella formata da chiave (rappresentata da un intero) - valore (rappresentato da una stringa)
     * formata dalle diverse preposizioni che possono essere utilizzate.
     * @return una tabella
     * @throws FileNotFoundException 
     */
    public static Map<String, ElementoFrase.Preposizione> getPreposizioni() throws FileNotFoundException{
        return ParseTabella.processaFile(FILE_PREPOSIZIONI, (String chiave) -> chiave, (String valore) -> ElementoFrase.Preposizione.valueOf(valore));
    }


    /**
     * Metodo che restituisce una tabella formata da chiave (rappresentata da un intero) - valore (rappresentato da una stringa),
     * formata dai diversi output che possono essere stampati.
     * @return una tabella
     * @throws FileNotFoundException 
     */
    public static Map<Integer, String> getTabellaOut() throws FileNotFoundException{
        return ParseTabella.processaFile(FILE_OUTPUT, (String chiave) -> Integer.parseInt(chiave), (String valore) -> valore);
    }

    /**
     * Metodo che restituisce una tabella formata da chiave (rappresentata da un intero) e
     * valore (intero) che rappresenta il riferimento alla tabella di output usata in parser medio
     * @return una tabella
     * @throws FileNotFoundException 
     */
    public static Map<Integer, Integer> getTabellaChiaviRiferimento() throws FileNotFoundException{
        return ParseTabella.processaFile(FILE_CHIAVI_RIFERIMENTO, (String chiave) -> Integer.parseInt(chiave), (String valore) -> Integer.parseInt(valore));
    }

}
