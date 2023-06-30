package Adventure;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Classe che ricerca e stampa del contenuto direttamente da una pagina Wikipedia.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class WikiAdventure {
    private static final String REGEX_HTML = "<[^>]+>";
    private static final String WIKI_API_URL = "https://it.wikipedia.org/w/api.php";

    /**
     * Metodo che prende un preciso testo da una pagina Wikipedia.
     * @param titolo
     * @return la descrizione sotto forma di stringa.
     */
    public static String getWikiPagina(String titolo){
        Client client = ClientBuilder.newClient(); //action?prop=extracts&format=json&titles=Avventura_testuale&exintro=&explaintext=

        String json = client.target(WIKI_API_URL)
                .queryParam("action", "query")
                .queryParam("prop", "extracts")
                .queryParam("format", "json")
                .queryParam("titles", titolo)
                .queryParam("exintro", "")
                .queryParam("explaintext", "")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        JsonObject pages = jsonObject.getAsJsonObject("query").getAsJsonObject("pages");

        String pagina = new String();

        for (String key : pages.keySet()) {
            pagina = pages.getAsJsonObject(key).get("extract").getAsString();
        }

        return pagina.replaceAll(REGEX_HTML, "");
    }
    
}
    