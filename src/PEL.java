import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class PEL extends CompteBancaire {
    public double plafond = 61200 ;
    public static final double tauxInteret = 0.0225f;
    public static double depotInitial = 225f;

    public PEL(String numeroCompte, String nom) {
        super(numeroCompte, nom, "PEL");
    }

    public PEL(String numeroCompte, String nom, String type, double solde, LocalDate dateCreation, ArrayList<Transaction> historiques) {
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
                System.out.println("Le plafond du PEL est atteint (61200€)");
            }
        } else {
            System.out.println("Le montant doit être positif");
        }
    }

    //interdit de retirer de largent avant la date lechancier(4em anniversaire de louverture)
    public void retrait(double somme) {
        LocalDate today = LocalDate.now();
        LocalDate dateEcheancier = getDateCreation().plusYears(4);
        if(today.isBefore(dateEcheancier)) {
            System.out.println("Vous ne pouvez pas retirer de l'argent avant le " + dateEcheancier);
            return;
        }
        if(somme < 0) {
            System.out.println("Le montant doit être positif");
            return;
        }
        if(getSolde() - somme >= 0) {
            setSolde(getSolde() - somme);
            Transaction newTransaction = new Transaction("retrait", somme, LocalDate.now());
            addHistorique(newTransaction);
            Fichier.updateCompteBancaire(this, newTransaction);
        } else {
            System.out.println("Le solde du PEL est insuffisant");
        }
    }


    //TODO : calculer les intérêts pour un PEL
    public double simuler(LocalDate date) {return 0;}


}
