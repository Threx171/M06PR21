package cat.iesesteveterradas;

public class Personatge {
    int id;
    String nom;
    double atac;
    double defensa;
    int idFaccio;

    public Personatge(int id, String nom, double atac, double defensa, int idFaccio) {
        this.id = id;
        this.nom = nom;
        this.atac = atac;
        this.defensa = defensa;
        this.idFaccio = idFaccio;
    }
}
