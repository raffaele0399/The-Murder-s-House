package Adventure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta un inventario.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class Inventario implements Serializable {

    private List<Oggetto> inventario = new ArrayList<>();

    /**
     * Metodo che aggiunge un oggetto all'interno della lista,
     * attributo di questa classe.
     * @param o 
     */
    public void addOggetto(Oggetto o){
        inventario.add(o);
    }

    /**
     * Metodo che rimuove un oggetto all'interno della lista,
     * attributo di questa classe.
     * @param o 
     */
    public void removeOggetto(Oggetto o){
        inventario.remove(o);
    }

    /**
     * Setter che imposta la lista corrispondente ad un inventario
     * @param inventario 
     */
    public void setInventario(List<Oggetto> inventario) {
        this.inventario = inventario;
    }

    /**
     * Getter che restituisce la lista corrispondente ad un inventario.
     * @return inventario
     */
    public List<Oggetto> getInventario() {
        return inventario;
    }

}
