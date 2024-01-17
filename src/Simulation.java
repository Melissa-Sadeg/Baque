import java.time.LocalDate;
import java.util.Scanner;
public class Simulation {
    public static void AfficheMenu() {
        System.out.print("Veuillez choisir une option\n"
                + "--c: Création d'un compte Bancaire\n" +
                "--a: Affichage de tous vos comptes\n" +
                "--r: Recherche d'un compte par NumCompte\n" +
                "--i: Calcul d'intérêt sur un compte\n" +
                "--t: Faire une Transaction\n" +
                "--j: Historique de vos Transactions\n" +
                "--e: Exporter les données du compte \n");
    }

    public static void creationCompte() {
        double solde;
        String type = "unknown";
        LocalDate date = LocalDate.now();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez choisir le compte que vous voulez créer");
        System.out.println(date);
        System.out.print(
                "1: Création du compte LivretA\n" +
                        "2: Création du compte LDD\n" +
                        "3: Création du compte PEL\n");
        String entree = scanner.next();
        while (!entree.equals("1") && !entree.equals("2") && !entree.equals("3")) {
            System.out.println("Entrez un choix valide");
            entree = scanner.next();
        }
        if (entree.equals("1")) {
            type = "Livret A";
        } else if (entree.equals("2")) {
            type = "LDD";
        } else if (entree.equals("3")) {
            type = "PEL";
        }

        // Appel de la méthode saisirSoldeInitial avec le type de compte choisi
        solde = saisirSoldeInitial(type);
        System.out.println("Le compte que vous avez choisi est " + type + " avec un solde initial de " + solde + " euros." + "le compte est creer");
    }

    public static double saisirSoldeInitial(String typeCompte) {
        Scanner scanner = new Scanner(System.in);
        double solde = 0.0;
        double soldeMinimum;

        // Définir le solde minimum en fonction du type de compte
        switch (typeCompte) {
            case "Livret A":
                soldeMinimum = 10.0;
                break;
            case "LDD":
                soldeMinimum = 15.0;
                break;
            case "PEL":
                soldeMinimum = 50.0;  // Vous pouvez ajuster selon vos besoins
                break;
            default:
                soldeMinimum = 0.0;  // Valeur par défaut, ajustez si nécessaire
                break;
        }

        while (solde < soldeMinimum) {
            System.out.print("Veuillez entrer un solde initial (au moins " + soldeMinimum + " euros) : ");
            try {
                solde = scanner.nextDouble();
                if (solde < soldeMinimum) {
                    System.out.println("Le solde doit être d'au moins " + soldeMinimum + " euros. Réessayez.");
                    solde = 0.0;
                }
            } catch (Exception e) {
                System.out.println("Erreur de saisie. Veuillez entrer un montant numérique.");
                scanner.nextLine();  // Nettoyer la ligne incorrecte pour éviter une boucle infinie
            }
        }

        return solde;
    }

    // Appel de fonction
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Afficher le menu
        AfficheMenu();

        // Attendre que l'utilisateur choisisse l'option "--c"
        System.out.print("Veuillez choisir une option : ");
        String choix = scanner.next();

        // Si l'utilisateur choisit "--c", alors appeler la création de compte
        if (choix.equals("--c")) {
            creationCompte();
        } else {
            System.out.println("Option non valide. Fin du programme.");
        }
    }
}

















