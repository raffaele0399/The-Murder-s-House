package Rete;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe che crea e avvia l'esecuzione del server.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class ServerChat {
    
    private ServerSocket serverSocket;
    private Map<String, ClientThread> clients;
    private static final int PORT = 6666;
    
    public ServerChat(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
        this.clients = new HashMap<>();
    }

    /**
     * Metodo che imposta il server in ascolto.
     */
    public void startServer() {
        try {
            System.out.println("Server avviato. In attesa di connessioni...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Nuovo client connesso: " + socket);
                
                Thread clientThread = new ClientThread(socket, this);
                clientThread.start();
            }
        } catch (IOException e) {
            System.err.println("Eccezione nella classe ServerChat e nel metodo startServer." + e.getMessage());
        } finally {
            chiudiServer(serverSocket);
        }
    }

    /**
     * Metodo che chiude la connessione al server.
     * @param serverSocket 
     */
    private void chiudiServer(ServerSocket serverSocket) {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Eccezione nella classe ServerChat e nel metodo chiudiServer." + e.getMessage());
        }
    }

    /**
     * Metodo che verifica se il nome inserito da un utente è già stato utilizzato da un altro giocatore.
     * @param nomeUtente
     * @return valore booleano di conferma o meno
     */
    public synchronized boolean isNomeUtenteDisponibile(String nomeUtente){
        return !clients.containsKey(nomeUtente);
    }

    /**
     * Metodo che aggiunge un nuovo giocatore all'interno della lista dei giocatori.
     * @param username
     * @param clientThread 
     */
    public synchronized void aggiungiClient(String username, ClientThread clientThread){
        clients.put(username, clientThread);
    }

    /**
     * Metodo che rimuove un giocatore dalla lista dei giocatori.
     * @param clientThread 
     */
    public synchronized void rimuoviClient(ClientThread clientThread){
        clients.remove(clientThread.getNomeUtente());
        this.mostraUtentiOnline();
    }

    /**
     * Metodo che mostra un messaggio inviato da un giocatore ad un altro.
     * @param utente
     * @param messaggio 
     */
    public synchronized void mostraMessaggio(String utente, String messaggio){
         for(ClientThread clientThread : clients.values()){
             clientThread.inviaMessaggio(String.format("%s: %s", utente, messaggio));
         }
    }

    /**
     * Mostra che mostra l'elenco dei giocatori connessi al server.
     */
    public synchronized void mostraUtentiOnline(){
        String connectedUsers = "Utenti connessi: ";
        for (String username : clients.keySet()) {
            connectedUsers += username + ", ";
        }
        this.mostraMessaggio("Server", connectedUsers);
    }

    /**
     * Main che avvia il server e lo imposta sull'ascolto.
     * @param args 
     */
    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server avviato sulla porta " + PORT);
            ServerChat server = new ServerChat(serverSocket);
            server.startServer();
        } catch (IOException e) {
            System.err.println("Eccezione nella classe ServerChat e nel metodo main." + e.getMessage());
        }
    }
    
}
