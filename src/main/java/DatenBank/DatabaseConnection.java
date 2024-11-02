package DatenBank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    public static void main(String[] args) {
        // H2-Datenbank-Verbindungsdetails
        String url = "jdbc:h2:file:./todo_db";  // Lokale Datei-basierte H2-Datenbank
        String user = "sa";
        String password = "";

        // Verbindung zur H2-Datenbank herstellen
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Verbindung zur H2-Datenbank hergestellt!");

            // Tabelle erstellen, falls sie noch nicht existiert
            String createTableSQL = "CREATE TABLE IF NOT EXISTS todos ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "task VARCHAR(255), "
                    + "completed BOOLEAN DEFAULT FALSE)";

            try (Statement statement = connection.createStatement()) {
                statement.execute(createTableSQL);
                System.out.println("Tabelle 'todos' erfolgreich erstellt oder bereits vorhanden!");
            } catch (SQLException e) {
                System.out.println("Fehler beim Erstellen der Tabelle!");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Verbindung fehlgeschlagen!");
            e.printStackTrace();
        }
    }
}
