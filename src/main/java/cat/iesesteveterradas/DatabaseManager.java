package cat.iesesteveterradas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DatabaseManager {
        private static final String DB_URL = "jdbc:sqlite:data/ForHonor.db";

    public static void initializeDatabase() throws Exception {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS Faccio (\n"
                    + " id integer PRIMARY KEY,\n"
                    + " nom text NOT NULL,\n"
                    + " resum text NOT NULL\n"
                    + ");";

            stmt.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS Personatge (\n"
                    + " id integer PRIMARY KEY,\n"
                    + " nom text NOT NULL,\n"
                    + " atac real NOT NULL,\n"
                    + " defensa real NOT NULL,\n"
                    + " idFaccio integer,\n"
                    + " FOREIGN KEY(idFaccio) REFERENCES Faccio(id)\n"
                    + ");";

            stmt.execute(sql);
        }
    }

    public static void addFaccio(Faccio faccio) throws Exception {
        String sql = "INSERT INTO Faccio(id, nom, resum) VALUES(?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, faccio.id);
            pstmt.setString(2, faccio.nom);
            pstmt.setString(3, faccio.resum);
            pstmt.executeUpdate();
        }
    }

    public static void addPersonatge(Personatge personatge) throws Exception {
        String sql = "INSERT INTO Personatge(id, nom, atac, defensa, idFaccio) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, personatge.id);
            pstmt.setString(2, personatge.nom);
            pstmt.setDouble(3, personatge.atac);
            pstmt.setDouble(4, personatge.defensa);
            pstmt.setInt(5, personatge.idFaccio);
            pstmt.executeUpdate();
        }
    }

    public static void getTable(String tableName) throws Exception {
        String sql = "SELECT * FROM " + tableName;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
        }
    }

    public static void getCharactersByFaction(int factionId) throws Exception {
        String sql = "SELECT * FROM Personatge WHERE idFaccio = " + factionId;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nom: " + rs.getString("nom") + ", Atac: " + rs.getDouble("atac") + ", Defensa: " + rs.getDouble("defensa"));
            }
        }
    }

    public static void getBestAttackerByFaction(int factionId) throws Exception {
        String sql = "SELECT * FROM Personatge WHERE idFaccio = " + factionId + " ORDER BY atac DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                System.out.println("El millor atacant de la facció " + factionId + " és " + rs.getString("nom") + " amb un atac de " + rs.getDouble("atac"));
            } else {
                System.out.println("No hi ha personatges en aquesta facció");
            }
        }
    }

    public static void getBestDefenderByFaction(int factionId) throws Exception {
        String sql = "SELECT * FROM Personatge WHERE idFaccio = " + factionId + " ORDER BY defensa DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                System.out.println("El millor defensor de la facció " + factionId + " és " + rs.getString("nom") + " amb una defensa de " + rs.getDouble("defensa"));
            } else {
                System.out.println("No hi ha personatges en aquesta facció");
            }
        }
    }
    
}