import java.time.LocalDate;
import java.util.Scanner;










public class Simulation {

    public static void  AfficheMenu (){
        System.out.print("veillier choisir une option\n"
                        +"--c:Création d'un  compte Bancaires\n" +
                        "--a:Affichage de tous vos comptes\n"+
                         "--r:Recherche d'un compte par NumCompte\n"+
                        "--i:Calcule d'interét sur un compte\n"+
                        "--t:Faire une Transaction\n"+
                        "--j:Historique de vos Transactions\n"+
                       "--e:Exporter les données du compte \n");



    }

    public static void creationCompte(){
        String type="unknown";
        double amount;
        String entree;
        LocalDate date = LocalDate.now() ;
        Scanner scanner = new Scanner(System.in);
        System.out.println("veuillez choisir le compte que vous voulez creez");
        System.out.println(date);
        System.out.print(
                "1:Création du compte LivretA\n" +
                "2:Création du compte LDD\n"+
                "3:Création du compte PEL\n");
        entree = scanner.next();
        while (!entree.equals("1") && !entree.equals("2") && !entree.equals("3") ){
            System.out.println("Entrez une choix valide");
            entree = scanner.next();
        }
        if(entree.equals("1"))
        {
            type = "Livret A";
        }
        else if(entree.equals("2"))
        {
            type =" LDD";
        }
        else if (entree.equals("3"))
        {
            type ="PEL";
        }
       System.out.println("le compte que vous avez choisi est " + type );

    }





    //A ppel de fonction
    public static void main (String[] args){
        AfficheMenu();
        creationCompte();
    }
}
