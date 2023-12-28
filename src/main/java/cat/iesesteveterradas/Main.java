package cat.iesesteveterradas;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            DatabaseManager.initializeDatabase();
            // Afegir faccions
            DatabaseManager.addFaccio(new Faccio(1, "Cavallers", "M'lady"));
            DatabaseManager.addFaccio(new Faccio(2, "Vikings", "BOY!"));
            DatabaseManager.addFaccio(new Faccio(3, "Samurais", "Omae wa mou shindeiru"));

            // Afegir personatges per a Cavallers
            DatabaseManager.addPersonatge(new Personatge(1, "Personatge1", 10.0, 10.0, 1));
            DatabaseManager.addPersonatge(new Personatge(2, "Personatge2", 20.0, 20.0, 1));
            DatabaseManager.addPersonatge(new Personatge(3, "Personatge3", 30.0, 30.0, 1));

            // Afegir personatges per a Vikings
            DatabaseManager.addPersonatge(new Personatge(4, "Personatge4", 40.0, 40.0, 2));
            DatabaseManager.addPersonatge(new Personatge(5, "Personatge5", 50.0, 50.0, 2));
            DatabaseManager.addPersonatge(new Personatge(6, "Personatge6", 60.0, 60.0, 2));

            // Afegir personatges per a Samurais
            DatabaseManager.addPersonatge(new Personatge(7, "Personatge7", 70.0, 70.0, 3));
            DatabaseManager.addPersonatge(new Personatge(8, "Personatge8", 80.0, 80.0, 3));
            DatabaseManager.addPersonatge(new Personatge(9, "Personatge9", 90.0, 90.0, 3));

        } catch (Exception e) {
            System.out.println("S'ha produït un error en llegir el fitxer: " + e.getMessage());
        }

                Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Mostrar una taula");
            System.out.println("2. Mostrar personatges per facció");
            System.out.println("3. Mostrar el millor atacant per facció");
            System.out.println("4. Mostrar el millor defensor per facció");
            System.out.println("5. Sortir");

            int selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    System.out.println("Introdueix el nom de la taula:");
                    String tableName = scanner.next();
                    try {
                        DatabaseManager.getTable(tableName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Introdueix l'ID de la facció:");
                    int factionId = scanner.nextInt();
                    try {
                        DatabaseManager.getCharactersByFaction(factionId);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("Introdueix l'ID de la facció:");
                    factionId = scanner.nextInt();
                    try {
                        DatabaseManager.getBestAttackerByFaction(factionId);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.println("Introdueix l'ID de la facció:");
                    factionId = scanner.nextInt();
                    try {
                        DatabaseManager.getBestDefenderByFaction(factionId);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Selecció no vàlida");
            }
        }
    }


}
