import java.util.ArrayList;
import java.util.Scanner;


public class CompteUser {
    private String Nom;
    private String Prenom;
    private String Mdp;
    private String email;
    private ArrayList<CompteBancaire> comptesBancaire;

    public CompteUser(String Nom ,String Prenom , String mdp, String email) {
        Mdp = mdp;
        this.email = email;
        this.comptesBancaire = comptesBancaire;
    }

    public String getMdp() {
        return Mdp;
    }
    public String getEmail(){
        return email;
    }

    public ArrayList<CompteBancaire> getComptesBancaire() {
        return this.comptesBancaire;
    }
    // verifie l'entree de la bonne adresse emeil
    public static Boolean bonAdresse(String Email) {
        if (Email == null)
            return false;

        if  (Email.indexOf('@') == -1 || Email.indexOf('.') == -1)
            return false;

        if (Email.indexOf('@') == 0 || Email.indexOf('.') < Email.indexOf('@') || Email.length() - 1 == Email.indexOf('.'))
            return false;
        if (Email.indexOf('@') + 1 == Email.indexOf('.'))
            return false;

        else
            return true;





    }



    public static Boolean codeBon(String Mdp) {
        if (Mdp == null || Mdp.length() < 8) {
            return false;
        }

        // Initialiser les variables de détection
        boolean contientMajuscule = false;
        boolean contientMinuscule = false;
        boolean contientChiffre = false;

        // Parcourir chaque caractère du mot de passe
        for (char caractere : Mdp.toCharArray()) {
            // Vérifier si le caractère est une majuscule
            if (Character.isUpperCase(caractere)) {
                contientMajuscule = true;
            }
            // Vérifier si le caractère est une minuscule
            else if (Character.isLowerCase(caractere)) {
                contientMinuscule = true;
            }
            // Vérifier si le caractère est un chiffre
            else if (Character.isDigit(caractere)) {
                contientChiffre = true;
            }
        }

        // Vérifier si le mot de passe contient une majuscule, une minuscule et un chiffre
        return contientMajuscule && contientMinuscule && contientChiffre;
    }

    public static CompteUser  creationCompte(){
        String Nom ;
        String Prenom;
        String Email ;
        String Mdp ;
        int NumCompte ;
        System.out.println("Creation du compte");

        System.out.println("rentrer votre Nom:");


        Scanner scanner = new Scanner(System.in);
        Nom = scanner.next();

        System.out.println("rentrer votre Prenom:");
        Prenom = scanner.next();


        System.out.println("rentrer votre Email:");
        Email = scanner.next();

        while (CompteUser.bonAdresse(Email) == false) {
            System.out.println("Reesseyer");
            System.out.println("rentrer votre Email:");
            Email = scanner.next();
        }
        System.out.println("rentrer votre Mdp:");
        Mdp = scanner.next();

        while (CompteUser.codeBon(Mdp) == false) {
            System.out.println("Reesseyer");
            System.out.println("rentrer votre Mdp:");
            Mdp = scanner.next();
        }
        CompteUser nvxUser = new CompteUser(Nom, Prenom,Email, Mdp);
        CompteUser.bonAdresse(Email);
        CompteUser.codeBon(Mdp);

        return nvxUser;
    }


    public static void main(String[] args ) {
        String Email = "moussy@gmail.com";
        System.out.println(CompteUser.bonAdresse(Email));


        String Mdp = "Celia2001";
        System.out.println(CompteUser.codeBon(Mdp));

        System.out.println(CompteUser.creationCompte());
    }




}
