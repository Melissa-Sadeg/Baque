import java.time.LocalDate;

public class Transaction {
    private String type;
    private float montant;
    private LocalDate date;

    // Constructeur

    public Transaction(String type, float montant, LocalDate date) {
        this.type = type;
        this.montant = montant;
        this.date = date;
    }

    // Getters
    public String getType() {
        return type;
    }

    public float getMontant() {
        return montant;
    }

    public LocalDate getDate() {
        return date;
    }
}
