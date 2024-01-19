import java.util.ArrayList;

public class User {
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private ArrayList<CompteBancaire> comptes;

    public User(String nom, String prenom, String email, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.comptes = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }

    public ArrayList<CompteBancaire> getComptes() {
        return comptes;
    }
    public int getNbComptes() {
        return this.comptes.size();
    }

    public Boolean addCompte(CompteBancaire compte) {
        if(compte.getType() != "Courant") {
            for(CompteBancaire c : this.comptes) {
                if(c.getType().equals(compte.getType())) {
                    System.out.println("Vous ne pouvez pas avoir plus d'un compte " + compte.getType());
                    return false;
                }
            }
        }
        this.comptes.add(compte);
        return true;
    }

    public Boolean pasDeCompte() {
        return this.comptes.isEmpty();
    }

    public Boolean pasDepargne() {
        if(pasDeCompte()) {
            return true;
        }
        for(CompteBancaire compte : this.comptes) {
            if(compte.getType() != "Courant") {
                return false;
            }
        }
        return true;
    }

    // TODO : à compléter
    public static Boolean estEmailValid(String email) {
        return email.contains("@");
    }

    // TODO : à compléter
    public static Boolean estMdpValid(String mdp) {
        return mdp.length() >= 8;
    }

    //trier les comptes par numéro de compte
    public void trierComptes(){ return; }

    public void afficherComptes() {
        if(pasDeCompte()) {
            System.out.println("Aucun compte trouvé !");
            return;
        }
        System.out.println("Liste des comptes :");
        int index = 1;
        for(CompteBancaire compte : this.comptes) {
            System.out.print(index++ + ". ");
            System.out.println(compte.getNumeroCompte() + " - " + compte.getType() + " - " + compte.getSolde() + "€");
        }
    }




    public CompteBancaire rechercherCompte(String numeroCompte) {
        for(CompteBancaire compte : this.comptes) {
            if(compte.getNumeroCompte().equals(numeroCompte)) {
                return compte;
            }
        }
        return null;
    }


}
