package Grafica;

import Adventure.Giocatore;
import Adventure.Salvataggio;
import Parser.Parser;
import Utility.Inizializzazione;
import Utility.Utils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Classe che rappresenta l'interfaccia grafica visualizzata da un'utente.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class InterfacciaGrafica extends javax.swing.JFrame {      
    
    private static final String INTRODUZIONE = "Buonasera Detective..\n\n\n"
            + "Innumerevoli denunce di scomparsa stanno trascinando Tokyo nel baratro. Delle misteriose pagine sono state recapitate in caserma, con su scritto nome, cognome e sorte delle vittime. Secondo la scientifica, non è stato ancora possibile risalire all'artefice."
            + "\n\nLe indagini non hanno ancora rivelato se l'artefice di tali atrocità sia un'unica persona, oppure un'organizzazione come la Yakuza."
            + "Come Pollicino che semina molliche di pane, anche noi abbiamo cercato di seguire le tracce del nostro sadico assassino, ma senza ottenere grandi risultati."
            + "\n\nDalle atrocità che abbiamo percepito nella lettura di quei manoscritti, ci sembra di aver a che fare con una creatura sovrannaturale, nulla ci ha fatto più ribrezzo delle torture inflitte alle vittime, i cui corpi rimangono ancora un interrogativo per la nostre ricerche."
            + "Tuttavia Ellen Carter non è soltanto uno dei tanti nomi pervenutici nelle ultime otto settimane, ma è anche la chiave di volta per sbrogliare questo caso."
            + "\n\nA quanto pare il presunto assassino un certo: Ezekiel Nostron ha commesso un errore lasciandoci risalire alla sua abitazione grazie all'esamina delle impronte digitali trovate sul foglio su cui vi era scritto il nome della donna."
            + "Ora c'è da chiedersi se si tratti per davvero di un errore, o se il criminale ci stia depistando tendendoci una trappola, tuttavia non possiamo rimanere inermi."
            + "\n\nDetective Smith, hai il compito di recarti presso il quartiere Kabukicho, dov'è situata l'abitazione del presunto assassino e cercare di arrestarlo prima che possa nuovamente commettere gli atti ignobili perpetrati troppo a lungo."
            + "\n\n\nRaggiungi il giorno stesso l'abitazione di Nostron."
            + "Ti ritrovi davanti alla porta di un rudere dall'aspetto decadente e grottesco a tratti inquietante."
            + "\n\nDavanti a te si trova un portone marrone chiuso a chiave.\n\nIn un primo momento provi a forzare la porta ma questa non accenna ad aprirsi, sul lato della portone principale noti la presenza di una finestra.!";

    private static final String SCELTA_SALVATAGGIO = "Scegli salvataggio: ";
    
    private Giocatore giocatore;
    private Parser parser;
    private StampaTesto stampaTesto;
    private final Musica musicaGioco = new Musica();

    private boolean playStop = false;
    
    /**
     * Costruttore della classe InterfacciaGrafica.
     * @throws FileNotFoundException 
     */
    public InterfacciaGrafica() throws FileNotFoundException {
        initComponents();
        
        musicaGioco.playMusic("MusicaGioco.wav");
        
        textAreaOutput.setCaretPosition(textAreaOutput.getDocument().getLength());
        
        iconeBottoni();
        schermataIniziale();
        
        this.stampaTesto = new StampaTesto(textAreaOutput, INTRODUZIONE);
        stampaTesto.start();
        
        
        this.giocatore = Inizializzazione.inizializzaGiocatore();
        parser = new Parser(giocatore, this.labelImmagini, this.textAreaOutput, this);
        
        String comando = textFieldInput.getText();
        
        parser.parse(comando);
    }

    /**
     * Metodo che assegna le icone ai tasti corrispondenti.
     */
    private void iconeBottoni() {
        buttonAiuto.setIcon(new javax.swing.ImageIcon("Icone/Info.png"));
        buttonInventario.setIcon(new javax.swing.ImageIcon("Icone/Inventario.png"));
        buttonMappa.setIcon(new javax.swing.ImageIcon("Icone/Mappa.png"));
        buttonWiki.setIcon(new javax.swing.ImageIcon("Icone/Wiki.png"));
        buttonMusica.setIcon(new javax.swing.ImageIcon("Icone/Musica.png"));
        buttonSalva.setIcon(new javax.swing.ImageIcon("Icone/Salva.png"));
        buttonCarica.setIcon(new javax.swing.ImageIcon("Icone/Carica.png"));
        buttonChat.setIcon(new javax.swing.ImageIcon("Icone/Chat.png"));
        menu.setIcon(new javax.swing.ImageIcon("Icone/Opzioni.png"));
    }

    /**
     * Metodo che assegna l'immagine introduttiva del programma.
     */
    private void schermataIniziale() {
        labelImmagini.setIcon(new ImageIcon("CasaSpettrale.png"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSfondo = new javax.swing.JPanel();
        textFieldInput = new javax.swing.JTextField();
        barraScrollo = new javax.swing.JScrollPane();
        textAreaOutput = new javax.swing.JTextArea();
        buttonAiuto = new javax.swing.JButton();
        buttonInventario = new javax.swing.JButton();
        buttonMappa = new javax.swing.JButton();
        buttonWiki = new javax.swing.JButton();
        labelImmagini = new javax.swing.JLabel();
        buttonMusica = new javax.swing.JButton();
        barraMenu = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        buttonSalva = new javax.swing.JMenuItem();
        buttonCarica = new javax.swing.JMenuItem();
        buttonChat = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("The Murder's House");
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));

        panelSfondo.setBackground(new java.awt.Color(0, 0, 0));

        textFieldInput.setBackground(new java.awt.Color(51, 51, 51));
        textFieldInput.setFont(new java.awt.Font("Century Schoolbook", 0, 23)); // NOI18N
        textFieldInput.setForeground(new java.awt.Color(255, 255, 255));
        textFieldInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldInputActionPerformed(evt);
            }
        });

        barraScrollo.setBorder(null);
        barraScrollo.setAutoscrolls(true);

        textAreaOutput.setEditable(false);
        textAreaOutput.setBackground(new java.awt.Color(0, 0, 0));
        textAreaOutput.setColumns(20);
        textAreaOutput.setFont(new java.awt.Font("Century Schoolbook", 0, 23)); // NOI18N
        textAreaOutput.setForeground(new java.awt.Color(255, 255, 255));
        textAreaOutput.setLineWrap(true);
        textAreaOutput.setRows(5);
        textAreaOutput.setWrapStyleWord(true);
        textAreaOutput.setBorder(null);
        textAreaOutput.setCaretColor(new java.awt.Color(255, 255, 255));
        textAreaOutput.setCaretPosition(textAreaOutput.getDocument().getLength());
        textAreaOutput.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        textAreaOutput.setMargin(new java.awt.Insets(10, 10, 10, 10));
        barraScrollo.setViewportView(textAreaOutput);

        buttonAiuto.setBackground(new java.awt.Color(255, 255, 255));
        buttonAiuto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonAiuto.setForeground(new java.awt.Color(0, 0, 0));
        buttonAiuto.setText("Aiuto");
        buttonAiuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAiutoActionPerformed(evt);
            }
        });

        buttonInventario.setBackground(new java.awt.Color(255, 255, 255));
        buttonInventario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonInventario.setForeground(new java.awt.Color(0, 0, 0));
        buttonInventario.setText("Zaino");
        buttonInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonInventarioActionPerformed(evt);
            }
        });

        buttonMappa.setBackground(new java.awt.Color(255, 255, 255));
        buttonMappa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonMappa.setForeground(new java.awt.Color(0, 0, 0));
        buttonMappa.setText("Mappa");
        buttonMappa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMappaActionPerformed(evt);
            }
        });

        buttonWiki.setBackground(new java.awt.Color(255, 255, 255));
        buttonWiki.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonWiki.setForeground(new java.awt.Color(0, 0, 0));
        buttonWiki.setText("Wiki");
        buttonWiki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonWikiActionPerformed(evt);
            }
        });

        labelImmagini.setBackground(new java.awt.Color(255, 255, 255));
        labelImmagini.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        buttonMusica.setBackground(new java.awt.Color(255, 255, 255));
        buttonMusica.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonMusica.setForeground(new java.awt.Color(0, 0, 0));
        buttonMusica.setText("Musica");
        buttonMusica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMusicaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSfondoLayout = new javax.swing.GroupLayout(panelSfondo);
        panelSfondo.setLayout(panelSfondoLayout);
        panelSfondoLayout.setHorizontalGroup(
            panelSfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSfondoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelSfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSfondoLayout.createSequentialGroup()
                        .addComponent(textFieldInput, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(buttonMappa, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonWiki, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonAiuto, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelSfondoLayout.createSequentialGroup()
                        .addComponent(barraScrollo, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelImmagini, javax.swing.GroupLayout.PREFERRED_SIZE, 856, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelSfondoLayout.setVerticalGroup(
            panelSfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSfondoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelSfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barraScrollo, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
                    .addComponent(labelImmagini, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(panelSfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldInput, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonMappa, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonWiki, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAiuto, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        barraMenu.setBackground(new java.awt.Color(255, 255, 255));
        barraMenu.setBorder(null);
        barraMenu.setForeground(new java.awt.Color(255, 255, 255));

        menu.setBackground(new java.awt.Color(255, 255, 255));
        menu.setBorder(null);
        menu.setForeground(new java.awt.Color(0, 0, 0));
        menu.setText("Opzioni");

        buttonSalva.setText("Salva");
        buttonSalva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalvaActionPerformed(evt);
            }
        });
        menu.add(buttonSalva);

        buttonCarica.setText("Carica");
        buttonCarica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCaricaActionPerformed(evt);
            }
        });
        menu.add(buttonCarica);

        buttonChat.setText("Chat");
        buttonChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChatActionPerformed(evt);
            }
        });
        menu.add(buttonChat);

        barraMenu.add(menu);

        setJMenuBar(barraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelSfondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelSfondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metodo che gestisce l'evento dell'interazione di un'utente con la JTextField.
     * @param evt 
     */
    private void textFieldInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldInputActionPerformed
        if(textFieldInput.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(InterfacciaGrafica.this, "Inserisci un comando...");
            textFieldInput.grabFocus();
        }else{
            try {
                String in = textFieldInput.getText().trim();
                textFieldInput.setText("");
                parser.parse(in);
                
                if(!parser.isAccettato())
                    Utils.stampa("Eh???", this.textAreaOutput);
            } catch (IOException e) {
                System.err.println("Eccezione nella classe InterfacciaGrafica e nel metodo textFieldInputActionPerformed: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_textFieldInputActionPerformed

    /**
     * Metodo che gestisce l'evento dell'interazione di un'utente con il bottone Aiuto.
     * @param evt 
     */
    private void buttonAiutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAiutoActionPerformed
        new InterfacciaAiuto().setVisible(true);
    }//GEN-LAST:event_buttonAiutoActionPerformed

    /**
     * Metodo che gestisce l'evento dell'interazione di un'utente con il bottone Wiki.
     * @param evt 
     */
    private void buttonWikiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonWikiActionPerformed
        new InterfacciaWiki().setVisible(true);
    }//GEN-LAST:event_buttonWikiActionPerformed

    /**
     * Metodo che gestisce l'evento dell'interazione di un'utente con il bottone Inventario.
     * @param evt 
     */
    private void buttonInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonInventarioActionPerformed
        new InterfacciaInventario(this.giocatore).setVisible(true);
    }//GEN-LAST:event_buttonInventarioActionPerformed

    /**
     * Metodo che gestisce l'evento dell'interazione di un'utente con il bottone Musica.
     * @param evt 
     */
    private void buttonMusicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMusicaActionPerformed
        if (playStop) {
            playStop = false;
            buttonMusica.setText("Play");
            musicaGioco.playMusic("MusicaGioco.wav");
        } else {
            playStop = true;
            buttonMusica.setText("Pause");
            musicaGioco.stopMusica();
        }
    }//GEN-LAST:event_buttonMusicaActionPerformed

    /**
     * Metodo che gestisce l'evento dell'interazione di un'utente con il bottone Salva.
     * @param evt 
     */
    private void buttonSalvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalvaActionPerformed
        String nomeSalvataggio = JOptionPane.showInputDialog(this, "Inserisci il nome del salvataggio: ");

        try {
            Salvataggio salvataggio = new Salvataggio();
            
            String[] nomiSalvataggi = salvataggio.recuperaNomiSalvataggi();
            
            for(String s : nomiSalvataggi) {
                if(s.equals(nomeSalvataggio)) {
                    while(s.equals(nomeSalvataggio)) {
                        JOptionPane.showMessageDialog(this, "Questo salvataggio esiste già.");
                        nomeSalvataggio = JOptionPane.showInputDialog(this, "Inserisci il nome del salvataggio: ");
                    }
                }
            }

            if (nomeSalvataggio != null) {
                salvataggio.salvaGiocatore(this.giocatore, nomeSalvataggio);
            }

        } catch (SQLException ex) {
            System.err.println("Eccezione nella classe InterfacciaGrafica e nel metodo buttonSalvaActionPerformed: " + ex.getMessage());
        }
    }//GEN-LAST:event_buttonSalvaActionPerformed

    /**
     * Metodo che gestisce l'evento dell'interazione di un'utente con il bottone Carica.
     * @param evt 
     */
    private void buttonCaricaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCaricaActionPerformed
        String[] salvataggi = new String[0];

        Salvataggio salvataggio = null;
        try {
            salvataggio = new Salvataggio();
            salvataggi = salvataggio.recuperaNomiSalvataggi();
        } catch (SQLException ex) {
            System.err.println("Eccezione nella classe InterfacciaGrafica e nel metodo buttonCaricaActionPerformed: " + ex.getMessage());
        }
        String nomeSalvataggio = (String) JOptionPane.showInputDialog(this, SCELTA_SALVATAGGIO, "Salvataggi", JOptionPane.PLAIN_MESSAGE, null, salvataggi, salvataggi[0]);

        if (nomeSalvataggio!= null && salvataggio != null) {
            this.giocatore = salvataggio.recuperaGiocatore(nomeSalvataggio);
            this.parser.setGiocatore(this.giocatore);
            textAreaOutput.append("\n\n" + this.giocatore.getStanzaCorrente().getNome());
            labelImmagini.setIcon(this.giocatore.getStanzaCorrente().getIcon());
        }
    }//GEN-LAST:event_buttonCaricaActionPerformed

    /**
     * Metodo che gestisce l'evento dell'interazione di un'utente con il bottone Chat.
     * @param evt 
     */
    private void buttonChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChatActionPerformed
        new InterfacciaChat().main(new String[0]);
    }//GEN-LAST:event_buttonChatActionPerformed

    private void buttonMappaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMappaActionPerformed
        new InterfacciaMappa(this.giocatore).setVisible(true);
    }//GEN-LAST:event_buttonMappaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfacciaGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new InterfacciaGrafica().setVisible(true);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InterfacciaGrafica.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JScrollPane barraScrollo;
    private javax.swing.JButton buttonAiuto;
    private javax.swing.JMenuItem buttonCarica;
    private javax.swing.JMenuItem buttonChat;
    private javax.swing.JButton buttonInventario;
    private javax.swing.JButton buttonMappa;
    private javax.swing.JButton buttonMusica;
    private javax.swing.JMenuItem buttonSalva;
    private javax.swing.JButton buttonWiki;
    private javax.swing.JLabel labelImmagini;
    private javax.swing.JMenu menu;
    private javax.swing.JPanel panelSfondo;
    private javax.swing.JTextArea textAreaOutput;
    private javax.swing.JTextField textFieldInput;
    // End of variables declaration//GEN-END:variables
}
