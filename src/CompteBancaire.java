



import java.time.LocalDate;
import java.util.ArrayList;

public  class CompteBancaire {
    private String type;
    private int numeroCompte;
    private ArrayList<Transaction> historiques;
    private float solde;
    private LocalDate date;
    private String rib;

    // Constructeur

    public CompteBancaire(String type, int numeroCompte, ArrayList<Transaction> historiques, float solde, LocalDate date, String rib) {
        this.type = type;
        this.numeroCompte = numeroCompte;
        this.historiques = historiques;
        this.solde = solde;
        this.date = date;
        this.rib = rib;
    }
    public int getNumeroCompte() {
        return numeroCompte;
    }

    public ArrayList<Transaction> getHistoriques() {
        return historiques;
    }

    public float getSolde() {
        return solde;
    }

    public LocalDate getDate() {
        return date;
    }

    public void Retrait(float amount) {
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
    }

     public void Depot(float amount) {
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
    }


    public void Solde(float amount) {
        // Vérification du solde actuel
        System.out.println("Le solde actuel est de : " + getSolde());
    }

    public float Simuler(LocalDate date) {

        // MODIFIER//
        float tauxInteret = 0.05f;
        float nouveauSolde = getSolde() * (1 + tauxInteret);

        // Ajouter la simulation à l'historique
        Transaction simulation = new Transaction("Simulation", nouveauSolde - getSolde(), date);
        getHistoriques().add(simulation);

        // Mettre à jour le solde avec le résultat de la simulation
        solde = nouveauSolde;

        // Retourner le nouveau solde simulé
        return solde;
    }



    public static void Main(String[] args) {
        // Créer une liste vide pour les transactions
        ArrayList<Transaction> transactions = new ArrayList<>();

        // Créer un nouveau compte bancaire
        CompteBancaire compte = new CompteBancaire("Compte Courant", 123456, transactions, 1000, LocalDate.now(), "123456789");

        // Afficher le solde initial
        System.out.println("Solde initial: " + compte.getSolde());

        // Effectuer un retrait
        compte.Retrait(10);

        // Afficher le solde après le retrait
        System.out.println("Solde après retrait: " + compte.getSolde());

        // Effectuer un dépôt
        compte.Depot(200);

        // Afficher le solde après le dépôt
        System.out.println("Solde après dépôt: " + compte.getSolde());

        // Effectuer une simulation
        LocalDate dateSimulation = LocalDate.now().plusMonths(1);
        float soldeSimule = compte.Simuler(dateSimulation);
        System.out.println("Nouveau solde simulé : " + soldeSimule);


    }



}

