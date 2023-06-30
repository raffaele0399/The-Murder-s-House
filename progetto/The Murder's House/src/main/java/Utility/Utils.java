/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Utility;

import Grafica.StampaTesto;
import javax.swing.JTextArea;

/**
 *
 * @author raffa
 */
@FunctionalInterface
public interface Utils {
    
    /**
     * Metodo di interfaccia funzionale
     * @param s
     * @param stanza
     * @return valore della chiave associato all'output
     */
    Integer operazioneChiave(String[] s, Integer stanza);
    
    
    /**
     * Metodo che calcola una chiave di tipo intero. 
     * @param s
     * @param stanza
     * @param op
     * @return una chiave
     */
    public static Integer calcolaChiave(String[] s, Integer stanza, Utils op){
        return op.operazioneChiave(s, stanza);
    }
    
    
    
    
    /**
     * Metodo che richiama un metodo della classe StampaTesto per effettuare l'animazione di stampa dell'output. 
     * @param stringa 
     * @param textArea
     */
    public static void stampa(String str, JTextArea textArea){
        if(textArea != null) {
            StampaTesto animazioneStampa = new StampaTesto(textArea, str);
            animazioneStampa.start();

        } else {
            System.out.println(str);
        }
    }
    
}
