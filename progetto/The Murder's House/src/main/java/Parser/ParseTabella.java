package Parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Classe che preocessa file di input e output in modo preliminare
 * in modo da poter effettuare l'analisi sintattica e
 * semantica in altre classi.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class ParseTabella {
    /**
     * Metodo che processa un file in input e restituisce un'HashMap di chiave valore.
     * @param <K> String per i file di input, Integer per i file di output
     * @param <V> Enumerativo per i file di input, Integer per i file di output
     * @param nomeFile
     * @param funzioneChiave
     * @param funzioneValore
     * @return Map<K, V>
     * @throws FileNotFoundException 
     */
    public static <K, V> Map<K, V> processaFile(String nomeFile, Function<String, K> funzioneChiave, Function<String, V> funzioneValore) throws FileNotFoundException{
        Scanner in = new Scanner(new BufferedReader(new FileReader(nomeFile)));
        Map<K, V> tabella = new HashMap<>();
        while(in.hasNextLine()){
            String riga = in.nextLine();
            String[] elementi = riga.split("\\s*=\\s*", 2); //splitto solo la prima occorrenza di = e ho due elementi
            tabella.put(funzioneChiave.apply(elementi[0]), funzioneValore.apply(elementi[1]));
        }
        return tabella;
    }

}
