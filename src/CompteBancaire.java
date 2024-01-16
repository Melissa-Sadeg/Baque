

import java.time.LocalDate;
import java.util.ArrayList;

public class CompteBancaire {
    private String type;
    private int numeroCompte;
    private ArrayList<Transactions> historiques;
    private float plafond;
    private LocalDate date;
    private String rib;

    // Constructeur

    public CompteBancaire(String type, int numeroCompte, ArrayList<Transactions> historiques, float plafond, LocalDate date, String rib) {
        this.type = type;
        this.numeroCompte = numeroCompte;
        this.historiques = historiques;
        this.plafond = plafond;
        this.date = date;
        this.rib = rib;
    }
    public int getNumeroCompte() {
        return numeroCompte;
    }

    public ArrayList<Transactions> getHistoriques() {
        return historiques;
    }

    public float getPlafond() {
        return plafond;
    }

    public LocalDate getDate() {
        return date;
    }

    public void retrait(float amount) {

    }

    /*public String exporterJson(){

    }*/

    /*  +exporterJson():string
+exporterRib():string
+retrait(amount1:float)void {abstract}
+depot(amount2:float)void {abstract}
+solde(amount3:float)void
+simuler(date:LocalDate):float
*/

}

