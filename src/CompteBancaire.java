

import java.time.LocalDate;
import java.util.ArrayList;

public class CompteBancaire {
    private String type;
    private int numeroCompte;
    private ArrayList<Transaction> historiques;
    private float plafond;
    private LocalDate dateCreation;
    private double amount;
   // private String rib;

    // Constructeur

    public CompteBancaire(String type, int numeroCompte, ArrayList<Transaction> historiques, float plafond, LocalDate date) {
        this.type = type;
        this.numeroCompte = numeroCompte;
        this.historiques = historiques;
        this.plafond = plafond;
        this.dateCreation = date;
        //this.rib = rib;
    }
    public int getNumeroCompte() {
        return numeroCompte;
    }

    public ArrayList<Transaction> getHistoriques() {
        return historiques;
    }

    public float getPlafond() {
        return plafond;
    }

    public LocalDate getDate() {
        return dateCreation;
    }










/*
    public void retrait(float amount) {
        // Vérifier si le montant du retrait est inférieur ou égal au solde disponible
        if (amount > 0 && amount <= plafond) {

            Transaction retrait = new Transaction("Retrait", amount, LocalDate.now());

            // Ajouter la transaction à l'historique
            historiques.add(retrait);

            // Mettre à jour le solde
            plafond -= amount;

            System.out.println("Retrait de " + amount + " effectué avec succès.");
        } else {
            System.out.println("Montant invalide pour le retrait.");
        }
    }



public static void main (String[] args ){
float amount = 10;
int plafond = 100;


}
*/
}

