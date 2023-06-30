package Grafica;

import java.io.FileNotFoundException;

/**
 * Classe che rappresenta l'engine del programma.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class Engine {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        new InterfacciaGrafica().setVisible(true);
    }
    
}
