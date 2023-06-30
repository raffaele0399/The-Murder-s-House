package Adventure;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Classe che gestisce la modalità di salvataggio di una partita.
 * @author Raffaele Nacci
 * @author Manuel Roberto Matera
 */
public class Salvataggio {
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS salvataggi (nomeSalvataggio VARCHAR(100) PRIMARY KEY, statoGiocatore VARBINARY)";

    /**
     * Costruttore che istanza un oggetto della classe e stabilisce una connessione con il DataBase.
     * @throws SQLException
     */
    public Salvataggio() throws SQLException {
        Properties proprietaDb = new Properties();

        proprietaDb.setProperty("user", "raffamanu");
        proprietaDb.setProperty("password", "1234"); 

        Connection connessione = DriverManager.getConnection("jdbc:h2:./db", proprietaDb);

        Statement statement = connessione.createStatement();
        statement.executeUpdate(CREATE_TABLE);
        statement.close();
        connessione.close();
    }


    /**
     * Metodo che ristabilisce ad ogni chiamata la connessione con il DataBase.
     * @return un oggetto di tipo connessione
     * @throws SQLException 
     */
    private Connection getConnection() throws SQLException {
        Properties proprietaDb = new Properties();

        proprietaDb.setProperty("user", "raffamanu");
        proprietaDb.setProperty("password", "1234");

        Connection connessione = DriverManager.getConnection("jdbc:h2:./db", proprietaDb);

        return connessione;
    }

    /**
     * Metodo che effettua l'azione di salvare.
     * @param giocatore
     * @param nomeSalvataggio 
     */
    public void salvaGiocatore(Giocatore giocatore, String nomeSalvataggio) {
        try (Connection connection = getConnection()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(giocatore);
            }

            byte[] serializedObject = baos.toByteArray();

            String sql = "INSERT INTO salvataggi VALUES (?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nomeSalvataggio);
                statement.setBytes(2, serializedObject);
                statement.executeUpdate();
                System.out.println("Giocatore salvato nel database.");
            }
        } catch (Exception e) {
            System.err.println("Eccezione nella classe Salvataggio nel metodo salvaGiocatore." + e.getMessage());
        }
    }

    /**
     * Metodo che effettua l'azione di caricamento di una partita salvata.
     * @param nomeSalvataggio
     * @return un oggetto giocatore di tipo Giocatore
     */
    public Giocatore recuperaGiocatore(String nomeSalvataggio) {
        Giocatore giocatore = null;

        try (Connection connection = getConnection()) {
            String sql = "SELECT statoGiocatore FROM salvataggi WHERE nomeSalvataggio = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nomeSalvataggio);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        byte[] serializedObject = resultSet.getBytes(1);

                        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(serializedObject))) {
                            giocatore = (Giocatore) ois.readObject();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Eccezione nella classe Salvataggio nel metodo recuperaGiocatore." + e.getMessage());
        }

        return giocatore;
    }

    /**
     * Metodo che effettua il recupero della lista dei salvataggi.
     * @return un array di stringhe
     * @throws SQLException 
     */
    public String[] recuperaNomiSalvataggi() throws SQLException {
        List<String> nomiSalvataggi = new ArrayList<>();

        try {
            try (Connection connection = getConnection()) {
                Statement statement = connection.createStatement();
                statement = connection.createStatement();

                ResultSet query;
                query = statement.executeQuery("SELECT nomeSalvataggio FROM salvataggi");

                while (query.next()) {
                    String salvataggio = query.getString(1);
                    nomiSalvataggi.add(salvataggio);
                }

                query.close();
                statement.close();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }

        return nomiSalvataggi.toArray(new String[nomiSalvataggi.size()]);
    }

}
