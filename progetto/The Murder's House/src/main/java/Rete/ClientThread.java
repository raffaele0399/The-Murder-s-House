package Rete;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Classe che gestisce le richieste di diversi client in modo simultaneo.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class ClientThread extends Thread{
    
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private String nomeUtente;
    private ServerChat server;
    
    private static final String NOME_UTENTE_NON_DISPONIBILE = "NOME UTENTE NON DISPONIBILE";
    private static final String OK = "OK";

    /**
     * Costruttore che prendi in input dei parametri che associa agli attributi della classe.
     * @param socket
     * @param serverChat 
     */
    ClientThread(Socket socket, ServerChat serverChat) {
        this.socket = socket;
        this.server = serverChat;
        try{
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch(IOException e) {
            System.err.println("Eccezione nella classe ClientThread e nel Costruttore." + e.getMessage());
        }
    }

    /**
     * Setter del nome utente.
     * @param nomeUtente 
     */
    public void setNomeUtente(String nomeUtente){
        this.nomeUtente = nomeUtente;
    }

    /**
     * Getter del nome utente.
     * @return il nome dell'utente
     */
    public String getNomeUtente() {
        return this.nomeUtente;
    }

    /**
     * Metodo che manda le diverse richieste di un'utente per avviare una conversazione
     * verificando la possibilità di un'utente di accedere al server, stampando i messaggi inviati al server.
     */
    @Override
    public void run() {
        try{
            String username = in.readLine();
            
            if(!this.server.isNomeUtenteDisponibile(username)){
                this.inviaMessaggio(NOME_UTENTE_NON_DISPONIBILE);
            }else{
                this.inviaMessaggio(OK);
                this.setNomeUtente(username);
                this.server.aggiungiClient(username, this);
                System.out.println("Nuovo utente connesso: " + username);
                this.server.mostraUtentiOnline();
            }
            
            String messaggio;
            while((messaggio = in.readLine())!= null && this.nomeUtente != null){
                if(messaggio.equals(username)){
                    messaggio = "Connesso";
                }
                this.server.mostraMessaggio(nomeUtente, messaggio);
            }
            
        }catch(IOException e){
            System.err.println("Eccezione nella classe ClientThread e nel metodo run: " + e.getMessage());
        }finally{
            try{
                if(this.nomeUtente != null){
                    server.rimuoviClient(this);
                }
                this.socket.close();
            }catch(IOException e){
                System.err.println("Eccezione nella classe ClientThread e nel metodo run: " + e.getMessage());
            }
        }
    }

    /**
     * Metodo che prende in input una stringa e la scrive sul file.
     * @param messaggio 
     */
    public void inviaMessaggio(String messaggio) {
        try{
            this.out.write(messaggio);
            this.out.newLine();
            this.out.flush();
        }catch(IOException e){
            System.err.println("Eccezione nella classe ClientThread e nel metodo inviaMessaggio: " + e.getMessage());
        }
    }

}
