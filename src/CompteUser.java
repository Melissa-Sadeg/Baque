import java.util.ArrayList;

public class CompteUser {
    private String Mdp;
    private String email;
    private ArrayList<CompteBancaires> comptesBancaire;

    public CompteUser(String mdp, String email, ArrayList<CompteBancaires> comptesBancaire) {
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

    public ArrayList<CompteBancaires> getComptesBancaire() {
        return comptesBancaire;
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









    public static void main(String[] args ) {
        String Email = "moussy@gmail.com";
        System.out.println(CompteUser.bonAdresse(Email));
    }
}
