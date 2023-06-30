package Rete;

import Grafica.InterfacciaChat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Classe che rappresenta il funzionamento dello scambio di messaggi all'interno di una chat.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class ClientChat {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private String nomeUtente;
    private InterfacciaChat finestraChat;

    /**
     * Costruttore che prendi in input dei parametri che associa agli attributi della classe.
     * @param socket
     * @param nomeUtente
     * @param finestraChat 
     */
    public ClientChat(Socket socket, String nomeUtente, InterfacciaChat finestraChat){
        this.socket = socket;
        this.nomeUtente = nomeUtente;
        this.finestraChat = finestraChat;
        try{
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.out.write(this.nomeUtente);
            this.out.newLine();
            this.out.flush();
        }catch(IOException e){
            System.err.println("Eccezione nella classe ClientChat e nel Costruttore." + e.getMessage());
        }
    }

    /**
     * Metodo che prende in input una stringa e la scrive sul file.
     * @param messaggio
     * @throws IOException 
     */
    public void inviaMessaggio(String messaggio) throws IOException{
        try{
            this.out.write(messaggio);
            this.out.newLine();
            this.out.flush();
        }catch(IOException e){
            System.err.println("Eccezione nella classe ClientChat e nel metodo inviaMessaggio." + e.getMessage());
        }
    }

    /**
     * Metodo che avvia un thread in modo da farlo visualizzare su un'altra finestra
     * di un altro giocatore.
     */
    public void riceviMessaggio() {
        new Thread(() -> {
            String messaggio;
            try {
                while ((messaggio = in.readLine()) != null) {
                    if (finestraChat.isVisible()) {
                        finestraChat.mostraMessaggio(messaggio);
                    }
                }
            } catch (IOException e) {
                System.err.println("Eccezione nella classe ClientChat e nel metodo riceviMessaggio." + e.getMessage());
            }
        }).start();
    }

    
}
