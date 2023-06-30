package Adventure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 * Classe che rappresenta una stanza.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class Stanza implements Serializable{
    private String nome;
    private String descrizione;
    private int codice;
    private boolean visitata = false;
    private boolean apribile = true;

    private List<Oggetto> oggettiStanza = new ArrayList<>();
    private ImageIcon icon;

    /**
     * Costruttore di default.
     */
    public Stanza(){
        //do nothing
    }
    
    /**
     * Costruttore che associa i parametri passati in input agli attributi della classe.
     * @param nome
     * @param codice
     * @param descrizione
     */
    public Stanza(String nome, int codice, String descrizione) {
        this.nome = nome;
        this.codice = codice;
        this.descrizione = descrizione;
    }

    /**
     * Costruttore che associa i parametri passati in input agli attributi della classe.
     * @param nome
     * @param codice
     * @param descrizione
     * @param icon
     */
    public Stanza(String nome, int codice, String descrizione, ImageIcon icon) {
        this.nome = nome;
        this.codice = codice;
        this.descrizione = descrizione;
        this.icon = icon;
    }
    
    /**
     * Costruttore che associa i parametri passati in input agli attributi della classe.
     * @param nome
     * @param codice
     * @param descrizione
     * @param icon
     * @param apribile
     */
    public Stanza(String nome, int codice, String descrizione, ImageIcon icon, boolean apribile) {
        this.nome = nome;
        this.codice = codice;
        this.descrizione = descrizione;
        this.icon = icon;
        this.apribile = apribile;
    }
    
    /**
     * Costruttore che associa i parametri passati in input agli attributi della classe,
     * differentemente dal costruttore sopra, è aggiunto anche l'attributo apribile.
     * @param nome
     * @param codice
     * @param descrizione
     * @param oggettiStanza
     * @param icon 
     */
    public Stanza(String nome, int codice, String descrizione, List<Oggetto> oggettiStanza, ImageIcon icon) {
        this.nome = nome;
        this.codice = codice;
        this.descrizione = descrizione;
        this.oggettiStanza = oggettiStanza;
        this.icon = icon;
    }
    
    /**
     * Costruttore che associa i parametri passati in input agli attributi della classe,
     * differentemente dal costruttore sopra, è aggiunto anche l'attributo apribile.
     * @param nome
     * @param codice
     * @param descrizione
     * @param oggettiStanza
     * @param icon 
     * @param apribile
     */
    public Stanza(String nome, int codice, String descrizione, List<Oggetto> oggettiStanza, ImageIcon icon, boolean apribile) {
        this.nome = nome;
        this.codice = codice;
        this.descrizione = descrizione;
        this.oggettiStanza = oggettiStanza;
        this.icon = icon;
        this.apribile = apribile;
    }

    /**
     * Setter dell'attributo codice.
     * @param codice 
     */
    public void setCodice(int codice) {
        this.codice = codice;
    }

    /**
     * Getter dell'attributo codice.
     * @return codice
     */
    public int getCodice() {
        return codice;
    }

    /**
     * Setter dell'attributo nome.
     * @param nome 
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Getter dell'attributo nome.
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Setter dell'attributo descrizione.
     * @param descrizione 
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Getter dell'attributo descrizione.
     * @return descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Setter dell'attributo visitata.
     * @param visitata 
     */
    public void setVisitata(boolean visitata) {
        this.visitata = visitata;
    }

    /**
     * Getter dell'attributo visitata.
     * @return visitata
     */
    public boolean isVisitata() {
        return visitata;
    }

    /**
     * Setter dell'attributo oggettiStanza.
     * @param oggettiStanza 
     */
    public void setOggettiStanza(List<Oggetto> oggettiStanza) {
        this.oggettiStanza = oggettiStanza;
    }

    /**
     * Getter dell'attributo oggettiStanza.
     * @return oggettiStanza
     */
    public List<Oggetto> getOggettiStanza() {
        return oggettiStanza;
    }

    /**
     * Setter dell'attributo apribile.
     * @param apribile 
     */
    public void setIsApribile(boolean apribile) {
        this.apribile = apribile;
    }

    /**
     * Getter dell'attributo apribile.
     * @return apribile
     */
    public boolean isApribile() {
        return apribile;
    }

    /**
     * Setter dell'attributo icona.
     * @param icon 
     */
    public void setIcon(ImageIcon icon){
        this.icon = icon;
    }

    /**
     * Getter dell'attributo icona.
     * @return icona
     */
    public ImageIcon getIcon(){
        return icon;
    }
    
    /**
     * Metodo che confronta due oggetti di tipo stanza.
     * @param obj
     * @return boolean in base all'uguaglianza di due oggetti di tipo Stanza
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stanza other = (Stanza) obj;
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        if (this.codice != other.codice) {
            return false;
        }
        if ((this.descrizione == null) ? (other.descrizione != null) : !this.descrizione.equals(other.descrizione)) {
            return false;
        }
        if (this.oggettiStanza != other.oggettiStanza && (this.oggettiStanza == null || !this.oggettiStanza.equals(other.oggettiStanza))) {
            return false;
        }
        if (this.visitata != other.visitata) {
            return false;
        }
        return true;
    }

}
