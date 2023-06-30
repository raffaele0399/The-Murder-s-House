package Adventure;

import java.io.Serializable;

import javax.swing.ImageIcon;

import Parser.ElementoFrase;

/**
 * Classe che rappresenta un oggetto.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class Oggetto implements Serializable{
    private ElementoFrase.Oggetto id;
    private String nome;
    private String descrizione;
    private boolean isPreso;
    private boolean isGettato;
    private boolean prendibile;
    private boolean gettabile = true;
    private ImageIcon icona;
    

    /**
     * Costruttore di default.
     */
    public Oggetto() {
        //do nothing
    }


    /**
     * Costruttore che associa i parametri passati in input con gli attributi della classe.
     * @param id
     * @param nome
     * @param descrizione
     * @param icona 
     */
    public Oggetto(ElementoFrase.Oggetto id, String nome, String descrizione, ImageIcon icona) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.isPreso = false;
        this.isGettato = false;
        this.icona = icona;
    }

    /**
     * Costruttore che associa i parametri passati in input con gli attributi della classe.
     * @param id
     * @param nome
     * @param descrizione
     * @param icona 
     */
    public Oggetto(ElementoFrase.Oggetto id, String nome, String descrizione, ImageIcon icona, boolean prendibile) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.isPreso = false;
        this.isGettato = false;
        this.icona = icona;
        this.prendibile = prendibile;
    }
    
    /**
     * Costruttore che associa i parametri passati in input con gli attributi della classe.
     * @param id
     * @param nome
     * @param descrizione
     * @param icona
     * @param prendibile
     * @param gettabile
     */
    public Oggetto(ElementoFrase.Oggetto id, String nome, String descrizione, ImageIcon icona, boolean prendibile, boolean gettabile){
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.isPreso = false;
        this.isGettato = false;
        this.icona = icona;
        this.prendibile = prendibile;
        this.gettabile = gettabile;
    }

    /**
     * Setter dell'indentificativo dell'oggetto.
     * @param id 
     */
    public void setId(ElementoFrase.Oggetto id) {
        this.id = id;
    }

    /**
     * Getter dell'indentificativo dell'oggetto.
     * @return identificativo
     */
    public ElementoFrase.Oggetto getId() {
        return id;
    }

    /**
     * Setter dell'attributo isGettato.
     * @param gettato 
     */
    public void setGettatto(boolean gettato) {
        isGettato = gettato;
    }

    /**
     * Getter dell'attributo isGettato.
     * @return boolean isGettato
     */
    public boolean isGettato() {
        return isGettato;
    }

    /**
     * Setter dell'attributo preso.
     * @param preso 
     */
    public void setPreso(boolean preso) {
        isPreso = preso;
    }

    /**
     * Getter dell'attributo preso.
     * @return il valore booleano di isPreso
     */
    public boolean isPreso() {
        return isPreso;
    }

    /**
     * Getter dell'attributo nome dell'oggetto.
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Setter dell'attributo preso.
     * @param preso 
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    
    /**
     * Getter dell'attributo descrizione dell'oggetto.
     * @return descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }
    
    /**
     * Getter dell'attributo icona dell'oggetto.
     * @return icona
     */
    public ImageIcon getIcona() {
        return icona;
    }

    /**
     * Setter dell'attributo prendibile.
     * @param prendibile 
     */
    public void setPrendibile(boolean prendibile) {
        this.prendibile = prendibile;
    }

    /**
     * Getter dell'attributo prendibile.
     * @return prendibile
     */
    public boolean isPrendibile() {
        return prendibile;
    }
    
    /**
     * Setter dell'attributo gettabile
     * @param gettabile 
     */
    public void setGettabile(boolean gettabile) {
        this.gettabile = gettabile;
    }
    
    /**
     * Getter dell'attributo gettabile
     * @return gettabile
     */
    public boolean isGettabile() {
        return gettabile;
    }

}
