import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Simulation {
    private static List<CompteBancaire> comptesUtilisateur = new ArrayList<>();

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
        // Appel de la méthode saisirSoldeInitial avec le type de compte choisi
        String type = "unknown";
        double solde;
        solde = saisirSoldeInitial(type);
        CompteBancaire compte = new CompteBancaire("Compte Courant", 123456, 1000, LocalDate.now(), "123456789");

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
        System.out.println("Le compte que vous avez choisi est " + type + " avec un solde initial de " + solde + " euros." + "le compte est créé");
        comptesUtilisateur.add(compte);

        System.out.println("Le compte que vous avez choisi est " + type + " avec un solde initial de " + solde + " euros.");
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

    public static void afficherComptesBancaires() {
        if (comptesUtilisateur.isEmpty()) {
            System.out.println("Aucun compte n'est associé à cet utilisateur.");
        } else {
            System.out.println("Comptes existants de l'utilisateur :");
            for (CompteBancaire compte : comptesUtilisateur) {
                System.out.println("Type de compte : " + compte.getType() + ", Solde : " + compte.getSolde() + " euros");
            }
        }
    }

    // Lit un fichier JSON et renvoie une liste d'objets JSON
    public static List<Map<String, String>> readJsonFileToListOfMaps(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        byte[] jsonData = Files.readAllBytes(path);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonData, new TypeReference<List<Map<String, String>>>() {});
    }

    // Appel de fonction
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Afficher le menu
        AfficheMenu();

        // Attendre que l'utilisateur choisisse l'option "--c"
        System.out.print("Veuillez choisir une option : ");
        // String choix = scanner.next();

        // Si l'utilisateur choisit "--c", alors appeler la création de compte
       if (choix.equals("--c")) {
            creationCompte();
       }
        else {
            System.out.println("Option non valide. Fin du programme.");
        }

        try {
            String filePath = "src/transaction.json";


            List<Map<String, String>> jsonList = readJsonFileToListOfMaps(filePath);


            for (Map<String, String> jsonObject : jsonList) {
                System.out.println("TypeCompte: " + jsonObject.get("typeCompteBancaire") +
                        " Nom: " + jsonObject.get("Nom") +
                        " NumeroCompte: " + jsonObject.get("numeroCompte") +
                        " rib: " + jsonObject.get("rib"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


























/*import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulation {

    // ...

    // Déclaration d'une liste pour stocker les comptes de l'utilisateur
    private static List<CompteBancaire> comptesUtilisateur = new ArrayList<>();

    // ...

    // Méthode pour afficher les comptes existants de l'utilisateur
    public static void afficherComptesUtilisateur() {
        if (comptesUtilisateur.isEmpty()) {
            System.out.println("Aucun compte n'est associé à cet utilisateur.");
        } else {
            System.out.println("Comptes existants de l'utilisateur :");
            for (CompteBancaire compte : comptesUtilisateur) {
                System.out.println("Type de compte : " + compte.getType() + ", Solde : " + compte.getSolde() + " euros");
            }
        }
    }

    // ...

    public static void creationCompte() {
        // ...

        // Appel de la méthode saisirSoldeInitial avec le type de compte choisi
        solde = saisirSoldeInitial(type);

        // Création d'un objet CompteBancaire
        CompteBancaire nouveauCompte = new CompteBancaire(type, solde);

        // Ajout du compte à la liste des comptes de l'utilisateur
        comptesUtilisateur.add(nouveauCompte);

        System.out.println("Le compte que vous avez choisi est " + type + " avec un solde initial de " + solde + " euros.");

        // ...
    }

    // ...

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ...

        // Attendre que l'utilisateur choisisse l'option "--a"
        System.out.print("Veuillez choisir une option : ");
        String choix = scanner.next();

        // Si l'utilisateur choisit "--a", alors appeler la fonction d'affichage des comptes
        if (choix.equals("--a")) {
            afficherComptesUtilisateur();
        } else {
            System.out.println("Option non valide. Fin du programme.");
        }
    }
}
*/











// l




