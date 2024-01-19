
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public abstract class CompteBancaire {
    private final String numeroCompte;
    private final String nom;
    private final String type;
    private final LocalDate dateCreation;
    private double solde;
    private ArrayList<Transaction> historiques;
    // private String rib;

    public CompteBancaire(String numeroCompte, String nom, String type) {
        this.numeroCompte = numeroCompte;
        this.nom = nom;
        this.type = type;
        this.solde = 0;
        this.dateCreation = LocalDate.now();  //date de création = aujourd'hui
        this.historiques = new ArrayList<>();
    }

    public CompteBancaire(String numeroCompte, String nom, String type, double solde, LocalDate dateCreation, ArrayList<Transaction> historiques) {
        this.numeroCompte = numeroCompte;
        this.nom = nom;
        this.type = type;
        this.solde = solde;
        this.dateCreation = dateCreation;
        this.historiques = historiques;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public double getSolde() {
        return solde;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public ArrayList<Transaction> getHistoriques() {
        return historiques;
    } //get toutes les transactions historiques
    public ArrayList<Transaction> getHistoriques(LocalDate debut, LocalDate fin) {
        ArrayList<Transaction> result = new ArrayList<>();
        for(Transaction transaction : getHistoriques()) {
            if(transaction.getDate().isAfter(debut) && transaction.getDate().isBefore(fin)) {
                result.add(transaction);
            }
        }
        return result;
    } //get les transactions historiques entre deux dates
    public void addHistorique(Transaction transaction) {
        this.historiques.add(transaction);
    } //ajouter une transaction historique
    public void setSolde(double solde) {
        this.solde = solde;
    }


    //affiche toutes les transactions ligne par ligne
    public void afficherHistoriques() {
        if(getHistoriques().isEmpty()) {
            System.out.println("Aucune transaction historique trouvée !");
            return;
        }
        for(Transaction transaction : getHistoriques()) {
            System.out.println(transaction);
        }
    }

    //affiche l'historique de transaction entre deux dates
    public void afficherHistoriques(LocalDate debut, LocalDate fin) {
        if(getHistoriques(debut, fin).isEmpty()) {
            System.out.println("Aucune transaction historique trouvée !");
            return;
        }
        System.out.println("Historique des transactions entre " + debut + " et " + fin + " :");
        for(Transaction transaction : getHistoriques(debut, fin)) {
            System.out.println(transaction);
        }
    }

    public static String generateNumeroCompte() {
        Random rand = new Random();
        String numeroCompte = "";

        for (int i = 0; i < 4; i++) {
            int fourDigit = rand.nextInt(9000) + 1000; // 1000 to 9999
            numeroCompte += Integer.toString(fourDigit);
            if(i < 3) {
                numeroCompte += " ";
            }
        }
        return numeroCompte;
    }

    @Override
    public String toString() {
        return "numeroCompte : " + numeroCompte + '\n' +
                "nom : " + nom + '\n' +
                "type : " + type + '\n' +
                "dateCreation : " + dateCreation + '\n' +
                "solde : " + solde + '\n' +
                "historiques : " + historiques;
    }


    public abstract void retrait(double montant);

    public abstract void depot(double montant);
}

   /* public void retrait(float amount) {
        // Vérifier si le montant du retrait est inférieur ou égal au solde disponible
        if (amount > 0 && amount <= solde) {
            Transaction retrait = new Transaction("Retrait", amount, LocalDate.now());
            // Ajouter la transaction à l'historique
            historiques.add(retrait);
            // Mettre à jour le solde
            solde -= amount;
            System.out.println("Retrait de " + amount + " effectué avec succès.");
        } else {
            System.out.println("Montant invalide pour le retrait.");
        }
    }*/








    /*public void depot(float amount) {
        if (amount > 0) {
            // Effectuer le dépôt
            solde += amount;
            // Ajouter la transaction à l'historique
            Transaction depot = new Transaction("Dépôt", amount, LocalDate.now());
            getHistoriques().add(depot);
            System.out.println("Dépôt de " + amount + " effectué avec succès.");
        } else {
            System.out.println("Montant invalide pour le dépôt.");
        }
    }*/











   /* public void afficheSolde() {
        // Vérification du solde actuel
        System.out.println("Le solde actuel est de : " + getSolde());
    }

    public float simuler(LocalDate date, float tauxInteret) {
        // Calculer le nombre d'années entre la date actuelle et la date fournie
        long nombreAnnees = ChronoUnit.YEARS.between(LocalDate.now(), date);
        // Calculer le nouveau solde en fonction du taux d'intérêt et du nombre d'années
        //float nouveauSolde = getSolde() * (float) Math.pow(1 + tauxInteret, nombreAnnees);
        // Mettre à jour le solde avec le résultat de la simulation
        solde = nouveauSolde;
        // Retourner le nouveau solde simulé
        return nouveauSolde;
    }*/
































