import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class LivretA extends CompteBancaire {
    public final double plafond =22950;
    public static final double tauxInteret = 0.03f;
    public static final double depotInitial = 10f;

    public LivretA(String numeroCompte, String nom) {
        super(numeroCompte, nom, "Livret A");
    }

    public LivretA(String numeroCompte, String nom, String type, double solde, LocalDate dateCreation, ArrayList<Transaction> historiques) {
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
                System.out.println("Le plafond du livret A est atteint (22950€)");
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
                System.out.println("Le solde du livret A est insuffisant");
            }
        } else {
            System.out.println("Le montant doit être positif");
        }
    }


    //on calcule les intérêts 2 fois par mois : le 15 et le dernier jour du mois
    //Calculé sur la base du solde minimum de chaque quinzaine
    public double simuler(LocalDate date) {
        LocalDate today = LocalDate.now();
        if(date.isAfter(today)) {
            System.out.println("La date doit être antérieure à la date du jour");
            return 0;
        }
        //on calcule le solde minimum de la 1ère quinzaine
        double soldeMin = getSolde();
        LocalDate debutQuinzaine;
        if(today.getDayOfMonth() < 15) {
            debutQuinzaine = LocalDate.of(today.getYear(), today.getMonth(), 1);
        } else {
            debutQuinzaine = LocalDate.of(today.getYear(), today.getMonth(), 15);
        }
        for(Transaction transaction : getHistoriques()) {
            if(transaction.getDate().isAfter(debutQuinzaine)) {
                if(transaction.getType().equals("depot")) {
                    soldeMin -= transaction.getMontant();
                }
            }
        }
        //on calcule le nombre de quinzaines entre la date de création du compte et la date de simulation
        int nbQuinzaines = (date.getYear() -today.getYear()) * 24 + (date.getMonthValue() - today.getMonthValue());
        //on calcule les intérêts
        double interets = 0;
        for(int i = 0; i < nbQuinzaines; i++) {
            interets += soldeMin * tauxInteret / 24;
        }
        return interets;
    }
}
