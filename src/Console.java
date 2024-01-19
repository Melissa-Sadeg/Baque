import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Console {
    //cilia
    private User user;
    // bowen

    public Console(User user) {
        this.user = user;
    }

    public void afficheBienvenue() {
        System.out.println("Bienvenue " + user.getPrenom() + " " + user.getNom());
    }

    public char getOptionValide(String options) {
        Scanner scanner = new Scanner(System.in);
        char option = scanner.next().charAt(0);
        //vérifier que l'option est valide
        Boolean isOptionValid = options.contains(String.valueOf(option)); //si l'option entrée est dans la liste des options valides
        while(!isOptionValid) {
            System.out.println("Option invalide, veuillez réessayer");
            option = scanner.next().charAt(0);
            isOptionValid = options.contains(String.valueOf(option));
        }
        return option;
    }
    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------");
        System.out.print("Veuillez choisir une option\n"
                + "--c: Création d'un compte bancaire\n" +
                "--a: Affichage de tous vos comptes\n" +
                "--r: Recherche d'un compte par NumCompte\n" +
                "--i: Calcul d'intérêt sur un compte\n" +
                "--t: Faire une transaction\n" +
                "--j: Historique de vos transactions\n" +
                "--e: Exporter les données du compte \n" +
                "--q: Quitter le programme\n");
        System.out.println("----------------------------");
        char option = getOptionValide("caritjeq");
        switch (option) {
            case 'c':
                creationCompte();
                break;
            case 'a':
                afficheComptes();
                break;
            case 'r':
                rechercheCompte();
                break;
            case 'i':
                calculInteret();
                break;
            case 't':
                transaction();
                break;
            case 'j':
                historique();
                break;
            case 'e':
                export();
                break;
            case 'q':
                System.out.println("Au revoir !");
                System.exit(0);
                break;
            default:
                System.out.println("Option invalide, veuillez réessayer");
                break;
        }
    }

    public void creationCompte() {
        Scanner scanner = new Scanner(System.in);
        CompteBancaire compte = null;
        String nom = user.getNom() + " " + user.getPrenom();
        double depotInitial = 0;
        System.out.println("Création d'un compte bancaire");
        System.out.println("----------------------------");
        System.out.print("Veuillez choisir le type du compte\n"
                + "--p: PEL\n" +
                "--a: Livret A\n" +
                "--l: LDD\n" +
                "--c: Compte courant\n" +
                "--q : Quitter\n");
        char option = getOptionValide("palcq");
        String numeroCompte = CompteBancaire.generateNumeroCompte();
        switch (option) {
            case 'p':
                compte = new PEL(numeroCompte, nom);
                depotInitial = PEL.depotInitial;
                break;
            case 'a':
                compte = new LivretA(numeroCompte, nom);
                depotInitial = LivretA.depotInitial;
                break;
            case 'l':
                compte = new LDD(numeroCompte, nom);
                depotInitial = LDD.depotInitial;
                break;
            case 'c':
                compte = new Courant(numeroCompte, nom);
                depotInitial = Courant.depotInitial;
                break;
            case 'q':
                mainMenu();
                break;
            default:
                System.out.println("Option invalide, veuillez réessayer");
                break;
        }
        if(compte != null) {
            if(user.addCompte(compte)) {
                Fichier.ajouterCompteBancaire(user, compte);
                System.out.println("Compte créé avec succès");
                System.out.println("Votre numéro de compte est : " + compte.getNumeroCompte());
                System.out.println("Dépôt initial : " + depotInitial + "€");
                compte.depot(depotInitial);
            }
        }
        System.out.println("Tapez une touche pour retourner au menu principal");
        scanner.nextLine();
        mainMenu();
    }
    public void afficheComptes() {
        Scanner scanner = new Scanner(System.in);
        user.afficherComptes();
        System.out.println("Tapez une touche pour retourner au menu principal");
        scanner.nextLine();
        mainMenu();
    }

    public void rechercheCompte() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Rechercher un compte");
        System.out.println("Veuillez entrer le numéro de compte :(format xxxx xxxx xxxx xxxx");
        String numeroRecherche = scanner.nextLine();
        CompteBancaire resultat = user.rechercherCompte(numeroRecherche);
        if(resultat == null) {
            System.out.println("Aucun compte trouvé !");
        }
        else {
            System.out.println("Compte trouvé !");
            System.out.println(resultat);
        }
        System.out.println("Tapez une touche pour retourner au menu principal");
        scanner.nextLine();
        mainMenu();
    }
    public void calculInteret() {
        System.out.println("Calculer les intérêts");
        if(user.pasDepargne()) {
            System.out.println("Vous n'avez pas de compte d'épargne");
        }

    }
    public void transaction() {
        System.out.println("Faire une transaction");
        if(user.pasDeCompte()) {
            System.out.println("Vous n'avez pas de compte");
            mainMenu();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Veuillez choisir une option\n"
                + "--d: Dépôt\n" +
                "--r: Retrait\n" +
                "--q : Quitter\n");
        char option = getOptionValide("drq");
        user.afficherComptes();
        System.out.println("Veuillez choisir un compte :");
        int choix = scanner.nextInt();
        while(choix < 1 || choix > user.getNbComptes()) {
            System.out.println("Veuillez entrer une valeur valide");
            choix = scanner.nextInt();
        }
        System.out.println("Veuillez entrer la somme :");
        double somme = scanner.nextDouble();
        switch (option) {
            case 'd':
                user.getComptes().get(choix - 1).depot(somme);
                break;
            case 'r':
                user.getComptes().get(choix - 1).retrait(somme);
                break;
            case 'q':
                mainMenu();
                break;
            default:
                System.out.println("Option invalide, veuillez réessayer");
                break;
        }
        System.out.println("Tapez une touche pour retourner au menu principal");
        scanner.nextLine();
        mainMenu();
    }
    public void historique() {
        System.out.println("Historique des transactions");
        if(user.pasDeCompte()) {
            System.out.println("Vous n'avez pas de compte");
            mainMenu();
        }
        Scanner scanner = new Scanner(System.in);
        user.afficherComptes();
        System.out.println("Veuillez choisir un compte :");
        int choix = scanner.nextInt();
        while(choix < 1 || choix > user.getNbComptes()) {
            System.out.println("Veuillez entrer une valeur valide");
            choix = scanner.nextInt();
        }
        System.out.println("Veuillez entrer une date de début(format : YYYY-MM-DD) :");
        LocalDate dateDebut = getDateValide();
        System.out.println("Veuillez entrer une date de fin(format : YYYY-MM-DD) :");
        LocalDate dateFin = getDateValide();
        user.getComptes().get(choix - 1).afficherHistoriques(dateDebut, dateFin);
        ArrayList<Transaction> result = user.getComptes().get(choix - 1).getHistoriques(dateDebut, dateFin);
        System.out.println("Exporter les données ?(O/N");
        char option = scanner.next().charAt(0);
        if(option == 'o' || option == 'O' ) {
            Fichier.exporterHistoriques(user.getComptes().get(choix - 1), result);
        }
        System.out.println("Tapez une touche pour retourner au menu principal");
        scanner.nextLine();
        mainMenu();
    }
    public void export() {
        System.out.println("Exporter les données");
    }

    public static void main(String[] args) {
        User user = Fichier.readUser("sadeg@et.esiea.fr");
        if(user == null) {
            System.out.println("Utilisateur non trouvé");
            System.exit(0);
        }
        Console console = new Console(user);
        console.afficheBienvenue();
        console.mainMenu();
    }

    public static Boolean estDateVailde(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static LocalDate getDateValide() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer une date(format : YYYY-MM-DD) :");
        String date = scanner.nextLine();
        while(!estDateVailde(date)) {
            System.out.println("Veuillez entrer une date valide(format : YYYY-MM-DD) :");
            date = scanner.nextLine();
        }
        return LocalDate.parse(date);
    }
}
