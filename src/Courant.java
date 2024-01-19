import java.time.LocalDate;
import java.util.ArrayList;

public class Courant extends CompteBancaire{
    public static final double plafond = 22950;
    public static final double depotInitial = 0f;
    public Courant(String numeroCompte, String nom) {
        super(numeroCompte, nom, "Courant");
    }

    public Courant(String numeroCompte, String nom, String type, double solde, LocalDate dateCreation, ArrayList<Transaction> historiques) {
        super(numeroCompte, nom, type, solde, dateCreation, historiques);
    }

    public void depot(double somme) {
        if (somme > 0) {
            if (getSolde() + somme <= plafond) {
                setSolde(getSolde() + somme);
                Transaction newTransaction = new Transaction("depot", somme, LocalDate.now());
                addHistorique(newTransaction);
                Fichier.updateCompteBancaire(this, newTransaction);
            } else {
                System.out.println("Le plafond du compte courant est atteint (22950€)");
            }
        } else {
            System.out.println("Le montant doit être positif");
        }
    }

    public void retrait(double somme) {
        if (somme > 0) {
            if (getSolde() - somme >= 0) {
                setSolde(getSolde() - somme);
                Transaction newTransaction = new Transaction("retrait", somme, LocalDate.now());
                addHistorique(newTransaction);
                Fichier.updateCompteBancaire(this, newTransaction);
            } else {
                System.out.println("Le solde du compte courant est insuffisant");
            }
        } else {
            System.out.println("Le montant doit être positif");
        }
    }
}