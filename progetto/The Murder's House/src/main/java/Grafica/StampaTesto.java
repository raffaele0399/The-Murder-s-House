package Grafica;

import javax.swing.JTextArea;

/**
 * Classe che gestisce la stampa di una stringa.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class StampaTesto extends Thread {

    private final JTextArea textAreaOutput;
    private String testo; 
    private final static int ATTESA = 50;

    public StampaTesto(JTextArea textAreaOutput, String testo) {
        this.textAreaOutput = textAreaOutput;
        this.testo = testo;
    }

    /**
     * Metodo che gestisce l'esecuzione del thread e la lettura della stinga da stampare. 
     */
    @Override
    public void run() {
        String[] paroleDaStampare = testo.split(" ");

        for (String parola : paroleDaStampare) {
            try {
                Thread.sleep(ATTESA);
                
                if(parola.equals(">")) {
                    parola = parola.replace(parola, ">>>>>>\n");
                    textAreaOutput.append("\n\n");
                } else if(parola.equals(">>")) {
                    parola = parola.replace(parola, "");
                    textAreaOutput.append("\n\n");
                }
                
                textAreaOutput.append(parola + " ");

                textAreaOutput.setCaretPosition(textAreaOutput.getDocument().getLength());
            } catch (InterruptedException e) {
                System.out.println("Errore nella stampa.");
            }
        }
        textAreaOutput.append("\n\nCosa fai?\n\n\n");
        textAreaOutput.setCaretPosition(textAreaOutput.getDocument().getLength());
    }

}
