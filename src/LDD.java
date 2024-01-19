import java.time.LocalDate;
import java.util.ArrayList;

public class LDD extends CompteBancaire {
    public final double plafond = 12000;
    public static final double tauxInteret = 0.03f;
    public static final double depotInitial = 10f;

    public LDD(String numeroCompte, String nom) {
        super(numeroCompte, nom, "LDD");
    }

    public LDD(String numeroCompte, String nom, String type, double solde, LocalDate dateCreation, ArrayList<Transaction> historiques) {
        super(numeroCompte, nom, type, solde, dateCreation, historiques);
    }

    public void depot(double somme) {
        if (somme > 0) {
            if (getSolde() + somme <= plafond) {
                setSolde(getSolde() + somme);
                addHistorique(new Transaction("depot", somme, LocalDate.now()));
                Transaction newTransaction = new Transaction("depot", somme, LocalDate.now());
                addHistorique(newTransaction);
                Fichier.updateCompteBancaire(this, newTransaction);
            } else {
                System.out.println("Le plafond du LDD est atteint (12000€)");
            }
        } else {
            System.out.println("Le montant doit être positif");
        }
    }

    public void retrait(double somme) {
        if (somme > 0) {
            if (getSolde() - somme >= 0) {
                setSolde(getSolde() - somme);
                addHistorique(new Transaction("retrait", somme, LocalDate.now()));
                Transaction newTransaction = new Transaction("retrait", somme, LocalDate.now());
                addHistorique(newTransaction);
                Fichier.updateCompteBancaire(this, newTransaction);
            } else {
                System.out.println("Le solde du LDD est insuffisant");
            }
        } else {
            System.out.println("Le montant doit être positif");
        }
    }

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
                } else {
                    soldeMin += transaction.getMontant();
                }
            }
        }
        //on calcule le solde minimum de la 2ème quinzaine
        double soldeMin2 = getSolde();
        LocalDate finQuinzaine;
        if(today.getDayOfMonth() < 15) {
            finQuinzaine = LocalDate.of(today.getYear(), today.getMonth(), 14);
        } else {
            finQuinzaine = LocalDate.of(today.getYear(), today.getMonth(), today.lengthOfMonth());
        }
        for(Transaction transaction : getHistoriques()) {
            if(transaction.getDate().isAfter(debutQuinzaine)) {
                if(transaction.getType().equals("depot")) {
                    soldeMin2 -= transaction.getMontant();
                } else {
                    soldeMin2 += transaction.getMontant();
                }
            }
        }
        //on calcule les intérêts
        double interets = (soldeMin + soldeMin2) * tauxInteret / 12;
        return interets;
    }

}
